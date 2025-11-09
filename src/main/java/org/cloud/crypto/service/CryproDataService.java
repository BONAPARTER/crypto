package org.cloud.crypto.service;

import lombok.RequiredArgsConstructor;
import org.cloud.crypto.dto.CryptoPriceDto;
import org.cloud.crypto.mapper.CryptoMapper;
import org.cloud.crypto.model.CryptoPrice;
import org.cloud.crypto.repository.CryptoPriceRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CryproDataService {
    private static final String BITCOIN = "bitcoin";
    private static final String ETHEREUM = "ethereum";

    private final CryptoPriceRepository repository;
    private final CryptoMapper mapper;

    public List<CryptoPriceDto> getAllPrices() {
        return mapper.toResponses(repository.findAll());
    }

    public CryptoPriceDto save(CryptoPriceDto request) {
            CryptoPrice price = repository.save(mapper.toEntity(request));
            return mapper.toDto(price);
    }

    public List<CryptoPriceDto> getListBitcoins() {
        List<CryptoPrice> listBitcoins = repository.findByCurrency(BITCOIN);
        return mapper.toResponses(listBitcoins);
    }

    public List<CryptoPriceDto> getListByTimestamp(LocalDateTime timestamp) {
        List<CryptoPrice> list = repository.findByTimestampAfter(timestamp);
        return mapper.toResponses(list);
    }

    public List<CryptoPriceDto> getListByTimestampTimestampBetween(LocalDate timestamp) {
        LocalDateTime start = timestamp.atStartOfDay();
        LocalDateTime end = timestamp.atTime(LocalTime.MAX);

        List<CryptoPrice> list = repository.findByTimestampBetween(start, end);

        BigDecimal sumBitcoins = BigDecimal.ZERO;
        BigDecimal countBitcoins = BigDecimal.ZERO;

        BigDecimal sumEthereum = BigDecimal.ZERO;
        BigDecimal countEthereum = BigDecimal.ZERO;

        for(CryptoPrice item : list) {
            if (item.getCurrency().equals(BITCOIN)) {
                sumBitcoins = sumBitcoins.add(item.getPriceUsd());
                countBitcoins = countBitcoins.add(BigDecimal.ONE);
            }
            if (item.getCurrency().equals(ETHEREUM)) {
                sumEthereum = sumEthereum.add(item.getPriceUsd());
                countEthereum = countEthereum.add(BigDecimal.ONE);
            }
        }

        BigDecimal average_rate_forBitcoins = sumBitcoins.divide(countBitcoins, 2, BigDecimal.ROUND_HALF_UP);
        BigDecimal average_rate_forEthereum = sumEthereum.divide(countEthereum, 2, BigDecimal.ROUND_HALF_UP);

        CryptoPriceDto bitcoin = CryptoPriceDto.builder()
                .currency(BITCOIN)
                .priceUsd(average_rate_forBitcoins)
                .timestamp(end)
                .createdAt(start)
                .build();

        CryptoPriceDto ethereum = CryptoPriceDto.builder()
                .currency(ETHEREUM)
                .priceUsd(average_rate_forEthereum)
                .timestamp(end)
                .createdAt(start)
                .build();

        return List.of(bitcoin, ethereum);
    }
}