package com.demo.algorithm.leetcode.easy;

/**
 * Created by chl on 2022/5/3.
 * description : 最小差值I
 *
 * 给你一个整数数组nums，和一个整数k 。
 * 在一个操作中，您可以选择0<=i<nums.length的任何索引i。将nums[i]改为nums[i]+ x，其中x是一个范围为[-k, k]的整数。对于每个索引i ，最多只能应用一次此操作。
 * nums的分数是nums中最大和最小元素的差值。
 * 在对nums中的每个索引最多应用一次上述操作后，返回nums的最低分数 。
 *
 * 示例 1：
 * 输入：nums = [1], k = 0
 * 输出：0
 * 解释：分数是 max(nums) - min(nums) = 1 - 1 = 0。
 *
 * 示例 2：
 * 输入：nums = [0,10], k = 2
 * 输出：6
 * 解释：将 nums 改为 [2,8]。分数是 max(nums) - min(nums) = 8 - 2 = 6。
 * 示例 3：
 *
 * 输入：nums = [1,3,6], k = 3
 * 输出：0
 * 解释：将 nums 改为 [4,4,4]。分数是 max(nums) - min(nums) = 4 - 4 = 0。
 *
 * 提示：
 * 1 <= nums.length <= 10^4
 * 0 <= nums[i] <= 10^4
 * 0 <= k <= 10^4
 */
public class SmallestRangeI {

    public int smallestRangeI(int[] nums, int k) {
        int length = nums.length;
        if (length == 1) {
            return 0;
        }
        int max = nums[0] - k;
        int min = nums[0] + k;
        for (int i = 1; i < length; i++) {
            min = Math.min(min, nums[i] + k);
            max = Math.max(max, nums[i] - k);
        }
        if (max < min) {
            return 0;
        }
        return max - min;
    }
}
