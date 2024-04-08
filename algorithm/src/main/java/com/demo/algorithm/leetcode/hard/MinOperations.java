package com.demo.algorithm.leetcode.hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * create on 2024/4/8
 * @author chenglong
 * description : 使数组连续的最少操作数
 *
 * 给你一个整数数组nums。每一次操作中，你可以将nums中任意一个元素替换成任意整数。
 * 如果nums满足以下条件，那么它是连续的：
 * nums中所有元素都是互不相同的。
 * nums中最大元素与最小元素的差等于nums.length-1。
 * 比方说，nums =[4, 2, 5, 3]是连续的 ，但是 nums = [1, 2, 3, 5, 6] 不是连续的 。
 * 请你返回使nums连续的最少操作次数。
 *
 * 示例 1：
 * 输入：nums = [4,2,5,3]
 * 输出：0
 * 解释：nums 已经是连续的了。
 *
 * 示例 2：
 * 输入：nums = [1,2,3,5,6]
 * 输出：1
 * 解释：一个可能的解是将最后一个元素变为 4 。
 * 结果数组为 [1,2,3,5,4] ，是连续数组。
 *
 * 示例 3：
 * 输入：nums = [1,10,100,1000]
 * 输出：3
 * 解释：一个可能的解是：
 * - 将第二个元素变为 2 。
 * - 将第三个元素变为 3 。
 * - 将第四个元素变为 4 。
 * 结果数组为 [1,2,3,4] ，是连续数组。
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 */
public class MinOperations {

    public int minOperations(int[] nums) {
        int n = nums.length;
        //1，去重
        List<Integer> dates = new ArrayList<>();
        Set<Integer> marks = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int cur = nums[i];
            if (!marks.contains(cur)) {
                marks.add(cur);
                dates.add(cur);
            }
        }
        //2，排序
        Collections.sort(dates);
        //3，获取最多有效数字的数量
        int max = 0;
        int count = 0;
        int startIndex = 0;
        int right = dates.get(startIndex) + n - 1;
        int endIndex = 0;
        while (endIndex < dates.size()) {
            if (dates.get(endIndex) <= right) {
                count++;
                endIndex++;
            } else {
                if (count > max) {
                    max = count;
                }
                count--;
                startIndex++;
                right = dates.get(startIndex) + n - 1;
            }
        }
        if (count > max) {
            max = count;
        }
        return n-max;
    }
}
