package com.demo.algorithm.leetcode.contest.week283;

import java.util.Arrays;

/**
 * Created by chl on 2022/3/8.
 * description : 向数组中追加K个整数
 *
 * 给你一个整数数组nums和一个整数k 。请你向nums中追加k个未出现在nums中的、互不相同的正整数，并使结果数组的元素和最小 。
 * 返回追加到nums中的k个整数之和。
 *
 * 示例 1：
 * 输入：nums = [1,4,25,10,25], k = 2
 * 输出：5
 * 解释：在该解法中，向数组中追加的两个互不相同且未出现的正整数是 2 和 3 。
 * nums 最终元素和为 1 + 4 + 25 + 10 + 25 + 2 + 3 = 70 ，这是所有情况中的最小值。
 * 所以追加到数组中的两个整数之和是 2 + 3 = 5 ，所以返回 5 。
 *
 * 示例 2：
 * 输入：nums = [5,6], k = 6
 * 输出：25
 * 解释：在该解法中，向数组中追加的两个互不相同且未出现的正整数是 1 、2 、3 、4 、7 和 8 。
 * nums 最终元素和为 5 + 6 + 1 + 2 + 3 + 4 + 7 + 8 = 36 ，这是所有情况中的最小值。
 * 所以追加到数组中的两个整数之和是 1 + 2 + 3 + 4 + 7 + 8 = 25 ，所以返回 25 。
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i], k <= 10^9
 */
public class AddKSum {

    public long minimalKSum(int[] nums, int k) {
        //1，对nums进行排序
        Arrays.sort(nums);
        long sum = 0;
        //2，在1～nums[0]之间插入数字
        int start = nums[0];
        //可以追加的数量
        long count = start - 1;
        if (count > 0) {
            if (k > count) {
                k -= count;
                //追加从1开始的count个数字
                sum += (1 + count) * count / 2;
            } else {
                sum += (1L + k) * k / 2;
                return sum;
            }
        }
        //3，遍历nums，在i与i+1之前插入数字
        int length = nums.length;
        for (int i = 1; i < length; i++) {
            int next = nums[i];
            if (next - start >= 2) {
                //start～next之间可以添加数字：start+1～next-1
                count = next - start - 1;
                if (k > count) {
                    k -= count;
                    sum += (start + next) * count / 2;
                } else {
                    long end = start + k;
                    sum += (start + 1 + end) * k / 2;
                    return sum;
                }
            }
            start = next;
        }
        //4，在nums[length-1]之后继续插入数字
        if (k > 0) {
            long end = start + k;
            sum += (start + 1 + end) * k / 2;
        }
        return sum;
    }
}
