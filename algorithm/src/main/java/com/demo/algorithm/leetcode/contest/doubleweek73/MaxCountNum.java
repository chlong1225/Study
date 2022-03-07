package com.demo.algorithm.leetcode.contest.doubleweek73;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chl on 2022/3/7.
 * description : 数组中紧跟key之后出现最频繁的数字
 *
 * 给你一个下标从0开始的整数数组nums，同时给你一个整数key，它在nums出现过。
 * 统计在nums数组中紧跟着key后面出现的不同整数target的出现次数。换言之，target的出现次数为满足以下条件的i的数目：
 * 0 <= i <= n - 2
 * nums[i] == key且
 * nums[i + 1] == target。
 * 请你返回出现最多次数的target。测试数据保证出现次数最多的target是唯一的。
 *
 * 示例 1：
 * 输入：nums = [1,100,200,1,100], key = 1
 * 输出：100
 * 解释：对于 target = 100 ，在下标1和4处出现过2次，且都紧跟着key 。
 * 没有其他整数在 key 后面紧跟着出现，所以我们返回 100 。
 *
 * 示例 2：
 * 输入：nums = [2,2,2,2,3], key = 2
 * 输出：2
 * 解释：对于 target = 2 ，在下标 1 ，2 和 3 处出现过 3 次，且都紧跟着 key 。
 * 对于 target = 3 ，在下标 4 出出现过 1 次，且紧跟着 key 。
 * target = 2 是紧跟着 key 之后出现次数最多的数字，所以我们返回 2 。
 *
 * 提示：
 * 2 <= nums.length <= 1000
 * 1 <= nums[i] <= 1000
 * 测试数据保证答案是唯一的。
 */
public class MaxCountNum {

    public int mostFrequent(int[] nums, int key) {
        Map<Integer, Integer> marks = new HashMap<>();
        int length = nums.length;
        int max = 0;
        int maxCount = 0;
        marks.put(nums[0], 1);
        for (int i = 1; i < length; i++) {
            if (marks.get(nums[i]) == null) {
                marks.put(nums[i], 1);
            } else {
                marks.put(nums[i], marks.get(nums[i]) + 1);
            }
            if (nums[i - 1] == key) {
                if (marks.get(nums[i]) > maxCount) {
                    maxCount = marks.get(nums[i]);
                    max = nums[i];
                }

            }
        }
        return max;
    }

}
