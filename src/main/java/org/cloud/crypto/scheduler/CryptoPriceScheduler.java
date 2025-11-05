package org.cloud.crypto.scheduler;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.cloud.crypto.dto.CryptoPriceDto;
import org.cloud.crypto.mapper.CryptoMapper;
import org.cloud.crypto.model.CryptoPrice;
import org.cloud.crypto.service.CryproDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Log4j2
public class CryptoPriceScheduler {
    private final CryproDataService service;
    private final RestTemplate restTemplate;
    private final CryptoMapper mapper;

    @Value("${crypto.api.url}")
    private String apiUrl;

    @Value("${crypto.api.currencies}")
    private String currencies;

    @Value("${crypto.api.vs_currencies}")
    private String vsCurrencies;

    @Scheduled(fixedRateString = "${crypto.api.update-interval}")
    public void fetchAndSaveCryptoPrices() {
        log.info("Starting scheduled crypto price update");

        try {
            String url = String.format("%s?ids=%s&vs_currencies=%s", apiUrl, currencies, vsCurrencies);

            var response = restTemplate.getForObject(url, Map.class);

            if(response != null) {
                processApiResponse(response);
                log.info("Successfully updated crypto prices");
            } else {
                log.error("Recived null response from API");
            }
        } catch (Exception e) {
            log.error("Error fetching crypto prices from API: {}", e.getMessage(), e);
        }
    }

    private void processApiResponse(Map<String, Object> response) {
        LocalDateTime now = LocalDateTime.now();

        response.forEach((currency, data) -> {
            if (data instanceof Map) {
                Map<String, Object> priceData = (Map<String, Object>) data;
                if (priceData.containsKey("usd")) {
                    BigDecimal price = new BigDecimal(priceData.get("usd").toString());
                    CryptoPrice cryptoPrice = new CryptoPrice(currency, price, now);
                    CryptoPriceDto cryptoPriceDto = mapper.toDto(cryptoPrice);
                    service.save(cryptoPriceDto);

                    log.debug("Saved price for {}: ${}", currency, price);
                }
            }
        });
    }


}
