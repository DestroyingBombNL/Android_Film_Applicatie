package com.example.bioscoopapplicatie.domain;

import java.util.ArrayList;
import java.util.List;

public class DataOrder {
    public static List<Order> getOrderList(){
        List<Order> OrderList = new ArrayList<>();

        Order noOrder = new Order();
        noOrder.setName("No order");
        OrderList.add(noOrder);

        Order rating = new Order();
        rating.setName("Rating");
        OrderList.add(rating);

        Order genre = new Order();
        genre.setName("Genre");
        OrderList.add(genre);

        return OrderList;
    }
}
