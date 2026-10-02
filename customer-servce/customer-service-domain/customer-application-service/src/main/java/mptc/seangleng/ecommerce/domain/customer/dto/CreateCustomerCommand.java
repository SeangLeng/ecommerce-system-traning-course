package mptc.seangleng.ecommerce.domain.customer.dto;

public record CreateCustomerCommand(
        String username,
        String familyName,
        String givenName,
        String email,
        String phoneNumber
) {
}
