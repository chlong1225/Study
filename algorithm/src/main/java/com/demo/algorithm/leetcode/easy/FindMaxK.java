package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/5/15
 * @author chenglong
 * description : 与对应负数同时存在的最大正整数
 *
 * 给你一个不包含任何零的整数数组nums，找出自身与对应的负数都在数组中存在的最大正整数k。
 * 返回正整数k，如果不存在这样的整数，-1 。
 *
 * 示例 1：
 * 输入：nums = [-1,2,-3,3]
 * 输出：3
 * 解释：3 是数组中唯一一个满足题目要求的 k 。
 *
 * 示例 2：
 * 输入：nums = [-1,10,6,7,-7,1]
 * 输出：7
 * 解释：数组中存在 1 和 7 对应的负数，7 的值更大。
 *
 * 示例 3：
 * 输入：nums = [-10,8,6,7,-2,-3]
 * 输出：-1
 * 解释：不存在满足题目要求的 k ，返回 -1 。
 *
 * 提示：
 * 1 <= nums.length <= 1000
 * -1000 <= nums[i] <= 1000
 * nums[i] != 0
 */
public class FindMaxK {

    public int findMaxK(int[] nums) {
        int max = -1;
        //记录正数是否出现
        boolean[] marks1 = new boolean[1001];
        //记录负数是否出现
        boolean[] marks2 = new boolean[1001];
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            if (cur > 0) {
                marks1[cur] = true;
                if (marks2[cur]) {
                    if (cur > max) {
                        max = cur;
                    }
                }
            } else {
                cur = -cur;
                marks2[cur] = true;
                if (marks1[cur]) {
                    if (cur > max) {
                        max = cur;
                    }
                }
            }
        }
        return max;
    }
}
