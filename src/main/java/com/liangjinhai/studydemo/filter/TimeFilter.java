package com.liangjinhai.studydemo.filter;

import com.liangjinhai.studydemo.common.util.MyHttpServletRequestWrapper;
import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

public class TimeFilter implements Filter {

    private static final FastDateFormat yMdHms = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("filter start on:" + yMdHms.format(new Date()));
        String url = ((HttpServletRequest) servletRequest).getRequestURI().substring(((HttpServletRequest)servletRequest).getContextPath().length());
        System.out.println(url);
        MyHttpServletRequestWrapper requestWrapper = new MyHttpServletRequestWrapper(
                (HttpServletRequest) servletRequest);
        String name = requestWrapper.getParameter("name");
        System.out.println("sb's name: "+name);
        Long start = System.currentTimeMillis();
        Map<String, String[]> args = requestWrapper.getParameterMap();
        for(Map.Entry<String, String[]> entry : args.entrySet()){
            System.out.println(entry.getKey()+"-----"+entry.getValue());
            for(String val:entry.getValue()){
                if(val.equals("卢镇安")){
                    requestWrapper.setParameter(entry.getKey(),"sb卢镇安");
                }
                if(val.contains("sb")){
                    requestWrapper.setParameter(entry.getKey(),val.replace("sb","很棒棒哦"));
                }
            }
        }
        filterChain.doFilter(requestWrapper,servletResponse);
        System.out.println("filter 耗时："+(System.currentTimeMillis() - start)/1000.0);
        System.out.println("filter finish on" + yMdHms.format(new Date()) );
    }

    @Override
    public void destroy() {
        System.out.println("filter destroy");
    }
}
