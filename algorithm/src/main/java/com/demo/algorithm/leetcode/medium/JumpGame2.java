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

    //使用贪心算法，遍历查找最近到达目前的位置，然后重置目标。这个是反向遍历
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

    //使用贪心算法，正向遍历。遍历查询跳转的下一步是能够达到最远的方案。如：{2,3,1,1,4}
    public int jump2(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return 0;
        }
        //执行的步数
        int step = 0;
        //能够跳转的最大位置
        int maxRange = 0;
        //两个maxRange范围内的位置所能跳转到的最大位置
        int maxJump = 0;
        //使用length-1限定，防止maxRange = length-1时需要再跳一步
        for (int i = 0; i < length - 1; i++) {
            //i == 0 时最大位置可以跳转到2，在1～2之间一步。
            maxJump = Math.max(maxJump, i + nums[i]);
            if (i == maxRange) {
                //将1～2之间位置跳转到的最大位置作为下一个边界，则0～maxRange之间均可以step步到达
                maxRange = maxJump;
                step++;
            }
        }
        return step;
    }
}
