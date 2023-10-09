package com.demo.algorithm.leetcode.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * create on 2023/10/9
 * @author chenglong
 * description : 最长连续序列
 *
 * 给定一个未排序的整数数组nums，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 请你设计并实现时间复杂度为O(n)的算法解决此问题。
 *
 * 示例 1：
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 *
 * 示例 2：
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 *
 * 提示：
 * 0 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 */
public class LongestConsecutive {

    public int longestConsecutive(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        //1，对数据进行去重
        Set<Integer> dates = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            dates.add(nums[i]);
        }
        int max = 1;
        for (int key : dates) {
            int pre = key - 1;
            if (dates.contains(pre)) {
                continue;
            }
            int count = 1;
            int next = key + 1;
            while (dates.contains(next)) {
                count++;
                next++;
            }
            if (count > max) {
                max = count;
            }
        }
        return max;
    }
}
