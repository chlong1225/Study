package com.demo.algorithm.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * create on 2023/4/23
 * @author chenglong
 * description : 最长等差数列
 *
 * 给你一个整数数组nums，返回nums中最长等差子序列的长度。
 * 回想一下，nums 的子序列是一个列表nums[i1], nums[i2], ..., nums[ik] ，且0 <= i1 < i2 < ... < ik <= nums.length - 1。
 * 并且如果seq[i+1]-seq[i](0 <= i < seq.length - 1)的值都相同，那么序列seq是等差的。
 *
 * 示例 1：
 * 输入：nums = [3,6,9,12]
 * 输出：4
 * 解释：
 * 整个数组是公差为3的等差数列。
 *
 * 示例 2：
 * 输入：nums = [9,4,7,2,10]
 * 输出：3
 * 解释：
 * 最长的等差子序列是 [4,7,10]。
 *
 * 示例 3：
 * 输入：nums = [20,1,15,3,10,5,8]
 * 输出：4
 * 解释：
 * 最长的等差子序列是 [20,15,10,5]。
 *
 * 提示：
 * 2 <= nums.length <= 1000
 * 0 <= nums[i] <= 500
 */
public class LongestArithSeqLength {

    public int longestArithSeqLength(int[] nums) {
        int n = nums.length;
        //1，获取最大最小值
        int min = nums[0];
        int max = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] < min) {
                min = nums[i];
            } else if (nums[i] > max) {
                max = nums[i];
            }
        }
        //2，公差范围：-diff～diff
        int diff = max - min;
        //特殊场景：所有的值都相同
        if (diff == 0) {
            return n;
        }
        int maxCount = 1;
        //统计当前需要被查找的数字与目标数组长度
        Map<Integer, Integer> marks = new HashMap<>();
        for (int i = -diff; i <= diff; i++) {
            marks.clear();
            for (int j = 0; j < n; j++) {
                int find = nums[j];
                int next = find + i;
                if (marks.containsKey(find)) {
                    int count = marks.get(find) + 1;
                    if (marks.containsKey(next)) {
                        if (marks.get(next) < count) {
                            marks.put(next, count);
                        }
                    } else {
                        marks.put(next, count);
                    }
                } else {
                    marks.put(next, 2);
                }
            }
            for (int key : marks.keySet()) {
                if (maxCount < marks.get(key) - 1) {
                    maxCount = marks.get(key) - 1;
                }
            }
        }
        return maxCount;
    }
}
