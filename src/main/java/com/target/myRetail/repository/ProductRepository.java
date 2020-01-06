package com.target.myRetail.repository;

import com.target.myRetail.domain.Price;
import com.target.myRetail.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Price, Integer> {
    //public Price findByProductId(String id);
}