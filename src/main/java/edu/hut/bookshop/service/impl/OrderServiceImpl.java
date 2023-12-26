package edu.hut.bookshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import edu.hut.bookshop.dao.OrderMapper;
import edu.hut.bookshop.pojo.Order;
import edu.hut.bookshop.service.OrderService;
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;
	@Override
	public int deleteByOrderId(Integer orderId) {
		// TODO Auto-generated method stub
		int orders = orderMapper.deleteByOrderId(orderId);
		return 0;
	}

	@Override
	@Deprecated
	public int insert(Order record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Order selectByOrderId(Integer orderId) {
		// TODO Auto-generated method stub
		Order orders = orderMapper.selectByOrderId(orderId);
		if(orders!=null)
		return orders;
		else
		return null;
	}

	@Override
	public int updateByOrderId(Order record) {
		// TODO Auto-generated method stub
		int orders = orderMapper.updateByOrderId(record);
		return orders;
	}

	@Override
	public List<Order> selectAll() {
		// TODO Auto-generated method stub
		List<Order> orders = orderMapper.selectAll(); 
		return orders;
	}

	@Override
	public List<Order> selectByUserId(Integer userId) {
		// TODO Auto-generated method stub
		List<Order> orders = orderMapper.selectByUserId(userId);
		return orders;
	}
	/*@Override
	public int orderIsExits(Integer orderId)
	{
		Order order = orderMapper.selectByOrderId(orderId);
		if(order!=null) 
		return 1;
		else
			return 0;
	}*/


	/**
	 * 多条件搜索订单 
	 *
	 * @param order
	 * @param page
	 * @param limit
	 * @return
	 */
	@Override
	public List<Order> searchOrders(Order order, Integer page, Integer limit) {
		PageHelper.startPage(page, limit);
		List<Order> orders = orderMapper.searchOrders(order);
		return orders;
	}
}
