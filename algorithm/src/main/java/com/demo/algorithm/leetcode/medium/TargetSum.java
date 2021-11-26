package com.demo.algorithm.leetcode.medium;

/**
 * Created by chl on 2021/11/26.
 * description : 目标和
 *
 * 给你一个整数数组 nums 和一个整数 target 。
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 *
 * 示例 1：
 * 输入：nums = [1,1,1,1,1], target = 3
 * 输出：5
 * 解释：一共有 5 种方法让最终目标和为 3 。
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 *
 * 示例 2：
 * 输入：nums = [1], target = 1
 * 输出：1
 *  
 * 提示：
 * 1 <= nums.length <= 20
 * 0 <= nums[i] <= 1000
 * 0 <= sum(nums[i]) <= 1000
 * -1000 <= target <= 1000
 */
public class TargetSum {

    private int[] mNums;

    public int findTargetSumWays(int[] nums, int target) {
        mNums = nums;
        int length = nums.length;
        int max = 0;
        int min = 0;
        for (int i = 0; i < length; i++) {
            max += nums[i];
            min -= nums[i];
        }
        if (target > max || target < min) {
            return 0;
        }
        if (target == max || target == min) {
            //对于0。+0与-0效果一致
            int result = 1;
            for (int i = 0; i < length; i++) {
                if (nums[i] == 0) {
                    result *= 2;
                }
            }
            return result;
        }
        //使用深度搜索dfs
        int index = length - 1;
        //限定最小最大值
        min += nums[index];
        max -= nums[index];
        //通过+nums[index]的方式等于target之前的目标值
        int add = target - nums[index];
        //通过-nums[index]的方式等于target之前的目标值
        int reduce = target + nums[index];
        index--;
        return dfs(add, min, max, index) + dfs(reduce, min, max, index);
    }

    private int dfs(int target, int min, int max, int index) {
        if (index < 0) {
            return 0;
        }
        if (target > max || target < min) {
            return 0;
        }
        if (target == min || target == max) {
            int result = 1;
            for (int i = 0; i <= index; i++) {
                if (mNums[i] == 0) {
                    result *= 2;
                }
            }
            return result;
        }
        min += mNums[index];
        max -= mNums[index];
        int add = target - mNums[index];
        int reduce = target + mNums[index];
        index--;
        return dfs(add, min, max, index) + dfs(reduce, min, max, index);
    }
}
