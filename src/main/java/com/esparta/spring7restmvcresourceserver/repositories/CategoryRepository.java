package com.esparta.spring7restmvcresourceserver.repositories;

import com.esparta.spring7restmvcresourceserver.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/*
 * Author: M
 * Date: 13-Feb-26
 * Project Name: spring-7-rest-mvc-resource-server
 * Description: beExcellent
 */
public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
