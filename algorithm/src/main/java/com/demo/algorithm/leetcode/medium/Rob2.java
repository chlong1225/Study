package com.demo.algorithm.leetcode.medium;

/**
 * create on 11/23/21
 * @author chenglong
 * description : 打家劫舍II
 *
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
 *
 * 示例 1：
 * 输入：nums = [2,3,2]
 * 输出：3
 * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 *
 * 示例 2：
 * 输入：nums = [1,2,3,1]
 * 输出：4
 * 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 *
 * 示例 3：
 * 输入：nums = [0]
 * 输出：0
 *  
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 1000
 */
public class Rob2 {

    public int rob(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        if (length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        if (length == 3) {
            return Math.max(Math.max(nums[0], nums[1]), nums[2]);
        }
        //拆分两种情况：1，第一家打劫，最后一家忽略；2，第一家忽略，最后一家可以被打劫
        //1，第一家打劫，最后一家忽略。初始化条件
        int preUnRob = nums[0];
        int preRob = nums[0];
        int unRob = nums[0];
        int rob = nums[0];
        for (int i = 2; i < length - 1; i++) {
            int num = nums[i];
            unRob = Math.max(preUnRob, preRob);
            rob = Math.max(preRob, preUnRob + num);
            if (i < length - 2) {
                preUnRob = unRob;
                preRob = rob;
            }
        }
        int max = Math.max(unRob, rob);
        //2,第一家忽略，最后一家打劫
        preUnRob = 0;
        preRob = nums[1];
        unRob = 0;
        rob = nums[1];
        for (int i = 2; i < length; i++) {
            int num = nums[i];
            unRob = Math.max(preUnRob, preRob);
            rob = Math.max(preRob, preUnRob + num);
            if (i < length - 1) {
                preUnRob = unRob;
                preRob = rob;
            }
        }
        return Math.max(max, Math.max(unRob, rob));
    }
}
