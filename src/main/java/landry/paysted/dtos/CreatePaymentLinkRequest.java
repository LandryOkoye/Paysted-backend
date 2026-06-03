package landry.paysted.dtos;

import java.util.List;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import landry.paysted.enums.LinkTypes;

public record CreatePaymentLinkRequest(
    @NotNull
    boolean fixed,
    @NotNull
    boolean one_time,
    @NotNull
    LinkTypes type,
    @NotNull
    String name,
    @NotNull
    String title,
    @NotNull
    String description,
    @NotNull
    String target_currency,
    @Nullable
    String target_amount,
    @Nullable
    List<Require_Extra_Info> require_extra_info
    ) 
{

}
