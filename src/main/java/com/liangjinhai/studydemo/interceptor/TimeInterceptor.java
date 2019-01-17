package com.liangjinhai.studydemo.interceptor;

import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * 如果要进行拦截器请求或重定向要在preHandle中将request或response进行处理
 */
//@Component
public class TimeInterceptor implements HandlerInterceptor {

    private static final FastDateFormat yMdHms = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Long start = System.currentTimeMillis();
        System.out.println(((HandlerMethod) handler).getBean().getClass().getName());
        System.out.println(((HandlerMethod) handler).getMethod().getName());
        System.out.println("进入到Interceptor中，时间:"+yMdHms.format(new Date()));
        Map<String, String[]> args = request.getParameterMap();
        request.setAttribute("start",start);
        for(Map.Entry<String, String[]> entry : args.entrySet()){
            for(String val :entry.getValue()){
                if(val.contains("sb")){
                    System.out.println("含有非法字段");//过滤发现含有非法字段过滤
                    request.getRequestDispatcher("/test2").forward(request,response);
//                    response.sendRedirect("/test2");
                    return true;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Map<String, String[]> args = request.getParameterMap();
        for(Map.Entry<String, String[]> entry : args.entrySet()){
            for(String val :entry.getValue()){
                if(val.contains("sb")){
                    System.out.println("含有非法字段*******");
                }
            }
        }
        System.out.println("拦截中处理");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Long startTime = (Long) request.getAttribute("start");
        System.out.println("startTime:" + startTime);
        Long time = (System.currentTimeMillis() - startTime);
        System.out.println(time);
        System.out.println("拦截结束，时间:"+yMdHms.format(new Date())+" ,耗时："+((System.currentTimeMillis() - startTime)/1000.00));
    }
}
