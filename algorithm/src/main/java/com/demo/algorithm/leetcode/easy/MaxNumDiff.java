package com.demo.algorithm.leetcode.easy;

/**
 * Created by chl on 2022/2/26.
 * description : 增量元素之间的最大差值
 *
 * 给你一个下标从0开始的整数数组nums，该数组的大小为n，请你计算nums[j]-nums[i]能求得的最大差值，其中0<=i<j<n且nums[i]< nums[j]。
 * 返回最大差值。如果不存在满足要求的i和j，返回-1。
 *
 * 示例 1：
 * 输入：nums = [7,1,5,4]
 * 输出：4
 * 解释：
 * 最大差值出现在 i = 1 且 j = 2 时，nums[j] - nums[i] = 5 - 1 = 4 。
 * 注意，尽管 i = 1 且 j = 0 时 ，nums[j] - nums[i] = 7 - 1 = 6 > 4 ，但 i > j 不满足题面要求，所以 6 不是有效的答案。
 *
 * 示例 2：
 * 输入：nums = [9,4,3,2]
 * 输出：-1
 * 解释：
 * 不存在同时满足 i < j 和 nums[i] < nums[j] 这两个条件的 i, j 组合。
 *
 * 示例 3：
 * 输入：nums = [1,5,2,10]
 * 输出：9
 * 解释：
 * 最大差值出现在 i = 0 且 j = 3 时，nums[j] - nums[i] = 10 - 1 = 9 。
 *
 * 提示：
 * n == nums.length
 * 2 <= n <= 1000
 * 1 <= nums[i] <= 10^9
 */
public class MaxNumDiff {

    //数据量少使用暴力枚举的方式
    public int maximumDifference(int[] nums) {
        int maxDiff = 0;
        int length = nums.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                int diff = nums[j] - nums[i];
                if (diff > maxDiff) {
                    maxDiff = diff;
                }
            }
        }
        return maxDiff == 0 ? -1 : maxDiff;
    }

    //使用单调栈方式
    public int maximumDifference2(int[] nums) {
        int maxDiff = 0;
        int length = nums.length;
        //用于记录当前位置后的最大值。即i固定时，满足i<j的最大nums[j]
        int maxNext = nums[length - 1];
        for (int i = length - 2; i >= 0; i--) {
            int diff = maxNext - nums[i];
            if (diff > maxDiff) {
                maxDiff = diff;
            }
            //更新从i~length-1的最大值
            if (nums[i] > maxNext) {
                maxNext = nums[i];
            }
        }
        return maxDiff == 0 ? -1 : maxDiff;
    }
}
