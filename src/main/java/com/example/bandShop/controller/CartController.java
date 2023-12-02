package com.example.bandShop.controller;


import com.example.bandShop.exception.ProductAlreadyExistException;
import com.example.bandShop.exception.ProductMinAmountException;
import com.example.bandShop.exception.ProductNotFoundedException;
import com.example.bandShop.exception.UserNotFoundException;
import com.example.bandShop.service.CartServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartServise cartServise;

    @PutMapping("/new")
    public ResponseEntity addProductToCart(@RequestHeader("user_id") int user_id,
                                           @RequestHeader("product_id") String product_id){
        try {
            return  ResponseEntity.ok(cartServise.addProductToCart(user_id, product_id));
        } catch(ProductAlreadyExistException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }catch(ProductNotFoundedException exce){
            return ResponseEntity.badRequest().body(exce.getMessage());
        }catch(UserNotFoundException exc){
            return ResponseEntity.badRequest().body(exc.getMessage());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PutMapping("/clean")
    public ResponseEntity cleanCart(@RequestHeader("user_id") int user_id){
        try {
            return  ResponseEntity.ok(cartServise.cleanCart(user_id));
        }catch(UserNotFoundException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PutMapping("/delete")
    public ResponseEntity deleteProductToCart(@RequestHeader("user_id") int user_id,
                                              @RequestHeader("product_id") String product_id){
        try {
            return  ResponseEntity.ok(cartServise.deleteProductFromCart(user_id, product_id));
        }catch(UserNotFoundException exc){
            return ResponseEntity.badRequest().body(exc.getMessage());
        }
        catch(ProductNotFoundedException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PutMapping("/changeamount")
    public ResponseEntity changeAmountInCart(@RequestHeader("user_id") int user_id,
                                             @RequestHeader("product_id") String product_id,
                                             @RequestParam boolean add){
        try {
            return  ResponseEntity.ok(cartServise.changeAmountInCart(user_id, product_id, add));
        }catch(UserNotFoundException exc){
            return ResponseEntity.badRequest().body(exc.getMessage());
        }
        catch(ProductNotFoundedException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        catch(ProductMinAmountException exce){
            return ResponseEntity.badRequest().body(exce.getMessage());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping()
    public ResponseEntity getCart(@RequestHeader("user_id") int user_id){
        try {
            return ResponseEntity.ok(cartServise.getCart(user_id));
        }catch(UserNotFoundException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

}
