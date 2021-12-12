package com.demo.algorithm.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chl on 2021/12/5.
 * description : 超级次方
 *
 * 你的任务是计算 ab 对 1337 取模，a 是一个正整数，b 是一个非常大的正整数且会以数组形式给出。
 *
 * 示例 1：
 * 输入：a = 2, b = [3]
 * 输出：8
 *
 * 示例 2：
 * 输入：a = 2, b = [1,0]
 * 输出：1024
 *
 * 示例 3：
 * 输入：a = 1, b = [4,3,3,8,5,2]
 * 输出：1
 *
 * 示例 4：
 * 输入：a = 2147483647, b = [2,0,0]
 * 输出：1198
 *  
 * 提示：
 * 1 <= a <= 231 - 1
 * 1 <= b.length <= 2000
 * 0 <= b[i] <= 9
 * b 不含前导 0
 */
public class SuperPow {

    private static final int MOD = 1337;
    private Map<Integer, Integer> marks = new HashMap<>();

    public int superPow(int a, int[] b) {
        /**
         * 分析：根据数学规律:
         * 1，((ka+b)^n)%a = b^n,可以对a进行缩放
         * 2，a^0 = 1
         * 3，1^n =1
         * 4，a^200 = (a^2)^100 = (a^2)^10^10
         * 5，a^211 = a^200 * a^10 * a = (a^2)^10^10 * a^10 * a
         * 6，((ka+b)*m)%a = (b*m)%a
         */
        //1，处理特殊场景
        if (a == 1) {
            return 1;
        }
        int length = b.length;
        if (length == 1 && b[0] == 0) {
            return 1;
        }
        //2,缩小a的范围
        a %= MOD;
        //3,遍历列表b
        int result = 1;
        for (int i = length - 1; i >= 0; i--) {
            result *= pow(a, b[i]);
            if (result >= MOD) {
                result %= MOD;
            }
            if (i > 0) {
                a = pow(a, 10);
            }
        }
        return result;
    }

    //num:0~10
    private int pow(int a, int num) {
        if (num == 0) {
            return 1;
        }
        if (num == 1) {
            return a;
        }
        int key = a * 10 + num - 1;
        if (marks.get(key) != null) {
            return marks.get(key);
        }
        //使用二分法计算
        int result = 1;
        while (num > 0) {
            if (num % 2 == 1) {
                result *= a;
                if (result >= MOD) {
                    result %= MOD;
                }
                num--;
            } else {
                a *= a;
                if (a >= MOD) {
                    a %= MOD;
                }
                num = num >> 1;
            }
        }
        marks.put(key, result);
        return result;
    }
}
