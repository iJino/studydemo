package com.liangjinhai.studydemo.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: liangJinHai
 * @Date: 2019/1/16 11:02
 * @Description: 自定义http类，可以修改请求参数
 */
public class MyHttpServletRequestWrapper extends HttpServletRequestWrapper {

    // 用于存储请求参数
    private Map<String , String[]> params = new HashMap<String, String[]>();

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public MyHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        this.params.putAll(request.getParameterMap());
    }

    /**
     * 添加参数到map中
     * @param extraParams
     */
    public void setParameters(Map<String, Object> extraParams) {
        for (Map.Entry<String, Object> entry : extraParams.entrySet()) {
            setParameter(entry.getKey(), entry.getValue());
        }
    }

    /**
     *添加参数到map中
     * @param name
     * @param value
     */
    public void setParameter(String name, Object value) {
        if (value != null) {
            System.out.println(value);
            if (value instanceof String[]) {
                params.put(name, (String[]) value);
            } else if (value instanceof String) {
                params.put(name, new String[]{(String) value});
            } else {
                params.put(name, new String[]{String.valueOf(value)});
            }
        }
    }

    /**
     * 重写getParameter，代表参数从当前类中的map获取
     * @param name
     * @return
     */
    @Override
    public String getParameter(String name) { String[]values = params.get(name); if(values == null || values.length == 0) { return null; } return values[0]; }
    /**
     * 重写getParameterValues方法，从当前类的 map中取值
     * @param name
     * @return
     */
    @Override public String[] getParameterValues(String name) { return params.get(name); }
}
