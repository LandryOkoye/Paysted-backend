package landry.paysted.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import landry.paysted.enums.LinkTypes;

public record CreatePaymentLinkRequest(
    @NotNull
    boolean fixed,
    @NotNull
    boolean oneTime,
    @NotNull
    LinkTypes linkTypes,
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
