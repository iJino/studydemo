package com.liangjinhai.studydemo.domin;

import java.util.Date;

/**
 * @author Jin
 * @Title: DoubleColorBall
 * @date 19-1-19下午11:00
 */
public class DoubleColorBall {

    private Integer id;

    private String time;

    private String numbers;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
