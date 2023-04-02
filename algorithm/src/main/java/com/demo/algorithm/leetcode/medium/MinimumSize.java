package com.demo.algorithm.leetcode.medium;

import java.util.Arrays;

/**
 * create on 2022/12/20
 * @author chenglong
 * description : 袋子里最少数目的球
 *
 * 给你一个整数数组nums，其中nums[i]表示第i个袋子里球的数目。同时给你一个整数maxOperations。
 * 你可以进行如下操作至多maxOperations次：
 * 选择任意一个袋子，并将袋子里的球分到2个新的袋子中，每个袋子里都有正整数个球。
 * 比方说，一个袋子里有5个球，你可以把它们分到两个新袋子里，分别有1个和4个球，或者分别有2个和3个球。
 * 你的开销是单个袋子里球数目的最大值，你想要最小化开销。
 * 请你返回进行上述操作后的最小开销。
 *
 * 示例 1：
 * 输入：nums = [9], maxOperations = 2
 * 输出：3
 * 解释：
 * - 将装有 9 个球的袋子分成装有 6 个和 3 个球的袋子。[9] -> [6,3] 。
 * - 将装有 6 个球的袋子分成装有 3 个和 3 个球的袋子。[6,3] -> [3,3,3] 。
 * 装有最多球的袋子里装有 3 个球，所以开销为 3 并返回 3 。
 *
 * 示例 2：
 * 输入：nums = [2,4,8,2], maxOperations = 4
 * 输出：2
 * 解释：
 * - 将装有 8 个球的袋子分成装有 4 个和 4 个球的袋子。[2,4,8,2] -> [2,4,4,4,2] 。
 * - 将装有 4 个球的袋子分成装有 2 个和 2 个球的袋子。[2,4,4,4,2] -> [2,2,2,4,4,2] 。
 * - 将装有 4 个球的袋子分成装有 2 个和 2 个球的袋子。[2,2,2,4,4,2] -> [2,2,2,2,2,4,2] 。
 * - 将装有 4 个球的袋子分成装有 2 个和 2 个球的袋子。[2,2,2,2,2,4,2] -> [2,2,2,2,2,2,2,2] 。
 * 装有最多球的袋子里装有 2 个球，所以开销为 2 并返回 2 。
 *
 * 示例 3：
 * 输入：nums = [7,17], maxOperations = 2
 * 输出：7
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= maxOperations, nums[i] <= 10^9
 */
public class MinimumSize {

    public int minimumSize(int[] nums, int maxOperations) {
        Arrays.sort(nums);
        int min = 1;
        int max = nums[nums.length - 1];
        while (min < max) {
            int middle = (max - min) / 2 + min;
            //判断middle是否符合条件
            boolean check = check(nums, maxOperations, middle);
            if (check) {
                max = middle;
            } else {
                min = middle + 1;
            }
        }
        return min;
    }

    private boolean check(int[] nums, int maxOperations, int target) {
        int length = nums.length;
        for (int i = length - 1; i >= 0; i--) {
            int cur = nums[i];
            if (cur <= target) {
                break;
            }
            int count = cur / target;
            if (cur % target != 0) {
                count++;
            }
            maxOperations -= count;
            maxOperations++;
            if (maxOperations < 0) {
                break;
            }
        }
        return maxOperations >= 0;
    }
}
