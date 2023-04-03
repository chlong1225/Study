package com.demo.algorithm.leetcode.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * create on 2023/3/16
 * @author chenglong
 * description : 统计中位数为K的子数组
 *
 * 给你一个长度为n的数组nums，该数组由从1到n的不同整数组成。另给你一个正整数k。
 * 统计并返回nums中的中位数等于k的非空子数组的数目。
 *
 * 注意：
 * 数组的中位数是按递增顺序排列后位于中间的那个元素，如果数组长度为偶数，则中位数是位于中间靠左的那个元素。
 * 例如，[2,3,1,4]的中位数是2，[8,4,3,5,1]的中位数是4。
 * 子数组是数组中的一个连续部分。
 *
 * 示例 1：
 * 输入：nums = [3,2,1,4,5], k = 4
 * 输出：3
 * 解释：中位数等于 4 的子数组有：[4]、[4,5] 和 [1,4,5] 。
 *
 * 示例 2：
 * 输入：nums = [2,3,1], k = 3
 * 输出：1
 * 解释：[3] 是唯一一个中位数等于 3 的子数组。
 *
 * 提示：
 * n == nums.length
 * 1 <= n <= 10^5
 * 1 <= nums[i], k <= n
 * nums 中的整数互不相同
 */
public class CountSubarrays {

    public int countSubarrays(int[] nums, int k) {
        int n = nums.length;
        //1，处理特殊场景
        if (k == n) {
            return 1;
        }
        //2，找到指定k的index
        int findIndex = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == k) {
                findIndex = i;
                break;
            }
        }
        //3，统计左右两边比k大值的数量-比k值小的数量
        int[] dates = new int[n];
        for (int i = findIndex + 1; i < n; i++) {
            if (nums[i] > k) {
                dates[i] = dates[i - 1] + 1;
            } else {
                dates[i] = dates[i - 1] - 1;
            }
        }
        for (int i = findIndex - 1; i >= 0; i--) {
            if (nums[i] > k) {
                dates[i] = dates[i + 1] + 1;
            } else {
                dates[i] = dates[i + 1] - 1;
            }
        }
        //统计左边大于k-小于k的数量差
        Map<Integer, Integer> left = new HashMap<>();
        for (int i = 0; i < findIndex; i++) {
            if (left.containsKey(dates[i])) {
                left.put(dates[i], left.get(dates[i]) + 1);
            } else {
                left.put(dates[i], 1);
            }
        }
        //统计右边大于k-小于k的数量差
        Map<Integer, Integer> right = new HashMap<>();
        for (int i = findIndex + 1; i < n; i++) {
            if (right.containsKey(dates[i])) {
                right.put(dates[i], right.get(dates[i]) + 1);
            } else {
                right.put(dates[i], 1);
            }
        }
        //默认自身一个
        int sum = 1;
        //4，子数组都在k左边
        //奇数居中
        if (left.containsKey(0)) {
            sum += left.get(0);
        }
        //偶数居中
        if (left.containsKey(1)) {
            sum += left.get(1);
        }
        //5，子数组都在k右边
        //奇数居中
        if (right.containsKey(0)) {
            sum += right.get(0);
        }
        //偶数居中
        if (right.containsKey(1)) {
            sum += right.get(1);
        }
        if (findIndex > 0 && findIndex < n - 1) {
            //6，子数组在k两边
            for (int key : left.keySet()) {
                //奇数居中
                int find = -key;
                if (right.containsKey(find)) {
                    sum += left.get(key) * right.get(find);
                }
                //偶数居中
                find = 1 - key;
                if (right.containsKey(find)) {
                    sum += (left.get(key) * right.get(find));
                }
            }
        }
        return sum;
    }
}
