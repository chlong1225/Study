package com.demo.algorithm.leetcode.medium;

/**
 * create on 2024/3/1
 * @author chenglong
 * description : 检查数组是否存在有效划分
 *
 * 给你一个下标从0开始的整数数组nums，你必须将数组划分为一个或多个连续子数组。
 * 如果获得的这些子数组中每个都能满足下述条件之一，则可以称其为数组的一种有效划分：
 * 子数组恰由2个相等元素组成，例如，子数组 [2,2] 。
 * 子数组恰由3个相等元素组成，例如，子数组 [4,4,4] 。
 * 子数组恰由3个连续递增元素组成，并且相邻元素之间的差值为1。例如，子数组[3,4,5]，但是子数组[1,3,5]不符合要求。
 * 如果数组至少存在一种有效划分，返回true，否则，返回false。
 *
 * 示例 1：
 * 输入：nums = [4,4,4,5,6]
 * 输出：true
 * 解释：数组可以划分成子数组 [4,4] 和 [4,5,6] 。
 * 这是一种有效划分，所以返回 true 。
 *
 * 示例 2：
 * 输入：nums = [1,1,1,2]
 * 输出：false
 * 解释：该数组不存在有效划分。
 *
 * 提示：
 *
 * 2 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^6
 */
public class Partition {

    public boolean validPartition(int[] nums) {
        int n = nums.length;
        boolean[] marks = new boolean[n + 1];
        marks[0] = true;
        marks[1] = false;
        marks[2] = (nums[0] == nums[1]);
        for (int i = 3; i <= n; i++) {
            //1，判断连续两个是否相等
            if (nums[i - 1] == nums[i - 2]) {
                if (marks[i - 2]) {
                    marks[i] = true;
                } else {
                    if (nums[i - 2] == nums[i - 3] && marks[i - 3]) {
                        marks[i] = true;
                    }
                }
            } else if (nums[i - 1] == nums[i - 2] + 1 && nums[i - 2] == nums[i - 3] + 1) {
                if (marks[i - 3]) {
                    marks[i] = true;
                }
            }
        }
        return marks[n];
    }
}
