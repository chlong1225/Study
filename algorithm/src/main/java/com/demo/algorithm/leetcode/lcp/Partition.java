package com.demo.algorithm.leetcode.lcp;

/**
 * create on 2023/12/21
 * @author chenglong
 * description : LCR 101. 分割等和子集
 *
 * 给定一个非空的正整数数组nums，请判断能否将这些数字分成元素和相等的两部分。
 *
 * 示例 1：
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：nums 可以分割成 [1, 5, 5] 和 [11] 。
 *
 * 示例 2：
 * 输入：nums = [1,2,3,5]
 * 输出：false
 * 解释：nums 不可以分为和相等的两部分
 *
 * 提示：
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 */
public class Partition {

    public boolean canPartition(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return false;
        }
        //1，获取最大值与求和，并处理特殊场景
        int sum = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        if (sum % 2 == 1) {
            return false;
        }
        sum /= 2;
        if (max > sum) {
            return false;
        }
        if (max == sum) {
            return true;
        }
        //2，使用动态规划进行打表
        boolean[] marks = new boolean[sum + 1];
        marks[0] = true;
        for (int i = 0; i < n; i++) {
            //当前插入的数据为：nums[i]
            for (int j = sum; j >= nums[i]; j--) {
                marks[j] = marks[j] | marks[j - nums[i]];
                if (j == sum && marks[j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
