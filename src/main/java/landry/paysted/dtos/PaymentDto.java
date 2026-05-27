package landry.paysted.dtos;

public class PaymentDto {
    String paymentLink;
    String name;
    String title;
    String description;
    
    
    
    public PaymentDto(String paymentLink, String name) {
        this.paymentLink = paymentLink;
        this.name = name;
        this.title = title;
        this.description = description;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    


    
}
