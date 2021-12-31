package com.demo.algorithm.leetcode.easy;

/**
 * create on 12/31/21
 * @author chenglong
 * description : 完美数
 *
 * 对于一个正整数，如果它和除了它自身以外的所有正因子之和相等，我们称它为 「完美数」。
 * 给定一个整数n，如果是完美数，返回true，否则返回false
 *
 * 示例 1：
 * 输入：num = 28
 * 输出：true
 * 解释：28 = 1 + 2 + 4 + 7 + 14
 * 1, 2, 4, 7, 和 14 是 28 的所有正因子。
 *
 * 示例 2：
 * 输入：num = 6
 * 输出：true
 *
 * 示例 3：
 * 输入：num = 496
 * 输出：true
 *
 * 示例 4：
 * 输入：num = 8128
 * 输出：true
 *
 * 示例 5：
 * 输入：num = 2
 * 输出：false
 *  
 * 提示：
 * 1 <= num <= 108
 */
public class PerfectNum {

    public boolean checkPerfectNumber(int num) {
        if (num < 6) {
            return false;
        }
        int sum = 1;
        int tem;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                tem = num / i;
                if (tem == i) {
                    sum += i;
                } else {
                    sum += (i + tem);
                }
            }
        }
        return num == sum;
    }
}
