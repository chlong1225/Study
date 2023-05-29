package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/5/29
 * @author chenglong
 * description : 可被三整除的偶数的平均值
 *
 * 给你一个由正整数组成的整数数组nums，返回其中可被3整除的所有偶数的平均值。
 * 注意：n个元素的平均值等于n个元素求和再除以n，结果向下取整到最接近的整数。
 *
 * 示例 1：
 * 输入：nums = [1,3,6,10,12,15]
 * 输出：9
 * 解释：6 和 12 是可以被 3 整除的偶数。(6 + 12) / 2 = 9 。
 *
 * 示例 2：
 * 输入：nums = [1,2,4,7,10]
 * 输出：0
 * 解释：不存在满足题目要求的整数，所以返回 0 。
 *
 * 提示：
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 1000
 */
public class AverageValue {

    public int averageValue(int[] nums) {
        int sum = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 6 == 0) {
                count++;
                sum += nums[i];
            }
        }
        if (count == 0) {
            return 0;
        }
        return sum / count;
    }
}
