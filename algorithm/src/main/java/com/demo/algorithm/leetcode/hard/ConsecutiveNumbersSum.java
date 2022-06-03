package com.demo.algorithm.leetcode.hard;

/**
 * Created by chl on 2022/6/3.
 * description : 连续整数求和
 *
 * 给定一个正整数n，返回连续正整数满足所有数字之和为n的组数。
 *
 * 示例 1:
 * 输入: n = 5
 * 输出: 2
 * 解释: 5 = 2 + 3，共有两组连续整数([5],[2,3])求和后为 5。
 *
 * 示例 2:
 * 输入: n = 9
 * 输出: 3
 * 解释: 9 = 4 + 5 = 2 + 3 + 4
 *
 * 示例 3:
 * 输入: n = 15
 * 输出: 4
 * 解释: 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5
 *
 * 提示:
 *
 * 1 <= n <= 10^9
 */
public class ConsecutiveNumbersSum {

    public int consecutiveNumbersSum(int n) {
        /**
         * 分析：k个连续整数求和: a,a+1,a+2,a+3,a+4...a+k-1
         * 求和为：k*a + (k-1)*k/2。当数量k确定时，能够满足条件的a是唯一的。
         * 可以遍历n，判断是否有唯一的a。a = (n - (k-1)*k/2)整除k
         */
        //至少包含一个本身
        int sum = 1;
        for (int i = 2; i < n; i++) {
            int add = (i - 1) * i / 2;
            int last = n - add;
            if (last > 0) {
                if (last % i == 0) {
                    sum++;
                }
            } else {
                break;
            }
        }
        return sum;
    }
}
