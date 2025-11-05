package org.cloud.crypto.mapper;

import org.cloud.crypto.dto.CryptoPriceDto;
import org.cloud.crypto.model.CryptoPrice;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CryptoMapper {
    List<CryptoPriceDto> toResponses(List<CryptoPrice> cryptoPrices);
    CryptoPrice toEntity(CryptoPriceDto cryptoPriceDto);
    CryptoPriceDto toDto(CryptoPrice cryptoPrice);
}
