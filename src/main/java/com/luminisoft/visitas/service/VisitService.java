package com.luminisoft.visitas.service;

import com.luminisoft.visitas.dto.VisitDetailDTO;
import com.luminisoft.visitas.dto.VisitGridDTO;
import com.luminisoft.visitas.dto.VisitRequestDTO;
import com.luminisoft.visitas.entity.*;
import com.luminisoft.visitas.repository.PaymentPromiseRepository;
import com.luminisoft.visitas.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.List;
import java.util.stream.Collectors;
import com.luminisoft.visitas.dto.VisitDetailDTO;

@Service
@RequiredArgsConstructor
public class VisitService {

    private final VisitRepository visitRepository;
    private final PaymentPromiseRepository paymentPromiseRepository;

    @Transactional
    public Long createVisit(VisitRequestDTO dto, Long currentUserId) {
        
        Visit visit = new Visit();
        visit.setVisitedAt(LocalDateTime.now());
        visit.setComments(dto.getComments());
        visit.setPropertyDescription(dto.getPropertyDescription());
        visit.setAttendedBy(dto.getAttendedBy());
        visit.setKinship(dto.getKinship());
        visit.setBusinessActivity(dto.getBusinessActivity());
        visit.setIsOperating(dto.getIsOperating());
        visit.setCreatedBy(currentUserId);

        // Asignación de llaves foráneas usando proxies (evita SELECTs innecesarios)
        if (dto.getClientId() != null) {
            Client client = new Client(); client.setId(dto.getClientId());
            visit.setClient(client);
        }
        if (dto.getAddressId() != null) {
            Address address = new Address(); address.setId(dto.getAddressId());
            visit.setAddress(address);
        }
        if (dto.getActionCodeId() != null) {
            ActionCode actionCode = new ActionCode(); actionCode.setId(dto.getActionCodeId());
            visit.setActionCode(actionCode);
        }
        if (dto.getResultId() != null) {
            VisitResult result = new VisitResult(); result.setId(dto.getResultId());
            visit.setResult(result);
        }
        if (dto.getNoPaymentReasonId() != null) {
            NoPaymentReason reason = new NoPaymentReason(); reason.setId(dto.getNoPaymentReasonId());
            visit.setNoPaymentReason(reason);
        }

        // 1. Guardar la visita
        Visit savedVisit = visitRepository.save(visit);

        // 2. Si el frontend envía datos de promesa de pago, la guardamos vinculada a la visita
        if (dto.getPaymentPromise() != null && dto.getPaymentPromise().getAmount() != null) {
            PaymentPromise promise = new PaymentPromise();
            promise.setVisit(savedVisit);
            promise.setAmount(dto.getPaymentPromise().getAmount());
            promise.setPromisedDate(dto.getPaymentPromise().getPromisedDate());
            promise.setCreatedBy(currentUserId);
            
            paymentPromiseRepository.save(promise);
        }

        return savedVisit.getId();
    }

    // Importa esto arriba si no lo tienes:

    public List<VisitGridDTO> getAllVisitsForGrid() {
        return visitRepository.findAll().stream().map(visit -> {
            
            // Validaciones para evitar NullPointerException si algún catálogo viene vacío
            String actionDesc = visit.getActionCode() != null ? visit.getActionCode().getDescription() : "N/A";
            String resultDesc = visit.getResult() != null ? visit.getResult().getDescription() : "N/A";
            String street = visit.getAddress() != null ? visit.getAddress().getStreet() : "Sin dirección";
            String clientName = visit.getClient() != null ? visit.getClient().getName() : "Desconocido";

            return VisitGridDTO.builder()
                    .id(visit.getId())
                    .clientName(clientName)
                    .addressStreet(street)
                    .actionCodeDescription(actionDesc)
                    .resultDescription(resultDesc)
                    .visitedAt(visit.getVisitedAt())
                    .isOperating(visit.getIsOperating().name())
                    .build();
        }).collect(Collectors.toList());
    }

    public VisitDetailDTO getVisitDetail(Long visitId) {
        Visit visit = visitRepository.findById(visitId)
                .orElseThrow(() -> new RuntimeException("Visita no encontrada"));

        // Buscamos si existe una promesa de pago vinculada
        VisitDetailDTO.PaymentPromiseDTO promiseDTO = null;
        var optionalPromise = paymentPromiseRepository.findByVisitId(visitId);
        
        if (optionalPromise.isPresent()) {
            promiseDTO = VisitDetailDTO.PaymentPromiseDTO.builder()
                    .amount(optionalPromise.get().getAmount())
                    .promisedDate(optionalPromise.get().getPromisedDate())
                    .build();
        }

        return VisitDetailDTO.builder()
                .visitId(visit.getId())
                .clientId(visit.getClient() != null ? visit.getClient().getId() : null)
                .addressId(visit.getAddress() != null ? visit.getAddress().getId() : null)
                .actionCodeId(visit.getActionCode() != null ? visit.getActionCode().getId() : null)
                .resultId(visit.getResult() != null ? visit.getResult().getId() : null)
                .noPaymentReasonId(visit.getNoPaymentReason() != null ? visit.getNoPaymentReason().getId() : null)
                .attendedBy(visit.getAttendedBy())
                .kinship(visit.getKinship())
                .comments(visit.getComments())
                .propertyDescription(visit.getPropertyDescription())
                .businessActivity(visit.getBusinessActivity())
                .isOperating(visit.getIsOperating())
                .visitedAt(visit.getVisitedAt())
                .paymentPromise(promiseDTO)
                .build();
    }
}