package com.luminisoft.visitas.service;

import com.luminisoft.visitas.dto.CatalogDTO;
import com.luminisoft.visitas.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CatalogService {

    private final ActionCodeRepository actionCodeRepo;
    private final VisitResultRepository visitResultRepo;
    private final NoPaymentReasonRepository noPaymentReasonRepo;
    private final AddressTypeRepository addressTypeRepo;
    private final PortfolioRepository portfolioRepo;

    public Map<String, Object> getAllCatalogs() {
        Map<String, Object> catalogs = new HashMap<>();
        
        catalogs.put("actionCodes", actionCodeRepo.findAll().stream()
                .map(c -> new CatalogDTO(c.getId(), c.getCode(), c.getDescription())).collect(Collectors.toList()));
                
        catalogs.put("visitResults", visitResultRepo.findAll().stream()
                .map(c -> new CatalogDTO(c.getId(), c.getCode(), c.getDescription())).collect(Collectors.toList()));
                
        catalogs.put("noPaymentReasons", noPaymentReasonRepo.findAll().stream()
                .map(c -> new CatalogDTO(c.getId(), c.getCode(), c.getDescription())).collect(Collectors.toList()));
                
        catalogs.put("addressTypes", addressTypeRepo.findAll().stream()
                .map(c -> new CatalogDTO(c.getId(), c.getCode(), c.getDescription())).collect(Collectors.toList()));
                
        catalogs.put("portfolios", portfolioRepo.findAll().stream()
                .map(c -> new CatalogDTO(c.getId(), c.getCode(), c.getDescription())).collect(Collectors.toList()));

        return catalogs;
    }
}