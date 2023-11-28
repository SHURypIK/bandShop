package com.example.bandShop.repository;


import com.example.bandShop.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepo extends CrudRepository<OrderEntity, Integer> {

}
