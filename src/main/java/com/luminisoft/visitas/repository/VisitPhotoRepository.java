package com.luminisoft.visitas.repository;

import com.luminisoft.visitas.entity.VisitPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitPhotoRepository extends JpaRepository<VisitPhoto, Long> {
}
