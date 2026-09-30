package com.luminisoft.visitas.repository;

import com.luminisoft.visitas.entity.ActionCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionCodeRepository extends JpaRepository<ActionCode, Long> {
}
