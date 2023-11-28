package com.example.bandShop.repository;

import com.example.bandShop.entity.CartEntity;
import org.springframework.data.repository.CrudRepository;

public interface CartRepo extends CrudRepository<CartEntity, Integer> {
    CartEntity findByUserId(Integer user);
}
