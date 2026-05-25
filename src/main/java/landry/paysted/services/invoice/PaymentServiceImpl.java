package landry.paysted.services.invoice;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import landry.paysted.dtos.CreatePaymentLinkRequest;
import landry.paysted.dtos.PaymentDto;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private HttpClient httpClient;

    public PaymentDto createPaymentLink(CreatePaymentLinkRequest request) throws IOException, InterruptedException{
        
        if(request == null || request.toString().isEmpty()){
            throw new IllegalArgumentException("Fields cannot be empty");
        }

        HttpRequest httpRequest = HttpRequest.newBuilder()
            .uri(URI.create("Api url goes in here"))
            .GET()
            .build();

        HttpResponse <String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());


        if(response.statusCode() == 200){
            // TODO: get the payload and feed to the client
        }
        return null;
    }

    public List<?> listAllPayment(){

        // TODO: implement function to retrieve and feed all paymemt links to the client.
        return null;
    }

}
