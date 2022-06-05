package com.demo.algorithm.leetcode.contest.week296;

/**
 * Created by chl on 2022/6/5.
 * description : 极大极小游戏
 *
 * 给你一个下标从0开始的整数数组nums ，其长度是2的幂。
 * 对nums执行下述算法：
 * 设n等于nums的长度，如果 n == 1 ，终止 算法过程。否则，创建 一个新的整数数组newNums ，新数组长度为 n / 2 ，下标从 0 开始。
 * 对于满足0 <= i < n / 2 的每个 偶数 下标 i ，将 newNums[i] 赋值 为 min(nums[2 * i], nums[2 * i + 1]) 。
 * 对于满足0 <= i < n / 2 的每个 奇数 下标 i ，将 newNums[i] 赋值 为 max(nums[2 * i], nums[2 * i + 1]) 。
 * 用 newNums 替换 nums 。
 * 从步骤1开始重复整个过程。
 * 执行算法后，返回nums中剩下的那个数字。
 *
 * 示例 1：
 * 输入：nums = [1,3,5,2,4,8,2,2]
 * 输出：1
 * 解释：重复执行算法会得到下述数组。
 * 第一轮：nums = [1,5,4,2]
 * 第二轮：nums = [1,4]
 * 第三轮：nums = [1]
 * 1 是最后剩下的那个数字，返回 1 。
 *
 * 示例 2：
 * 输入：nums = [3]
 * 输出：3
 * 解释：3 就是最后剩下的数字，返回 3 。
 *
 * 提示：
 * 1 <= nums.length <= 1024
 * 1 <= nums[i] <= 10^9
 * nums.length 是 2 的幂
 */
public class MinMaxGame {

    public int minMaxGame(int[] nums) {
        int length = nums.length;
        while (length > 1) {
            length >>= 1;
            for (int i = 0; i < length; i++) {
                if (i % 2 == 0) {
                    nums[i] = Math.min(nums[2 * i], nums[2 * i + 1]);
                } else {
                    nums[i] = Math.max(nums[2 * i], nums[2 * i + 1]);
                }
            }
        }
        return nums[0];
    }
}
