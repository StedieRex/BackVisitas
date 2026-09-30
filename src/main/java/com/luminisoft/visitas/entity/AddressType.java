package com.luminisoft.visitas.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "address_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String code;

    @Column(nullable = false, length = 150)
    private String description;
}