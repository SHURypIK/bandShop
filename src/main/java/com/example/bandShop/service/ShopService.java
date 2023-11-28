package com.example.bandShop.service;


import com.example.bandShop.entity.OrderEntity;
import com.example.bandShop.entity.ShopEntity;
import com.example.bandShop.exception.ShopAlredyExistException;
import com.example.bandShop.exception.ShopNotFoundedException;
import com.example.bandShop.model.Order;
import com.example.bandShop.model.Shop;
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

    public ShopEntity create(ShopEntity shop, int admin_id) throws ShopAlredyExistException {
        ShopEntity shope = shopRepo.findById(shop.getId()).get();
        if (shop != null){
            throw new ShopAlredyExistException("Магазин уже существует");
        }
        shope.setOrders(new ArrayList<>());
        shope.setAdmin(adminRepo.findById(admin_id).get());
        adminRepo.findById(admin_id).get().setShop(shope);
        adminRepo.save(adminRepo.findById(admin_id).get());
        return shopRepo.save(shope);
    }

    public Integer delete(Integer id)  {
        shopRepo.deleteById(id);
        return id;
    }

    public List<Order> getOrders(int shop_id) throws ShopNotFoundedException {
        ShopEntity shop = shopRepo.findById(shop_id).get();
        if (shop == null){
            throw new ShopNotFoundedException("Магазин не найден");
        }
        List<Order> orders = new ArrayList<>();
        for(OrderEntity oe : shop.getOrders())
            orders.add(Order.toModel(oe));
        return orders;
    }

    public Shop getOne(int admin_id) throws ShopNotFoundedException {
        ShopEntity shop = shopRepo.findByAdminId(admin_id);
        if (shop == null){
            throw new ShopNotFoundedException("Магазин не найден");
        }
        return Shop.toModel(shop);
    }

    public List<Shop> getShops() {
        List<ShopEntity> shopEntities = (List<ShopEntity>) shopRepo.findAll();
        List<Shop> shops = new ArrayList<>();
        for(ShopEntity se : shopEntities){
            shops.add(Shop.toModel(se));
        }
        return shops;
    }

    public Shop updateShop (ShopEntity shop) throws ShopNotFoundedException {
        if(!shopRepo.findById(shop.getId()).isPresent()){
            shopRepo.save(shop);
            return Shop.toModel(shop);
        }
        throw new ShopNotFoundedException("Магазин не найден");
    }
}
