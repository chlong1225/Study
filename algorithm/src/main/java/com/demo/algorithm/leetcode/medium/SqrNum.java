package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2021/11/28.
 * description : 完全平方数
 *
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 * 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。 
 *
 * 示例 1：
 * 输入：n = 12
 * 输出：3
 * 解释：12 = 4 + 4 + 4
 *
 * 示例 2：
 * 输入：n = 13
 * 输出：2
 * 解释：13 = 4 + 9
 *  
 * 提示：
 * 1 <= n <= 104
 */
public class SqrNum {

    public int numSquares(int n) {
        //1,获取平方数
        List<Integer> datas = new ArrayList<>();
        int num = 1;
        while (num * num <= n) {
            datas.add(num * num);
            num++;
        }
        int size = datas.size();
        //2,转换为完全背包问题,在datas中找到最少数量的数组合成n
        /**
         * 定义的dp数组: i:组合的总和; marks[i]:组合当前数的最少数据
         * 状态转移方程 : marks[i] = Math.min(marks[i],marks[i-tem]+1)
         */
        int[] marks = new int[n + 1];
        marks[1] = 1;
        for (int i = 2; i <= n; i++) {
            marks[i] = i;
        }
        for (int i = 0; i < size; i++) {
            int tem = datas.get(i);
            for (int j = tem; j <= n; j++) {
                marks[j] = Math.min(marks[j], marks[j - tem] + 1);
            }
        }
        return marks[n];
    }


    //使用四平方和定理：任意正整数最多为四个正整数的平方和。则结果只能为1,2,3,4
    public int numSquares2(int n) {
        //1，判断是完全平方数返回1
        if (isSqr(n)) {
            return 1;
        }
        //2，根据四平方和定理判断是否为4个
        if (isAnswer4(n)) {
            return 4;
        }
        //3,判断是否为两个平方和
        int num = 1;
        while (num * num < n) {
            int tem = n - num * num;
            if (isSqr(tem)) {
                return 2;
            }
            num++;
        }
        return 3;
    }

    private boolean isAnswer4(int n) {
        while (n % 4 == 0) {
            n /= 4;
        }
        return n % 8 == 7;
    }

    //判读n是否为完全平方数
    public static boolean isSqr(int n) {
        if (n == 1) {
            return true;
        }
        int min = 1;
        int max = n;
        int middle;
        while (min <= max) {
            middle = (min + max) >> 1;
            if (middle * middle == n) {
                return true;
            } else if (middle * middle < n) {
                min = middle + 1;
            } else {
                max = middle - 1;
            }
        }
        return false;
    }
}
