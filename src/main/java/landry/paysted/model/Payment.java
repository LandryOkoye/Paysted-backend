package landry.paysted.model;

import java.time.Instant;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String paymentId;
    @NotBlank
    private String paymentLink;
    @NotBlank
    private String name;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private int amount;
    @NotBlank
    private String currency;
    @NotBlank
    private String cus_email;
    @NotBlank
    private String cus_name;
    @NotBlank
    private Instant createdAt;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Payment(@NotBlank String paymentId, @NotBlank String paymentLink, @NotBlank String name,
            @NotBlank String title, @NotBlank String description, @NotBlank int amount, @NotBlank String currency,
            @NotBlank String cus_email, @NotBlank String cus_name, @NotBlank Instant createdAt) {
        this.paymentId = paymentId;
        this.paymentLink = paymentLink;
        this.name = name;
        this.title = title;
        this.description = description;
        this.amount = amount;
        this.currency = currency;
        this.cus_email = cus_email;
        this.cus_name = cus_name;
        this.createdAt = createdAt;
    }



    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
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
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public String getCus_email() {
        return cus_email;
    }
    public void setCus_email(String cus_email) {
        this.cus_email = cus_email;
    }
    public String getCus_name() {
        return cus_name;
    }
    public void setCus_name(String cus_name) {
        this.cus_name = cus_name;
    }
    public Instant getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    
}
