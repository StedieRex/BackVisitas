package com.luminisoft.visitas.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment_promises")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentPromise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //[cite: 1]

    @OneToOne(fetch = FetchType.LAZY) // Puede ser OneToOne porque es una promesa por visita
    @JoinColumn(name = "visit_id", nullable = false)
    private Visit visit; //[cite: 1]

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal amount; //[cite: 1]

    @Column(name = "promised_date", nullable = false)
    private LocalDate promisedDate; //[cite: 1]

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
