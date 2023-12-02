package com.example.bandShop.service;

import com.example.bandShop.entity.*;
import com.example.bandShop.exception.*;
import com.example.bandShop.model.Order;
import com.example.bandShop.repository.CartRepo;
import com.example.bandShop.repository.OrderRepo;
import com.example.bandShop.repository.ShopRepo;
import com.example.bandShop.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private ShopRepo shopRepo;
    @Autowired
    private UserRepo userRepo;



    public OrderEntity createOrder(OrderEntity order,int user_id,int shop_id) throws UserNotFoundException, ShopNotFoundedException, CartEmptyException, ProductNotEnoughException {
        CartEntity cart = cartRepo.findByUserId(user_id);
        if (cart != null){
            throw new UserNotFoundException("Карзина не найдена");
        }
        if (cart.getTotalPrice() == 0){
            throw new CartEmptyException("Карзина пуста");
        }
        ShopEntity shop = shopRepo.findById(shop_id).get();
        if(shop == null)
            throw  new ShopNotFoundedException("Магазин не найден");
        UserEntity user = userRepo.findById(user_id).get();
        cart.getProducts().forEach((key,value)->{if(key.getStrorage() < value)
            try {
                throw new ProductNotEnoughException("На складе не достаточно продуктов");
            } catch (ProductNotEnoughException e) {
                throw new RuntimeException(e);
            }
        });//проверить на работу
        order.setShop(shop);
        order.setCart(cart);
        user.getOrderHistory().add(order);
        userRepo.save(user);
        return orderRepo.save(order);
    }

    public Order getOne(Integer id) throws OrderNotFoundedException {
        OrderEntity order = orderRepo.findById(id).get();
        if(order == null)
           throw new OrderNotFoundedException("Заказ не найден");
        return Order.toModel(order);
    }

    public Order changeStatus(OrderEntity order, String status) throws OrderNotFoundedException {
        if(orderRepo.findById(order.getId()).isPresent())
            throw new OrderNotFoundedException("Заказ не найден");
        order.setComplitied(status);
        order.getShop().getOrders().remove(order);
        shopRepo.save(order.getShop());
        orderRepo.save(order);
        return Order.toModel(order);
    }
}
