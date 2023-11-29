package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/11/29
 * @author chenglong
 * description : 第三大的数
 *
 * 给你一个非空数组，返回此数组中第三大的数。如果不存在，则返回数组中最大的数。
 *
 * 示例 1：
 * 输入：[3, 2, 1]
 * 输出：1
 * 解释：第三大的数是 1 。
 *
 * 示例 2：
 * 输入：[1, 2]
 * 输出：2
 * 解释：第三大的数不存在, 所以返回最大的数2。
 *
 * 示例 3：
 * 输入：[2, 2, 3, 1]
 * 输出：1
 * 解释：注意，要求返回第三大的数，是指在所有不同数字中排第三大的数。
 * 此例中存在两个值为 2 的数，它们都排第二。在所有不同数字中排第三大的数为 1 。
 *
 * 提示：
 * 1 <= nums.length <= 10^4
 * -2^31 <= nums[i] <= 2^31 - 1
 *
 * 进阶：你能设计一个时间复杂度 O(n) 的解决方案吗？
 */
public class ThirdMax {

    public int thirdMax(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        if (n == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int max = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        int second = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (nums[i] != max) {
                if (nums[i] > second) {
                    second = nums[i];
                }
            }
        }
        if (second == Integer.MIN_VALUE) {
            return max;
        }
        long third = Long.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (nums[i] != max && nums[i] != second) {
                if (nums[i] > third) {
                    third = nums[i];
                }
            }
        }
        if (third == Long.MIN_VALUE) {
            return max;
        }
        return (int) third;
    }
}
