package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/9/19
 * @author chenglong
 * description : 打家劫舍IV
 *
 * 沿街有一排连续的房屋。每间房屋内都藏有一定的现金。现在有一位小偷计划从这些房屋中窃取现金。
 * 由于相邻的房屋装有相互连通的防盗系统，所以小偷不会窃取相邻的房屋 。
 * 小偷的窃取能力定义为他在窃取过程中能从单间房屋中窃取的最大金额 。
 * 给你一个整数数组nums表示每间房屋存放的现金金额。形式上，从左起第i间房屋中放有nums[i]美元。
 * 另给你一个整数k，表示窃贼将会窃取的最少房屋数。小偷总能窃取至少k间房屋。
 * 返回小偷的最小窃取能力。
 *
 * 示例 1：
 * 输入：nums = [2,3,5,9], k = 2
 * 输出：5
 * 解释：
 * 小偷窃取至少 2 间房屋，共有 3 种方式：
 * - 窃取下标 0 和 2 处的房屋，窃取能力为 max(nums[0], nums[2]) = 5 。
 * - 窃取下标 0 和 3 处的房屋，窃取能力为 max(nums[0], nums[3]) = 9 。
 * - 窃取下标 1 和 3 处的房屋，窃取能力为 max(nums[1], nums[3]) = 9 。
 * 因此，返回 min(5, 9, 9) = 5 。
 *
 * 示例 2：
 * 输入：nums = [2,7,9,3,1], k = 2
 * 输出：2
 * 解释：共有 7 种窃取方式。窃取能力最小的情况所对应的方式是窃取下标 0 和 4 处的房屋。返回 max(nums[0], nums[4]) = 2 。
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= k <= (nums.length + 1)/2
 */
public class MinCapability {

    public int minCapability(int[] nums, int k) {
        //1，遍历查找房子最大，最小金额
        int start = nums[0];
        int end = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > end) {
                end = nums[i];
            }
            if (nums[i] < start) {
                start = nums[i];
            }
        }
        //2，二分查找满足条件的金额
        while (start < end) {
            int middle = (end - start) / 2 + start;
            if (checkMoney(middle, k, nums)) {
                //此时满足条件
                end = middle;
            } else {
                start = middle + 1;
            }
        }
        return start;
    }

    private boolean checkMoney(int max, int k, int[] nums) {
        int preIndex = -1;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= max) {
                if (preIndex == -1) {
                    count++;
                    preIndex = i;
                } else {
                    if (i - preIndex > 1) {
                        count++;
                        preIndex = i;
                    }
                }
            }
        }
        return count >= k;
    }
}
