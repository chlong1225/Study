package com.demo.algorithm.leetcode.hard;

/**
 * create on 12/8/21
 * @author chenglong
 * description : 三个无重叠子数组的最大和
 *
 * 给你一个整数数组 nums 和一个整数 k ，找出三个长度为 k 、互不重叠、且 3 * k 项的和最大的子数组，并返回这三个子数组。
 * 以下标的数组形式返回结果，数组中的每一项分别指示每个子数组的起始位置（下标从 0 开始）。如果有多个结果，返回字典序最小的一个。
 *
 * 示例 1：
 * 输入：nums = [1,2,1,2,6,7,5,1], k = 2
 * 输出：[0,3,5]
 * 解释：子数组 [1, 2], [2, 6], [7, 5] 对应的起始下标为 [0, 3, 5]。
 * 也可以取 [2, 1], 但是结果 [1, 3, 5] 在字典序上更大。
 *
 * 示例 2：
 * 输入：nums = [1,2,1,2,1,2,1,2,1], k = 2
 * 输出：[0,2,4]
 *  
 * 提示：
 * 1 <= nums.length <= 2 * 104
 * 1 <= nums[i] < 216
 * 1 <= k <= floor(nums.length / 3)
 */
public class MaxThreeInterval {

    //使用暴力解法
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int length = nums.length;
        if (length < 3 * k) {
            return null;
        }
        //1，对区间进行求和
        length -= k;
        int[] sums = new int[length];
        int pre = 0;
        for (int i = 0; i < k; i++) {
            pre += nums[i];
        }
        sums[0] = pre;
        for (int i = 1; i < length; i++) {
            pre = pre - nums[i - 1] + nums[k - 1 + i];
            sums[i] = pre;
        }
        //2，从sums中找到三个值的和最大并且保证间距为k
        int[] result = new int[3];
        result[1] = k;
        result[2] = 2 * k;
        int max = sums[0] + sums[k] + sums[2 * k];
        for (int i = 1; i < length - 2 * k; i++) {
            for (int j = i + k; j < length - k; j++) {
                for (int l = j + k; l < length; l++) {
                    int tem = sums[i] + sums[j] + sums[l];
                    if (tem > max) {
                        max = tem;
                        result[0] = i;
                        result[1] = j;
                        result[2] = l;
                    }
                }
            }
        }
        return result;
    }
}
