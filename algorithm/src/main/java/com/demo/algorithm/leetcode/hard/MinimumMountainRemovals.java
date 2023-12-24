package com.demo.algorithm.leetcode.hard;

/**
 * create on 2023/12/22
 * @author chenglong
 * description : 得到山形数组的最少删除次数
 *
 * 我们定义arr是山形数组当且仅当它满足：
 * arr.length >= 3
 * 存在某个下标i（从0开始）满足0 < i < arr.length - 1 且：
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * 给你整数数组nums，请你返回将nums变成山形状数组的最少删除次数。
 *
 * 示例 1：
 * 输入：nums = [1,3,1]
 * 输出：0
 * 解释：数组本身就是山形数组，所以我们不需要删除任何元素。
 *
 * 示例 2：
 * 输入：nums = [2,1,1,5,6,2,3,1]
 * 输出：3
 * 解释：一种方法是将下标为 0，1 和 5 的元素删除，剩余元素为 [1,5,6,3,1] ，是山形数组。
 *
 * 提示：
 * 3 <= nums.length <= 1000
 * 1 <= nums[i] <= 10^9
 * 题目保证nums删除一些元素后一定能得到山形数组。
 */
public class MinimumMountainRemovals {
    public int minimumMountainRemovals(int[] nums) {
        int n = nums.length;
        //1，使用动态规划获取以当前位置结尾的最长递增子序列
        int[] lefts = new int[n];
        lefts[0] = 1;
        for (int i = 1; i < n; i++) {
            lefts[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    int tem = lefts[j] + 1;
                    if (tem > lefts[i]) {
                        lefts[i] = tem;
                    }
                }
            }
        }
        //2，使用动态规划获取以当前位置结尾的最长递减子序列
        int[] rights = new int[n];
        rights[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            rights[i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (nums[i] > nums[j]) {
                    int tem = rights[j] + 1;
                    if (tem > rights[i]) {
                        rights[i] = tem;
                    }
                }
            }
        }
        //3，统计最长山形子数组
        int max = 0;
        for (int i = 1; i < n - 1; i++) {
            if (lefts[i] >= 2 && rights[i] >= 2) {
                int count = lefts[i] + rights[i] - 1;
                if (count > max) {
                    max = count;
                }
            }
        }
        return n-max;
    }
}
