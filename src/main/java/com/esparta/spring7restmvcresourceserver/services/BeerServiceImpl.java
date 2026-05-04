package com.esparta.spring7restmvcresourceserver.services;

import com.esparta.spring7restmvcresourceserver.entities.Beer;
import com.esparta.spring7restmvcresourceserver.exceptions.NotFoundException;
import com.esparta.spring7restmvcresourceserver.mappers.BeerMapper;
import com.esparta.spring7restmvcresourceserver.model.BeerDTO;

import com.esparta.spring7restmvcresourceserver.model.BeerStyle;
import com.esparta.spring7restmvcresourceserver.repositories.BeerRepository;

import com.esparta.spring7restmvcresourceserver.repositories.specifications.BeerSpecifications;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.util.*;

/*
 * Author: M
 * Date: 24-Jan-26
 * Project Name: spring-7-rest-mvc-resource-server
 * Description: beExcellent
 */
@Service
@Slf4j
public class BeerServiceImpl implements BeerService {


    private final BeerMapper beerMapper;
    private final BeerRepository beerRepository;

    public BeerServiceImpl(BeerMapper beerMapper, BeerRepository beerRepository){
        this.beerMapper = beerMapper;
        this.beerRepository = beerRepository;


    }
    // Post Create method
    @Override
    public BeerDTO saveNewBeer(BeerDTO beerDTO) {
        log.debug("Save New BeerDTO - in Service. BeerDTO: {}", beerDTO);

        Beer beerToSave = beerMapper.beerDTOToBeer(beerDTO);
        Beer savedBeer = beerRepository.save(beerToSave);
        log.debug("Beer entity saved: {}", savedBeer);

        return beerMapper.beerToBeerDTO(savedBeer);
    }
    // Get Read methods
    @Override
    public BeerDTO getBeerById(UUID beerId) {
        log.debug("Get BeerDTO by Id - in Service. Id: {}", beerId);

        if (beerId == null) {
            throw new IllegalArgumentException("Beer ID must be provided");
        }

        Beer beer = beerRepository.findById(beerId)
                .orElseThrow(() -> new NotFoundException(
                        "Beer not found with id " + beerId
                ));

        log.debug("Loaded Beer: {}", beer);

        return beerMapper.beerToBeerDTO(beer);
    }

    @Override
    public Page<BeerDTO> getAllBeers(String beerName, String beerStyle, Integer page, Integer size){
        // return beerMap.values().stream().toList();
        log.debug("Get All Beers - in Service. Filters - Name: {}, Style: {}, Page: {}, Size: {}", beerName, beerStyle, page, size);
        String name = (beerName == null || beerName.isBlank()) ? null : beerName.trim();

        BeerStyle style = (beerStyle == null || beerStyle.isBlank())
                ? null
                : BeerStyle.valueOf(beerStyle);
        // Default sorting by beerName ascending A-Z
        Sort sort = Sort.by(Sort.Direction.ASC, "beerName");

        Pageable pageable = PageRequest.of(
                page == null || page < 0 ? 0 : page,
                size == null || size <= 0 ? 25 : size,
                sort
        );

        Specification<Beer> spec = Specification
                .where(BeerSpecifications.hasNameLike(name))
                .and(BeerSpecifications.hasStyle(style));
        log.debug("Constructed Specification: {}", spec);

        return beerRepository.findAll(spec, pageable)
                .map(beerMapper::beerToBeerDTO);

    }
    //  Put Update methods
    @Override
    public BeerDTO updateBeer(UUID beerId, BeerDTO beerDTO) {
        log.debug("Update BeerDTO - in Service. Id: {}, BeerDTO: {}", beerId, beerDTO);
        // Validate input (PUT requires ID)
        if (beerId == null) {
            throw new IllegalArgumentException("Beer ID must be provided for update");
        }

        // Load the managed entity
        Beer beer = beerRepository.findById(beerId)
                .orElseThrow(() -> new NotFoundException(
                        "Beer not found with id " + beerDTO.getBeerId()
                ));
        log.debug("Loaded Beer entity for update: {}", beer);
        //  Apply DTO values to the managed entity
        //     - does NOT touch id
        //     - does NOT touch auditing fields
        //     - does NOT touch version
        beerMapper.updateBeerFromDTO(beerDTO, beer);

        //  Explicit save (optional but clear)
        Beer savedBeer = beerRepository.save(beer);
        log.debug("Beer entity updated and saved: {}", savedBeer);
        //  Return fresh DTO (with updated auditing + version)
        return beerMapper.beerToBeerDTO(savedBeer);
    }



    //  Patch Update method
    @Override
    public BeerDTO patchBeer(UUID beerId, BeerDTO beerDTO) {
        log.debug("Patch BeerDTO - in Service. Id: {}, BeerDTO: {}", beerId, beerDTO);
        // Validate input (patch requires ID)
        if (beerId == null) {
            throw new IllegalArgumentException("Beer ID must be provided for patch");
        }
        //  Load managed entity (fail fast if not found)
        Beer beer = beerRepository.findById(beerId)
                .orElseThrow(() -> new NotFoundException(
                        "Beer not found with id " + beerId
                ));

        log.debug("Loaded Beer entity for patch: {}", beer);
        //  Apply non-null DTO values to the managed entity
        //     - does NOT touch id
        //     - does NOT touch auditing fields
        //     - does NOT touch version
        beerMapper.patchBeerFromDTO(beerDTO, beer);
        //  Explicit save (optional but clear)
        Beer savedBeer = beerRepository.save(beer);
        log.debug("Beer entity patched and saved: {}", savedBeer);
        //  Return fresh DTO (with updated auditing + version)
        return beerMapper.beerToBeerDTO(savedBeer);

    }

    @Override
    public BeerDTO deleteById(UUID beerId) {
        log.debug("Delete BeerDTO - in Service. Id: {}", beerId);
        // Validate input (DELETE requires ID)
        if (beerId == null) {
            throw new IllegalArgumentException("Beer ID must be provided for delete");
        }

        //  Load managed entity (fail fast if not found)
        Beer beer = beerRepository.findById(beerId)
                .orElseThrow(() -> new NotFoundException(
                        "Beer not found with id " + beerId
                ));

        log.debug("Loaded Beer entity for deletion: {}", beer);
        //  Delete managed entity
        beerRepository.delete(beer);

        return beerMapper.beerToBeerDTO(beer);
    }
}
