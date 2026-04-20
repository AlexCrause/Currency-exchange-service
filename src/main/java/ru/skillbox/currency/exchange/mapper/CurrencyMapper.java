package ru.skillbox.currency.exchange.mapper;

import org.mapstruct.Mapper;
import ru.skillbox.currency.exchange.dto.CurrencyDto;
import ru.skillbox.currency.exchange.dto.CurrencyListDto;
import ru.skillbox.currency.exchange.dto.CurrencyShotDto;
import ru.skillbox.currency.exchange.entity.Currency;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {

    CurrencyDto convertToDto(Currency currency);

    Currency convertToEntity(CurrencyDto currencyDto);

    default CurrencyListDto covertToListDto(List<Currency> currencyList) {
        CurrencyListDto response = new CurrencyListDto();
        response.setCurrencies(currencyListToCurrencyDtoList(currencyList));
        return response;
    }

    List<CurrencyShotDto> currencyListToCurrencyDtoList(List<Currency> currencyList);
}
