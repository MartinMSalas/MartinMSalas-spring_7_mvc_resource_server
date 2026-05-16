package com.esparta.spring7restmvcresourceserver.repositories;

import com.esparta.spring7restmvcresourceserver.entities.Customer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
/*
 * Author: M
 * Date: 29-Jan-26
 * Project Name: spring-7-rest-mvc-resource-server
 * Description: beExcellent
 */
@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    static Customer customer;
    static List<Customer> customerList;

    @BeforeAll
    static void setUp() {
        customer = Customer.builder()
                .customerName("Panchito")
                .build();
        customerList = List.of(customer);
    }

    @Test
    void givenValidCustomer_whenSavingCustomer_thenReturnsBeerSaved(){
        // GIVEN

        // WHEN
        Customer savedCustomer = customerRepository.save(customer);

        // THEN
        assertThat(savedCustomer).isNotNull();
        assertThat(savedCustomer.getCustomerId()).isNotNull();

    }
}
