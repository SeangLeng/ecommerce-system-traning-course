package mptc.seangleng.ecommerce.user.mapper;

import mptc.seangleng.ecommerce.user.dto.UserCreateRequest;
import mptc.seangleng.ecommerce.user.dto.UserResponse;
import mptc.seangleng.ecommerce.user.dto.UserUpdateRequest;
import mptc.seangleng.ecommerce.user.entity.UserEntity;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.*;
import org.springframework.data.repository.query.parser.Part;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toEntity(UserCreateRequest userCreateRequest);

    UserResponse toResponse(UserEntity userEntity);

    @Mapping(source = "familyName", target = "firstName")
    @Mapping(source = "givenName", target = "lastName")
    UserRepresentation toRepresentation(UserCreateRequest userCreateRequest);

    default UserResponse toRepresentation(UserEntity userEntity, UserRepresentation userRepresentation) {
        return UserResponse.builder()
                .id(userEntity.getId())
                .familyName(userRepresentation.getFirstName())
                .givenName(userRepresentation.getLastName())
                .address(userEntity.getAddress())
                .gender(userEntity.getGender())
                .mobilePhone(userEntity.getMobilePhone())
                .build();
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toEntity(UserUpdateRequest request, @MappingTarget UserEntity entity);
}