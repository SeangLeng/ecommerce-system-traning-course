package mptc.seangleng.ecommerce.domain.customer.dto;

public record CreateCustomerCommand(
        String userId,
        String username,
        String familyName,
        String givenName,
        String email,
        String phoneNumber
) {
}
