package ru.skillbox.currency.exchange.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.currency.exchange.dto.CurrencyDto;
import ru.skillbox.currency.exchange.dto.CurrencyListDto;
import ru.skillbox.currency.exchange.pojo.ValCurs;
import ru.skillbox.currency.exchange.service.AutoUpdateCurrencyService;
import ru.skillbox.currency.exchange.service.CurrencyService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/currency")
public class CurrencyController {
    private final CurrencyService service;
    //private final AutoUpdateCurrencyService autoUpdateCurrencyService;

    @GetMapping
    ResponseEntity<CurrencyListDto> getAllCurrency() {
        return ResponseEntity.ok(service.getAllCurrency());
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<CurrencyDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping(value = "/convert")
    ResponseEntity<Double> convertValue(@RequestParam("value") Long value,
                                        @RequestParam("numCode") Long numCode) {
        return ResponseEntity.ok(service.convertValue(value, numCode));
    }

    @PostMapping("/create")
    ResponseEntity<CurrencyDto> create(@RequestBody CurrencyDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

//    @GetMapping("/test")
//    public ResponseEntity<ValCurs> getTestData(){
//        return ResponseEntity.ok(autoUpdateCurrencyService.getFreshDataCurrencies());
//    }

}
