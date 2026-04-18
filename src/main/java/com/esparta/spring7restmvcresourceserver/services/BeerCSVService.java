package com.esparta.spring7restmvcresourceserver.services;

import com.esparta.spring7restmvcresourceserver.model.BeerCSVRecord;

import java.io.File;
import java.util.List;

/*
 * Author: M
 * Date: 04-Feb-26
 * Project Name: spring-7-rest-mvc-resource-server
 * Description: beExcellent
 */
public interface BeerCSVService {


    List<BeerCSVRecord> beerCSVRecords(File file);

    List<BeerCSVRecord> beerCSVRecords(String path);
}
