package org.cloud.crypto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CryptoPriceDto {
    private String currency;
    private BigDecimal priceUsd;
    private LocalDateTime timestamp;
    private LocalDateTime createdAt;
}
