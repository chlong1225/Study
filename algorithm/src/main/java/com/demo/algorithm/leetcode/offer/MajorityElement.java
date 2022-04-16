package com.demo.algorithm.leetcode.offer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chl on 2022/4/16.
 * description : 剑指Offer39. 数组中出现次数超过一半的数字
 *
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * 示例1:
 * 输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
 * 输出: 2
 *
 * 限制：
 * 1 <= 数组长度 <= 50000
 */
public class MajorityElement {

    public int majorityElement(int[] nums) {
        int length = nums.length;
        if (length < 3) {
            return nums[0];
        }
        int max = length / 2;
        Map<Integer, Integer> marks = new HashMap<>();
        for (int i = 0; i < length; i++) {
            int num = nums[i];
            if (marks.containsKey(num)) {
                int count = marks.get(num) + 1;
                if (count > max) {
                    return num;
                } else {
                    marks.put(num, count);
                }
            } else {
                marks.put(num, 1);
            }
        }
        return 0;
    }


}
