package com.esparta.spring7restmvcresourceserver.csv.converter;

import com.esparta.spring7restmvcresourceserver.model.BeerStyle;
import com.opencsv.bean.AbstractBeanField;
import lombok.extern.slf4j.Slf4j;

/*
 * Author: M
 * Date: 05-Feb-26
 * Project Name: spring-7-rest-mvc-resource-server
 * Description: beExcellent
 */
@Slf4j
public class BeerStyleConverter
        extends AbstractBeanField<BeerStyle, String> {

    @Override
    public BeerStyle convert(String value) {
        if (value == null || value.isBlank() || value.equalsIgnoreCase("NA")) {
            log.debug("CSV style is null or blank; returning UNKNOWN");
            return BeerStyle.UNKNOWN;
        }
        try {
            return BeerStyle.fromCsv(value);

        } catch (IllegalArgumentException ex) {
            log.warn("Unknown beer style: '{}'", value);
            return BeerStyle.UNKNOWN;
        }
    }
}