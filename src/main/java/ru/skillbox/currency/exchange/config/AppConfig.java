package ru.skillbox.currency.exchange.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.skillbox.currency.exchange.pojo.ValCurs;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

@Configuration
public class AppConfig {

    @Bean
    public JAXBContext jaxb() throws JAXBException {
        return JAXBContext.newInstance(ValCurs.class);
    }
}
