package edu.hut.bookshop.interceptor;

import edu.hut.bookshop.exception.CustomizeException;
import edu.hut.bookshop.pojo.Admin;
import edu.hut.bookshop.util.ResultCode;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Description: 管理员访问拦截器
 */
public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute("admin")==null) {
            //用户未登录,抛出异常
            throw new CustomizeException(ResultCode.USER_NOT_LOGGED_IN);
        }
        Admin admin = (Admin) session.getAttribute("admin");
        //已登录，放行请求
        return true;
    }
}
