package edu.hut.bookshop.service;

import edu.hut.bookshop.pojo.Order;

import java.util.List;

/**
 * @Description: 订单处理模块业务接口
 */
public interface OrderHandleService {

    /**
     * 创建订单
     * @param order
     */
    void createOrder(Order order);

    /**
     * 根据ID删除订单
     * @param orderId
     * @return
     */
    int deleteOrderById(Integer orderId);


    /**
     * 根据用户ID分页获取用户订单
     * @param userId
     * @return
     */
   List<Order> getOrdersByUserId(Integer userId,Integer page ,Integer limit);

    /**
     * 通过分页获取所有订单
     * @param page
     * @param limit
     * @return
     */
    List<Order> getAllOrdersByPage(Integer page, Integer limit);

}
