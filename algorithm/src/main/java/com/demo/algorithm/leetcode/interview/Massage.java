package com.demo.algorithm.leetcode.interview;

/**
 * Created by chl on 2022/6/3.
 * description : 面试题17.16. 按摩师
 *
 * 一个有名的按摩师会收到源源不断的预约请求，每个预约都可以选择接或不接。在每次预约服务之间要有休息时间，因此她不能接受相邻的预约。
 * 给定一个预约请求序列，替按摩师找到最优的预约集合（总预约时间最长），返回总的分钟数。
 *
 * 注意：本题相对原题稍作改动
 *
 * 示例 1：
 * 输入： [1,2,3,1]
 * 输出： 4
 * 解释： 选择 1 号预约和 3 号预约，总时长 = 1 + 3 = 4。
 *
 * 示例 2：
 * 输入： [2,7,9,3,1]
 * 输出： 12
 * 解释： 选择 1 号预约、 3 号预约和 5 号预约，总时长 = 2 + 9 + 1 = 12。
 *
 * 示例 3：
 * 输入： [2,1,4,5,3,1,1,3]
 * 输出： 12
 * 解释： 选择 1 号预约、 3 号预约、 5 号预约和 8 号预约，总时长 = 2 + 4 + 3 + 3 = 12
 */
public class Massage {

    public int massage(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return 0;
        }
        int[][] marks = new int[length][2];
        marks[0][0] = 0;
        marks[0][1] = nums[0];
        for (int i = 1; i < length; i++) {
            marks[i][0] = Math.max(marks[i - 1][0], marks[i - 1][1]);
            marks[i][1] = marks[i - 1][0] + nums[i];
        }
        return Math.max(marks[length - 1][0], marks[length - 1][1]);
    }
}
