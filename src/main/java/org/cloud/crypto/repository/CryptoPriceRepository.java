package org.cloud.crypto.repository;

import org.cloud.crypto.model.CryptoPrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CryptoPriceRepository extends JpaRepository<CryptoPrice, Long> {
    List<CryptoPrice> findByCurrency(String currency);
    List<CryptoPrice> findByTimestampAfter(LocalDateTime timestamp);
    List<CryptoPrice> findByTimestampBetween(LocalDateTime start, LocalDateTime end);
}