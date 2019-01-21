package com.liangjinhai.studydemo.Lottery;

/**
 * @author Jin
 * @Title: JhUtil (聚合数据api url)
 * @date 19-1-21下午8:03
 */
public class JhUtil {

    public class lottery {

        public static final String KEY = "0146450fc69712b60f71e44770c03c87";
        /*获取彩票类型*/
        public static final String TYPES = "http://apis.juhe.cn/lottery/types";
        /*开奖情况*/
        public static final String QUERY = "http://apis.juhe.cn/lottery/query";

        public static final String HISTORY = "http://apis.juhe.cn/lottery/history";

        public static final String BONUS = "http://apis.juhe.cn/lottery/bonus";

    }

    public class constellation {

        public static final String KEY = "0dcff0da52ae9e494ec0f492716ea973";

        public static final String QUERY = "http://web.juhe.cn:8080/constellation/getAll";

    }
}
