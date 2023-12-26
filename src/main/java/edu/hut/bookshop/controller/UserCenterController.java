package edu.hut.bookshop.controller;

import com.github.pagehelper.PageInfo;
import edu.hut.bookshop.pojo.Order;
import edu.hut.bookshop.pojo.User;
import edu.hut.bookshop.service.OrderHandleService;
import edu.hut.bookshop.util.ResultCode;
import edu.hut.bookshop.util.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * @Description: 用户中心模块
 */
@Controller
@RequestMapping("/user_center")
public class UserCenterController {

    @Autowired
    private OrderHandleService orderHandleService;



    /**
     * 根据用户ID,获取用户订单
     * @return
     */
    @GetMapping("/orders")
    @ResponseBody
    public ResultVO getUserOrders(Integer page,Integer limit,HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Order> orders = orderHandleService.getOrdersByUserId(user.getUserId(), page, limit);
        PageInfo pageInfo = new PageInfo(orders);
        return new ResultVO(ResultCode.SUCCESS, (int)pageInfo.getTotal(),orders);
    }

    /**
     * 删除指定orderId的订单
     * @param orderId
     * @return
     */
    @DeleteMapping("/orders/{orderId}")
    @ResponseBody
    public ResultVO deleteOrder(@PathVariable("orderId") Integer orderId) {
        int orders = orderHandleService.deleteOrderById(orderId);
        return new ResultVO(ResultCode.SUCCESS,null);
    }

}
