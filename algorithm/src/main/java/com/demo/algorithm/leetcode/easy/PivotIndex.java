package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/12/1
 * @author chenglong
 * description : 寻找数组的中心下标
 *
 * 给你一个整数数组nums，请计算数组的中心下标。
 * 数组中心下标是数组的一个下标，其左侧所有元素相加的和等于右侧所有元素相加的和。
 * 如果中心下标位于数组最左端，那么左侧数之和视为0，因为在下标的左侧不存在元素。这一点对于中心下标位于数组最右端同样适用。
 * 如果数组有多个中心下标，应该返回最靠近左边的那一个。如果数组不存在中心下标，返回-1。
 *
 * 示例 1：
 * 输入：nums = [1, 7, 3, 6, 5, 6]
 * 输出：3
 * 解释：
 * 中心下标是 3 。
 * 左侧数之和 sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11 ，
 * 右侧数之和 sum = nums[4] + nums[5] = 5 + 6 = 11 ，二者相等。
 *
 * 示例 2：
 * 输入：nums = [1, 2, 3]
 * 输出：-1
 * 解释：
 * 数组中不存在满足此条件的中心下标。
 *
 * 示例 3：
 * 输入：nums = [2, 1, -1]
 * 输出：0
 * 解释：
 * 中心下标是 0 。
 * 左侧数之和 sum = 0 ，（下标 0 左侧不存在元素），
 * 右侧数之和 sum = nums[1] + nums[2] = 1 + -1 = 0 。
 *
 * 提示：
 * 1 <= nums.length <= 10^4
 * -1000 <= nums[i] <= 1000
 *
 */
public class PivotIndex {

    public int pivotIndex(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        int[] lefts = new int[n];
        for (int i = 1; i < n; i++) {
            lefts[i] = lefts[i - 1] + nums[i - 1];
        }
        int[] rights = new int[n];
        for (int i = n - 2; i >= 0; i--) {
            rights[i] = rights[i + 1] + nums[i + 1];
        }
        for (int i = 0; i < n; i++) {
            if (lefts[i] == rights[i]) {
                return i;
            }
        }
        return -1;
    }
}
