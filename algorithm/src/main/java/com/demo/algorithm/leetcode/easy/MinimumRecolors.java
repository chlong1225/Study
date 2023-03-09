package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/3/9
 * @author chenglong
 * description : 得到K个黑块的最少涂色次数
 *
 * 给你一个长度为n下标从0开始的字符串blocks，blocks[i]要么是'W'要么是'B'，表示第i块的颜色。字符'W'和'B'分别表示白色和黑色。
 * 给你一个整数k，表示想要连续黑色块的数目。
 * 每一次操作中，你可以选择一个白色块将它涂成黑色块。
 * 请你返回至少出现一次连续k个黑色块的最少操作次数。
 *
 * 示例 1：
 * 输入：blocks = "WBBWWBBWBW", k = 7
 * 输出：3
 * 解释：
 * 一种得到 7 个连续黑色块的方法是把第 0 ，3 和 4 个块涂成黑色。
 * 得到 blocks = "BBBBBBBWBW" 。
 * 可以证明无法用少于 3 次操作得到 7 个连续的黑块。
 * 所以我们返回 3 。
 *
 * 示例 2：
 * 输入：blocks = "WBWBBBW", k = 2
 * 输出：0
 * 解释：
 * 不需要任何操作，因为已经有 2 个连续的黑块。
 * 所以我们返回 0 。
 *
 * 提示：
 * n == blocks.length
 * 1 <= n <= 100
 * blocks[i]要么是'W'，要么是'B' 。
 * 1 <= k <= n
 */
public class MinimumRecolors {

    public int minimumRecolors(String blocks, int k) {
        int count = 0;
        int length = blocks.length();
        for (int i = 0; i < k; i++) {
            if (blocks.charAt(i) == 'W') {
                count++;
            }
        }
        if (count == 0) {
            return count;
        }
        int min = count;
        for (int i = k; i < length; i++) {
            if (blocks.charAt(i) == 'W') {
                count++;
            }
            if (blocks.charAt(i - k) == 'W') {
                count--;
            }
            if (count < min) {
                min = count;
                if (min == 0) {
                    return 0;
                }
            }
        }
        return min;
    }
}
