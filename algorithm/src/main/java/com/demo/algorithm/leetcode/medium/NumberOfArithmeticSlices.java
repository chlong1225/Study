package com.demo.algorithm.leetcode.medium;

/**
 * create by chenglong on 2023/12/19
 * description : 等差数列划分
 *
 * 如果一个数列至少有三个元素，并且任意两个相邻元素之差相同，则称该数列为等差数列。
 * 例如，[1,3,5,7,9]、[7,7,7,7] 和 [3,-1,-5,-9] 都是等差数列。
 * 给你一个整数数组nums，返回数组nums中所有为等差数组的子数组个数。
 * 子数组是数组中的一个连续序列。
 *
 * 示例 1：
 * 输入：nums = [1,2,3,4]
 * 输出：3
 * 解释：nums 中有三个子等差数组：[1, 2, 3]、[2, 3, 4] 和 [1,2,3,4] 自身。
 *
 * 示例 2：
 * 输入：nums = [1]
 * 输出：0
 *
 * 提示：
 * 1 <= nums.length <= 5000
 * -1000 <= nums[i] <= 1000
 */
public class NumberOfArithmeticSlices {

    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return 0;
        }
        int startIndex = 0;
        int diff = nums[startIndex + 1] - nums[startIndex];
        int curIndex = startIndex + 2;
        int sum = 0;
        while (curIndex < n) {
            if (nums[curIndex] - nums[curIndex - 1] == diff) {
                curIndex++;
            } else {
                //此时区间为：[startIndex,curIndex)
                int count = curIndex - startIndex;
                if (count >= 3) {
                    sum += (count - 2) * (count - 1) / 2;
                }
                startIndex = curIndex - 1;
                if (startIndex + 2 >= n) {
                    break;
                }
                diff = nums[startIndex + 1] - nums[startIndex];
                curIndex = startIndex + 2;
            }
        }
        int count = curIndex - startIndex;
        if (count >= 3) {
            sum += (count - 2) * (count - 1) / 2;
        }
        return sum;
    }
}
