package com.example.bandShop.controller;

import com.example.bandShop.entity.ProductEntity;
import com.example.bandShop.exception.PasswordUncorectException;
import com.example.bandShop.exception.ProductNotFoundedException;
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

    @PostMapping("/registre")
    public ResponseEntity registration(@RequestBody UserEntity user){
        try {
            userService.registration(user);
            return  ResponseEntity.ok("Клиент успешно сохранен");
        } catch(UserAlreadyExistException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка" + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneUser(@PathVariable Integer id){
        try {
            return  ResponseEntity.ok(userService.getOne(id));
        }catch(UserNotFoundException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка" + e.getMessage());
      }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        try {
            return  ResponseEntity.ok(userService.delete(id));
        }catch(UserNotFoundException exc){
            return ResponseEntity.badRequest().body(exc.getMessage());
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка"+ e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity updateUser(@RequestBody UserEntity user){
        try {
            return ResponseEntity.ok(userService.updateUser(user));
        } catch(UserNotFoundException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка"+ e.getMessage());
        }
    }

    @GetMapping("/enter")
    public ResponseEntity enterUser(@RequestBody UserEntity user){
        try {
            return  ResponseEntity.ok(userService.enter(user));
        }catch(UserNotFoundException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }catch(PasswordUncorectException exc){
            return ResponseEntity.badRequest().body(exc.getMessage());
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка"+ e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity getUsers(){
        try {
            return ResponseEntity.ok(userService.getUsers());
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка"+ e.getMessage());
        }
    }

    @PutMapping("/wishlist/{product_id}")
    public ResponseEntity addToWishList(@PathVariable String product_id, @RequestHeader("user_id") int user_id){
        try {
            return ResponseEntity.ok(userService.addToWishList(product_id, user_id));
        } catch(UserNotFoundException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        catch(ProductNotFoundedException exc){
            return ResponseEntity.badRequest().body(exc.getMessage());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка"+ e.getMessage());
        }
    }
    @PutMapping("/wishlist/del/{product_id}")
    public ResponseEntity deleteFromWishList(@PathVariable String product_id, @RequestHeader("user_id") int user_id){
        try {
            return ResponseEntity.ok(userService.dellFromWishList(product_id, user_id));
        } catch(UserNotFoundException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        catch(ProductNotFoundedException exc){
            return ResponseEntity.badRequest().body(exc.getMessage());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка"+ e.getMessage());
        }
    }

    @PutMapping("/info")
    public ResponseEntity updatePersonalInfo(@RequestBody UserEntity user){
        try {
            return ResponseEntity.ok(userService.updatePerosnalInfo(user));
        } catch(UserNotFoundException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка"+ e.getMessage());
        }
    }

    @GetMapping("/wishlist")
    public ResponseEntity getwishlist(@RequestHeader("user_id") int user_id){
        try {
            return  ResponseEntity.ok(userService.getWishList(user_id));
       } catch(UserNotFoundException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка"+ e.getMessage());
        }
    }

    @GetMapping("/orderhistory")
    public ResponseEntity getorderhistory(@RequestHeader("user_id") int user_id){
        try {
            return  ResponseEntity.ok(userService.getOrderHistory(user_id));
        }catch(UserNotFoundException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка"+ e.getMessage());
        }
    }

    @GetMapping("/check")
    public ResponseEntity getUserscheck(){
        try {
            return ResponseEntity.ok("норм");
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка"+ e.getMessage());
        }
    }
}