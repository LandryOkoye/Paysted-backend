package landry.paysted.services.invoice;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources.Chain.Strategy.Content;
import org.springframework.stereotype.Service;

import landry.paysted.dtos.CreatePaymentLinkRequest;
import landry.paysted.dtos.PaymentDto;
import landry.paysted.exceptions.ResourceNotFoundException;
import landry.paysted.model.Payment;
import tools.jackson.databind.JsonNode;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private HttpClient httpClient;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PaymentRepository paymentRepository;

    public PaymentDto createPaymentLink(CreatePaymentLinkRequest request) throws IOException, InterruptedException{
        
        if(request == null || request.toString().isEmpty()){
            throw new IllegalArgumentException("Fields cannot be empty");
        }

        HttpRequest httpRequest = HttpRequest.newBuilder()
            .uri(URI.create("Api url goes in here"))
            .header("Content_Type", "Application/json")
            .POST(HttpRequest.BodyPublishers.ofString(request.toString()))
            .build();

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() != 200){
            System.out.println("payment link could not be created. Details: \n" + response.body());
            throw new ResourceNotFoundException("Payment link could not be created");
        };
        JsonNode responseBody = modelMapper.map(response.body(), JsonNode.class);

        String paymentId = responseBody.get("data").get("id").toString();
        String paymentLink = responseBody.get("data").get("link").toString();
        String name = responseBody.get("data").get("name").toString();
        String title = responseBody.get("data").get("title").toString();
        String description = responseBody.get("data").get("description").toString();
        int amount = responseBody.get("data").get("target_amount").asInt();
        String currency = responseBody.get("data").get("target_currency").toString();
        String cus_email = responseBody.get("data").get("meta").get("customer_email").toString();
        String cus_name = responseBody.get("data").get("meta").get("customer_name").toString();
        Instant createdAt = Instant.parse(responseBody.get("data").get("created_at").toString());

        Payment payment= new Payment(
            paymentId, 
            paymentLink, 
            name, 
            title, 
            description, 
            amount, 
            currency, 
            cus_email, 
            cus_name, 
            createdAt
        );
        paymentRepository.save(payment);

        PaymentDto paymentDto = modelMapper.map(payment, PaymentDto.class);

       return paymentDto;
    }

    public List<?> listAllPayment(){

        // TODO: implement function to retrieve and feed all paymemt links to the client.
        return null;
    }

}
