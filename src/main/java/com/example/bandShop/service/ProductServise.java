package com.example.bandShop.service;

import com.example.bandShop.entity.ProductEntity;
import com.example.bandShop.exception.ProductAlreadyExistException;
import com.example.bandShop.exception.ProductNotFoundedException;
import com.example.bandShop.exception.SortOrderException;
import com.example.bandShop.exception.UserNotFoundException;
import com.example.bandShop.model.Product;
import com.example.bandShop.model.ProductCard;
import com.example.bandShop.model.User;
import com.example.bandShop.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ProductServise {

    @Autowired
    private ProductRepo productRepo;

    public Product delete(String id) throws ProductNotFoundedException {

        if(!productRepo.existsById(id))
            throw  new ProductNotFoundedException("Проукт не найден");
        ProductEntity product = productRepo.findById(id).get();
        product.setStrorage(0);
        product.setTitle("Удалено");
        product.setHit(false);
        product.setDescription(null);
        product.setPictures(null);
        product.setPrice(null);
        productRepo.save(product);
        return new Product();
    }

    public ProductEntity createProduct (ProductEntity product) throws ProductAlreadyExistException {
        if (productRepo.existsById(product.getId()) || productRepo.findByTitle(product.getTitle()) != null)
            throw new ProductAlreadyExistException("Продукт уже существует");
        product.setStrorage(0);
        product.setReviews(new ArrayList<>());
        return productRepo.save(product);
    }

    public Product addProduct (String id, int amount) throws ProductNotFoundedException {

        if (!productRepo.existsById(id))
            throw new ProductNotFoundedException("Продукт не найден");
        ProductEntity product = productRepo.findById(id).get();
        product.setStrorage(product.getStrorage()+amount);
        productRepo.save(product);
        return Product.toModel(product);
    }

    public Product updateProduct (ProductEntity product) throws ProductNotFoundedException, ProductAlreadyExistException {
        String id = product.getId();
        String title = product.getTitle();
        if(productRepo.existsById(product.getId())){
            if(productRepo.findByTitle(title) != null)
                if(productRepo.findByTitle(title).getId().equals(id))
                    throw  new ProductAlreadyExistException("Название занято");
            product.setReviews(productRepo.findById(product.getId()).get().getReviews());
            product.setStrorage(productRepo.findById(product.getId()).get().getStrorage());
            productRepo.save(product);
            return Product.toModel(product);
        }
        throw new ProductNotFoundedException("Продукт не найден");
    }

    public Product getOneProduct (String id) throws ProductNotFoundedException {
        if(!productRepo.existsById(id))
            throw new ProductNotFoundedException("Продукт не найден");
        return Product.toModel(productRepo.findById(id).get());
    }

    public List<ProductCard> getProducts() {
        List<ProductEntity> productEntities = (List<ProductEntity>) productRepo.findAll();
        List<ProductCard> products = new ArrayList<>();
        for(ProductEntity pe: productEntities){
            if(pe.getTitle().equals("Удалено"))
                continue;
            products.add(ProductCard.toModel(pe));
        }
        return products;
    }

    public List<ProductCard> getProductsHit(boolean hit) {
        List<ProductEntity> productEntities = (List<ProductEntity>) productRepo.findAll();
        List<ProductCard> products = new ArrayList<>();
        for(ProductEntity pe: productEntities){
            if(pe.getTitle().equals("Удалено"))
                continue;
            if(pe.isHit() == hit)
                products.add(ProductCard.toModel(pe));
        }
        return products;
    }

    public List<ProductCard> getProductsSortedByTitle(String order) throws SortOrderException {
        List<ProductCard> products = getProducts();
        switch (order.trim()){
            case "increase"-> products.sort(Comparator.comparing(ProductCard::getTitle));
            case "decrease"-> products.sort(Comparator.comparing(ProductCard::getTitle).reversed());
            default -> throw new SortOrderException("Не верный порядок сортировки");
        }
        return products;
    }

    public List<ProductCard> getProductsSortedByPrice(String order) throws SortOrderException {
        List<ProductCard> products = getProducts();
        switch (order){
            case "increase"->products.sort(Comparator.comparing(ProductCard::getPrice));
            case "decrease"->products.sort(Comparator.comparing(ProductCard::getPrice).reversed());
            default -> throw new SortOrderException("Не верный порядок сортировки");
        }
        return products;
    }

    public List<ProductCard> getProductsSortedByHit(){
        List<ProductCard> products = getProducts();
        List<ProductCard> sortProducts = new ArrayList<>();
        for(ProductCard pc : products)
            if(pc.isHit())
                sortProducts.add(pc);
            return sortProducts;
    }

    public List<ProductCard> getProductsFind(String request) throws ProductNotFoundedException {
        List<ProductCard> products = getProducts();
        List<ProductCard> sortProducts = new ArrayList<>();
        for(ProductCard pc : products)
            if(pc.getTitle().contains(request.trim()) || pc.getId().contains(request.trim()))
                sortProducts.add(pc);
        if(sortProducts.isEmpty())
            throw new ProductNotFoundedException("Продукт не найден");
        return sortProducts;
    }



}
