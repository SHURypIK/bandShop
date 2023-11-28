package com.example.bandShop.repository;

import com.example.bandShop.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;


public interface ProductRepo extends CrudRepository<ProductEntity,String> {
}
