package com.demo.algorithm.leetcode.medium;

import java.util.Arrays;

/**
 * create on 2023/11/23
 * @author chenglong
 * description : 统计公平数对的数目
 *
 * 给你一个下标从0开始、长度为n的整数数组nums，和两个整数lower和upper，返回公平数对的数目 。
 * 如果(i,j)数对满足以下情况，则认为它是一个公平数对：
 * 0 <= i < j < n，且
 * lower <= nums[i] + nums[j] <= upper
 *
 * 示例 1：
 * 输入：nums = [0,1,7,4,4,5], lower = 3, upper = 6
 * 输出：6
 * 解释：共计 6 个公平数对：(0,3)、(0,4)、(0,5)、(1,3)、(1,4) 和 (1,5) 。
 *
 * 示例 2：
 * 输入：nums = [1,7,9,2,5], lower = 11, upper = 11
 * 输出：1
 * 解释：只有单个公平数对：(2,3) 。
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * nums.length == n
 * -10^9 <= nums[i] <= 10^9
 * -10^9 <= lower <= upper <= 10^9
 */
public class CountFairPairs {

    public long countFairPairs(int[] nums, int lower, int upper) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        //1，对数据源进行排序
        Arrays.sort(nums);
        long sum = 0;
        for (int i = 0; i < n - 1; i++) {
            int min = lower - nums[i];
            int max = upper - nums[i];
            int minIndex = findIndexByMin(min, i + 1, nums);
            if (minIndex != -1) {
                int maxIndex = findIndexByMax(max, i + 1, nums);
                if (maxIndex != -1 && maxIndex >= minIndex) {
                    sum += (maxIndex - minIndex + 1);
                }
            }
        }
        return sum;
    }

    //查找不大于max的位置，从index = start开始查找
    private int findIndexByMax(int max, int start, int[] nums) {
        int end = nums.length - 1;
        if (nums[end] <= max) {
            return end;
        }
        if (nums[start] > max) {
            return -1;
        }
        while (start < end) {
            int middle = (end - start) / 2 + start;
            if (nums[middle] > max) {
                end = middle - 1;
            } else {
                start = middle + 1;
                if (nums[start] > max) {
                    return middle;
                }
            }
        }
        return start;
    }

    //查找不小于min的位置，从index = start开始查找
    private int findIndexByMin(int min, int start, int[] nums) {
        int end = nums.length - 1;
        if (nums[end] < min) {
            return -1;
        }
        if (nums[start] >= min) {
            return start;
        }
        while (start < end) {
            int middle = (end - start) / 2 + start;
            if (nums[middle] < min) {
                start = middle + 1;
            } else {
                end = middle;
            }
        }
        return start;
    }
}
