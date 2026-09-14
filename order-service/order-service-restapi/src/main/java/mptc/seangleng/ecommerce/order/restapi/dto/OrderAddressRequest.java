package mptc.seangleng.ecommerce.order.restapi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record OrderAddressRequest(@NotNull @Size(min = 1, max = 20) String street,
                                  @NotNull @Size(min = 1, max = 10) String PastaCode,
                                  @NotNull @Size(min = 1, max = 30) String City) {
}
