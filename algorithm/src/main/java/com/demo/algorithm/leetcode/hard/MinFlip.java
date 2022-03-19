package com.demo.algorithm.leetcode.hard;

/**
 * Created by chl on 2022/3/9.
 * description : K连续位的最小翻转次数
 *
 * 给定一个二进制数组nums和一个整数k 。
 * k位翻转就是从nums中选择一个长度为k的子数组，同时把子数组中的每一个0都改成1，把子数组中的每一个1都改成0。
 * 返回数组中不存在0所需的最小k位翻转次数。如果不可能，则返回-1。
 * 子数组是数组的连续部分。
 *
 * 示例 1：
 * 输入：nums = [0,1,0], K = 1
 * 输出：2
 * 解释：先翻转 A[0]，然后翻转 A[2]。
 *
 * 示例 2：
 * 输入：nums = [1,1,0], K = 2
 * 输出：-1
 * 解释：无论我们怎样翻转大小为 2 的子数组，我们都不能使数组变为 [1,1,1]。
 *
 * 示例 3：
 * 输入：nums = [0,0,0,1,0,1,1,0], K = 3
 * 输出：3
 * 解释：
 * 翻转 A[0],A[1],A[2]:A变成 [1,1,1,1,0,1,1,0]
 * 翻转 A[4],A[5],A[6]:A变成 [1,1,1,1,1,0,0,0]
 * 翻转 A[5],A[6],A[7]:A变成 [1,1,1,1,1,1,1,1]
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= k <= nums.length
 */
public class MinFlip {

    public int minKBitFlips(int[] nums, int k) {
        int length = nums.length;
        int step = 0;
        int add = 0;
        int[] counts = new int[length + 1];
        for (int i = 0; i < length; i++) {
            add += counts[i];
            if ((add + nums[i]) % 2 == 0) {
                //此时需要翻转
                if (i + k > length) {
                    //此时不够翻转的数量
                    return -1;
                }
                counts[i + 1]++;
                counts[i + k]--;
                step++;
            }
        }
        return step;
    }

}
