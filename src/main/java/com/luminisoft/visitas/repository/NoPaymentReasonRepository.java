package com.luminisoft.visitas.repository;

import com.luminisoft.visitas.entity.NoPaymentReason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoPaymentReasonRepository extends JpaRepository<NoPaymentReason, Long> {
}