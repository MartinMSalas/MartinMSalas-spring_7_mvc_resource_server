package com.esparta.spring7restmvcresourceserver.services;

import com.esparta.spring7restmvcresourceserver.model.CustomerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/*
 * Author: M
 * Date: 25-Jan-26
 * Project Name: spring-7-rest-mvc-resource-server
 * Description: beExcellent
 */
public interface CustomerService {

    CustomerDTO getCustomerById(UUID id);

    List<CustomerDTO> getAllCustomers();

    CustomerDTO saveNewCustomer(CustomerDTO customerDTO);

    CustomerDTO updateCustomer(UUID customerId, CustomerDTO customerDTO);

    CustomerDTO deleteCustomer(UUID customerId);

    CustomerDTO patchCustomer(UUID customerId, CustomerDTO customerDTO);
}
