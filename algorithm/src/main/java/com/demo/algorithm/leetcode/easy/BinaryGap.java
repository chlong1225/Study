package com.demo.algorithm.leetcode.easy;

/**
 * create on 2022/4/24
 * @author chenglong
 * description :  二进制间距
 *
 * 给定一个正整数n，找到并返回n的二进制表示中两个相邻1之间的最长距离 。如果不存在两个相邻的1，返回0 。
 * 如果只有0将两个1分隔开（可能不存在0），则认为这两个1彼此相邻 。两个1之间的距离是它们的二进制表示中位置的绝对差。例如，"1001" 中的两个 1 的距离为 3 。
 *
 * 示例 1：
 * 输入：n = 22
 * 输出：2
 * 解释：22 的二进制是 "10110" 。
 * 在 22 的二进制表示中，有三个 1，组成两对相邻的 1 。
 * 第一对相邻的 1 中，两个 1 之间的距离为 2 。
 * 第二对相邻的 1 中，两个 1 之间的距离为 1 。
 * 答案取两个距离之中最大的，也就是 2 。
 *
 * 示例 2：
 * 输入：n = 8
 * 输出：0
 * 解释：8 的二进制是 "1000" 。
 * 在 8 的二进制表示中没有相邻的两个 1，所以返回 0 。
 *
 * 示例 3：
 * 输入：n = 5
 * 输出：2
 * 解释：5 的二进制是 "101" 。
 *
 * 提示：
 * 1 <= n <= 10^9
 */
public class BinaryGap {

    public int binaryGap(int n) {
        int max = 0;
        int start = -1;
        int step = 0;
        while (n > 0) {
            step++;
            if (n % 2 == 1) {
                if (start == -1) {
                    start = step;
                } else {
                    int space = step - start;
                    if (space > max) {
                        max = space;
                    }
                    start = step;
                }
            }
            n /= 2;
        }
        return max;
    }
}
