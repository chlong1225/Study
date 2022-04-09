package com.demo.algorithm.leetcode.offer;

/**
 * Created by chl on 2022/4/9.
 * description : 剑指Offer61. 扑克牌中的顺子
 *
 * 从若干副扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，
 * A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A不能视为14。
 *
 * 示例1:
 * 输入: [1,2,3,4,5]
 * 输出: True
 *
 *
 * 示例2:
 * 输入: [0,0,1,2,5]
 * 输出: True
 *
 * 限制：
 * 数组长度为5
 *
 * 数组的数取值为 [0, 13] .
 */
public class Straight {

    public boolean isStraight(int[] nums) {
        //记录最大值
        int max = 0;
        //记录最小值
        int min = 14;
        //记录数字是否出现，防止重复
        boolean[] marks = new boolean[14];
        for (int i = 0; i < 5; i++) {
            if (nums[i] != 0) {
                if (marks[nums[i]]) {
                    return false;
                } else {
                    marks[nums[i]] = true;
                }
                if (max < nums[i]) {
                    max = nums[i];
                }
                if (min > nums[i]) {
                    min = nums[i];
                }
            }
        }
        if (max - min >= 5) {
            return false;
        }
        return true;
    }
}
