package org.cloud.crypto.model;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "crypto_prices")
@Data
public class CryptoPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "currency", nullable = false)
    private String currency;

    @Column(name = "price_usd", nullable = false)
    private BigDecimal priceUsd;

    @Column(name = "time_stamp", nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public CryptoPrice() {
        this.createdAt = LocalDateTime.now();
    }

    public CryptoPrice(String currency, BigDecimal priceUsd, LocalDateTime timestamp) {
        this();
        this.currency = currency;
        this.priceUsd = priceUsd;
        this.timestamp = timestamp;
    }
}
