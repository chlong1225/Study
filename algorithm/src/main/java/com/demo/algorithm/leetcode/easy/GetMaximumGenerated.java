package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/11/22
 * @author chenglong
 * description : 获取生成数组中的最大值
 *
 * 给你一个整数n。按下述规则生成一个长度为n+1的数组nums：
 * nums[0] = 0
 * nums[1] = 1
 * 当 2 <= 2 * i <= n 时，nums[2 * i] = nums[i]
 * 当 2 <= 2 * i + 1 <= n 时，nums[2 * i + 1] = nums[i] + nums[i + 1]
 * 返回生成数组nums中的最大值。
 *
 * 示例 1：
 * 输入：n = 7
 * 输出：3
 * 解释：根据规则：
 *   nums[0] = 0
 *   nums[1] = 1
 *   nums[(1 * 2) = 2] = nums[1] = 1
 *   nums[(1 * 2) + 1 = 3] = nums[1] + nums[2] = 1 + 1 = 2
 *   nums[(2 * 2) = 4] = nums[2] = 1
 *   nums[(2 * 2) + 1 = 5] = nums[2] + nums[3] = 1 + 2 = 3
 *   nums[(3 * 2) = 6] = nums[3] = 2
 *   nums[(3 * 2) + 1 = 7] = nums[3] + nums[4] = 2 + 1 = 3
 * 因此，nums = [0,1,1,2,1,3,2,3]，最大值 3
 *
 * 示例 2：
 * 输入：n = 2
 * 输出：1
 * 解释：根据规则，nums[0]、nums[1] 和 nums[2] 之中的最大值是 1
 *
 * 示例 3：
 * 输入：n = 3
 * 输出：2
 * 解释：根据规则，nums[0]、nums[1]、nums[2] 和 nums[3] 之中的最大值是 2
 *
 * 提示：
 * 0 <= n <= 100
 */
public class GetMaximumGenerated {

    public int getMaximumGenerated(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[] marks = new int[n + 1];
        marks[0] = 0;
        marks[1] = 1;
        int max = 1;
        int index = 2;
        while (index <= n) {
            if (index % 2 == 0) {
                marks[index] = marks[index / 2];
            } else {
                marks[index] = marks[index / 2] + marks[index / 2 + 1];
            }
            if (marks[index] > max) {
                max = marks[index];
            }
            index++;
        }
        return max;
    }
}
