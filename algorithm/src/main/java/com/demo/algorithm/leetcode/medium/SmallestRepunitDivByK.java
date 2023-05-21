package com.demo.algorithm.leetcode.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * create on 2023/5/10
 * @author chenglong
 * description : 可被K整除的最小整数
 *
 * 给定正整数k，你需要找出可以被k整除的、仅包含数字1的最小正整数n的长度。
 * 返回n的长度。如果不存在这样的n，就返回-1。
 * 注意： n不符合64位带符号整数。
 *
 * 示例 1：
 * 输入：k = 1
 * 输出：1
 * 解释：最小的答案是 n = 1，其长度为 1。
 *
 * 示例 2：
 * 输入：k = 2
 * 输出：-1
 * 解释：不存在可被 2 整除的正整数 n 。
 *
 * 示例 3：
 * 输入：k = 3
 * 输出：3
 * 解释：最小的答案是 n = 111，其长度为 3。
 *
 * 提示：
 * 1 <= k <= 10^5
 */
public class SmallestRepunitDivByK {

    public int smallestRepunitDivByK(int k) {
        //1，处理特殊场景
        if (k == 1) {
            return 1;
        }
        if (k % 2 == 0 || k % 5 == 0) {
            return -1;
        }
        Set<Integer> marks = new HashSet<>();
        marks.add(1);
        int step = 1;
        int num = 1;
        while (true) {
            step++;
            num = (num * 10 + 1) % k;
            if (num == 0) {
                return step;
            }
            if (marks.contains(num)) {
                return -1;
            }
            marks.add(num);
        }
    }
}
