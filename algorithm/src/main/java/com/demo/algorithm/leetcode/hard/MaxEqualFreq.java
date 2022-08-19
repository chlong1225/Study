package com.demo.algorithm.leetcode.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * create on 2022/8/18
 * @author chenglong
 * description : 最大相等频率
 *
 * 给你一个正整数数组nums，请你帮忙从该数组中找出能满足下面要求的最长前缀，并返回该前缀的长度：
 * 从前缀中恰好删除一个元素后，剩下每个数字的出现次数都相同。
 * 如果删除这个元素后没有剩余元素存在，仍可认为每个数字都具有相同的出现次数（也就是0次）。
 *
 * 示例 1：
 * 输入：nums = [2,2,1,1,5,3,3,5]
 * 输出：7
 * 解释：对于长度为 7 的子数组 [2,2,1,1,5,3,3]，如果我们从中删去 nums[4] = 5，就可以得到 [2,2,1,1,3,3]，里面每个数字都出现了两次。
 *
 * 示例 2：
 * 输入：nums = [1,1,1,2,2,2,3,3,3,4,4,4,5]
 * 输出：13
 *
 * 提示：
 * 2 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 */
public class MaxEqualFreq {


    public int maxEqualFreq(int[] nums) {
        //统计数字出现的次数
        Map<Integer, Integer> counts = new HashMap<>();
        //统计出现次数的数字数量
        Map<Integer, Integer> dates = new HashMap<>();
        int max = 0;
        int maxCount = 0;
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            int cur = nums[i];
            if (counts.containsKey(cur)) {
                int curCount = counts.get(cur);
                if (maxCount < curCount + 1) {
                    maxCount = curCount + 1;
                }
                dates.put(curCount, dates.get(curCount) - 1);
                counts.put(cur, curCount + 1);
                if (dates.containsKey(curCount + 1)) {
                    dates.put(curCount + 1, dates.get(curCount + 1) + 1);
                } else {
                    dates.put(curCount + 1, 1);
                }
            } else {
                if (maxCount < 1) {
                    maxCount = 1;
                }
                counts.put(cur, 1);
                if (dates.containsKey(1)) {
                    dates.put(1, dates.get(1) + 1);
                } else {
                    dates.put(1, 1);
                }
            }
            int preSize = 0;
            if (dates.containsKey(maxCount - 1)) {
                preSize = dates.get(maxCount - 1);
            }
            if (maxCount == 1 || maxCount * dates.get(maxCount) == i || maxCount + (maxCount - 1) * preSize == i + 1) {
                max = Math.max(max, i + 1);
            }
        }
        return max;
    }
}
