package com.luminisoft.visitas.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "visit_results")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VisitResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //[cite: 1]

    @Column(nullable = false, unique = true, length = 50)
    private String code; //[cite: 1]

    @Column(nullable = false, length = 150)
    private String description; //[cite: 1]
}
