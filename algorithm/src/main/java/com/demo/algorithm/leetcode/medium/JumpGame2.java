package com.demo.algorithm.leetcode.medium;

/**
 * create on 10/27/21
 * @author chenglong
 * description : 跳跃游戏II
 *
 * 给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * 假设你总是可以到达数组的最后一个位置。
 *
 * 示例 1:
 * 输入: nums = [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 *
 * 示例 2:
 * 输入: nums = [2,3,0,1,4]
 * 输出: 2
 *  
 * 提示:
 * 1 <= nums.length <= 104
 * 0 <= nums[i] <= 1000
 */
public class JumpGame2 {

    //使用贪心算法，遍历查找最近到达目前的位置，然后重置目标
    public int jump(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return 0;
        }
        int step = 0;
        int target = length - 1;
        while (target > 0) {
            for (int i = 0; i < target; i++) {
                if (nums[i] + i >= target) {
                    target = i;
                    step++;
                    break;
                }
            }
        }
        return step;
    }
}
