package com.demo.algorithm.leetcode.contest.doubleweek75;

/**
 * Created by chl on 2022/4/12.
 * description : 数组的三角和
 *
 * 给你一个下标从0开始的整数数组nums，其中nums[i]是0到9之间（两者都包含）的一个数字。
 * nums的三角和是执行以下操作以后最后剩下元素的值：
 * nums初始包含n个元素。如果n == 1，终止操作。否则，创建一个新的下标从0开始的长度为n-1的整数数组newNums。
 * 对于满足0<=i<n-1的下标i，newNums[i]赋值为(nums[i] + nums[i+1])%10，%表示取余运算。
 * 将newNums替换数组nums。
 * 从步骤1开始重复整个过程。
 * 请你返回nums的三角和。
 *
 * 示例 1：
 * 输入：nums = [1,2,3,4,5]
 * 输出：8
 * 解释：
 * 上图展示了得到数组三角和的过程。
 *
 * 示例 2：
 * 输入：nums = [5]
 * 输出：5
 * 解释：
 * 由于 nums 中只有一个元素，数组的三角和为这个元素自己。
 *
 * 提示：
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 9
 */
public class TriangularSum {

    public int triangularSum(int[] nums) {
        int n = nums.length;
        while (n > 1) {
            for (int i = 0; i < n - 1; i++) {
                nums[i] = (nums[i] + nums[i + 1]) % 10;
            }
            n--;
        }
        return nums[0];
    }
}
