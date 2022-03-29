package com.demo.algorithm.leetcode.contest.week286;

/**
 * Created by chl on 2022/3/29.
 * description : 美化数组的最少删除数
 *
 * 给你一个下标从0开始的整数数组nums，如果满足下述条件，则认为数组nums是一个美丽数组 ：
 * nums.length为偶数
 * 对所有满足i%2==0的下标i，nums[i]!= nums[i + 1]均成立
 * 注意，空数组同样认为是美丽数组。
 * 你可以从nums中删除任意数量的元素。当你删除一个元素时，被删除元素右侧的所有元素将会向左移动一个单位以填补空缺，而左侧的元素将会保持不变 。
 * 返回使 nums 变为美丽数组所需删除的 最少元素数目。
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,2,3,5]
 * 输出：1
 * 解释：可以删除 nums[0] 或 nums[1] ，这样得到的 nums = [1,2,3,5] 是一个美丽数组。可以证明，要想使 nums 变为美丽数组，至少需要删除 1 个元素。
 *
 * 示例 2：
 * 输入：nums = [1,1,2,2,3,3]
 * 输出：2
 * 解释：可以删除 nums[0] 和 nums[5] ，这样得到的 nums = [1,2,2,3] 是一个美丽数组。可以证明，要想使 nums 变为美丽数组，至少需要删除 2 个元素。
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^5
 */
public class MinDeletion {

    public int minDeletion(int[] nums) {
        int length = nums.length;
        int count = 0;
        int first = nums[0];
        for (int i = 1; i < length; i++) {
            if (nums[i] != first) {
                count += 2;
                if (i + 1 < length) {
                    first = nums[i + 1];
                    i++;
                }
            }
        }
        return length - count;
    }
}
