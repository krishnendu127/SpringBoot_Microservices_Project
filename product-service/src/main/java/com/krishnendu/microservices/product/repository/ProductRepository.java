package com.krishnendu.microservices.product.repository;

import com.krishnendu.microservices.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

}
