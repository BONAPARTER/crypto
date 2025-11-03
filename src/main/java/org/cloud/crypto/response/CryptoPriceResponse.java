package org.cloud.crypto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CryptoPriceResponse {
    private String currency;
    private BigDecimal priceUsd;
    private LocalDateTime timestamp;
    private LocalDateTime createdAt;
}
