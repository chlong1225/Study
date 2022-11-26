package com.demo.algorithm.leetcode.medium;

/**
 * create on 2022/11/24
 * @author chenglong
 * description : 区间子数组个数
 *
 * 给你一个整数数组nums和两个整数：left及right。找出nums中连续、非空且其中最大元素在范围[left, right]内的子数组，并返回满足条件的子数组的个数。
 * 生成的测试用例保证结果符合 32-bit 整数范围。
 *
 * 示例 1：
 * 输入：nums = [2,1,4,3], left = 2, right = 3
 * 输出：3
 * 解释：满足条件的三个子数组：[2], [2, 1], [3]
 *
 * 示例 2：
 * 输入：nums = [2,9,2,5,6], left = 2, right = 8
 * 输出：7
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^9
 * 0 <= left <= right <= 10^9
 */
public class NumSubarrayBoundedMax {

    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        /**
         * 分析：通过数值拆分数组，统计小于等于right的区间，然后移除小于left的区间
         */
        long sum = 0;
        int length = nums.length;
        int start = -1;
        int end = -1;
        for (int i = 0; i < length; i++) {
            if (nums[i] <= right) {
                if (start == -1) {
                    start = i;
                }
                end = i;
            } else {
                //计算区间[start,end]之间的数量
                if (start != -1) {
                    if (start == end) {
                        sum++;
                    } else {
                        long count = end - start + 1;
                        sum += (count * (count + 1) / 2);
                    }
                    start = -1;
                    end = -1;
                }
            }
        }
        if (start != -1) {
            if (start == end) {
                sum++;
            } else {
                long count = end - start + 1;
                sum += (count * (count + 1) / 2);
            }
            start = -1;
            end = -1;
        }
        //统计小于left的区间
        for (int i = 0; i < length; i++) {
            if (nums[i] < left) {
                if (start == -1) {
                    start = i;
                }
                end = i;
            } else {
                if (start != -1) {
                    if (start == end) {
                        sum--;
                    } else {
                        long count = end - start + 1;
                        sum -= (count * (count + 1) / 2);
                    }
                    start = -1;
                    end = -1;
                }
            }
        }
        if (start != -1) {
            if (start == end) {
                sum--;
            } else {
                long count = end - start + 1;
                sum -= (count * (count + 1) / 2);
            }
        }
        return (int) sum;
    }
}
