package xby.service;

import xby.domain.Order;

import java.util.List;

public interface IOrderService {
    List<Order> findAll(int page,int size);
    Order findById(String id);
}
