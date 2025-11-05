package org.cloud.crypto.controller;

import lombok.RequiredArgsConstructor;
import org.cloud.crypto.dto.CryptoPriceDto;
import org.cloud.crypto.repository.CryptoPriceRepository;
import org.cloud.crypto.service.CryproDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class CryptoPriceController {
    private final CryptoPriceRepository cryptoPriceRepository;
    private final CryproDataService cryproDataService;

    @GetMapping
    public ResponseEntity<List<CryptoPriceDto>> getAllPrices() {
        return ResponseEntity.ok(cryproDataService.getAllPrices());
    }

}
