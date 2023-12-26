package edu.hut.bookshop.controller;

import edu.hut.bookshop.exception.CustomizeException;
import edu.hut.bookshop.pojo.Admin;
import edu.hut.bookshop.pojo.User;
import edu.hut.bookshop.service.LoginRegisterService;
import edu.hut.bookshop.util.ResultCode;
import edu.hut.bookshop.util.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @Description: 登录注册模块的控制器
 */

@Controller
public class LoginRegisterController {

    @Autowired
    private LoginRegisterService loginRegisterService;

    /**
     * 用户登录请求处理
     * @param user
     * @return
     */
    @PostMapping("/user/login")
    @ResponseBody
    public ResultVO userLoginHandler(@RequestBody @Valid User user, HttpSession session) {
        loginRegisterService.userLogin(user,session);
        return new ResultVO(ResultCode.SUCCESS,"/");
    }

    /**
     * 用户注册请求处理
     * @param user
     * @return
     */
    @PostMapping("/user/register")
    @ResponseBody
    public ResultVO userRegisterHandler(@RequestBody @Valid User user) {
        loginRegisterService.userRegister(user);
        return new ResultVO(ResultCode.SUCCESS,"/login");
    }

    /**
     * 管理员登录请求处理
     * @param admin
     * @return
     */
    @PostMapping("/admin/login")
    @ResponseBody
    public ResultVO adminLoginHandler(@RequestBody Admin admin, HttpSession session) {
        if(!("admin".equals(admin.getAdminName())&& "123456".equals(admin.getPassword()))){
            throw new CustomizeException(ResultCode.FAILED,"管理员账户或密码错误");
        }
        session.setAttribute("admin",admin);
        return new ResultVO(ResultCode.SUCCESS,"/admin/book_manage");
    }

    /**
     * 用户退出
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String userLogout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/login";
    }

    /**
     * 后台管理退出
     * @param session
     * @return
     */
    @GetMapping("/admin/logout")
    public String adminLogout(HttpSession session){
        session.removeAttribute("admin");
        return "redirect:/";
    }

    @GetMapping("/checkLoggedIn")
    @ResponseBody
    public ResultVO checkUserIsLoggedIn(HttpSession session) {
        if (session.getAttribute("user") == null) {
            throw new CustomizeException(ResultCode.USER_NOT_LOGGED_IN);
        }
        return new ResultVO(ResultCode.SUCCESS);
    }
}
