package com.demo.algorithm.leetcode.hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * create on 2023/6/28
 * @author chenglong
 * description : 最小不兼容性
 *
 * 给你一个整数数组nums和一个整数k。你需要将这个数组划分到k个相同大小的子集中，使得同一个子集里面没有两个相同的元素。
 * 一个子集的不兼容性是该子集里面最大值和最小值的差。
 * 请你返回将数组分成k个子集后，各子集不兼容性的和的最小值，如果无法分成分成k个子集，返回-1。
 * 子集的定义是数组中一些数字的集合，对数字顺序没有要求。
 *
 * 示例 1：
 * 输入：nums = [1,2,1,4], k = 2
 * 输出：4
 * 解释：最优的分配是 [1,2] 和 [1,4] 。
 * 不兼容性和为 (2-1) + (4-1) = 4 。
 * 注意到 [1,1] 和 [2,4] 可以得到更小的和，但是第一个集合有 2 个相同的元素，所以不可行。
 *
 * 示例 2：
 * 输入：nums = [6,3,8,1,3,1,2,2], k = 4
 * 输出：6
 * 解释：最优的子集分配为 [1,2]，[2,3]，[6,8] 和 [1,3] 。
 * 不兼容性和为 (2-1) + (3-2) + (8-6) + (3-1) = 6 。
 *
 * 示例 3：
 * 输入：nums = [5,3,3,6,3,3], k = 3
 * 输出：-1
 * 解释：没办法将这些数字分配到 3 个子集且满足每个子集里没有相同数字。
 *
 * 提示：
 * 1 <= k <= nums.length <= 16
 * nums.length 能被k整除。
 * 1 <= nums[i] <= nums.length
 */
public class MinimumIncompatibility {

    public int minimumIncompatibility(int[] nums, int k) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        Arrays.sort(nums);
        int maxCount = 1;
        int count = 1;
        int pre = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] == pre) {
                count++;
            } else {
                if (count > maxCount) {
                    maxCount = count;
                }
                pre = nums[i];
                count = 1;
            }
        }
        if (count > maxCount) {
            maxCount = count;
        }
        if (maxCount > k) {
            //此时必定存在子集中有两个相同的数字
            return -1;
        }
        int m = n / k;
        int length = 1 << n;
        int[] marks = new int[length];
        marks[0] = 0;
        for (int i = 1; i < length; i++) {
            marks[i] = Integer.MAX_VALUE;
        }
        Map<Integer, Integer> cache = new HashMap<>();
        for (int i = 1; i < length; i++) {
            if (Integer.bitCount(i) != m) {
                continue;
            }
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            Set<Integer> curs = new HashSet<>();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    if (curs.contains(nums[j])) {
                        break;
                    }
                    curs.add(nums[j]);
                    if (nums[j] > max) {
                        max = nums[j];
                    }
                    if (nums[j] < min) {
                        min = nums[j];
                    }
                }
            }
            if (curs.size() == m) {
                cache.put(i, max - min);
            }
        }
        for (int i = 0; i < length; i++) {
            if (marks[i] == Integer.MAX_VALUE) {
                continue;
            }
            Map<Integer, Integer> dates = new HashMap<>();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) == 0) {
                    dates.put(nums[j], j);
                }
            }
            if (dates.size() < m) {
                continue;
            }
            int sum = 0;
            for (int value : dates.values()) {
                sum |= (1 << value);
            }
            int next = sum;
            while (next > 0) {
                if (cache.containsKey(next)) {
                    marks[i | next] = Math.min(marks[i | next], marks[i] + cache.get(next));
                }
                next = (next - 1) & sum;
            }
        }
        return (marks[length - 1] == Integer.MAX_VALUE) ? -1 : marks[length - 1];
    }
}
