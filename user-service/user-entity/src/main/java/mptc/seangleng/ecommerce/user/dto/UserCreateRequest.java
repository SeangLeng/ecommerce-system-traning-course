package mptc.seangleng.ecommerce.user.dto;

import jakarta.validation.constraints.NotBlank;

public record UserCreateRequest(
        @NotBlank
        String username,
        @NotBlank
        String email,
        @NotBlank
        String familyName,
        @NotBlank
        String givenName,
        @NotBlank
        String roleId,
        @NotBlank
        String password,
        @NotBlank
        String confirmPassword
) {
}
