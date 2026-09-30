package com.luminisoft.visitas.service;

import com.luminisoft.visitas.dto.VisitRequestDTO;
import com.luminisoft.visitas.entity.*;
import com.luminisoft.visitas.repository.PaymentPromiseRepository;
import com.luminisoft.visitas.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

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
}