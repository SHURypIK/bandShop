package com.example.bandShop.service;

import com.example.bandShop.entity.ProductEntity;
import com.example.bandShop.exception.ProductAlreadyExistException;
import com.example.bandShop.exception.ProductNotFoundedException;
import com.example.bandShop.exception.SortOrderException;
import com.example.bandShop.model.Product;
import com.example.bandShop.model.ProductCard;
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

    public String delete(String id)  {
        productRepo.deleteById(id);
        return id;
    }

    public ProductEntity createProduct (ProductEntity product) throws ProductAlreadyExistException {
        if (!productRepo.findById(product.getId()).isPresent())
            throw new ProductAlreadyExistException("Продукт уже существует");
        product.setStrorage(0);
        product.setReviews(new ArrayList<>());
        return productRepo.save(product);
    }

    public Product addProduct (String id, int amount) throws ProductNotFoundedException {
        ProductEntity product = productRepo.findById(id).get();
        if (product == null)
            throw new ProductNotFoundedException("Продукт не найден");
        product.setStrorage(product.getStrorage()+amount);
        productRepo.save(product);
        return Product.toModel(product);
    }

    public Product updateProduct (ProductEntity product) throws ProductNotFoundedException {
        if(!productRepo.findById(product.getId()).isPresent()){
            productRepo.save(product);
            return Product.toModel(product);
        }
        throw new ProductNotFoundedException("Продукт не найден");
    }

    public Product getOneProduct (String id) throws ProductNotFoundedException {
        ProductEntity product = productRepo.findById(id).get();
        if(product == null)
            throw new ProductNotFoundedException("Продукт не найден");
        return Product.toModel(product);
    }

    public List<ProductCard> getProducts() {
        List<ProductEntity> productEntities = (List<ProductEntity>) productRepo.findAll();
        List<ProductCard> products = new ArrayList<>();
        for(ProductEntity pe: productEntities){
            products.add(ProductCard.toModel(pe));
        }
        return products;
    }

    public List<ProductCard> getProductsHit(boolean hit) {
        List<ProductEntity> productEntities = (List<ProductEntity>) productRepo.findAll();
        List<ProductCard> products = new ArrayList<>();
        for(ProductEntity pe: productEntities){
            if(pe.isHit() == hit)
                products.add(ProductCard.toModel(pe));
        }
        return products;
    }

    public List<ProductCard> getProductsSortedByTitle(String order) throws SortOrderException {
        List<ProductCard> products = getProducts();
        switch (order){
            case "increase"->products.sort(Comparator.comparing(ProductCard::getTitle));
            case "decrease"->products.sort(Comparator.comparing(ProductCard::getTitle).reversed());
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
                sortProducts.add(0,pc);
            else
               sortProducts.add(pc);
            return sortProducts;
    }

    public List<ProductCard> getProductsFind(String request) throws ProductNotFoundedException {
        List<ProductCard> products = getProducts();
        List<ProductCard> sortProducts = new ArrayList<>();
        for(ProductCard pc : products)
            if(pc.getTitle().contains(request) || pc.getId().contains(request))
                sortProducts.add(pc);
        if(sortProducts.get(0) == null)
            throw new ProductNotFoundedException("Продукт не найден");
        return sortProducts;
    }



}
