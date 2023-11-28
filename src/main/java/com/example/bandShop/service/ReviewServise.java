package com.example.bandShop.service;

import com.example.bandShop.entity.ReviewEntity;
import com.example.bandShop.exception.ProductAlreadyExistException;
import com.example.bandShop.exception.ReviewNotFoundedException;
import com.example.bandShop.model.Review;
import com.example.bandShop.repository.ProductRepo;
import com.example.bandShop.repository.ReviewRepo;
import com.example.bandShop.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


@Service
public class ReviewServise {

    @Autowired
    private ReviewRepo reviewRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ProductRepo productRepo;

    public ReviewEntity createReview (ReviewEntity review, int user_id, String product_id) throws ProductAlreadyExistException {
        review.setUser(userRepo.findById(user_id).get());
        review.setProduct(productRepo.findById(product_id).get());
        if (!reviewRepo.findById(review.getId()).isPresent() || reviewRepo.findByProductIdAndUserId(review.getProduct().getId(),review.getUser().getId()) != null)
            throw new ProductAlreadyExistException("Отзыв уже существует");
        return reviewRepo.save(review);
    }

    public String delete(int user_id, String product_id)  {
        reviewRepo.deleteByProductIdAndUserId(product_id,user_id);
        return "Отзыв удален";
    }

    public Review updateReview (ReviewEntity review, int user_id, String product_id) throws ReviewNotFoundedException {
        if(!reviewRepo.findById(review.getId()).isPresent()){
            reviewRepo.save(review);
            return Review.toModel(review);
        }
        throw new ReviewNotFoundedException("Отзыв не найден");
    }

    public Review getOneReview (int user_id, String product_id) throws ReviewNotFoundedException {
        ReviewEntity review = reviewRepo.findByProductIdAndUserId(product_id,user_id);
        if(review == null)
            throw new ReviewNotFoundedException("Отзыв не найден");
        return Review.toModel(review);
    }


    public List<Review> getReviews(){
        List<ReviewEntity> reviewEntities = (List<ReviewEntity>) reviewRepo.findAll();
        List<Review> reviews = new ArrayList<>();
        for(ReviewEntity re : reviewEntities)
            reviews.add(Review.toModel(re));
        return reviews;
    }

    public List<Review> getReviewsByUser(String login){
        List<Review> reviews = getReviews();
        List<Review> sortReviews = new ArrayList<>();
        for(Review r : reviews)
            if(r.getUser().equals(login))
                sortReviews.add(r);
        return sortReviews;
    }

    public List<Review> getReviewsByProduct(String title){
        List<ReviewEntity> reviewEntities = (List<ReviewEntity>) reviewRepo.findAll();
        List<Review> reviews = new ArrayList<>();
        for(ReviewEntity re : reviewEntities)
            if(re.getProduct().getId().equals(title))
                reviews.add(Review.toModel(re));
        return reviews;
    }

}
