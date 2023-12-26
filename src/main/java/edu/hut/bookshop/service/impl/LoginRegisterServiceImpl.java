package edu.hut.bookshop.service.impl;

import edu.hut.bookshop.dao.UserMapper;
import edu.hut.bookshop.exception.CustomizeException;
import edu.hut.bookshop.pojo.Admin;
import edu.hut.bookshop.pojo.User;
import edu.hut.bookshop.service.LoginRegisterService;
import edu.hut.bookshop.util.ResultCode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @Description: 登录注册模块实现类
 */
@Service
public class LoginRegisterServiceImpl implements LoginRegisterService {

    @Resource
    private UserMapper userMapper;

    /**
     * 用户登录处理
     * @param record
     * @param session
     */
    @Override
    public void userLogin(User record, HttpSession session) {
        User user=userMapper.selectByUserName(record.getUserName());
        if(user==null){
            //用户不存在
            throw new CustomizeException(ResultCode.USER_NOT_FOUND);
        }
        if(!user.getPassword().equals(record.getPassword())){
            //密码错误
            throw new CustomizeException(ResultCode.PASSWORD_ERROR);
        }
        session.setAttribute("user",user);
    }

    /**
     * 用户注册处理
     * @param record
     */
    @Override
    public void userRegister(User record) {
        User user=userMapper.selectByUserName(record.getUserName());
        if(user!=null){
            throw new CustomizeException(ResultCode.FAILED,"用户名已存在");
        }
        userMapper.insert(record);
    }

    @Override
    public void adminLogin(Admin admin, HttpSession session) {

    }
}
