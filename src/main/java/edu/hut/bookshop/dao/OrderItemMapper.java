package edu.hut.bookshop.dao;

import edu.hut.bookshop.pojo.OrderItem;

import java.util.List;

public interface OrderItemMapper {

    int insert(OrderItem record);

    List<OrderItem> selectByOrderId(Integer orderId);

    int updateByOrderIdAndBookId(OrderItem record);
}