package mptc.seangleng.ecommerce.user.repository;

import mptc.seangleng.ecommerce.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    boolean existsByUsername(String username);
}
