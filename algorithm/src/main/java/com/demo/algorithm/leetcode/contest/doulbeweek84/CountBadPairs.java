package com.demo.algorithm.leetcode.contest.doulbeweek84;

import java.util.HashMap;
import java.util.Map;

/**
 * create on 2022/8/12
 * @author chenglong
 * description : 统计坏数对的数目
 *
 * 给你一个下标从0开始的整数数组nums。如果 i<j且j-i != nums[j]-nums[i]，那么我们称(i, j)是一个坏数对。
 * 请你返回nums中坏数对的总数目。
 *
 * 示例 1：
 * 输入：nums = [4,1,3,3]
 * 输出：5
 * 解释：数对 (0, 1) 是坏数对，因为 1 - 0 != 1 - 4 。
 * 数对 (0, 2) 是坏数对，因为 2 - 0 != 3 - 4, 2 != -1 。
 * 数对 (0, 3) 是坏数对，因为 3 - 0 != 3 - 4, 3 != -1 。
 * 数对 (1, 2) 是坏数对，因为 2 - 1 != 3 - 1, 1 != 2 。
 * 数对 (2, 3) 是坏数对，因为 3 - 2 != 3 - 3, 1 != 0 。
 * 总共有 5 个坏数对，所以我们返回 5 。
 *
 * 示例 2：
 * 输入：nums = [1,2,3,4,5]
 * 输出：0
 * 解释：没有坏数对。
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 */
public class CountBadPairs {

    public long countBadPairs(int[] nums) {
        long base = 1;
        int n = nums.length;
        //1，计算总的数对
        long total = base * n * (n + 1) / 2;
        //2，计算好的数对，即：j-i = nums[j]-nums[i] => nums[j]-j = nums[i]-i
        Map<Integer, Integer> marks = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int diff = nums[i] - i;
            if (marks.containsKey(diff)) {
                marks.put(diff, marks.get(diff) + 1);
            } else {
                marks.put(diff, 1);
            }
        }
        long sum = 0;
        //遍历marks
        for (int key : marks.keySet()) {
            int count = marks.get(key);
            sum += base * count * (count + 1) / 2;
        }
        return total - sum;
    }
}
