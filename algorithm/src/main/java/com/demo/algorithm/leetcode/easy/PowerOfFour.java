package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/11/29
 * @author chenglong
 * description : 4的幂
 *
 * 给定一个整数，写一个函数来判断它是否是4的幂次方。如果是，返回true；否则，返回false。
 * 整数n是4的幂次方需满足：存在整数x使得n==4^x
 *
 * 示例 1：
 * 输入：n = 16
 * 输出：true
 *
 * 示例 2：
 * 输入：n = 5
 * 输出：false
 *
 * 示例 3：
 * 输入：n = 1
 * 输出：true
 *
 * 提示：
 * -2^31 <= n <= 2^31 - 1
 */
public class PowerOfFour {

    public boolean isPowerOfFour(int n) {
        while (n > 1) {
            if (n % 4 == 0) {
                n /= 4;
            } else {
                return false;
            }
        }
        return n == 1;
    }
}
