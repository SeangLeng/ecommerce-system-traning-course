package mptc.seangleng.ecommerce.restapi.dto;

public record FieldErrorResponse(
        String fieldName,
        String code,
        String reason
) {
}
