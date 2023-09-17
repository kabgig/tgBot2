package com.kabgig.tgBot2.dto;


import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.datatype.XMLGregorianCalendar;


@XmlRootElement(name = "GetCursOnDateXML", namespace = "http://web.cbr.ru/")
//@Data //Геттеры и сеттеры
public class GetCursOnDateXml {
    @XmlElement(name = "On_date", required = true, namespace = "http://web.cbr.ru/")
    protected XMLGregorianCalendar onDate;

//    public XMLGregorianCalendar getOnDate() {
//        return onDate;
//    }

    public void setOnDate(XMLGregorianCalendar onDate) {
        this.onDate = onDate;
    }
}