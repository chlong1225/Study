package com.demo.algorithm.leetcode.offer;

/**
 * Created by chl on 2022/4/24.
 * description : 剑指Offer17. 打印从1到最大的n位数
 *
 * 输入数字n，按顺序打印出从1到最大的n位十进制数。比如输入3，则打印出 1、2、3 一直到最大的3位数999。
 *
 * 示例 1:
 * 输入: n = 1
 * 输出: [1,2,3,4,5,6,7,8,9]
 *
 * 说明：
 * 用返回一个整数列表来代替打印
 * n 为正整数
 */
public class PrintNumbers {

    public int[] printNumbers(int n) {
        int min = 1;
        int max = 1;
        while (n > 0) {
            max *= 10;
            n--;
        }
        int count = max - min;
        int[] result = new int[count];
        for (int i = min; i < max; i++) {
            result[i - 1] = i;
        }
        return result;
    }
}
