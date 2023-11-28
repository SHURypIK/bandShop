package com.example.bandShop.controller;


import com.example.bandShop.entity.OrderEntity;

import com.example.bandShop.exception.*;
import com.example.bandShop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
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
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
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

    @PutMapping("/status")
    public ResponseEntity changeStatus(@RequestBody OrderEntity order,
                                       @RequestHeader("status") String status){
        try {
            return  ResponseEntity.ok(orderService.changeStatus(order, status));
        }catch(OrderNotFoundedException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

}
