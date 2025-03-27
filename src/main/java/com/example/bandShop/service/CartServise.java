package com.example.bandShop.service;

import com.example.bandShop.entity.CartEntity;
import com.example.bandShop.entity.ProductEntity;
import com.example.bandShop.exception.ProductAlreadyExistException;
import com.example.bandShop.exception.ProductMinAmountException;
import com.example.bandShop.exception.ProductNotFoundedException;
import com.example.bandShop.exception.UserNotFoundException;
import com.example.bandShop.model.Cart;
import com.example.bandShop.repository.CartRepo;
import com.example.bandShop.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CartServise {

    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private ProductRepo productRepo;

    public Cart addProductToCart(int user_id, String product_id) throws UserNotFoundException, ProductAlreadyExistException, ProductNotFoundedException {
        CartEntity cart = cartRepo.findByUserId(user_id);
        if(cart == null)
            throw new UserNotFoundException("Карзина не найдена");
        if(!productRepo.existsById(product_id))
            throw new ProductNotFoundedException("Продукт не найден");
        ProductEntity product = productRepo.findById(product_id).get();
        if(cart.getPrducts().contains(product))
            throw new ProductAlreadyExistException("Продукт уже добавлен в карзину");
        cart.getPrducts().add(product);
        cart.getAmounts().add(cart.getPrducts().indexOf(product),1);
        cart.setTotalPrice(cart.getTotalPrice() + product.getPrice());
        cartRepo.save(cart);
        return Cart.toModel(cart);
    }

    public Cart cleanCart(int user_id) throws UserNotFoundException {
        CartEntity cart = cartRepo.findByUserId(user_id);
        if(cart == null)
            throw new UserNotFoundException("Карзина не найдена");
        cart.getAmounts().clear();
        cart.getPrducts().clear();
        cart.setTotalPrice(0);
        cartRepo.save(cart);
        return Cart.toModel(cart);
    }

    public Cart deleteProductFromCart(int user_id, String product_id) throws UserNotFoundException, ProductNotFoundedException {
        CartEntity cart = cartRepo.findByUserId(user_id);
        if(cart == null)
            throw new UserNotFoundException("Карзина не найдена");
        if(!productRepo.existsById(product_id))
            throw new ProductNotFoundedException("Продукт не найден");
        ProductEntity product = productRepo.findById(product_id).get();
        if(!cart.getPrducts().contains(product))
            throw new ProductNotFoundedException("Продукт не найден в каризне");
        cart.setTotalPrice(cart.getTotalPrice() - product.getPrice()*cart.getAmounts().get(cart.getPrducts().indexOf(product)));
        cart.getAmounts().remove(cart.getPrducts().indexOf(product));
        cart.getPrducts().remove(productRepo.findById(product_id).get());
        cartRepo.save(cart);
        return Cart.toModel(cart);
    }

    public Cart changeAmountInCart(int user_id, String product_id, boolean add) throws UserNotFoundException, ProductNotFoundedException, ProductMinAmountException {
        CartEntity cart = cartRepo.findByUserId(user_id);
        if(cart == null)
            throw new UserNotFoundException("Карзина не найдена");
        if(!productRepo.existsById(product_id))
            throw new ProductNotFoundedException("Продукт не найден");
        ProductEntity product = productRepo.findById(product_id).get();
        if(!cart.getPrducts().contains(productRepo.findById(product_id).get()))
            throw new ProductNotFoundedException("Продукт не найден в карзине");
        int index = cart.getPrducts().indexOf(product);
        if(add){
            cart.setTotalPrice(cart.getTotalPrice() + product.getPrice());
            cart.getAmounts().set(index,  cart.getAmounts().get(index) + 1);
        }
        else {
            if(cart.getAmounts().get(index) == 1)
                throw new ProductMinAmountException("Выбрано минимальное количество товаров");
            cart.setTotalPrice(cart.getTotalPrice() - product.getPrice());
            cart.getAmounts().set(index,  cart.getAmounts().get(index) - 1);
        }
        cartRepo.save(cart);
        return Cart.toModel(cart);
    }

    public Cart getCart (int user_id) throws UserNotFoundException {
        CartEntity cart = cartRepo.findByUserId(user_id);
        if(cart == null)
            throw new UserNotFoundException("Карзина не найдена");
        return Cart.toModel(cart);
    }
}
