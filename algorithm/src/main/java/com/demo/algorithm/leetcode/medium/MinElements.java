package com.demo.algorithm.leetcode.medium;

/**
 * create on 2022/12/16
 * @author chenglong
 * description : 构成特定和需要添加的最少元素
 *
 * 给你一个整数数组nums，和两个整数limit与goal。数组nums有一条重要属性：abs(nums[i]) <= limit 。
 * 返回使数组元素总和等于goal所需要向数组中添加的最少元素数量，添加元素不应改变数组中abs(nums[i])<=limit这一属性。
 * 注意，如果 x >= 0 ，那么 abs(x) 等于 x ；否则，等于 -x 。
 *
 * 示例 1：
 * 输入：nums = [1,-1,1], limit = 3, goal = -4
 * 输出：2
 * 解释：可以将 -2 和 -3 添加到数组中，数组的元素总和变为 1 - 1 + 1 - 2 - 3 = -4 。
 *
 * 示例 2：
 * 输入：nums = [1,-10,9,1], limit = 100, goal = 0
 * 输出：1
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= limit <= 10^6
 * -limit <= nums[i] <= limit
 * -10^9 <= goal <= 10^9
 */
public class MinElements {

    public int minElements(int[] nums, int limit, int goal) {
        long diff = goal;
        for (int i = 0; i < nums.length; i++) {
            diff -= nums[i];
        }
        diff = Math.abs(diff);
        long count = diff / limit;
        if (diff % limit != 0) {
            count++;
        }
        return (int) count;
    }
}
