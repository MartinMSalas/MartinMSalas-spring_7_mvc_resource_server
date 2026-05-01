package com.esparta.spring7restmvcresourceserver.repositories;

import com.esparta.spring7restmvcresourceserver.entities.BeerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/*
 * Author: m
 * Date: 10/2/26
 * Project Name: spring-7-rest-mvc-resource-server
 * Description: beExcellent
 */
public interface BeerOrderRepository extends JpaRepository<BeerOrder, UUID> {

}
