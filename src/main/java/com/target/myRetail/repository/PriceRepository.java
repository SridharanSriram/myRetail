package com.target.myRetail.repository;

import com.target.myRetail.domain.Price;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends MongoRepository<Price, Integer> {
}