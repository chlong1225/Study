package com.demo.algorithm.leetcode.medium;

/**
 * create on 9/30/21
 *
 * @author chenglong
 * description : 最接近的三数之和
 * <p>
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * 示例：
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 *  
 * 提示：
 * 3 <= nums.length <= 10^3
 * -10^3 <= nums[i] <= 10^3
 * -10^4 <= target <= 10^4
 */
public class ThreeSumNear {

    public static int threeSumClosest(int[] nums, int target) {
        int sum = nums[0] + nums[1] + nums[2];
        if (target == sum) {
            return sum;
        }
        int check;
        int length = nums.length;
        for (int i = 0; i < length - 2; i++) {
            for (int j = i + 1; j < length - 1; j++) {
                for (int k = j + 1; k < length; k++) {
                    check = nums[i] + nums[j] + nums[k];
                    if (check == target) {
                        return check;
                    }
                    if (Math.abs(target - check) < Math.abs(target - sum)) {
                        sum = check;
                    }
                }
            }
        }
        return sum;
    }
}
