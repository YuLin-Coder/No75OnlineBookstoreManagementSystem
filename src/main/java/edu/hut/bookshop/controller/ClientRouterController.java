package edu.hut.bookshop.controller;

import edu.hut.bookshop.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

/**
 * @Description: 客户端路由控制器
 */
@Controller
public class ClientRouterController {

    //跳转首页
    @GetMapping({"/","/index"})
    public String toHomePage(){
        return "index";
    }

    //跳转登录页面
    @GetMapping("/login")
    public String toLogin(HttpSession session){
        if(session.getAttribute("user")!=null){
            return "redirect:/";
        }
        return "login";
    }

    //跳转注册页面
    @GetMapping("/register")
    public String toRegister(){
        return "register";
    }

    //跳转个人订单页面
    @GetMapping("/{userName}/orders")
    public String toOrderCenter(@PathVariable("userName") String userName,HttpSession session){
        User user= (User) session.getAttribute("user");
        if(user!=null && user.getUserName().equals(userName)){
            return "user_orders";
        }
        return "redirect:/login";
    }

    //跳转购物车页面
    @GetMapping("/{userName}/shopping_cart")
    public String toUserShoppingCart(@PathVariable("userName") String userName,HttpSession session){
        User user= (User) session.getAttribute("user");
        if(user!=null && user.getUserName().equals(userName)){
            return "shopping_cart";
        }
        return "redirect:/login";
    }


}
