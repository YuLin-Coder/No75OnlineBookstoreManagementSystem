package edu.hut.bookshop.service;

import java.util.List;

import edu.hut.bookshop.pojo.User;

public interface UserService {
	/**
	 * 通过用户ID删除用户获得书籍
	 * @param userId
	 */
    int deleteByUserId(Integer userId);
    /**
	 * 增加用户
	 * @param record
	 */
    int insert(User record);
    /**
	 * 通过用户书籍ID获得用户书籍
	 * @param  userId
	 */
    User selectByUserId(Integer userId);
    /**
	 * 通过用户ID更新用户
	 * @param record
	 */
    int updateByUserId(User record);
    /**
	 * 打印全部用户
	 */
    List<User> selectAll(Integer page,Integer limit);


	/**
	 * 多条件查询用户
	 * @param user
	 * @param page
	 * @param limit
	 * @return
	 */
	List<User> searchUsers(User user, Integer page, Integer limit);

}
