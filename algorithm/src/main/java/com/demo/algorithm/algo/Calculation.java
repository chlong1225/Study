package com.demo.algorithm.algo;

/**
 * Created by chl on 2021/8/22.
 */
public class Calculation {

    /**
     * @param num :
     * @return : 计算num*3
     */
    public static int multiplyBy3(int num) {
        if (num == 0) {
            return 0;
        }
        //位运算的优先级低于加法,左移相对于*2
        return  (num << 1) + num;
    }

    /**
     * @param num :
     * @return : 计算num/3
     */
    public static int divideBy3(int num) {
        if (num == 0) {
            return 0;
        }
        //位运算右移相对/2
        int result = num >> 2;
        while (multiplyBy3(result + 1) < num) {
            result++;
        }
        return result;
    }
}
