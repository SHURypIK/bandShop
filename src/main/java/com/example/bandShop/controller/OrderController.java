package com.example.bandShop.controller;


import com.example.bandShop.entity.OrderEntity;

import com.example.bandShop.exception.*;
import com.example.bandShop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = {"http://127.0.0.1:5500"})
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity createOrder(@RequestBody OrderEntity order,
                                      @RequestHeader("user_id") int user_id,
                                      @RequestHeader("shop_id") int shop_id){
        try {
            return  ResponseEntity.ok(orderService.createOrder(order, user_id, shop_id));
        } catch(UserNotFoundException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        catch(CartEmptyException exce){
            return ResponseEntity.badRequest().body(exce.getMessage());
        }
        catch(ShopNotFoundedException exc){
            return ResponseEntity.badRequest().body(exc.getMessage());
        } catch(ProductNotEnoughException excep){
            return ResponseEntity.badRequest().body(excep.getMessage());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка" + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneOrder(@PathVariable Integer id){
        try {
            return  ResponseEntity.ok(orderService.getOne(id));
        }catch(OrderNotFoundedException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PutMapping("/status/{id}")
    public ResponseEntity changeStatus(@PathVariable Integer id){
        try {
            return  ResponseEntity.ok(orderService.changeStatus(id));
        }catch(OrderNotFoundedException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка"+e.getMessage());
        }
    }

}
