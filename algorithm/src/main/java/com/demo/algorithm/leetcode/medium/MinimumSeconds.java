package com.demo.algorithm.leetcode.medium;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create by chenglong on 2024/1/30
 * description : 使循环数组所有元素相等的最少秒数
 *
 * 给你一个下标从0开始长度为n的数组nums。
 * 每一秒，你可以对数组执行以下操作：
 * 对于范围在[0, n-1]内的每一个下标i，将nums[i]替换成nums[i]，nums[(i-1+n)%n]或者nums[(i+1) %n]三者之一。
 * 注意，所有元素会被同时替换。
 * 请你返回将数组nums中所有元素变成相等元素所需要的最少秒数。
 *
 * 示例 1：
 * 输入：nums = [1,2,1,2]
 * 输出：1
 * 解释：我们可以在 1 秒内将数组变成相等元素：
 * - 第 1 秒，将每个位置的元素分别变为 [nums[3],nums[1],nums[3],nums[3]] 。变化后，nums = [2,2,2,2] 。
 * 1 秒是将数组变成相等元素所需要的最少秒数。
 *
 * 示例 2：
 * 输入：nums = [2,1,3,3,2]
 * 输出：2
 * 解释：我们可以在 2 秒内将数组变成相等元素：
 * - 第 1 秒，将每个位置的元素分别变为 [nums[0],nums[2],nums[2],nums[2],nums[3]] 。变化后，nums = [2,3,3,3,3] 。
 * - 第 2 秒，将每个位置的元素分别变为 [nums[1],nums[1],nums[2],nums[3],nums[4]] 。变化后，nums = [3,3,3,3,3] 。
 * 2 秒是将数组变成相等元素所需要的最少秒数。
 *
 * 示例 3：
 * 输入：nums = [5,5,5,5]
 * 输出：0
 * 解释：不需要执行任何操作，因为一开始数组中的元素已经全部相等。
 *
 * 提示：
 * 1 <= n == nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 */
public class MinimumSeconds {

    public int minimumSeconds(List<Integer> nums) {
        int n = nums.size();
        //记录当前值对于的index
        Map<Integer, Integer> marks = new HashMap<>();
        //记录当前值之间的最大间距
        Map<Integer, Integer> times = new HashMap<>();
        for (int i = 0; i < 2 * n; i++) {
            int cur = nums.get(i % n);
            if (marks.containsKey(cur)) {
                int preIndex = marks.get(cur);
                int time = (i - preIndex) / 2;
                marks.put(cur, i);
                if (time > times.get(cur)) {
                    times.put(cur, time);
                }
            } else {
                marks.put(cur, i);
                times.put(cur, 0);
            }
        }
        int min = n;
        for (int key : times.keySet()) {
            if (times.get(key) < min) {
                min = times.get(key);
            }
        }
        return min;
    }
}
