package landry.paysted.services.invoice;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import landry.paysted.dtos.CreatePaymentLinkRequest;
import landry.paysted.dtos.PaymentDto;
import landry.paysted.dtos.Require_Extra_Info;
import landry.paysted.exceptions.ResourceNotFoundException;
import landry.paysted.model.Payment;
import landry.paysted.model.User;
import landry.paysted.repository.PaymentRepository;
import landry.paysted.repository.UserRepository;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private HttpClient httpClient;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Value("${busha.api}")
    private String BUSHA_API;
    @Value("${busha.api.key}")
    private String BUSHA_API_KEY;

    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

    @Override
    public PaymentDto createPaymentLink(CreatePaymentLinkRequest request, Long user_id) throws IOException, InterruptedException{
        
        if(request == null || request.toString().isEmpty()){
            throw new IllegalArgumentException("Fields cannot be empty");
        };
        List<Require_Extra_Info> r_ExtraInfo = List.of(new Require_Extra_Info("email", true));

        CreatePaymentLinkRequest modifiedRequest = new CreatePaymentLinkRequest(
            request.fixed(),
            request.one_time(),
            request.type(),
            request.name(),
            request.title(),
            request.description(),
            request.target_currency(),
            request.target_amount(),
            r_ExtraInfo
        );
        

        String requestBody = objectMapper.writeValueAsString(modifiedRequest);
    
        HttpRequest httpRequest = HttpRequest.newBuilder()
            .uri(URI.create("https://api.sandbox.busha.so/v1/payments/links"))
            .header("Content-Type", "application/json")
            .header("accept", "application/json")
            .header("Authorization", "Bearer " + "T3MwdzlrTDFOeTo0bmhQTExNblF1TVp6OXo2c0pSVUJwU2NnZEs3S3RvUGM5dWpTUWhMbXFPaWNqWDU=")
            .header("X-BU-PROFILE-ID", "BUS_dER0mKqfJfIvwr7cnlfNr")
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .build();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() != 200 && response.statusCode() != 201){
            logger.error("payment link could not be created. Details: \n" + response.body() + response.statusCode());
            throw new ResourceNotFoundException("Payment link could not be created");
        };
        JsonNode responseBody = objectMapper.readValue(response.body(), JsonNode.class);

        logger.info("Payment link created successfully. Details : " + responseBody);
        Payment payment= new Payment(
            responseBody.path("data").path("id").asString(),
            responseBody.path("data").path("link").asString(),
            responseBody.path("data").path("name").asString(),
            responseBody.path("data").path("title").asString(),
            responseBody.path("data").path("description").asString(),
            responseBody.path("data").path("target_amount").asInt(),
            responseBody.path("data").path("target_currency").asString(),
            responseBody.path("data").path("meta").path("customer_email").asString(),
            responseBody.path("data").path("meta").path("customer_name").asString(),
            Instant.parse(responseBody.path("data").path("created_at").asString())
        );

        User user = userRepository.findById(user_id)
            .orElseThrow(() -> new ResourceNotFoundException("User with id " + user_id + " not found"));
        logger.info("User with id " + user_id + " found. Proceeding to save payment link");
        payment.setUser(user);
        paymentRepository.save(payment);

        PaymentDto paymentDto = modelMapper.map(payment, PaymentDto.class);

       return paymentDto;
    }

    @Override
    public List<PaymentDto> listAllPayments(){        
        return paymentRepository.findAll()
            .stream()
            .map(payment -> modelMapper.map(payment, PaymentDto.class))
            .toList();
    }

    @Override
    public PaymentDto getPaymentById(Long id){
        Payment payment = paymentRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Payment with id " + id + " not found"));
        return modelMapper.map(payment, PaymentDto.class);
    }

    @Override
    public List<PaymentDto> getPaymentsByUserId(Long userId){
        List<Payment> payments = paymentRepository.findAll()
            .stream()
            .filter(payment -> payment.getUser().getId().equals(userId))
            .toList();
        if(payments.isEmpty()){
            throw new ResourceNotFoundException("No payments found for user with id " + userId);
        }
        return payments.stream()
            .map(payment -> modelMapper.map(payment, PaymentDto.class)).toList();
    }

}
 