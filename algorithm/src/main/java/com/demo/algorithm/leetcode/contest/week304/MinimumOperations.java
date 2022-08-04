package com.demo.algorithm.leetcode.contest.week304;

import java.util.Arrays;

/**
 * create on 2022/8/4
 * @author chenglong
 * description : 使数组中所有元素都等于零
 *
 * 给你一个非负整数数组nums。在一步操作中，你必须：
 * 选出一个正整数x，x需要小于或等于nums中最小的非零元素。
 * nums中的每个正整数都减去x。
 * 返回使nums中所有元素都等于0需要的最少操作数。
 *
 * 示例 1：
 * 输入：nums = [1,5,0,3,5]
 * 输出：3
 * 解释：
 * 第一步操作：选出 x = 1 ，之后 nums = [0,4,0,2,4] 。
 * 第二步操作：选出 x = 2 ，之后 nums = [0,2,0,0,2] 。
 * 第三步操作：选出 x = 2 ，之后 nums = [0,0,0,0,0] 。
 *
 * 示例 2：
 * 输入：nums = [0]
 * 输出：0
 * 解释：nums 中的每个元素都已经是 0 ，所以不需要执行任何操作。
 *
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 */
public class MinimumOperations {

    public int minimumOperations(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        int reduce = 0;
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (nums[i] == 0 || nums[i] - reduce == 0) {
                continue;
            }
            count++;
            reduce = nums[i];
        }
        return count;
    }
}
