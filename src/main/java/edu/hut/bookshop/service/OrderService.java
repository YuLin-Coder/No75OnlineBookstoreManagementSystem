package edu.hut.bookshop.service;

import java.util.List;

import edu.hut.bookshop.pojo.Order;

public interface OrderService {
    int deleteByOrderId(Integer orderId);

    int insert(Order record);

    Order selectByOrderId(Integer orderId);
    
    int updateByOrderId(Order record);

    List<Order> selectAll();

    List<Order> selectByUserId(Integer userId);
    
   // int orderIsExits(Integer orderId);

    /**
     * 多条件搜索订单
     * @param order
     * @param page
     * @param limit
     * @return
     */
    List<Order> searchOrders(Order order,Integer page,Integer limit);
}
