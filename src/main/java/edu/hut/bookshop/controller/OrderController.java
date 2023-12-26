package edu.hut.bookshop.controller;

import edu.hut.bookshop.pojo.Order;
import edu.hut.bookshop.pojo.User;
import edu.hut.bookshop.service.OrderHandleService;
import edu.hut.bookshop.service.OrderService;
import edu.hut.bookshop.util.ResultCode;
import edu.hut.bookshop.util.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @Description: 订单管理模块
 */
@RestController
@RequestMapping("/order")
public class OrderController {
     @Autowired
     private OrderService orderService;

    @Autowired
    private OrderHandleService orderHandleService;

    /**
     * 分页获取订单列表，如果都为空，则获取所有
     * @param page    页码
     * @param limit   每页的条数
     * @return
     */
    @GetMapping("/list")
    public ResultVO getOrderList(@RequestParam(required = false) Integer page,@RequestParam(required = false) Integer limit) {
        List<Order> orders = orderHandleService.getAllOrdersByPage(page, limit);
        PageInfo pageInfo = new PageInfo(orders);
        return new ResultVO(ResultCode.SUCCESS,(int)pageInfo.getTotal(),orders);
    }

    /**
     * 用户提交订单处理
     * @param order
     * @param session
     * @return
     */
    @PostMapping("/submit")
    public ResultVO orderSubmit(@RequestBody @Valid Order order, HttpSession session) {
        User user = (User) session.getAttribute("user");
        order.setUserId(user.getUserId());
        orderHandleService.createOrder(order);
        return new ResultVO(ResultCode.SUCCESS,"/"+user.getUserName()+"/orders");
    }

    /**
     * 更新订单信息，比如审核通过
     * @param order
     * @return
     */
    @PutMapping("/list/{orderId}")
    public ResultVO updateOrder(@PathVariable("orderId") Integer orderId,@RequestBody Order order) {
           Order orders1 = orderService.selectByOrderId(orderId);
           if(orders1!=null)
           {
        	order.setOrderId(orderId);
        	int orders = orderService.updateByOrderId(order);
        	return new ResultVO(ResultCode.SUCCESS,null);
           }
           else
        	return new ResultVO(ResultCode.RECORD_NOT_FOUND,null);
    }

    /**
     * 删除指定orderId的订单
     * @param orderId
     * @return
     */
    @DeleteMapping("/list/{orderId}")
    public ResultVO deleteOrder(@PathVariable("orderId") Integer orderId) {
    	int orders = orderService.deleteByOrderId(orderId);
        return new ResultVO(ResultCode.SUCCESS,null);
    }

   /* @GetMapping("/list/user_order/{userId}")
    public ResultVO getOrderByUserId(@PathVariable("userId") Integer userId) {

    	List<Order> orders = orderService.selectByUserId(userId);
    	if(orders.size()!=0)
    	{
    		return new ResultVO(ResultCode.SUCCESS,orders);
    	}
        else
        	return new ResultVO(ResultCode.RECORD_NOT_FOUND,null);
    }*/


    @GetMapping("/list/{orderId}")
        public ResultVO getOrderByOrderId(@PathVariable("orderId") Integer orderId) {
    	Order orders = orderService.selectByOrderId(orderId);
    	if(orders!=null)
        return new ResultVO(ResultCode.SUCCESS,orders);
    	else
    	return new ResultVO(ResultCode.RECORD_NOT_FOUND,null);
    }

    /**
     * 多条件搜索订单 ---- by guozongchao
     * @param order
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/search")
    public ResultVO searchOrders(Order order, Integer page, Integer limit) {
        if (order.getConsigneeName().isEmpty()) {
            order.setConsigneeName(null);
        }
        List<Order> orders = orderService.searchOrders(order, page, limit);
        PageInfo pageInfo = new PageInfo(orders);
        return new ResultVO(ResultCode.SUCCESS, (int) pageInfo.getTotal(), orders);
    }

}
