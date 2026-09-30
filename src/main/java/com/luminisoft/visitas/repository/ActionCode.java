package com.luminisoft.visitas.repository;

import com.luminisoft.visitas.entity.VisitResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionCode extends JpaRepository<VisitResult, Long> {
}
