package com.luminisoft.visitas.dto;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class ClientActiveDTO {
    private Long clientId;
    private String name;
    private List<AddressDTO> addresses;

    @Data
    @Builder
    public static class AddressDTO {
        private Long addressId;
        private String fullAddress;
    }
}