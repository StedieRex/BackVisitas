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

// Importaciones necesarias para entregar archivos
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    // NUEVO ENDPOINT: Expone la fotografía a Angular
    @GetMapping("/fotos/{filename}")
    public ResponseEntity<Resource> getPhoto(@PathVariable String filename) {
        try {
            // Lee físicamente de la carpeta uploads/photos
            Path filePath = Paths.get("uploads", "photos").resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() || resource.isReadable()) {
                String contentType = Files.probeContentType(filePath);
                if (contentType == null) {
                    contentType = "application/octet-stream";
                }

                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}