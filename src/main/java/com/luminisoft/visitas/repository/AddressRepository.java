package com.luminisoft.visitas.repository;

import com.luminisoft.visitas.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Client, Long> {
}
