package landry.paysted.controllers;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import landry.paysted.dtos.ApiResponse;
import landry.paysted.dtos.CreatePaymentLinkRequest;
import landry.paysted.dtos.PaymentDto;
import landry.paysted.exceptions.ResourceNotFoundException;
import landry.paysted.services.invoice.PaymentService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController()
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;


    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);

    @PostMapping("/create-link")
    public ResponseEntity<ApiResponse> createPaymentLink(@RequestBody CreatePaymentLinkRequest request, @RequestParam Long user_id){
        try{
            PaymentDto paymentDto = paymentService.createPaymentLink(request, user_id);
            return ResponseEntity.ok(new ApiResponse("Payment link created successfully", paymentDto));

        }catch(ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Not Found", e.getMessage()));
        }catch(IOException | InterruptedException e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ApiResponse("IO Exception", e.getMessage()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Unknown error", e.getMessage()));
        }
        
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllPaymentLinks(){
        try {
            List<PaymentDto> payments = paymentService.listAllPayments();
            return ResponseEntity.ok(new ApiResponse("Success", payments));
        } catch (Exception e) {
            log.error("Unknown error. Details: " + e.getCause());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Unknown error", HttpStatus.INTERNAL_SERVER_ERROR));
        } 
    };

    @GetMapping("/{user_id}")
    public ResponseEntity<ApiResponse> getUserPaymentLinks(@PathVariable Long user_id){
        try {
            List<PaymentDto> payment = paymentService.getPaymentsByUserId(user_id);
            return ResponseEntity.ok(new ApiResponse("success", payment));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Unknown error", HttpStatus.INTERNAL_SERVER_ERROR));
        }
        
    }
    
}
