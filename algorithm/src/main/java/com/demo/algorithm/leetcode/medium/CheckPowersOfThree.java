package com.demo.algorithm.leetcode.medium;

/**
 * create on 2022/12/9
 * @author chenglong
 * description : 判断一个数字是否可以表示成三的幂的和
 *
 * 给你一个整数n，如果你可以将n表示成若干个不同的三的幂之和，请你返回true，否则请返回false。
 * 对于一个整数y，如果存在整数x满足y==3x，我们称这个整数y是三的幂。
 *
 * 示例 1：
 * 输入：n = 12
 * 输出：true
 * 解释：12 = 3^1 + 3^2
 *
 * 示例 2：
 * 输入：n = 91
 * 输出：true
 * 解释：91 = 3^0 + 3^2 + 3^4
 *
 * 示例 3：
 * 输入：n = 21
 * 输出：false
 *
 * 提示：
 * 1 <= n <= 10^7
 */
public class CheckPowersOfThree {

    public boolean checkPowersOfThree(int n) {
        while (n > 1) {
            int last = n % 3;
            if (last == 0 || last == 1) {
                n /= 3;
            } else {
                return false;
            }
        }
        return true;
    }
}
