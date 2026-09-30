package com.luminisoft.visitas.repository;

import com.luminisoft.visitas.entity.PaymentPromise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentPromiseRepository extends JpaRepository<PaymentPromise, Long> {
}