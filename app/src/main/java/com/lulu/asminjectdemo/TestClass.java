package com.lulu.asminjectdemo;

/**
 * @author zhanglulu on 2020/5/25.
 * for
 */
public class TestClass {
    public void sample1(int a){
        int num = 5;
        System.out.println(num);
        sample2(1, 2);
    }

    public String sample2(int b, int a) {
        return "b";
    }
}
