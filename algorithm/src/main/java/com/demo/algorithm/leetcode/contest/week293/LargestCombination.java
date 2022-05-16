package com.demo.algorithm.leetcode.contest.week293;

/**
 * create on 2022/5/16
 * @author chenglong
 * description : 按位与结果大于零的最长组合
 *
 * 对数组nums执行按位与相当于对数组nums中的所有整数执行按位与 。
 * 例如，对 nums = [1, 5, 3] 来说，按位与等于 1 & 5 & 3 = 1 。
 * 同样，对 nums = [7] 而言，按位与等于 7 。
 * 给你一个正整数数组 candidates 。计算 candidates 中的数字每种组合下 按位与 的结果。 candidates 中的每个数字在每种组合中只能使用 一次 。
 * 返回按位与结果大于0的最长组合的长度。
 *
 * 示例 1：
 * 输入：candidates = [16,17,71,62,12,24,14]
 * 输出：4
 * 解释：组合 [16,17,62,24] 的按位与结果是 16 & 17 & 62 & 24 = 16 > 0 。
 * 组合长度是 4 。
 * 可以证明不存在按位与结果大于 0 且长度大于 4 的组合。
 * 注意，符合长度最大的组合可能不止一种。
 * 例如，组合 [62,12,24,14] 的按位与结果是 62 & 12 & 24 & 14 = 8 > 0 。
 *
 * 示例 2：
 * 输入：candidates = [8,8]
 * 输出：2
 * 解释：最长组合是 [8,8] ，按位与结果 8 & 8 = 8 > 0 。
 * 组合长度是 2 ，所以返回 2 。
 *
 * 提示：
 * 1 <= candidates.length <= 10^5
 * 1 <= candidates[i] <= 10^7
 */
public class LargestCombination {

    public int largestCombination(int[] candidates) {
        int[] counts = new int[32];
        int length = candidates.length;
        for (int i = 0; i < length; i++) {
            int cur = candidates[i];
            int index = 0;
            while (cur > 0) {
                if ((cur & 1) == 1) {
                    counts[index]++;
                }
                cur >>= 1;
                index++;
            }
        }
        int max = counts[0];
        for (int i = 1; i < 32; i++) {
            if (counts[i] > max) {
                max = counts[i];
            }
        }
        return max;
    }
}
