package com.example.bandShop.service;

import com.example.bandShop.entity.ReviewEntity;
import com.example.bandShop.exception.ProductAlreadyExistException;
import com.example.bandShop.exception.ProductNotFoundedException;
import com.example.bandShop.exception.ReviewNotFoundedException;
import com.example.bandShop.exception.UserNotFoundException;
import com.example.bandShop.model.Review;
import com.example.bandShop.model.User;
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

    public ReviewEntity createReview (ReviewEntity review, int user_id, String product_id) throws ProductAlreadyExistException, UserNotFoundException, ProductNotFoundedException {
        if(!userRepo.existsById(user_id))
            throw new UserNotFoundException("Пользователь не найден");
        if(!productRepo.existsById(product_id))
            throw new ProductNotFoundedException("Продукт не найден");
        review.setUser(userRepo.findById(user_id).get());
        review.setProduct(productRepo.findById(product_id).get());
        if (reviewRepo.existsById(review.getId())   || reviewRepo.findByProductIdAndUserId(review.getProduct().getId(),review.getUser().getId()) != null)
            throw new ProductAlreadyExistException("Отзыв уже существует");
        return reviewRepo.save(review);
    }

    public Review delete(int user_id, String product_id) throws ReviewNotFoundedException {
        if(reviewRepo.findByProductIdAndUserId(product_id,user_id) == null)
            throw new ReviewNotFoundedException("Отзыв не найден");
        ReviewEntity review = reviewRepo.findByProductIdAndUserId(product_id,user_id);
        reviewRepo.deleteById(review.getId());
        return new Review();
    }

    public Review updateReview (ReviewEntity review, int user_id, String product_id) throws ReviewNotFoundedException {
        ReviewEntity reviewe = reviewRepo.findByProductIdAndUserId(product_id,user_id);
        if(reviewe != null){
            reviewe.setGrade(review.getGrade());
            reviewe.setText(review.getText());
            reviewRepo.save(reviewe);
            return Review.toModel(reviewe);
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

    public List<Review> getReviewsByUser(String login) throws UserNotFoundException {
        List<Review> reviews = getReviews();
        List<Review> sortReviews = new ArrayList<>();
        if(userRepo.findByLogin(login) == null)
            throw new UserNotFoundException("Пользователь не найден");
        for(Review r : reviews)
            if(r.getUser().equals(login))
                sortReviews.add(r);
        return sortReviews;
    }

    public List<Review> getReviewsByProduct(String title) throws ProductNotFoundedException {
        List<ReviewEntity> reviewEntities = (List<ReviewEntity>) reviewRepo.findAll();
        List<Review> reviews = new ArrayList<>();
        if(productRepo.findByTitle(title) == null)
            throw new ProductNotFoundedException("Продукт не найден");
        for(ReviewEntity re : reviewEntities)
            if(re.getProduct().getTitle().equals(title))
                reviews.add(Review.toModel(re));
        return reviews;
    }

    public List<Review> getAll() throws UserNotFoundException {
        List<Review> reviews = getReviews();
        return reviews;
    }
}
