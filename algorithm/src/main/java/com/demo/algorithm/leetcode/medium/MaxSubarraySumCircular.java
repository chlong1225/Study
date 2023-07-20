package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/7/20
 * @author chenglong
 * description : 环形子数组的最大和
 *
 * 给定一个长度为n的环形整数数组nums，返回nums的非空子数组的最大可能和。
 * 环形数组意味着数组的末端将会与开头相连呈环状。形式上，nums[i]的下一个元素是nums[(i + 1)%n] ，nums[i]的前一个元素是nums[(i-1+n)%n]。
 * 子数组最多只能包含固定缓冲区nums中的每个元素一次。形式上，对于子数组nums[i],nums[i+1], ..., nums[j]，不存在i <= k1, k2 <= j其中k1 % n == k2 % n。
 *
 * 示例 1：
 * 输入：nums = [1,-2,3,-2]
 * 输出：3
 * 解释：从子数组[3]得到最大和3
 *
 * 示例 2：
 * 输入：nums = [5,-3,5]
 * 输出：10
 * 解释：从子数组 [5,5] 得到最大和 5 + 5 = 10
 *
 * 示例 3：
 * 输入：nums = [3,-2,2,-3]
 * 输出：3
 * 解释：从子数组 [3] 和 [3,-2,2] 都可以得到最大和 3
 *
 * 提示：
 * n == nums.length
 * 1 <= n <= 3 * 10^4
 * -3 * 10^4<= nums[i] <= 3 * 10^4
 */
public class MaxSubarraySumCircular {

    public int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;
        int max = nums[0];
        int sum = nums[0];
        //1，获取0～n-1之间连续的最大值
        for (int i = 1; i < n; i++) {
            sum = Math.max(nums[i], sum + nums[i]);
            max = Math.max(max, sum);
        }
        //2，跨越环形，区间对应：[0,i]与[j,n-1]。此时i<j
        int[] leftSums = new int[n];
        int[] leftMax = new int[n];
        leftSums[0] = nums[0];
        leftMax[0] = nums[0];
        for (int i = 1; i < n; i++) {
            leftSums[i] = leftSums[i - 1] + nums[i];
            leftMax[i] = Math.max(leftMax[i - 1], leftSums[i]);
        }
        int[] rightSums = new int[n];
        int[] rightMax = new int[n];
        rightSums[n - 1] = nums[n - 1];
        rightMax[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightSums[i] = rightSums[i + 1] + nums[i];
            rightMax[i] = Math.max(rightMax[i + 1], rightSums[i]);
        }
        for (int i = 0; i < n - 1; i++) {
            int tem = leftMax[i] + rightMax[i + 1];
            if (max < tem) {
                max = tem;
            }
        }
        return max;
    }
}
