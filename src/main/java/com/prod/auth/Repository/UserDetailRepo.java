package com.prod.auth.Repository;

import com.prod.auth.Entity.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailRepo extends JpaRepository<User, Long> {

    Boolean existsByEmail(String email);

    Boolean existsByUsername(@NotBlank String username);
    Optional<User> findByEmail(String email);

    Optional<User> findUserByUsername(@NotBlank String username);
}
