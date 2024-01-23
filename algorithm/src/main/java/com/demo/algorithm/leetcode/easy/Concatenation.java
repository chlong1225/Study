package com.demo.algorithm.leetcode.easy;

/**
 * create by chenglong on 2024/1/23
 * description : 数组串联
 *
 * 给你一个长度为n的整数数组nums。请你构建一个长度为2n的答案数组ans，数组下标从0开始计数，对于所有0 <= i < n的i，满足下述所有要求：
 * ans[i] == nums[i]
 * ans[i + n] == nums[i]
 * 具体而言，ans由两个nums数组串联形成。
 * 返回数组 ans 。
 *
 * 示例 1：
 * 输入：nums = [1,2,1]
 * 输出：[1,2,1,1,2,1]
 * 解释：数组 ans 按下述方式形成：
 * - ans = [nums[0],nums[1],nums[2],nums[0],nums[1],nums[2]]
 * - ans = [1,2,1,1,2,1]
 *
 * 示例 2：
 * 输入：nums = [1,3,2,1]
 * 输出：[1,3,2,1,1,3,2,1]
 * 解释：数组 ans 按下述方式形成：
 * - ans = [nums[0],nums[1],nums[2],nums[3],nums[0],nums[1],nums[2],nums[3]]
 * - ans = [1,3,2,1,1,3,2,1]
 *
 * 提示：
 * n == nums.length
 * 1 <= n <= 1000
 * 1 <= nums[i] <= 1000
 */
public class Concatenation {

    public int[] getConcatenation(int[] nums) {
        int n = nums.length;
        int[] answer = new int[2 * n];
        for (int i = 0; i < n; i++) {
            answer[i] = nums[i];
            answer[i + n] = nums[i];
        }
        return answer;
    }
}
