package com.demo.algorithm.leetcode.medium;

/**
 * Created by chl on 2021/10/28.
 * description : 跳跃游戏
 *
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标。
 *
 * 示例 1：
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 *
 * 示例 2：
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 *  
 * 提示：
 * 1 <= nums.length <= 3 * 104
 * 0 <= nums[i] <= 105
 */
public class JumpGame {

    //使用正向贪心算法
    public boolean canJump(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return true;
        }
        int maxJump = 0;
        for (int i = 0; i < length - 1; i++) {
            maxJump = Math.max(maxJump, i + nums[i]);
            //当前位置包含之前能够jump最大的位置是自己,此时nums[i]=0.无法进入下一位
            if (maxJump == i) {
                return false;
            }
        }
        return true;
    }

    //反向遍历排查
    public boolean canJump2(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return true;
        }
        boolean result = true;
        int compare = 0;
        for (int i = length - 2; i >= 0; i--) {
            if (nums[i] == 0 && result) {
                compare = i;
                result = false;
            } else {
                if (!result) {
                    if (nums[i] + i > compare) {
                        result = true;
                    }
                }
            }
        }
        return result;
    }
}
