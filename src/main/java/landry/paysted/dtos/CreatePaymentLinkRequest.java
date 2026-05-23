package landry.paysted.dtos;

import java.util.Enumeration;

import org.jspecify.annotations.NonNull;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import landry.paysted.LinkTypes;

public record CreatePaymentLinkRequest(
    @NotNull
    boolean fixed,
    @NotNull
    boolean oneTime,
    @NotNull
    LinkTypes LinkType,
    @NotNull
    String name,
    @NotNull
    String title,
    @NotNull
    String description,
    @NotNull
    String targetCurrency,
    @Nullable
    String targetAmount
    
) {

}
