package com.demo.algorithm.leetcode.hard;

/**
 * create on 2023/5/12
 * @author chenglong
 * description : 翻转子数组得到最大的数组值
 *
 * 给你一个整数数组nums。「数组值」定义为所有满足0 <= i < nums.length-1的|nums[i]-nums[i+1]|的和。
 * 你可以选择给定数组的任意子数组，并将该子数组翻转。但你只能执行这个操作一次 。
 * 请你找到可行的最大数组值。
 *
 * 示例 1：
 * 输入：nums = [2,3,1,5,4]
 * 输出：10
 * 解释：通过翻转子数组 [3,1,5] ，数组变成 [2,5,1,3,4] ，数组值为 10 。
 *
 * 示例 2：
 * 输入：nums = [2,4,9,24,2,1,10]
 * 输出：68
 *
 * 提示：
 * 1 <= nums.length <= 3*10^4
 * -10^5 <= nums[i] <= 10^5
 */
public class MaxValueAfterReverse {

    public int maxValueAfterReverse(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        //1，计算翻转前的数组值
        int base = 0;
        for (int i = 0; i < n - 1; i++) {
            base += Math.abs(nums[i] - nums[i + 1]);
        }
        int max = 0;
        //2，计算起始开始翻转的最大增量
        for (int i = 1; i < n - 1; i++) {
            int pre = Math.abs(nums[i + 1] - nums[i]);
            int after = Math.abs(nums[i + 1] - nums[0]);
            int add = after - pre;
            if (max < add) {
                max = add;
            }
        }
        //3，计算截止到尾部翻转的最大增量
        for (int i = n - 2; i >= 1; i--) {
            int pre = Math.abs(nums[i] - nums[i - 1]);
            int after = Math.abs(nums[n - 1] - nums[i - 1]);
            int add = after - pre;
            if (max < add) {
                max = add;
            }
        }
        //4，计算中间部分翻转的最大增量
        int[] rightMins = new int[n];
        int[] rightMaxs = new int[n];
        rightMaxs[n - 1] = Integer.MIN_VALUE;
        rightMins[n - 1] = Integer.MAX_VALUE;
        for (int i = n - 2; i >= 0; i--) {
            rightMaxs[i] = Math.min(nums[i + 1], nums[i]);
            rightMaxs[i] = Math.max(rightMaxs[i], rightMaxs[i + 1]);
            rightMins[i] = Math.max(nums[i + 1], nums[i]);
            rightMins[i] = Math.min(rightMins[i], rightMins[i + 1]);
        }
        for (int i = 1; i < n - 2; i++) {
            //当左边较小，右边较大时
            int leftMin = Math.max(nums[i], nums[i - 1]);
            int add = 2 * (rightMaxs[i] - leftMin);
            if (add > max) {
                max = add;
            }
            //当左边较大，右边较小时
            int leftMax = Math.min(nums[i], nums[i - 1]);
            add = 2 * (leftMax - rightMins[i]);
            if (add > max) {
                max = add;
            }
        }
        return base + max;
    }
}
