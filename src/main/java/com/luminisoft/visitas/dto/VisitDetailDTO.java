package com.luminisoft.visitas.dto;

import com.luminisoft.visitas.entity.OperatingStatus;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class VisitDetailDTO {
    private Long visitId;
    private Long clientId;
    private Long addressId;
    private Long actionCodeId;
    private Long resultId;
    private Long noPaymentReasonId;

    private String attendedBy;
    private String kinship;
    private String comments;
    private String propertyDescription;
    private String businessActivity;
    private OperatingStatus isOperating;
    private LocalDateTime visitedAt;

    private PaymentPromiseDTO paymentPromise;

    @Data
    @Builder
    public static class PaymentPromiseDTO {
        private BigDecimal amount;
        private LocalDate promisedDate;
    }
}