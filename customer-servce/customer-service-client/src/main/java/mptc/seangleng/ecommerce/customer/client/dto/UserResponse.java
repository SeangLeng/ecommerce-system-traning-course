package mptc.seangleng.ecommerce.customer.client.dto;

import lombok.Builder;

import java.util.Date;

@Builder
public record UserResponse(
        String id,
        String familyName,
        String givenName,
        String gender,
        String mobilePhone,
        String address,
        Date birthday
){}
