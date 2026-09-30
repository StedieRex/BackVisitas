package com.luminisoft.visitas.controller;

import com.luminisoft.visitas.dto.VisitRequestDTO;
import com.luminisoft.visitas.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/agentes/visitas")
@RequiredArgsConstructor
public class VisitController {

    private final VisitService visitService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> registerVisit(@RequestBody VisitRequestDTO requestDTO) {
        // NOTA: Por ahora simulamos que el agente con ID 2 es quien hace la petición.
        // En la Fase 5 cambiaremos este "2L" por el ID extraído del token JWT de Spring Security.
        Long currentAgentId = 2L; 
        
        Long newVisitId = visitService.createVisit(requestDTO, currentAgentId);
        
        Map<String, Object> response = new HashMap<>();
        response.put("visitId", newVisitId);
        response.put("message", "Visita registrada exitosamente");
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}