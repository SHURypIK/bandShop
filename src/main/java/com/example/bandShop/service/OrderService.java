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
    @Autowired
    private CartServise cartServise;



    public Order createOrder(OrderEntity order,int user_id,int shop_id) throws UserNotFoundException, ShopNotFoundedException, CartEmptyException, ProductNotEnoughException {
        CartEntity cart = cartRepo.findByUserId(user_id);
        if (cart == null){
            throw new UserNotFoundException("Карзина не найдена");
        }
        if (cart.getTotalPrice() == 0){
            throw new CartEmptyException("Карзина пуста");
        }
        ShopEntity shop = shopRepo.findById(shop_id).get();
        if(shop == null)
            throw  new ShopNotFoundedException("Магазин не найден");
        UserEntity user = userRepo.findById(user_id).get();
        int i;
        for(i = 0; i < cart.getPrducts().size(); i++){
            if(cart.getPrducts().get(i).getStrorage() < cart.getAmounts().get(i))
                throw new ProductNotEnoughException("На складе не достаточно продуктов");
        }
        for(i = 0; i < cart.getPrducts().size(); i++)
            cart.getPrducts().get(i).setStrorage(cart.getPrducts().get(i).getStrorage()- cart.getAmounts().get(i));
        order.setShop(shop);
        order.setUserEmail(user.getEmail());
        order.getPrducts().addAll(cart.getPrducts());
        order.getAmounts().addAll(cart.getAmounts());
        user.getOrderHistory().add(order);
        userRepo.save(user);
        cartServise.cleanCart(user_id);
        cartRepo.save(cart);
        orderRepo.save(order);
        return Order.toModel(order);
    }

    public Order getOne(Integer id) throws OrderNotFoundedException {
        if(!orderRepo.existsById(id))
           throw new OrderNotFoundedException("Заказ не найден");
        return Order.toModel(orderRepo.findById(id).get());
    }

    public Order changeStatus(int id) throws OrderNotFoundedException {
        if(!orderRepo.existsById(id))
            throw new OrderNotFoundedException("Заказ не найден");
        OrderEntity ordere = orderRepo.findById(id).get();
        ordere.setComplitied("готов");
        ordere.getShop().getOrders().remove(ordere);
        shopRepo.save(ordere.getShop());
        orderRepo.save(ordere);
        return Order.toModel(ordere);
    }
}
