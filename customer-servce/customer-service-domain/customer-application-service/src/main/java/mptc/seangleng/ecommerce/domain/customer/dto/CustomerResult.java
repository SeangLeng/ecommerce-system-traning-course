package mptc.seangleng.ecommerce.domain.customer.dto;

import java.util.UUID;

public record CustomerResult (
        UUID id,
        String familyName,
        String givenName,
        String email,
        String phoneNumber
){}
