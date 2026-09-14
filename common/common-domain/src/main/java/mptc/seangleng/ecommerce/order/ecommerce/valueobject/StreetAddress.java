package mptc.seangleng.ecommerce.order.ecommerce.valueobject;

import java.util.UUID;

public record StreetAddress(UUID id, String Street, String PastaCode, String City, String StreetAddress) {
}
