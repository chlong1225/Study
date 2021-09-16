package com.demo.algorithm.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * create by chenglong on 9/14/21
 * description : 多数元素
 *
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * 示例 1：
 * 输入：[3,2,3]
 * 输出：3
 *
 * 示例 2：
 * 输入：[2,2,1,1,1,2,2]
 * 输出：2
 *  
 * 进阶：
 * 尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 */
public class MoreNumber {

    public int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int length = nums.length;
        int tem;
        for (int i = 0; i < length; i++) {
            tem = nums[i];
            if (map.get(tem) == null) {
                map.put(tem, 1);
            } else {
                map.put(tem, map.get(tem) + 1);
            }
        }
        for (Integer key : map.keySet()) {
            if (map.get(key) > length / 2) {
                return key;
            }
        }
        return -1;
    }

}
