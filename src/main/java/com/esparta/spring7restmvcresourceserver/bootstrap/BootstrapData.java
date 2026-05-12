package com.esparta.spring7restmvcresourceserver.bootstrap;

import com.esparta.spring7restmvcresourceserver.entities.Customer;
import com.esparta.spring7restmvcresourceserver.entities.Beer;

import com.esparta.spring7restmvcresourceserver.mappers.BeerMapper;
import com.esparta.spring7restmvcresourceserver.model.BeerCSVRecord;
import com.esparta.spring7restmvcresourceserver.model.BeerStyle;
import com.esparta.spring7restmvcresourceserver.repositories.CustomerRepository;
import com.esparta.spring7restmvcresourceserver.repositories.BeerRepository;

import com.esparta.spring7restmvcresourceserver.services.BeerCSVService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

/*
 * Author: M
 * Date: 29-Jan-26
 * Project Name: spring-7-rest-mvc-resource-server
 * Description: beExcellent
 */
@Component
@RequiredArgsConstructor
@Slf4j
@Profile("!test")
public class BootstrapData implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final BeerRepository beerRepository;
    private final BeerCSVService beerCSVService;
    private final BeerMapper beerMapper;

    @Transactional
    @Override
    public void run(String... args) {
        log.debug("In bootstrap; ");
        // ======== Beer ======== */
        long beerCount = beerRepository.count();
        log.debug("Initial Beer Count: {}", beerCount);
        if(beerCount == 0) {
            log.debug("Loading Beer Data");
            //loadBeerData();
            loadCSVBeerData();
            beerCount = beerRepository.count();
        }

        /* ======== Beer CSV  ======== */

        /* ========= Customer ======== */
        long customerCount = customerRepository.count();
        log.debug("Initial Customer Count: {}", customerCount);
        if(customerCount == 0) {
            log.debug("Loading Customer Data");
            loadCustomerData();
            customerCount = customerRepository.count();
        }

        /* ========= Log ========= */
        log.debug("Beer Count: {}", beerCount);
        log.debug("Saved Beer: {}", beerRepository.findFirstByOrderByCreatedDateAsc().orElseThrow(() -> new NoSuchElementException("No beers found after bootstrap")));
        log.debug("Customer Count: {}", customerCount);
        log.debug("Saved Customer: {}", customerRepository.findFirstByOrderByCreatedDateAsc().orElseThrow(() -> new NoSuchElementException("No customers found after bootstrap")));

    }
    private void loadCSVBeerData(){
        log.debug("Loading Beer CSV Data");
        String beerFileName = "beers.csv";
        String pathToFile = "src/main/java/com/esparta/spring7restmvcresourceserver/model/csv/" + beerFileName;
        List<BeerCSVRecord> beerCSVRecords = beerCSVService.beerCSVRecords(pathToFile);

        List<Beer> beers = beerMapper.csvToBeerList(beerCSVRecords);
        List<Beer> savedBeers = beerRepository.saveAll(beers);
        log.debug("Saved {} beers from CSV", savedBeers.size());

    }



    private void loadCustomerData() {
        customerRepository.saveAll(List.of(
                Customer.builder().customerName("Don Pepe").build(),
                Customer.builder().customerName("Maria Luisa").build(),
                Customer.builder().customerName("Juan Carlos").build()
        ));
    }


}
