package com.example.bandShop.service;


import com.example.bandShop.entity.AdminEntity;
import com.example.bandShop.entity.OrderEntity;
import com.example.bandShop.entity.ShopEntity;
import com.example.bandShop.exception.OrdersExcistException;
import com.example.bandShop.exception.ShopAlredyExistException;
import com.example.bandShop.exception.ShopNotFoundedException;
import com.example.bandShop.exception.UserNotFoundException;
import com.example.bandShop.model.Admin;
import com.example.bandShop.model.Order;
import com.example.bandShop.model.Shop;
import com.example.bandShop.model.User;
import com.example.bandShop.repository.AdminRepo;
import com.example.bandShop.repository.ShopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopService {

    @Autowired
    private ShopRepo shopRepo;
    @Autowired
    private AdminRepo adminRepo;

    public ShopEntity create(ShopEntity shop) throws ShopAlredyExistException {
        if (shopRepo.existsById(shop.getId())){
            throw new ShopAlredyExistException("Магазин уже существует");
        }
        shop.setOrders(new ArrayList<>());
        return shopRepo.save(shop);
    }

    public Shop delete(Integer id) throws ShopNotFoundedException {
        if(!shopRepo.existsById(id))
            throw  new ShopNotFoundedException("Магазин не найден");
        shopRepo.deleteById(id);
        return new Shop();
    }

    public List<Order> getOrders(int shop_id) throws ShopNotFoundedException {
        if (!shopRepo.existsById(shop_id)){
            throw new ShopNotFoundedException("Магазин не найден");
        }
        ShopEntity shop = shopRepo.findById(shop_id).get();
        List<Order> orders = new ArrayList<>();
        for(OrderEntity oe : shop.getOrders())
            orders.add(Order.toModel(oe));
        return orders;
    }

    public Shop getOne(int id) throws ShopNotFoundedException {
        if (!shopRepo.existsById(id)){
            throw new ShopNotFoundedException("Магазин не найден");
        }
        return Shop.toModel(shopRepo.findById(id).get());
    }

    public List<Shop> getShops() {
        List<ShopEntity> shopEntities = (List<ShopEntity>) shopRepo.findAll();
        List<Shop> shops = new ArrayList<>();
        for(ShopEntity se : shopEntities){
            shops.add(Shop.toModel(se));
        }
        return shops;
    }

    public Shop updateShop (ShopEntity shop) throws ShopNotFoundedException, OrdersExcistException {
        if(shopRepo.existsById(shop.getId())){
            if(!shopRepo.findById(shop.getId()).get().getOrders().isEmpty())
                throw new OrdersExcistException("Нельзя изменить магазин пока не выполнены все заказы");
            shopRepo.save(shop);
            return Shop.toModel(shop);
        }
        throw new ShopNotFoundedException("Магазин не найден");
    }

    public List<Shop> getFree() {
        List<ShopEntity> shopEntities = (List<ShopEntity>) shopRepo.findAll();
        List<Shop> shops = new ArrayList<>();
        for(ShopEntity se : shopEntities){
            if(se.getAdmin() == null)
                shops.add(Shop.toModel(se));
        }
        return shops;
    }

}
