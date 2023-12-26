package edu.hut.bookshop.service;

import edu.hut.bookshop.pojo.Order;
import edu.hut.bookshop.pojo.OrderItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: guozongchao
 * @Date: 2020/6/5 1:31
 */
@SpringBootTest
public class OrderHandleServiceTest {
    @Autowired
    private OrderHandleService orderHandleService;

    @Test
    @Transactional
    public void testCreateOrder() {
        Order order = new Order();
        order.setUserId(2020004);
        order.setAddress("广东省");
        order.setConsigneeName("张dsf三");
        order.setZip("124446");
        order.setPhoneNumber("1fd5698745");
        order.setOrderItems(new ArrayList<>());

        for (int i = 1; i < 5; i++) {
            OrderItem orderItem=new OrderItem();
            orderItem.setBookId(i);
            orderItem.setPrice(new BigDecimal(115.90));
            orderItem.setQuantity(10*i);
            order.getOrderItems().add(orderItem);
        }
        System.out.println(order);
        orderHandleService.createOrder(order);
    }


    @Test
    public void testGetAllOrdersByPage() {
        List<Order> orders = orderHandleService.getAllOrdersByPage(1, 3);
        orders.stream().forEach(order -> {
            System.out.println(order);
            order.getOrderItems().stream().forEach(orderItem -> {
                System.out.println(orderItem);
            });
        });
    }

    @Test
    public void testGetOrdersByUserId() {
        List<Order> orders = orderHandleService.getOrdersByUserId(2020003,1, 3);
        orders.stream().forEach(order -> {
            System.out.println(order);
            order.getOrderItems().stream().forEach(orderItem -> {
                System.out.println(orderItem);
            });
        });
    }

    @Test
    @Transactional
    public void testDeleteOrderById() {
        orderHandleService.deleteOrderById(9);
    }

}
