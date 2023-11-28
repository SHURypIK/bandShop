package com.example.bandShop.controller;

import com.example.bandShop.entity.ShopEntity;
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
    public ResponseEntity createShop(@RequestBody ShopEntity shop,
                                     @RequestHeader("admin_id") int admin_id){
        try {
            shopService.create(shop, admin_id);
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
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/orders")
    public ResponseEntity getOrders(@RequestHeader("shop_id") int shop_id){
        try {
            return  ResponseEntity.ok(shopService.getOrders(shop_id));
        }catch(ShopNotFoundedException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneShop(@RequestHeader("admin_id") int admin_id){
        try {
            return  ResponseEntity.ok(shopService.getOne(admin_id));
        }catch(ShopNotFoundedException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
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
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
