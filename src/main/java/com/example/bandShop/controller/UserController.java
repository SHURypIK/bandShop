package com.example.bandShop.controller;

import com.example.bandShop.entity.ProductEntity;
import com.example.bandShop.exception.UserAlreadyExistException;
import com.example.bandShop.exception.UserNotFoundException;
import com.example.bandShop.entity.UserEntity;
import com.example.bandShop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity registration(@RequestBody UserEntity user){
        try {
            userService.registration(user);
            return  ResponseEntity.ok("Клиент успешно сохранен");
        } catch(UserAlreadyExistException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneUser(@PathVariable Integer id){
        try {
            return  ResponseEntity.ok(userService.getOne(id));
        }catch(UserNotFoundException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        try {
            return  ResponseEntity.ok(userService.delete(id));
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PutMapping
    public ResponseEntity updateUser(@RequestBody UserEntity user){
        try {
            return ResponseEntity.ok(userService.updateUser(user));
        } catch(UserNotFoundException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/enter")
    public ResponseEntity enterUser(@RequestBody UserEntity user){
        try {
            return  ResponseEntity.ok(userService.enter(user));
        }catch(UserNotFoundException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/all")
    public ResponseEntity getUsers(){
        try {
            return ResponseEntity.ok(userService.getUsers());
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PutMapping("/wishlist")
    public ResponseEntity addToWishList(@RequestBody ProductEntity product, @RequestHeader("user_id") int user_id){
        try {
            return ResponseEntity.ok(userService.addToWishList(product, user_id));
        } catch(UserNotFoundException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PutMapping("/info")
    public ResponseEntity updatePersonalInfo(@RequestBody UserEntity user){
        try {
            return ResponseEntity.ok(userService.updatePerosnalInfo(user));
        } catch(UserNotFoundException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/wishlist")
    public ResponseEntity getwishlist(@RequestHeader("user_id") int user_id){
        try {
            return  ResponseEntity.ok(userService.getWishList(user_id));
       } catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/orderhistory")
    public ResponseEntity getorderhistory(@RequestHeader("user_id") int user_id){
        try {
            return  ResponseEntity.ok(userService.getOrderHistory(user_id));
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}