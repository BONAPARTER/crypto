package org.cloud.crypto.mapper;

import org.cloud.crypto.model.CryptoPrice;
import org.cloud.crypto.response.CryptoPriceResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CryptoMapper {
    List<CryptoPriceResponse> toResponses(List<CryptoPrice> cryptoPrices);
}
