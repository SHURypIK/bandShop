package com.example.bandShop.controller;



import com.example.bandShop.entity.ReviewEntity;
import com.example.bandShop.exception.ProductAlreadyExistException;
import com.example.bandShop.exception.ReviewNotFoundedException;
import com.example.bandShop.service.ReviewServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewServise reviewServise;

    @PostMapping
    public ResponseEntity createReview(@RequestBody ReviewEntity review,
                                       @RequestHeader("user_id") int user_id,
                                       @RequestHeader("product_id") String product_id){
        try {
            reviewServise.createReview(review, user_id, product_id);
            return  ResponseEntity.ok("Продукт успешно добавлен");
        } catch(ProductAlreadyExistException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @DeleteMapping()
    public ResponseEntity deleteReview(@RequestHeader("user_id") int user_id,
                                        @RequestHeader("product_id") String product_id){
        try {
            return  ResponseEntity.ok(reviewServise.delete(user_id, product_id));
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PutMapping
    public ResponseEntity updateReview(@RequestBody ReviewEntity review,
                                        @RequestHeader("user_id") int user_id,
                                        @RequestHeader("product_id") String product_id){
        try {
            return  ResponseEntity.ok(reviewServise.updateReview(review, user_id, product_id));
        } catch(ReviewNotFoundedException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/one")
    public ResponseEntity getOneReview(@RequestHeader("user_id") int user_id,
                                        @RequestHeader("product_id") String product_id){
        try {
            return  ResponseEntity.ok(reviewServise.getOneReview(user_id, product_id));
        }catch(ReviewNotFoundedException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/byuser")
    public ResponseEntity getReviewsByUser(@RequestHeader("user_login") String user_login){
        try {
            return ResponseEntity.ok(reviewServise.getReviewsByUser(user_login));
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/byproduct")
    public ResponseEntity getReviewsByProduct(@RequestHeader("product_title") String product_title){
        try {
            return ResponseEntity.ok(reviewServise.getReviewsByProduct(product_title));
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
