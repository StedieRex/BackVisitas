package com.luminisoft.visitas.controller;

import com.luminisoft.visitas.dto.ClientActiveDTO;
import com.luminisoft.visitas.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/agentes/clientes")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientActiveDTO>> getClients() {
        Long currentAgentId = 2L; // Seguimos simulando la sesión del agente
        return ResponseEntity.ok(clientService.getClientsByAgent(currentAgentId));
    }
}