package ru.skillbox.currency.exchange.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.skillbox.currency.exchange.entity.Currency;
import ru.skillbox.currency.exchange.pojo.ValCurs;
import ru.skillbox.currency.exchange.pojo.Valute;
import ru.skillbox.currency.exchange.repository.CurrencyRepository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AutoUpdateCurrencyService {

    private final CurrencyRepository currencyRepository;

    @Value("${cb-rf.api.url}")
    private String cbRfApiUrl;


    @Scheduled(cron = "${cb-rf.api.update.currency.cron}")
    private void getFreshDataCurrencies(){
        try {
            JAXBContext context = JAXBContext.newInstance(ValCurs.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            URL url = new URL(cbRfApiUrl);
            ValCurs valCurs = (ValCurs) unmarshaller.unmarshal(url);
            saveAllCurrenciesToDb(valCurs);
        } catch (JAXBException | MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveAllCurrenciesToDb(ValCurs valCurs) {
        List<Valute> valutes = valCurs.getValute();
        for (Valute valute : valutes) {
            Double correctValueCurrency = Double.parseDouble(valute.getValue().replace(',', '.'));
            Currency currency = currencyRepository.findByIsoNumCode(valute.getNumCode());
            if (currency != null) {
                currency.setValue(correctValueCurrency);
                currency.setIsoLetterCode(valute.getCharCode());
                currencyRepository.save(currency);
            } else {
                Currency newCurrency = Currency.builder()
                        .name(valute.getName())
                        .nominal(valute.getNominal())
                        .value(correctValueCurrency)
                        .isoNumCode(valute.getNumCode())
                        .isoLetterCode(valute.getCharCode())
                        .build();
                currencyRepository.save(newCurrency);
            }
        }
    }
}
