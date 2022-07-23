package com.demo.algorithm.leetcode.contest.week302;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create on 2022/7/23
 * @author chenglong
 * description : 数位和相等数对的最大和
 *
 * 给你一个下标从0开始的数组nums，数组中的元素都是正整数。请你选出两个下标i和j（i != j），且nums[i]的数位和与nums[j]的数位和相等。
 * 请你找出所有满足条件的下标i和j，找出并返回nums[i] + nums[j]可以得到的最大值 。
 *
 * 示例 1：
 * 输入：nums = [18,43,36,13,7]
 * 输出：54
 * 解释：满足条件的数对 (i, j) 为：
 * - (0, 2) ，两个数字的数位和都是 9 ，相加得到 18 + 36 = 54 。
 * - (1, 4) ，两个数字的数位和都是 7 ，相加得到 43 + 7 = 50 。
 * 所以可以获得的最大和是54。
 *
 * 示例 2：
 * 输入：nums = [10,12,19,14]
 * 输出：-1
 * 解释：不存在满足条件的数对，返回 -1 。
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 */
public class MaximumSum {

    public int maximumSum(int[] nums) {
        int max = -1;
        //用于统计数位和相同的数
        Map<Integer, List<Integer>> marks = new HashMap<>();
        int length = nums.length;
        //1，统计所有数的数位和
        for (int i = 0; i < length; i++) {
            int sum = getSum(nums[i]);
            if (marks.containsKey(sum)) {
                marks.get(sum).add(nums[i]);
            } else {
                List<Integer> items = new ArrayList<>();
                items.add(nums[i]);
                marks.put(sum, items);
            }
        }
        //2，查找相同数位和最大和
        for (int key : marks.keySet()) {
            List<Integer> items = marks.get(key);
            if (items.size() > 1) {
                Collections.sort(items);
                int sum = items.get(items.size() - 1) + items.get(items.size() - 2);
                if (sum > max) {
                    max = sum;
                }
            }
        }
        return max;
    }

    private int getSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += (num % 10);
            num /= 10;
        }
        return sum;
    }
}
