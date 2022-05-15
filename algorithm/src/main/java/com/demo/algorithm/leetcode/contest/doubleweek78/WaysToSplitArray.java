package com.demo.algorithm.leetcode.contest.doubleweek78;

/**
 * Created by chl on 2022/5/15.
 * description : 分割数组的方案数
 *
 * 给你一个下标从0开始长度为n的整数数组nums。
 * 如果以下描述为真，那么nums在下标i处有一个合法的分割：
 * 前i + 1个元素的和大于等于剩下的n-i-1个元素的和。
 * 下标i的右边至少有一个元素，也就是说下标i满足0 <= i < n - 1。
 * 请你返回nums中的合法分割方案数。
 *
 * 示例 1：
 * 输入：nums = [10,4,-8,7]
 * 输出：2
 * 解释：
 * 总共有 3 种不同的方案可以将 nums 分割成两个非空的部分：
 * - 在下标 0 处分割 nums 。那么第一部分为 [10] ，和为 10 。第二部分为 [4,-8,7] ，和为 3 。因为 10 >= 3 ，所以 i = 0 是一个合法的分割。
 * - 在下标 1 处分割 nums 。那么第一部分为 [10,4] ，和为 14 。第二部分为 [-8,7] ，和为 -1 。因为 14 >= -1 ，所以 i = 1 是一个合法的分割。
 * - 在下标 2 处分割 nums 。那么第一部分为 [10,4,-8] ，和为 6 。第二部分为 [7] ，和为 7 。因为 6 < 7 ，所以 i = 2 不是一个合法的分割。
 * 所以 nums 中总共合法分割方案受为 2 。
 *
 * 示例 2：
 * 输入：nums = [2,3,1,0]
 * 输出：2
 * 解释：
 * 总共有 2 种 nums 的合法分割：
 * - 在下标 1 处分割 nums 。那么第一部分为 [2,3] ，和为 5 。第二部分为 [1,0] ，和为 1 。因为 5 >= 1 ，所以 i = 1 是一个合法的分割。
 * - 在下标 2 处分割 nums 。那么第一部分为 [2,3,1] ，和为 6 。第二部分为 [0] ，和为 0 。因为 6 >= 0 ，所以 i = 2 是一个合法的分割。
 *
 * 提示：
 * 2 <= nums.length <= 10^5
 * -10^5 <= nums[i] <= 10^5
 */
public class WaysToSplitArray {

    public int waysToSplitArray(int[] nums) {
        //使用前缀和
        int length = nums.length;
        long[] sums = new long[length];
        sums[0] = nums[0];
        for (int i = 1; i < length; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }
        long total = sums[length - 1];
        int count = 0;
        for (int i = 0; i < length - 1; i++) {
            long left = sums[i];
            long right = total - left;
            if (left >= right) {
                count++;
            }
        }
        return count;
    }
}
