package com.esparta.spring7restmvcresourceserver.model;

import com.esparta.spring7restmvcresourceserver.csv.converter.BeerStyleConverter;
import com.esparta.spring7restmvcresourceserver.csv.converter.NullableBigDecimalConverter;
import com.esparta.spring7restmvcresourceserver.csv.converter.NullableIntegerConverter;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/*
 * Author: M
 * Date: 04-Feb-26
 * Project Name: spring-7-rest-mvc-resource-server
 * Description: beExcellent
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeerCSVRecord {

//    "","count.x","abv","ibu","id","beer","style","brewery_id","ounces","style2","count.y","brewery","city","state","label"

    @CsvBindByName
    private Integer row;

    @CsvCustomBindByName(column = "count.x",
            converter = NullableIntegerConverter.class)
    private Integer count;

    @CsvCustomBindByName(converter = NullableBigDecimalConverter.class)
    private BigDecimal abv;

    @CsvCustomBindByName(converter = NullableIntegerConverter.class)
    private Integer ibu;

    @CsvCustomBindByName(column = "id",
            converter = NullableIntegerConverter.class)
    private Integer beerId;

    @CsvBindByName
    private String beer;

    @CsvCustomBindByName( column = "style",
            converter = BeerStyleConverter.class)
    private BeerStyle beerStyle;

    @CsvCustomBindByName(column = "brewery_id",
            converter = NullableIntegerConverter.class)
    private Integer breweryId;

    @CsvCustomBindByName(converter = NullableBigDecimalConverter.class)
    private BigDecimal ounces;

    @CsvBindByName
    private String brewery;

    @CsvBindByName
    private String city;

    @CsvBindByName
    private String state;

    @CsvBindByName
    private String label;
}
