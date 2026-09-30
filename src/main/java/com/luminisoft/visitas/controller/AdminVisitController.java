package com.luminisoft.visitas.controller;

import com.luminisoft.visitas.dto.VisitGridDTO;
import com.luminisoft.visitas.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import com.luminisoft.visitas.dto.VisitDetailDTO;

@RestController
@RequestMapping("/api/admin/visitas")
@RequiredArgsConstructor
public class AdminVisitController {

    private final VisitService visitService;

    @GetMapping
    public ResponseEntity<List<VisitGridDTO>> getVisitsGrid() {
        List<VisitGridDTO> visits = visitService.getAllVisitsForGrid();
        return ResponseEntity.ok(visits);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisitDetailDTO> getVisitDetail(@PathVariable Long id) {
        VisitDetailDTO detail = visitService.getVisitDetail(id);
        return ResponseEntity.ok(detail);
    }
}