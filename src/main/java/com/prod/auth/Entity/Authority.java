package com.prod.auth.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user_authority")
@Entity
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String role;

    @ManyToOne(fetch = FetchType.LAZY)
            @JoinColumn(name = "userId", nullable = false)
    User user;
    @Override
    public String getAuthority() {
        return role;
    }
}
