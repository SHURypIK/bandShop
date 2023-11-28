package com.example.bandShop.repository;

import com.example.bandShop.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository <UserEntity, Integer> {

    UserEntity findByLogin(String login);


}
