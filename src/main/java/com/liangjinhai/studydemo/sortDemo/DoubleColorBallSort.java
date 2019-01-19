package com.liangjinhai.studydemo.sortDemo;

import com.liangjinhai.studydemo.common.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jin
 * @Title: DoubleColorBallSort(双色球)
 * @date 19-1-19下午10:11
 */
public class DoubleColorBallSort {

    public static List<String> getNumber() {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            List<Integer> numbers = new ArrayList<>();
            for (int j = 0; j < Integer.MAX_VALUE; j++) {
                Integer blue = (int)(33*Math.random()+1);
                if(!numbers.contains(blue)){
                    numbers.add(blue);
                    if(numbers.size() == 6){
                        break;
                    }
                }
            }
            Integer red = (int)(33*Math.random()+1);
            numbers.add(red);
            result.add(JsonUtil.object2Json(numbers));
        }
        return result;
    }

}
