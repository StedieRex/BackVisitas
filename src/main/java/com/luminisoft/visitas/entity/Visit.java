package com.luminisoft.visitas.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "visits")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //[cite: 1]

    // Relaciones (Foreign Keys)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client; //[cite: 1]

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "action_code_id")
    private ActionCode actionCode; //[cite: 1]

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "result_id")
    private VisitResult result; //[cite: 1]

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "no_payment_reason_id")
    private NoPaymentReason noPaymentReason; //[cite: 1]

    // Datos crudos de la visita
    @Column(name = "visited_at", nullable = false)
    private LocalDateTime visitedAt; //[cite: 1]

    @Column(name = "attended_by")
    private String attendedBy; //[cite: 1]

    @Column(length = 100)
    private String kinship; //[cite: 1]

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comments; //[cite: 1]

    @Column(name = "property_description", columnDefinition = "TEXT", nullable = false)
    private String propertyDescription; //[cite: 1]

    @Column(name = "business_activity")
    private String businessActivity; //[cite: 1]

    @Enumerated(EnumType.STRING)
    @Column(name = "is_operating", nullable = false)
    private OperatingStatus isOperating; //[cite: 1]

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

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}