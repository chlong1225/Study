package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/11/30
 * @author chenglong
 * description : 最大连续1的个数
 *
 * 给定一个二进制数组nums，计算其中最大连续1的个数。
 *
 * 示例 1：
 * 输入：nums = [1,1,0,1,1,1]
 * 输出：3
 * 解释：开头的两位和最后的三位都是连续 1 ，所以最大连续 1 的个数是 3.
 *
 * 示例 2:
 * 输入：nums = [1,0,1,1,0,1]
 * 输出：2
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * nums[i]不是0就是1.
 */
public class FindMaxConsecutiveOnes {

    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                count++;
            } else {
                if (count > 0) {
                    if (count > max) {
                        max = count;
                    }
                    count = 0;
                }
            }
        }
        if (count > max) {
            max = count;
        }
        return max;
    }
}
