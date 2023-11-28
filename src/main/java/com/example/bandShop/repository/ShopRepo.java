package com.example.bandShop.repository;

import com.example.bandShop.entity.ShopEntity;
import org.springframework.data.repository.CrudRepository;

public interface ShopRepo extends CrudRepository<ShopEntity, Integer> {

    ShopEntity findByAdminId(int admin);
}
