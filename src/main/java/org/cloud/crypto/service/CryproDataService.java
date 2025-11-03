package org.cloud.crypto.service;

import lombok.RequiredArgsConstructor;
import org.cloud.crypto.mapper.CryptoMapper;
import org.cloud.crypto.repository.CryptoPriceRepository;
import org.cloud.crypto.response.CryptoPriceResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CryproDataService {
    private final CryptoPriceRepository repository;
    private final CryptoMapper mapper;

    public List<CryptoPriceResponse> getAllPrices() {
        return mapper.toResponses(repository.findAll());
    }
}
