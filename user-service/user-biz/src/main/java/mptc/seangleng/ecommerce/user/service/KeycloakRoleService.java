package mptc.seangleng.ecommerce.user.service;

public interface KeycloakRoleService {
    void assignRole(String userId, String roleNameWithDelimiter);
}
