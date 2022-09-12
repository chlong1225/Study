package com.demo.algorithm.leetcode.easy;

import java.util.Arrays;

/**
 * create on 2022/9/12
 * @author chenglong
 * description : 特殊数组的特征值
 *
 * 给你一个非负整数数组nums。如果存在一个数x，使得nums中恰好有x个元素大于或者等于x，那么就称nums是一个特殊数组，而x是该数组的特征值。
 * 注意：x不必是nums的中的元素。
 * 如果数组 nums 是一个特殊数组，请返回它的特征值x。否则，返回-1。可以证明的是，如果nums是特殊数组，那么其特征值x是唯一的。
 *
 * 示例 1：
 * 输入：nums = [3,5]
 * 输出：2
 * 解释：有 2 个元素（3 和 5）大于或等于 2 。
 *
 * 示例 2：
 * 输入：nums = [0,0]
 * 输出：-1
 * 解释：没有满足题目要求的特殊数组，故而也不存在特征值 x 。
 * 如果 x = 0，应该有 0 个元素 >= x，但实际有 2 个。
 * 如果 x = 1，应该有 1 个元素 >= x，但实际有 0 个。
 * 如果 x = 2，应该有 2 个元素 >= x，但实际有 0 个。
 * x 不能取更大的值，因为 nums 中只有两个元素。
 *
 * 示例 3：
 * 输入：nums = [0,4,3,0,4]
 * 输出：3
 * 解释：有 3 个元素大于或等于 3 。
 *
 * 示例 4：
 * 输入：nums = [3,6,7,7,0]
 * 输出：-1
 *
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 1000
 */
public class SpecialArray {

    public int specialArray(int[] nums) {
        //1，对数组进行排序
        Arrays.sort(nums);
        //2，遍历查找满足条件的值
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            int cur = nums[i];
            if (i > 0 && cur == nums[i - 1]) {
                continue;
            }
            int count = length - i;
            if (count <= cur) {
                if (i > 1) {
                    if (count > nums[i - 1]) {
                        return count;
                    }
                } else {
                    return count;
                }
            }
        }
        return -1;
    }
}
