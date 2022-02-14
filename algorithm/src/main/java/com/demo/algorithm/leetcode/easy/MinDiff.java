package com.demo.algorithm.leetcode.easy;

import java.util.Arrays;

/**
 * create on 2022/2/11
 * @author chenglong
 * description : 学生分数的最小差值
 *
 * 给你一个下标从0开始的整数数组nums，其中nums[i]表示第i名学生的分数。另给你一个整数k 。
 * 从数组中选出任意k名学生的分数，使这k个分数间最高分和最低分的差值达到最小化 。
 * 返回可能的 最小差值 。
 *
 * 示例 1：
 * 输入：nums = [90], k = 1
 * 输出：0
 * 解释：选出 1 名学生的分数，仅有 1 种方法：
 * - [90] 最高分和最低分之间的差值是 90 - 90 = 0
 * 可能的最小差值是 0
 *
 * 示例 2：
 * 输入：nums = [9,4,1,7], k = 2
 * 输出：2
 * 解释：选出 2 名学生的分数，有 6 种方法：
 * - [9,4,1,7] 最高分和最低分之间的差值是 9 - 4 = 5
 * - [9,4,1,7] 最高分和最低分之间的差值是 9 - 1 = 8
 * - [9,4,1,7] 最高分和最低分之间的差值是 9 - 7 = 2
 * - [9,4,1,7] 最高分和最低分之间的差值是 4 - 1 = 3
 * - [9,4,1,7] 最高分和最低分之间的差值是 7 - 4 = 3
 * - [9,4,1,7] 最高分和最低分之间的差值是 7 - 1 = 6
 * 可能的最小差值是 2
 *
 * 提示：
 * 1 <= k <= nums.length <= 1000
 * 0 <= nums[i] <= 10^5
 */
public class MinDiff {

    public int minimumDifference(int[] nums, int k) {
        if (k == 1) {
            return 0;
        }
        Arrays.sort(nums);
        int length = nums.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i + k <= length; i++) {
            int diff = nums[i + k - 1] - nums[i];
            if (diff < min) {
                min = diff;
            }
        }
        return min;
    }
}
