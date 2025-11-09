package org.cloud.crypto.controller;

import lombok.RequiredArgsConstructor;
import org.cloud.crypto.dto.CryptoPriceDto;
import org.cloud.crypto.repository.CryptoPriceRepository;
import org.cloud.crypto.service.CryproDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class CryptoPriceController {
    private final CryproDataService cryproDataService;

    @GetMapping
    public ResponseEntity<List<CryptoPriceDto>> getAllPrices() {
        return ResponseEntity.ok(cryproDataService.getAllPrices());
    }

    @GetMapping("/bitcoins")
    public ResponseEntity<List<CryptoPriceDto>> getBitcoinPrices() {
        return ResponseEntity.ok(cryproDataService.getListBitcoins());
    }

    @GetMapping("/timestamp")
    public ResponseEntity<List<CryptoPriceDto>> getTimestampPrices(@RequestParam("timestamp") LocalDateTime timestamp) {
        return ResponseEntity.ok(cryproDataService.getListByTimestamp(timestamp));
    }

    @GetMapping("/average_rate")
    public ResponseEntity<List<CryptoPriceDto>> getAveragePrices(@RequestParam("date") LocalDate date) {
        return ResponseEntity.ok(cryproDataService.getListByTimestampTimestampBetween(date));
    }
}