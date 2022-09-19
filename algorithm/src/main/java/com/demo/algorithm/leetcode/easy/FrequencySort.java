package com.demo.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create on 2022/9/19
 * @author chenglong
 * description : 按照频率将数组升序排序
 *
 * 给你一个整数数组nums，请你将数组按照每个值的频率升序排序。如果有多个值的频率相同，请你按照数值本身将它们降序排序。
 * 请你返回排序后的数组。
 *
 * 示例 1：
 * 输入：nums = [1,1,2,2,2,3]
 * 输出：[3,1,1,2,2,2]
 * 解释：'3' 频率为 1，'1' 频率为 2，'2' 频率为 3 。
 *
 * 示例 2：
 * 输入：nums = [2,3,1,3,2]
 * 输出：[1,3,3,2,2]
 * 解释：'2' 和 '3' 频率都为 2 ，所以它们之间按照数值本身降序排序。
 *
 * 示例 3：
 * 输入：nums = [-1,1,-6,4,5,-6,1,4,1]
 * 输出：[5,-1,4,4,-6,-6,1,1,1]
 *
 * 提示：
 * 1 <= nums.length <= 100
 * -100 <= nums[i] <= 100
 */
public class FrequencySort {

    public int[] frequencySort(int[] nums) {
        Map<Integer, Integer> marks = new HashMap<>();
        int length = nums.length;
        //1，统计数字出现的次数
        for (int i = 0; i < length; i++) {
            int cur = nums[i];
            if (marks.containsKey(cur)) {
                marks.put(cur, marks.get(cur) + 1);
            } else {
                marks.put(cur, 1);
            }
        }
        //2，将数据转换为List后台进行排序
        List<int[]> dates = new ArrayList<>();
        for (int key : marks.keySet()) {
            dates.add(new int[]{key, marks.get(key)});
        }
        Collections.sort(dates, (o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o2[0] - o1[0];
            }
            return o1[1] - o2[1];
        });
        //3，重新赋值排序
        int index = 0;
        for (int i = 0; i < dates.size(); i++) {
            int cur = dates.get(i)[0];
            int count = dates.get(i)[1];
            for (int j = 0; j < count; j++) {
                nums[index] = cur;
                index++;
            }
        }
        return nums;
    }
}
