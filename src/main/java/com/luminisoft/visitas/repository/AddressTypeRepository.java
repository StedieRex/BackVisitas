package com.luminisoft.visitas.repository;

import com.luminisoft.visitas.entity.AddressType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface AddressTypeRepository extends  JpaRepository<AddressType, Long>{
} 
