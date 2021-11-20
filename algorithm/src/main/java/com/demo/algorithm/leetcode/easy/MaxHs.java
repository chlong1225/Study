package com.demo.algorithm.leetcode.easy;

/**
 * Created by chl on 2021/11/20.
 * description : 最长的和谐子序列
 *
 * 和谐数组是指一个数组里元素的最大值和最小值之间的差别正好是 1 。
 * 现在，给你一个整数数组nums，请你在所有可能的子序列中找到最长的和谐子序列的长度。
 * 数组的子序列是一个由数组派生出来的序列，它可以通过删除一些元素或不删除元素、且不改变其余元素的顺序而得到。
 *
 * 示例 1：
 * 输入：nums = [1,3,2,2,5,2,3,7]
 * 输出：5
 * 解释：最长的和谐子序列是 [3,2,2,2,3]
 *
 * 示例 2：
 * 输入：nums = [1,2,3,4]
 * 输出：2
 *
 * 示例 3：
 * 输入：nums = [1,1,1,1]
 * 输出：0
 *
 * 提示：
 * 1 <= nums.length <= 2 * 104
 * -109 <= nums[i] <= 109
 */
public class MaxHs {

    public static int findLHS(int[] nums) {
        int max = 0;
        int length = nums.length;
        if (length == 1) {
            return max;
        }
        for (int i = 0; i < length - 1; i++) {
            int compare = nums[i];
            int maxCount = 0;
            int minCount = 0;
            int sameCount = 1;
            if (max >= (length - i)) {
                return max;
            }
            for (int j = i + 1; j < length; j++) {
                if (nums[j] == compare - 1) {
                    minCount++;
                } else if (nums[j] == compare + 1) {
                    maxCount++;
                } else if (nums[j] == compare) {
                    sameCount++;
                }
            }
            if (minCount > 0) {
                minCount += sameCount;
            }
            if (maxCount > 0) {
                maxCount += sameCount;
            }
            max = Math.max(max, Math.max(maxCount, minCount));
        }
        return max;
    }


}
