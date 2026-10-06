package com.luminisoft.visitas.service;

import com.luminisoft.visitas.dto.ClientActiveDTO;
import com.luminisoft.visitas.entity.Client;
import com.luminisoft.visitas.repository.AddressRepository;
import com.luminisoft.visitas.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;

    public List<ClientActiveDTO> getClientsByAgent(Long agentId) {
        // En un sistema real, filtraríamos con: clientRepository.findByCreatedBy(agentId)
        // Por ahora, traemos todos para la prueba
        List<Client> clients = clientRepository.findAll(); 

        return clients.stream().map(client -> {
            List<ClientActiveDTO.AddressDTO> addressDTOs = addressRepository.findByClient_Id(client.getId())
                    .stream().map(addr -> {
                        String street = addr.getStreet() != null ? addr.getStreet() : "Sin calle";
                        return ClientActiveDTO.AddressDTO.builder()
                                .addressId(addr.getId())
                                .fullAddress(street)
                                .build();
                    }).collect(Collectors.toList());

            return ClientActiveDTO.builder()
                    .clientId(client.getId())
                    .name(client.getName())
                    .addresses(addressDTOs)
                    .build();
        }).collect(Collectors.toList());
    }
}