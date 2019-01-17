package com.liangjinhai.studydemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudydemoApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void test1(){
        q:
        for(int i = 0;i<5;i++){
            for(int j=0;j<5;j++){
                if(j==3){
                    break q;
                }
                System.out.println(+i+"-"+j);
            }
            System.out.println(""+i);
        }
    }
}

