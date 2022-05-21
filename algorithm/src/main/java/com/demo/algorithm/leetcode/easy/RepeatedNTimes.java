package com.demo.algorithm.leetcode.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by chl on 2022/5/21.
 * description : 在长度2N的数组中找出重复N次的元素
 *
 * 给你一个整数数组nums，该数组具有以下属性：
 * nums.length == 2 * n.
 * nums包含n + 1个不同的元素
 * nums中恰有一个元素重复n次
 * 找出并返回重复了 n 次的那个元素。
 *
 * 示例 1：
 * 输入：nums = [1,2,3,3]
 * 输出：3
 *
 * 示例 2：
 * 输入：nums = [2,1,2,5,3,2]
 * 输出：2
 *
 * 示例 3：
 * 输入：nums = [5,1,5,2,5,3,5,4]
 * 输出：5
 *
 * 提示：
 * 2 <= n <= 5000
 * nums.length == 2 * n
 * 0 <= nums[i] <= 10^4
 * nums 由 n + 1 个 不同的 元素组成，且其中一个元素恰好重复 n 次
 */
public class RepeatedNTimes {

    public int repeatedNTimes(int[] nums) {
        Set<Integer> marks = new HashSet<>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (marks.contains(nums[i])) {
                return nums[i];
            } else {
                marks.add(nums[i]);
            }
        }
        return 0;
    }
}
