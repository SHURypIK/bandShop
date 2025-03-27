package com.example.bandShop.service;


import com.example.bandShop.entity.AdminEntity;
import com.example.bandShop.entity.OrderEntity;
import com.example.bandShop.entity.ShopEntity;
import com.example.bandShop.exception.*;
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

    public Shop delete(Integer id) throws ShopNotFoundedException, OrdersNotComplitedEception {
        if(!shopRepo.existsById(id))
            throw  new ShopNotFoundedException("Магазин не найден");
        if(!shopRepo.findById(id).get().getOrders().isEmpty())
            throw new OrdersNotComplitedEception("Заказы не выполнены");
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
            if(!oe.getComplitied().equals("готов"))
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
            shop.setAdmin(shopRepo.findById(shop.getId()).get().getAdmin());
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
