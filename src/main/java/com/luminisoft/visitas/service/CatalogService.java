package com.luminisoft.visitas.service;

import com.luminisoft.visitas.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class CatalogService {

    private final VisitResultRepository visitResultRepo;
    private final ActionCodeRepository actionCodeRepo;
    private final NoPaymentReasonRepository noPaymentReasonRepo;
    // Inyecta el resto de los repositorios de catálogos aquí

    public Map<String, Object> getAllCatalogs() {
        Map<String, Object> catalogs = new HashMap<>();
        catalogs.put("visitResults", visitResultRepo.findAll());
        catalogs.put("actionCodes", actionCodeRepo.findAll());
        catalogs.put("noPaymentReasons", noPaymentReasonRepo.findAll());
        // Agrega el resto a este mapa
        return catalogs;
    }
}
