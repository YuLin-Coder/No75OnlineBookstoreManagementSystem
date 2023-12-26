package edu.hut.bookshop.service;

import edu.hut.bookshop.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:
 * @Author: guozongchao
 * @Date: 2020/6/4 11:16
 */
@SpringBootTest
public class LoginRegisterServiceTest {
    @Autowired
    private LoginRegisterService loginRegisterService;

    @Test
    public void testUserRegister(){
       /* User user=new User();
        user.setUserName("张三");
        user.setEmail("sdsjdnf@qq,com");
        user.setPassword("123456");
        loginRegisterService.userRegister(user);*/
    }

    @Test
    @Transactional
    public void testUserRegister1(){
        User user=new User();
        user.setUserName("李四");
        user.setEmail("sdsjdnf@qq,com");
        user.setPassword("123456");
        loginRegisterService.userRegister(user);
    }

}
