package com.demo.algorithm.leetcode.easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by chl on 2023/6/4.
 * description : 不同的平均值数目
 *
 * 给你一个下标从0开始长度为偶数的整数数组nums。
 * 只要nums不是空数组，你就重复执行以下步骤：
 * 找到nums中的最小值，并删除它。
 * 找到nums中的最大值，并删除它。
 * 计算删除两数的平均值。
 * 两数a和b的平均值为(a + b)/2。
 * 比方说，2和3的平均值是(2+3)/2 = 2.5。
 * 返回上述过程能得到的不同平均值的数目。
 * 注意，如果最小值或者最大值有重复元素，可以删除任意一个。
 *
 * 示例 1：
 * 输入：nums = [4,1,4,0,3,5]
 * 输出：2
 * 解释：
 * 1. 删除 0 和 5 ，平均值是 (0 + 5) / 2 = 2.5 ，现在 nums = [4,1,4,3] 。
 * 2. 删除 1 和 4 ，平均值是 (1 + 4) / 2 = 2.5 ，现在 nums = [4,3] 。
 * 3. 删除 3 和 4 ，平均值是 (3 + 4) / 2 = 3.5 。
 * 2.5 ，2.5 和 3.5 之中总共有 2 个不同的数，我们返回 2 。
 *
 * 示例 2：
 * 输入：nums = [1,100]
 * 输出：1
 * 解释：
 * 删除 1 和 100 后只有一个平均值，所以我们返回 1 。
 *
 * 提示：
 * 2 <= nums.length <= 100
 * nums.length是偶数。
 * 0 <= nums[i] <= 100
 */
public class DistinctAverages {

    public int distinctAverages(int[] nums) {
        Arrays.sort(nums);
        Set<Integer> marks = new HashSet<>();
        int n = nums.length / 2;
        for (int i = 0; i < n; i++) {
            int sum = nums[i] + nums[2 * n - 1 - i];
            marks.add(sum);
        }
        return marks.size();
    }
}
