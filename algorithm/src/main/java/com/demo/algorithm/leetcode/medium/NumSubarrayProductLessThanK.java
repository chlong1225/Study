package com.demo.algorithm.leetcode.medium;

/**
 * create on 2022/5/5
 * @author chenglong
 * description : 乘积小于K的子数组
 *
 * 给你一个整数数组nums和一个整数k，请你返回子数组内所有元素的乘积严格小于k的连续子数组的数目。
 *
 * 示例 1：
 * 输入：nums = [10,5,2,6], k = 100
 * 输出：8
 * 解释：8 个乘积小于 100 的子数组分别为：[10]、[5]、[2],、[6]、[10,5]、[5,2]、[2,6]、[5,2,6]。
 * 需要注意的是 [10,5,2] 并不是乘积小于 100 的子数组。
 *
 * 示例 2：
 * 输入：nums = [1,2,3], k = 0
 * 输出：0
 *
 * 提示:
 * 1 <= nums.length <= 3 * 104
 * 1 <= nums[i] <= 1000
 * 0 <= k <= 10^6
 */
public class NumSubarrayProductLessThanK {

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        /**
         * 使用双指针遍历
         */
        int length = nums.length;
        //1，处理长度为1的特殊常见
        if (length == 1) {
            if (nums[0] >= k) {
                return 0;
            } else {
                return 1;
            }
        }
        //2，遍历查找第一个符合条件的数
        int start = 0;
        while (start < length) {
            if (nums[start] < k) {
                break;
            }
            start++;
        }
        //如果数组中所有的数据都大于等于k，而不存在符合条件的连续子数组
        if (start == length) {
            return 0;
        }
        int count = 0;
        //统计连续子数组的乘积
        int total = nums[start];
        int end = start + 1;
        while (end < length) {
            if (total * nums[end] < k) {
                total *= nums[end];
                end++;
            } else {
                //此时start～end子数组的乘积已经越界
                count += (end - start);
                total /= nums[start];
                start++;
                if (start == end) {
                    //重新查找起点
                    while (start < length) {
                        if (nums[start] < k) {
                            break;
                        }
                        start++;
                    }
                    if (start == length) {
                        return count;
                    }
                    total = nums[start];
                    end = start + 1;
                }
            }
        }
        if (start < end) {
            //此时end=length
            int space = end - start;
            count += (space * (space + 1) / 2);
        }
        return count;
    }
}
