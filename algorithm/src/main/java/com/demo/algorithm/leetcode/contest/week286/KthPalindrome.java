package com.demo.algorithm.leetcode.contest.week286;

/**
 * Created by chl on 2022/3/29.
 * description :  找到指定长度的回文数
 *
 * 给你一个整数数组queries和一个正整数intLength，请你返回一个数组answer，其中answer[i]是长度为intLength的正回文数中第queries[i]小的数字，
 * 如果不存在这样的回文数，则为-1。
 * 回文数指的是从前往后和从后往前读一模一样的数字。回文数不能有前导0 。
 *
 * 示例 1：
 * 输入：queries = [1,2,3,4,5,90], intLength = 3
 * 输出：[101,111,121,131,141,999]
 * 解释：
 * 长度为 3 的最小回文数依次是：
 * 101, 111, 121, 131, 141, 151, 161, 171, 181, 191, 202, ...
 * 第 90 个长度为 3 的回文数是 999 。
 *
 * 示例 2：
 * 输入：queries = [2,4,6], intLength = 4
 * 输出：[1111,1331,1551]
 * 解释：
 * 长度为 4 的前 6 个回文数是：
 * 1001, 1111, 1221, 1331, 1441 和 1551 。
 *
 * 提示：
 * 1 <= queries.length <= 5 * 10^4
 * 1 <= queries[i] <= 10^9
 * 1 <= intLength<= 15
 */
public class KthPalindrome {

    public long[] kthPalindrome(int[] queries, int intLength) {
        int length = queries.length;
        long[] answer = new long[length];
        if (intLength == 1) {
            //回文长度为1时，此时取值范围1~9
            for (int i = 0; i < length; i++) {
                if (queries[i] < 10 && queries[i] > 0) {
                    answer[i] = queries[i];
                } else {
                    answer[i] = -1;
                }
            }
            return answer;
        }
        //左半部分回文的长度。奇数时包含中间数字
        int count = intLength / 2 + (intLength % 2 == 0 ? 0 : 1);
        long min = 1;
        for (int i = 0; i < count - 1; i++) {
            min *= 10;
        }
        long max = 10 * min - 1;
        for (int i = 0; i < length; i++) {
            long num = min + queries[i] - 1;
            if (num > max) {
                answer[i] = -1;
            } else {
                long last = num;
                if (intLength % 2 == 1) {
                    last /= 10;
                }
                //反转last
                long sum = 0;
                long factor = 1;
                while (last > 0) {
                    sum = 10 * sum + (last % 10);
                    last /= 10;
                    factor *= 10;
                }
                answer[i] = num * factor + sum;
            }
        }
        return answer;
    }
}
