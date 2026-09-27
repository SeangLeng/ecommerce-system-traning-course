package mptc.seangleng.ecommerce.order.dto;

public record CommandOrderAddress(
        String street,
        String pastaCode,
        String city
) {
}
