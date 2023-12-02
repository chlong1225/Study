package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/11/29
 * @author chenglong
 * description : 3的幂
 *
 * 给定一个整数，写一个函数来判断它是否是3的幂次方。如果是，返回true；否则，返回false。
 * 整数n是3的幂次方需满足：存在整数 x 使得 n == 3^x
 *
 * 示例 1：
 * 输入：n = 27
 * 输出：true
 *
 * 示例 2：
 * 输入：n = 0
 * 输出：false
 *
 * 示例 3：
 * 输入：n = 9
 * 输出：true
 *
 * 示例 4：
 * 输入：n = 45
 * 输出：false
 *
 * 提示：
 * -2^31 <= n <= 2^31 - 1
 *
 * 进阶：你能不使用循环或者递归来完成本题吗？
 */
public class PowerOfThree {

    public boolean isPowerOfThree(int n) {
        while (n > 1) {
            if (n % 3 == 0) {
                n /= 3;
            } else {
                return false;
            }
        }
        return n==1;
    }
}
