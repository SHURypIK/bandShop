package com.example.bandShop.controller;

import com.example.bandShop.entity.ProductEntity;
import com.example.bandShop.exception.ProductAlreadyExistException;
import com.example.bandShop.exception.ProductNotFoundedException;
import com.example.bandShop.exception.SortOrderException;
import com.example.bandShop.service.ProductServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductServise productServise;

    @PostMapping
    public ResponseEntity createProduct(@RequestBody ProductEntity product){
        try {
            productServise.createProduct(product);
            return  ResponseEntity.ok("Продукт успешно добавлен");
        } catch(ProductAlreadyExistException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка" + e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable String id){
        try {
            return  ResponseEntity.ok(productServise.delete(id));
        } catch(ProductNotFoundedException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity addProduct(@PathVariable String id, @RequestHeader("amount") int amount){
        try {
            return  ResponseEntity.ok(productServise.addProduct(id,amount));
        } catch(ProductNotFoundedException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PutMapping
    public ResponseEntity updateProduct(@RequestBody ProductEntity product){
        try {
            return  ResponseEntity.ok(productServise.updateProduct(product));
        } catch(ProductNotFoundedException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка" + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneProduct(@PathVariable String id){
        try {
            return  ResponseEntity.ok(productServise.getOneProduct(id));
        }catch(ProductNotFoundedException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping
    public ResponseEntity getProducts(){
        try {
            return ResponseEntity.ok(productServise.getProducts());
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/hit/{hit}")
    public ResponseEntity getProductsHit(@PathVariable boolean hit){
        try {
            return ResponseEntity.ok(productServise.getProductsHit(hit));
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/sort/title")
    public ResponseEntity getProductsSortedByTitle(@RequestParam String order){
        try {
            return ResponseEntity.ok(productServise.getProductsSortedByTitle(order));
        }catch(SortOrderException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/sort/price")
    public ResponseEntity getProductsSortedByPrice(@RequestParam String order){
        try {
            return ResponseEntity.ok(productServise.getProductsSortedByPrice(order));
        }catch(SortOrderException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/sort/hit")
    public ResponseEntity getProductsSortedByHit(){
        try {
            return ResponseEntity.ok(productServise.getProductsSortedByHit());
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/find")
    public ResponseEntity getProductsFind(@RequestParam String request){
        try {
            return ResponseEntity.ok(productServise.getProductsFind(request));
        }catch(ProductNotFoundedException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
