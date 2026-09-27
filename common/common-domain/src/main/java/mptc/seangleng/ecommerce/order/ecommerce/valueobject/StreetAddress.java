package mptc.seangleng.ecommerce.order.ecommerce.valueobject;

import java.util.UUID;

public record StreetAddress(UUID id, String street, String pastaCode, String city) {
}
