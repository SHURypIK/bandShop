package com.example.bandShop.repository;


import com.example.bandShop.entity.ReviewEntity;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepo extends CrudRepository<ReviewEntity, Integer> {

    ReviewEntity findByProductIdAndUserId(String product,Integer user);

    ReviewEntity deleteByProductIdAndUserId(String product,Integer user);
}
