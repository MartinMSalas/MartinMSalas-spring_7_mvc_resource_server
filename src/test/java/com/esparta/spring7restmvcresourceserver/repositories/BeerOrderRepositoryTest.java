package com.esparta.spring7restmvcresourceserver.repositories;

import com.esparta.spring7restmvcresourceserver.entities.Beer;
import com.esparta.spring7restmvcresourceserver.entities.BeerOrder;
import com.esparta.spring7restmvcresourceserver.entities.BeerOrderShipment;
import com.esparta.spring7restmvcresourceserver.entities.Customer;
import com.esparta.spring7restmvcresourceserver.model.BeerStyle;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@EnableJpaAuditing
@DataJpaTest
class BeerOrderRepositoryTest {

    @Autowired
    BeerOrderRepository beerOrderRepository;

    @Autowired
    BeerRepository beerRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BeerOrderShipmentRepository beerOrderShipmentRepository;

    @Autowired
    private EntityManager entityManager;

    private Customer customer;
    private Beer beer;
    private BeerOrder beerOrder;
    private BeerOrderShipment beerOrderShipment;

    @BeforeEach
    void setUp() {
        // Create and save a Customer
        customer = Customer.builder()
                .customerName("Test Customer")
                .build();
        customer = customerRepository.save(customer);

        // Create and save a Beer
        beer = Beer.builder()
                .beerName("Test Beer")
                .beerStyle(BeerStyle.AMERICAN_BARLEYWINE)
                .upc("123456789012")
                .price(BigDecimal.valueOf(11.22))
                .quantityOnHand(111)
                .build();

        beer = beerRepository.save(beer);


        // Create and save a BeerOrder
        beerOrder = BeerOrder.builder()
                .customer(customer)
                .customerRef("Test Order Ref")
                .build();
        /*
        beerOrder = beerOrderRepository.save(beerOrder);
*/
        beerOrderShipment = BeerOrderShipment.builder()
                .shipmentTrackingNumber("AM1234567890")
                .beerOrder(beerOrder)
                .build();

        beerOrderShipment = beerOrderShipmentRepository.save(beerOrderShipment);


    }

    @Test
    void testBeerOrders(){
        System.out.println("Beer Orders: " + beerOrderRepository.count());
        System.out.println("Beers: " + beerRepository.count());
        System.out.println("Customers: " + customerRepository.count());
        System.out.println("Beer Order Shipments: " + beerOrderShipmentRepository.count());
            assertEquals(1, beerOrderRepository.count());
            assertEquals(1, beerRepository.count());
            assertEquals(1, customerRepository.count());

            BeerOrder retrievedOrder = beerOrderRepository.findById(beerOrder.getBeerOrderId()).orElse(null);
            assertNotNull(retrievedOrder);
            assertEquals("Test Order Ref", retrievedOrder.getCustomerRef());
            assertEquals(customer.getCustomerId(), retrievedOrder.getCustomer().getCustomerId());
    }


}