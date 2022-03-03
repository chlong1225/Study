package com.demo.algorithm.leetcode.easy;

/**
 * create on 2022/3/3
 * @author chenglong
 * description : 各位相加
 *
 * 给定一个非负整数num，反复将各个位上的数字相加，直到结果为一位数。返回这个结果。
 *
 * 示例 1:
 * 输入: num = 38
 * 输出: 2
 * 解释: 各位相加的过程为：
 * 38 --> 3 + 8 --> 11
 * 11 --> 1 + 1 --> 2
 * 由于2 是一位数，所以返回 2。
 *
 * 示例 2:
 * 输入: num = 0
 * 输出: 0
 *
 * 提示：
 * 0 <= num <= 2^31- 1
 *
 * 进阶：你可以不使用循环或者递归，在 O(1) 时间复杂度内解决这个问题吗？
 */
public class AddDigits {

    public int addDigits(int num) {
        while (num >= 10) {
            int sum = 0;
            while (num >= 10) {
                sum += num % 10;
                num /= 10;
            }
            sum += num;
            num = sum;
        }
        return num;
    }


    public int addDigits2(int num) {
        /**
         * 使用数学找规律
         * num = a1*10^n+a2*10^(n-1)+....an
         * 对应10^n%9 =1。则a1*10^n%9=a1
         * 故：num%9=(a1+..+an)%9。这种方式可以将数据大小降维。
         * 但存在问题：a1+a2+...an=9，此时正确结果应该为9，但取模后为0,，存在偏差。对应其它9的倍数没有影响
         * 可以先偏移1位，然后取模后再补回
         */
        return (num - 1) % 9 + 1;
    }
}
