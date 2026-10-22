package com.luminisoft.visitas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatalogDTO {
    private Long id;
    private String code;
    private String description;
}