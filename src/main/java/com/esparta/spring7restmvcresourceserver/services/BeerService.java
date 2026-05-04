package com.esparta.spring7restmvcresourceserver.services;


import com.esparta.spring7restmvcresourceserver.model.BeerDTO;
import org.springframework.data.domain.Page;


import java.util.List;

import java.util.UUID;

/*
 * Author: M
 * Date: 24-Jan-26
 * Project Name: spring-7-rest-mvc-resource-server
 * Description: beExcellent
 */
public interface BeerService {

    BeerDTO getBeerById(UUID id);

    Page<BeerDTO> getAllBeers(String beerName, String beerStyle, Integer page, Integer size);

    BeerDTO saveNewBeer(BeerDTO BeerDTO);

    BeerDTO updateBeer(UUID beerId, BeerDTO BeerDTO);

    BeerDTO deleteById(UUID beerId);

    BeerDTO patchBeer(UUID beerId, BeerDTO BeerDTO);
}
