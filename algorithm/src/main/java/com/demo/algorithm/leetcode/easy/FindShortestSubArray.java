package com.demo.algorithm.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * create on 2023/12/1
 * @author chenglong
 * description : 数组的度
 *
 * 给定一个非空且只包含非负数的整数数组nums，数组的度的定义是指数组里任一元素出现频数的最大值。
 * 你的任务是在nums中找到与nums拥有相同大小的度的最短连续子数组，返回其长度。
 *
 * 示例 1：
 * 输入：nums = [1,2,2,3,1]
 * 输出：2
 * 解释：
 * 输入数组的度是 2 ，因为元素 1 和 2 的出现频数最大，均为 2 。
 * 连续子数组里面拥有相同度的有如下所示：
 * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 * 最短连续子数组 [2, 2] 的长度为 2 ，所以返回 2 。
 *
 * 示例 2：
 * 输入：nums = [1,2,2,3,1,4,2]
 * 输出：6
 * 解释：
 * 数组的度是 3 ，因为元素 2 重复出现 3 次。
 * 所以 [2,2,3,1,4,2] 是最短子数组，因此返回 6 。
 *
 * 提示：
 * nums.length在1到50,000范围内。
 * nums[i]是一个在0到49,999范围内的整数。
 */
public class FindShortestSubArray {

    public int findShortestSubArray(int[] nums) {
        //1，统计数据元素的个数，获取最大度
        int maxDegree = 1;
        Map<Integer, Integer> marks = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (marks.containsKey(nums[i])) {
                int count = marks.get(nums[i]) + 1;
                marks.put(nums[i], count);
                if (count > maxDegree) {
                    maxDegree = count;
                }
            } else {
                marks.put(nums[i], 1);
            }
        }
        if (maxDegree == 1) {
            return 1;
        }
        //2，遍历统计区间
        Map<Integer, int[]> dates = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (dates.containsKey(nums[i])) {
                int[] tem = dates.get(nums[i]);
                tem[1] = i;
            } else {
                int[] tem = new int[2];
                tem[0] = i;
                dates.put(nums[i], tem);
            }
        }
        int min = Integer.MAX_VALUE;
        for (int key : marks.keySet()) {
            if (marks.get(key) == maxDegree) {
                int[] tem = dates.get(key);
                int length = tem[1] - tem[0] + 1;
                if (length < min) {
                    min = length;
                }
            }
        }
        return min;
    }
}
