package com.demo.algorithm.leetcode.medium;

/**
 * create on 11/19/21
 * @author chenglong
 * description : 整数替换
 *
 * 给定一个正整数 n ，你可以做如下操作：
 * 如果 n 是偶数，则用 n / 2替换 n 。
 * 如果 n 是奇数，则可以用 n + 1或n - 1替换 n 。
 * n 变为 1 所需的最小替换次数是多少？
 *
 * 示例 1：
 * 输入：n = 8
 * 输出：3
 * 解释：8 -> 4 -> 2 -> 1
 *
 * 示例 2：
 * 输入：n = 7
 * 输出：4
 * 解释：7 -> 8 -> 4 -> 2 -> 1
 * 或 7 -> 6 -> 3 -> 2 -> 1
 *
 * 示例 3：
 * 输入：n = 4
 * 输出：2
 *  
 * 提示：
 * 1 <= n <= 231 - 1
 */
public class IntReplace {

    public static int integerReplacement(int n) {
        int count = 0;
        if (n == 1) {
            return count;
        }
        if (n == Integer.MAX_VALUE) {
            n = n >> 1;
            n++;
            count += 2;
        }
        while (n > 1) {
            if ((n & 1) == 1) {
                //奇数
                if (n == 3) {
                    count += 2;
                    return count;
                }
                if (n % 4 == 1) {
                    count += 2;
                    //n-1然后n/2。位运算右移，最后一位1直接被覆盖了，不需要再运算-1
                    n = n >> 1;
                } else {
                    //n = 4*k+3
                    count += 2;
                    n = (n + 1) >> 1;
                }
            } else {
                n = n >> 1;
                count++;
            }
        }
        return count;
    }
}
