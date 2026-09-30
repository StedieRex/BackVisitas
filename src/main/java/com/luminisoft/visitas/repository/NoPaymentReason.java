package com.luminisoft.visitas.repository;

import com.luminisoft.visitas.entity.VisitResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoPaymentReason extends JpaRepository<VisitResult, Long> {
}
