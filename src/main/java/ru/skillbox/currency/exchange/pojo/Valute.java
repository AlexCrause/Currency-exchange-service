package ru.skillbox.currency.exchange.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Valute {

    @XmlAttribute(name = "ID")
    private String id;

    @XmlElement(name = "NumCode")
    private Long numCode;

    @XmlElement(name = "CharCode")
    private String charCode;

    @XmlElement(name = "Nominal")
    private Long nominal;

    @XmlElement(name = "Name")
    private String name;

    @XmlElement(name = "Value")
    private String value;

    @XmlElement(name = "VunitRate")
    private String vunitRate;
}
