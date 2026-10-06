package com.luminisoft.visitas.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements  UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //[cite: 1]

    @Column(nullable = false)
    private String name; //[cite: 1]

    @Column(nullable = false, unique = true)
    private String email; //[cite: 1]

    @Column(nullable = false)
    private String password; //[cite: 1]

    @Column(nullable = false)
    private String role; 

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt; //[cite: 1]

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt; //[cite: 1]

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    //seguridad
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Agregamos el prefijo ROLE_ por convención de Spring Security
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.role));
    }

    @Override
    public String getUsername() {
        return this.email; 
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}