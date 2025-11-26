package com.prod.auth.Repository;

import com.prod.auth.Entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepo extends JpaRepository<Authority, Long> {
    Authority findByRole(String role);
}
