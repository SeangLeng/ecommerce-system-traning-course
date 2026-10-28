package mptc.seangleng.ecommerce.user.dto;

import java.util.Date;

public record UserResponse (
        String id,
        String familyName,
        String givenName,
        String gender,
        String mobilePhone,
        String address,
        Date Birthday
){}
