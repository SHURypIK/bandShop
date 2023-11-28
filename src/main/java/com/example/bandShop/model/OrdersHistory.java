package com.example.bandShop.model;


import com.example.bandShop.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class OrdersHistory {

    private List<Order> orderHistory;

    public static OrdersHistory toModel(UserEntity entity){
        OrdersHistory model = new OrdersHistory();
        model.setOrderHistory(entity.getOrderHistory().stream().map(Order :: toModel).collect(Collectors.toList()));
        return model;
    }

    public OrdersHistory() {
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(List<Order> orderHistory) {
        this.orderHistory = orderHistory;
    }
}
