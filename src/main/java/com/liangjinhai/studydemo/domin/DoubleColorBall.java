package com.liangjinhai.studydemo.domin;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Jin
 * @Title: DoubleColorBall
 * @date 19-1-19下午11:00
 */
@Data
@Entity
@Table(name = "double_color_ball")
public class DoubleColorBall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String time;

    private String numbers;

    private String result;

    private Double probability;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
