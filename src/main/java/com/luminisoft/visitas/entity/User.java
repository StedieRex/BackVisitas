package com.luminisoft.visitas.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

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
}