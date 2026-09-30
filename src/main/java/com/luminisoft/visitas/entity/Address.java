package com.luminisoft.visitas.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client; //

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_type_id")
    private AddressType addressType; // Asumiendo que replicaste esta clase catálogo

    @Column(precision = 10, scale = 7)
    private BigDecimal latitude; //[cite: 1]

    @Column(precision = 10, scale = 7)
    private BigDecimal longitude; //[cite: 1]

    @Column(length = 255)
    private String street; //[cite: 1]

    @Column(length = 255)
    private String locality; //[cite: 1]
    
    @Column(length = 150)
    private String municipality; //[cite: 1]
    
    @Column(length = 150)
    private String state; //[cite: 1]

    @Column(name = "postal_code", length = 10)
    private String postalCode; //[cite: 1]

    @Column(name = "created_by", nullable = false)
    private Long createdBy; //[cite: 1]

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt; //[cite: 1]

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt; //[cite: 1]

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}