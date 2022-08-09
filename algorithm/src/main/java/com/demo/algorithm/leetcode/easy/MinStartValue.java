package com.demo.algorithm.leetcode.easy;

/**
 * create on 2022/8/9
 * @author chenglong
 * description : 逐步求和得到正数的最小值
 *
 * 给你一个整数数组nums。你可以选定任意的正数startValue作为初始值。
 * 你需要从左到右遍历nums数组，并将startValue依次累加上nums数组中的值。
 * 请你在确保累加和始终大于等于1的前提下，选出一个最小的正数作为startValue 。
 *
 * 示例 1：
 * 输入：nums = [-3,2,-3,4,2]
 * 输出：5
 * 解释：如果你选择 startValue = 4，在第三次累加时，和小于 1 。
 *                 累加求和
 *                startValue = 4 | startValue = 5 | nums
 *                  (4 -3 ) = 1  | (5 -3 ) = 2    |  -3
 *                  (1 +2 ) = 3  | (2 +2 ) = 4    |   2
 *                  (3 -3 ) = 0  | (4 -3 ) = 1    |  -3
 *                  (0 +4 ) = 4  | (1 +4 ) = 5    |   4
 *                  (4 +2 ) = 6  | (5 +2 ) = 7    |   2
 *
 * 示例 2：
 * 输入：nums = [1,2]
 * 输出：1
 * 解释：最小的 startValue 需要是正数。
 *
 * 示例 3：
 * 输入：nums = [1,-2,-3]
 * 输出：5
 *
 * 提示：
 * 1 <= nums.length <= 100
 * -100 <= nums[i] <= 100
 */
public class MinStartValue {

    public int minStartValue(int[] nums) {
        //获取最小的前缀和
        int min = nums[0];
        int length = nums.length;
        for (int i = 1; i < length; i++) {
            nums[i] += nums[i - 1];
            if (nums[i] < min) {
                min = nums[i];
            }
        }
        return Math.max(1, 1 - min);
    }
}
