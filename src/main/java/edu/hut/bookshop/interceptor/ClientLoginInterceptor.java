package edu.hut.bookshop.interceptor;

import edu.hut.bookshop.exception.CustomizeException;
import edu.hut.bookshop.util.ResultCode;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Description: 登录验证拦截器
 */
public class ClientLoginInterceptor implements HandlerInterceptor {
    /**
     * 拦截请求判断是否已经登录
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute("user")==null) {
            //用户未登录,抛出异常
            throw new CustomizeException(ResultCode.USER_NOT_LOGGED_IN);
        }
        //已登录，放行请求
        return true;
    }
}
