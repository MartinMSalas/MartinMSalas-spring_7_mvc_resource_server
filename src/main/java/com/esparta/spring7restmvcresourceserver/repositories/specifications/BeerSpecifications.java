package com.esparta.spring7restmvcresourceserver.repositories.specifications;

import com.esparta.spring7restmvcresourceserver.entities.Beer;
import com.esparta.spring7restmvcresourceserver.model.BeerStyle;
import org.springframework.data.jpa.domain.Specification;

/*
 * Author: M
 * Date: 09-Feb-26
 * Project Name: spring-7-rest-mvc-resource-server
 * Description: beExcellent
 */
public class BeerSpecifications {

    public static Specification<Beer> hasNameLike(String beerName) {
        return (root, query, cb) ->
                beerName == null
                        ? null
                        : cb.like(
                        cb.lower(root.get("beerName")),
                        "%" + beerName.toLowerCase() + "%"
                );
    }

    public static Specification<Beer> hasStyle(BeerStyle style) {
        return (root, query, cb) ->
                style == null
                        ? null
                        : cb.equal(root.get("beerStyle"), style);
    }
}
