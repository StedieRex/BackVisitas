package com.luminisoft.visitas.repository;

import com.luminisoft.visitas.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByClient_Id(Long clientId);
}