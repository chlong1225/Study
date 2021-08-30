package com.demo.algorithm.leetcode;

/**
 * create by chenglong on 8/30/21
 * description : 两数求和
 *
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 */
public class TwoNumSum {

    public static int[] twoSum(int[] nums, int target) {
        int[] result = {-1, -1};
        if (nums == null && nums.length < 2) {
            return result;
        }

        for (int i = 0; i < nums.length; i++) {
            int space = target - nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (space == nums[j]) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }

}
