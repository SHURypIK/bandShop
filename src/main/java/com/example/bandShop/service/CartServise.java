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
        if(cart.getProducts().containsKey(product))
            throw new ProductAlreadyExistException("Продукт уже добавлен в карзину");
        cart.getProducts().put(product,1);
        cart.setTotalPrice(cart.getTotalPrice() + product.getPrice());
        cartRepo.save(cart);
        return Cart.toModel(cart);
    }

    public Cart cleanCart(int user_id) throws UserNotFoundException {
        CartEntity cart = cartRepo.findByUserId(user_id);
        if(cart == null)
            throw new UserNotFoundException("Карзина не найдена");
        cart.getProducts().clear();
        cart.setTotalPrice(0);
        return Cart.toModel(cart);
    }

    public Cart deleteProductFromCart(int user_id, String product_id) throws UserNotFoundException, ProductNotFoundedException {
        CartEntity cart = cartRepo.findByUserId(user_id);
        if(cart == null)
            throw new UserNotFoundException("Карзина не найдена");
        if(!productRepo.existsById(product_id))
            throw new ProductNotFoundedException("Продукт не найден");
        ProductEntity product = productRepo.findById(product_id).get();
        if(!cart.getProducts().containsKey(product))
            throw new ProductNotFoundedException("Продукт не найден в каризне");
        cart.setTotalPrice(cart.getTotalPrice() - product.getPrice()*cart.getProducts().get(product));
        cart.getProducts().remove(product);
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
        if(!cart.getProducts().containsKey(product))
            throw new ProductNotFoundedException("Продукт не найден");
        if(add){
            cart.setTotalPrice(cart.getTotalPrice() + product.getPrice());
            cart.getProducts().put(product, cart.getProducts().get(product) + 1);
        }
        else {
            if(cart.getProducts().get(product) == 1)
                throw new ProductMinAmountException("Выбрано минимальное количество товаров");
            cart.setTotalPrice(cart.getTotalPrice() - product.getPrice());
            cart.getProducts().put(product, cart.getProducts().get(product) - 1);
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
