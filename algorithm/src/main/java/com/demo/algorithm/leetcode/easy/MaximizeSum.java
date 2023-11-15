package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/11/15
 * @author chenglong
 * description : K个元素的最大和
 *
 * 给你一个下标从0开始的整数数组nums和一个整数k。你需要执行以下操作恰好k次，最大化你的得分：
 * 从nums中选择一个元素m。
 * 将选中的元素m从数组中删除。
 * 将新元素m+1添加到数组中。
 * 你的得分增加m。
 * 请你返回执行以上操作恰好k次后的最大得分。
 *
 * 示例 1：
 * 输入：nums = [1,2,3,4,5], k = 3
 * 输出：18
 * 解释：我们需要从 nums 中恰好选择 3 个元素并最大化得分。
 * 第一次选择 5 。和为 5 ，nums = [1,2,3,4,6] 。
 * 第二次选择 6 。和为 6 ，nums = [1,2,3,4,7] 。
 * 第三次选择 7 。和为 5 + 6 + 7 = 18 ，nums = [1,2,3,4,8] 。
 * 所以我们返回 18 。
 * 18 是可以得到的最大答案。
 *
 * 示例 2：
 * 输入：nums = [5,5,5], k = 2
 * 输出：11
 * 解释：我们需要从 nums 中恰好选择 2 个元素并最大化得分。
 * 第一次选择 5 。和为 5 ，nums = [5,5,6] 。
 * 第二次选择 6 。和为 6 ，nums = [5,5,7] 。
 * 所以我们返回 11 。
 * 11 是可以得到的最大答案。
 *
 * 提示：
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 * 1 <= k <= 100
 */
public class MaximizeSum {

    public int maximizeSum(int[] nums, int k) {
        //1，获取数组最大值
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        //2，统计和
        return k * (max + max + k - 1) / 2;
    }
}
