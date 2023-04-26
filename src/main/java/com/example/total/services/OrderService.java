package com.example.total.services;

import com.example.total.models.Order;
import com.example.total.models.Person;
import com.example.total.models.Product;
import com.example.total.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private Order order;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    public List<Order> showAllOrders(){
        return orderRepository.findAll();
    }

  public Order getOrder(){
        return this.order;
  }

public Order getOrderById(int id){
        Optional<Order> optionalOrder = orderRepository.findOrderById(id);
        return optionalOrder.orElse(null);
}
public Order findByNumber(String number){
        Optional<Order> optionalOrder = orderRepository.findByNumber(number);
        return optionalOrder.orElse(null);
        }

    @Transactional
    public void updateOrderStatus(String number, Order order){
        Optional<Order> optionalOrder = orderRepository.findByNumber(number);
        order.setNumber(number);
        orderRepository.save(order);
    }

    public List<Order> findByNumberEndingWithIgnoreCase(String endingWith){
   return orderRepository.findByNumberEndingWithIgnoreCase(endingWith);
    }
}

