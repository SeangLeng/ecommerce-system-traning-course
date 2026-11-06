package mptc.seangleng.ecommerce.user.mapper;

import mptc.seangleng.ecommerce.user.dto.UserCreateRequest;
import mptc.seangleng.ecommerce.user.dto.UserResponse;
import mptc.seangleng.ecommerce.user.dto.UserUpdateRequest;
import mptc.seangleng.ecommerce.user.entity.UserEntity;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "birthday", qualifiedByName = "dateToBirthday")
    UserEntity toEntity(UserCreateRequest userCreateRequest);

    @Mapping(target = "birthday", qualifiedByName = "birthdayToDate")
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
                .birthday(birthdayToDate(userEntity.getBirthday()))
                .build();
    }

    @Named("dateToBirthday")
    default String dateToBirthday(Date birthday) {
        if (birthday == null) {
            return null;
        }
        return birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();
    }

    @Named("birthdayToDate")
    default Date birthdayToDate(String birthday) {
        if (birthday == null || birthday.isBlank()) {
            return null;
        }
        LocalDate date = LocalDate.parse(birthday);
        return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "birthday", qualifiedByName = "dateToBirthday")
    void toEntity(UserUpdateRequest request, @MappingTarget UserEntity entity);
}