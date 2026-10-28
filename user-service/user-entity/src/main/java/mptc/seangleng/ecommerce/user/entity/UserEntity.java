package mptc.seangleng.ecommerce.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    private String id;

    @Column(nullable = false, unique = true)
    private String username;
    private String gender;
    private String birthday;

    @Column(unique = true)
    private String mobilePhone;
    private String address;
}
