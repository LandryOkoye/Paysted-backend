package landry.paysted.dtos;

import java.util.Enumeration;

import landry.paysted.LinkTypes;

public record CreatePaymentLinkRequest(
    boolean fixed,
    boolean oneTime,
    LinkTypes LinkType,
    String name,
    String title,
    String description,
    String targetCurrency
    
) {

}
