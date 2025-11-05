package org.cloud.crypto.service;

import lombok.RequiredArgsConstructor;
import org.cloud.crypto.dto.CryptoPriceDto;
import org.cloud.crypto.mapper.CryptoMapper;
import org.cloud.crypto.model.CryptoPrice;
import org.cloud.crypto.repository.CryptoPriceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CryproDataService {
    private final CryptoPriceRepository repository;
    private final CryptoMapper mapper;

    public List<CryptoPriceDto> getAllPrices() {
        return mapper.toResponses(repository.findAll());
    }

        public CryptoPriceDto save(CryptoPriceDto request) {
            CryptoPrice price = repository.save(mapper.toEntity(request));
            return mapper.toDto(price);
        }
    }