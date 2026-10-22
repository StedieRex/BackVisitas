package com.luminisoft.visitas.repository;

import com.luminisoft.visitas.entity.Portfolios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface PortfolioRepository extends JpaRepository<Portfolios, Long>{
    
} 
