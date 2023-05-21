package com.demo.algorithm.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * create on 2023/4/13
 * @author chenglong
 * description : 出现最频繁的偶数元素
 *
 * 给你一个整数数组nums，返回出现最频繁的偶数元素。
 * 如果存在多个满足条件的元素，只需要返回最小的一个。如果不存在这样的元素，返回-1。
 *
 * 示例 1：
 * 输入：nums = [0,1,2,2,4,4,1]
 * 输出：2
 * 解释：
 * 数组中的偶数元素为 0、2 和 4 ，在这些元素中，2 和 4 出现次数最多。
 * 返回最小的那个，即返回 2 。
 *
 * 示例 2：
 * 输入：nums = [4,4,4,9,2,4]
 * 输出：4
 * 解释：4 是出现最频繁的偶数元素。
 *
 * 示例 3：
 * 输入：nums = [29,47,21,41,13,37,25,7]
 * 输出：-1
 * 解释：不存在偶数元素。
 *
 * 提示：
 * 1 <= nums.length <= 2000
 * 0 <= nums[i] <= 10^5
 */
public class MostFrequentEven {

    public int mostFrequentEven(int[] nums) {
        Map<Integer, Integer> marks = new HashMap<>();
        int maxCount = 0;
        int min = -1;
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            if (cur % 2 == 1) {
                continue;
            }
            if (marks.containsKey(cur)) {
                int count = marks.get(cur) + 1;
                marks.put(cur, count);
                if (count > maxCount) {
                    maxCount = count;
                    min = cur;
                } else if (count == maxCount) {
                    if (cur < min) {
                        min = cur;
                    }
                }
            } else {
                marks.put(cur, 1);
                if (maxCount < 1) {
                    maxCount = 1;
                    min = cur;
                } else if (maxCount == 1) {
                    if (cur < min) {
                        min = cur;
                    }
                }
            }
        }
        return min;
    }
}
