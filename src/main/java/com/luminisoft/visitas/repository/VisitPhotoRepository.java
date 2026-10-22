package com.luminisoft.visitas.repository;

import com.luminisoft.visitas.entity.VisitPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional; // <-- IMPORTANTE AGREGAR ESTO

@Repository
public interface VisitPhotoRepository extends JpaRepository<VisitPhoto, Long> {
    
    // Spring leerá este nombre y automáticamente hará: SELECT * FROM visit_photos WHERE visit_id = ?
    Optional<VisitPhoto> findByVisitId(Long visitId);
    
}