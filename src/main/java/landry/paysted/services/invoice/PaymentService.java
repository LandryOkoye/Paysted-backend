package landry.paysted.services.invoice;

import java.io.IOException;
import java.util.List;

import landry.paysted.dtos.CreatePaymentLinkRequest;
import landry.paysted.dtos.PaymentDto;

public interface PaymentService {

    PaymentDto createPaymentLink(CreatePaymentLinkRequest request, Long user_id) throws IOException, InterruptedException;

    List<PaymentDto> listAllPayments();

    List<PaymentDto> getPaymentsByUserId(Long userId);

    PaymentDto getPaymentById(Long id);

}
