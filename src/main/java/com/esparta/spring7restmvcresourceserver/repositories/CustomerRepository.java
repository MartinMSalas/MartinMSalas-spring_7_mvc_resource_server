package com.esparta.spring7restmvcresourceserver.repositories;

import com.esparta.spring7restmvcresourceserver.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/*
 * Author: M
 * Date: 28-Jan-26
 * Project Name: spring-7-rest-mvc-resource-server
 * Description: beExcellent
 */
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Optional<Customer> findFirstByOrderByCreatedDateAsc();
}
