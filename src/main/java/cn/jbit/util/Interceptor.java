package cn.jbit.util;

import cn.jbit.entity.GameCardUser;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * 拦截器
 */
public class Interceptor extends HandlerInterceptorAdapter {

    /**
     * 拦截方法，放过登录
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler)throws  Exception {
        GameCardUser user = (GameCardUser) request.getSession().getAttribute("user");
        boolean flg = false;
        String uri = request.getRequestURI();
        if(user!=null){
            flg=true;
        }else if (uri.length() > 9) {
            flg = "/resultJsp".equals(uri.substring(0, 10)) || "/images/jb.jpg".equals(uri) || "/static".equals(uri.substring(0, 7)) ? true : false;
        } else if (uri.length() > 5) {
            flg = "/index".equals(uri) || "/static".equals(uri.substring(0, 7)) ? true : false;
            System.out.println(flg);
        } else if (uri.length() > 0) {
            flg = "/".equals(uri) ? true : false;
        }
        if (flg == false && null==user) {
            if (uri.length() > 10) {
                int ret = "/toPayment".equals(uri.substring(0, 10)) ? 1 : 2;
                Browse.operation = ret;
                System.out.println(ret);
            }
            request.getSession().setAttribute("e", "你没有权限访问该页面！请登录后再访问！");
            //在这之前进行一个判断，看用户是否要进买东西得操作byCard
            // 浏览某件具体商品tocardView再后台去处理
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
        return flg;
    }
}
