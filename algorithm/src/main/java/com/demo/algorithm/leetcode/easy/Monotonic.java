package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/12/4
 * @author chenglong
 * description : 单调数列
 *
 * 如果数组是单调递增或单调递减的，那么它是单调的。
 * 如果对于所有i <= j，nums[i] <= nums[j]，那么数组nums是单调递增的。 如果对于所有i <= j，nums[i]> = nums[j]，那么数组nums是单调递减的。
 * 当给定的数组nums是单调数组时返回true，否则返回false。
 *
 * 示例 1：
 * 输入：nums = [1,2,2,3]
 * 输出：true
 *
 * 示例 2：
 * 输入：nums = [6,5,4,4]
 * 输出：true
 *
 * 示例 3：
 * 输入：nums = [1,3,2]
 * 输出：false
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * -10^5 <= nums[i] <= 10^5
 */
public class Monotonic {

    public boolean isMonotonic(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return true;
        }
        //定义type=0初始默认状态，type=1单调递增，type=2单调递减
        int type = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i - 1] != nums[i]) {
                if (type == 0) {
                    if (nums[i - 1] < nums[i]) {
                        //单调递增
                        type = 1;
                    } else {
                        type = 2;
                    }
                } else if (type == 1) {
                    if (nums[i - 1] > nums[i]) {
                        return false;
                    }
                } else {
                    if (nums[i - 1] < nums[i]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
