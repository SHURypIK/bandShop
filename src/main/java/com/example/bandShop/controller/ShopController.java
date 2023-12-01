package com.example.bandShop.controller;

import com.example.bandShop.entity.ShopEntity;
import com.example.bandShop.exception.OrdersExcistException;
import com.example.bandShop.exception.ShopAlredyExistException;
import com.example.bandShop.exception.ShopNotFoundedException;
import com.example.bandShop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;


    @PostMapping
    public ResponseEntity createShop(@RequestBody ShopEntity shop){
        try {
            shopService.create(shop);
            return  ResponseEntity.ok("Магазин усешно создан");
        } catch(ShopAlredyExistException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteShop(@PathVariable int id){
        try {
            return  ResponseEntity.ok(shopService.delete(id));
        }catch(ShopNotFoundedException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity getOrders(@PathVariable int id){
        try {
            return  ResponseEntity.ok(shopService.getOrders(id));
        }catch(ShopNotFoundedException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка" + e.getMessage());
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity getOneShop(@PathVariable int id){
        try {
            return  ResponseEntity.ok(shopService.getOne(id));
        }catch(ShopNotFoundedException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка"+ e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity getShops(){
        try {
            return ResponseEntity.ok(shopService.getShops());
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PutMapping()
    public ResponseEntity updateShop(@RequestBody ShopEntity shop){
        try {
            return ResponseEntity.ok(shopService.updateShop(shop));
        } catch(ShopNotFoundedException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }catch(OrdersExcistException exс){
            return ResponseEntity.badRequest().body(exс.getMessage());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
    @GetMapping("/free")
    public ResponseEntity getFree(){
        try {
            return  ResponseEntity.ok(shopService.getFree());
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка"+ e.getMessage());
        }
    }
}
