package com.luminisoft.visitas.controller;

import com.luminisoft.visitas.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api/catalogos")
@RequiredArgsConstructor
public class CatalogController {

    private final CatalogService catalogService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getCatalogs() {
        return ResponseEntity.ok(catalogService.getAllCatalogs());
    }
}
