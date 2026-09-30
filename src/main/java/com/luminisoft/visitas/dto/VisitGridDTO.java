package com.luminisoft.visitas.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class VisitGridDTO {
    private Long id;
    private String clientName;
    private String addressStreet;
    private String actionCodeDescription;
    private String resultDescription;
    private LocalDateTime visitedAt;
    private String isOperating;
}