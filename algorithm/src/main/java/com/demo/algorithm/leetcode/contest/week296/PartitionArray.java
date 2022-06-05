package com.demo.algorithm.leetcode.contest.week296;

import java.util.Arrays;

/**
 * Created by chl on 2022/6/5.
 * description : 划分数组使最大差为K
 *
 * 给你一个整数数组nums和一个整数k 。你可以将nums划分成一个或多个子序列，使nums中的每个元素都恰好出现在一个子序列中。
 * 在满足每个子序列中最大值和最小值之间的差值最多为k的前提下，返回需要划分的最少子序列数目。
 * 子序列本质是一个序列，可以通过删除另一个序列中的某些元素（或者不删除）但不改变剩下元素的顺序得到。
 *
 * 示例 1：
 * 输入：nums = [3,6,1,2,5], k = 2
 * 输出：2
 * 解释：
 * 可以将 nums 划分为两个子序列 [3,1,2] 和 [6,5] 。
 * 第一个子序列中最大值和最小值的差值是 3 - 1 = 2 。
 * 第二个子序列中最大值和最小值的差值是 6 - 5 = 1 。
 * 由于创建了两个子序列，返回 2 。可以证明需要划分的最少子序列数目就是 2 。
 *
 * 示例 2：
 * 输入：nums = [1,2,3], k = 1
 * 输出：2
 * 解释：
 * 可以将 nums 划分为两个子序列 [1,2] 和 [3] 。
 * 第一个子序列中最大值和最小值的差值是 2 - 1 = 1 。
 * 第二个子序列中最大值和最小值的差值是 3 - 3 = 0 。
 * 由于创建了两个子序列，返回 2 。注意，另一种最优解法是将 nums 划分成子序列 [1] 和 [2,3] 。
 *
 * 示例 3：
 * 输入：nums = [2,2,4,5], k = 0
 * 输出：3
 * 解释：
 * 可以将 nums 划分为三个子序列 [2,2]、[4] 和 [5] 。
 * 第一个子序列中最大值和最小值的差值是 2 - 2 = 0 。
 * 第二个子序列中最大值和最小值的差值是 4 - 4 = 0 。
 * 第三个子序列中最大值和最小值的差值是 5 - 5 = 0 。
 * 由于创建了三个子序列，返回 3 。可以证明需要划分的最少子序列数目就是 3 。
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^5
 * 0 <= k <= 10^5
 */
public class PartitionArray {

    public int partitionArray(int[] nums, int k) {
        int length = nums.length;
        if (length == 1) {
            return 1;
        }
        Arrays.sort(nums);
        int startIndex = 0;
        int count = 1;
        for (int i = 1; i < length; i++) {
            if (nums[i] - nums[startIndex] > k) {
                count++;
                startIndex = i;
            }
        }
        return count;
    }
}
