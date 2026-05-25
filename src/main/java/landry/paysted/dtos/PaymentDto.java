package landry.paysted.dtos;

public class PaymentDto {
    String paymentLink;
    String name;
    
    
    
    public PaymentDto(String paymentLink, String name) {
        this.paymentLink = paymentLink;
        this.name = name;
    }

    public PaymentDto() {
    }

    public String getPaymentLink() {
        return paymentLink;
    }
    public void setPaymentLink(String paymentLink) {
        this.paymentLink = paymentLink;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    
}
