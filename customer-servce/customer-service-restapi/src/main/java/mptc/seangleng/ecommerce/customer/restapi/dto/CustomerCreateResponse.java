package mptc.seangleng.ecommerce.customer.restapi.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record CustomerCreateResponse(
        UUID customerId
) {
}
