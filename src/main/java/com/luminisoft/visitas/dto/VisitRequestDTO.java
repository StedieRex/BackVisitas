package com.luminisoft.visitas.dto;

import com.luminisoft.visitas.entity.OperatingStatus;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class VisitRequestDTO {
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

    private PaymentPromiseDTO paymentPromise;

    @Data
    public static class PaymentPromiseDTO {
        private BigDecimal amount;
        private LocalDate promisedDate;
    }
}