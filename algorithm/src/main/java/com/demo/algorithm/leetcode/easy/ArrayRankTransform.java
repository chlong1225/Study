package com.demo.algorithm.leetcode.easy;

import java.util.Arrays;

/**
 * Created by chl on 2022/7/28.
 * description : 数组序号转换
 *
 * 给你一个整数数组arr，请你将数组中的每个元素替换为它们排序后的序号。
 * 序号代表了一个元素有多大。序号编号的规则如下：
 * 序号从1开始编号。
 * 一个元素越大，那么序号越大。如果两个元素相等，那么它们的序号相同。
 * 每个数字的序号都应该尽可能地小。
 *
 * 示例 1：
 * 输入：arr = [40,10,20,30]
 * 输出：[4,1,2,3]
 * 解释：40 是最大的元素。 10 是最小的元素。 20 是第二小的数字。 30 是第三小的数字。
 *
 * 示例 2：
 * 输入：arr = [100,100,100]
 * 输出：[1,1,1]
 * 解释：所有元素有相同的序号。
 *
 * 示例 3：
 * 输入：arr = [37,12,28,9,100,56,80,5,12]
 * 输出：[5,3,4,2,8,6,7,1,3]
 *
 * 提示：
 * 0 <= arr.length <= 10^5
 * -10^9<= arr[i] <= 10^9
 */
public class ArrayRankTransform {

    public int[] arrayRankTransform(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }
        int length = arr.length;
        int[] result = new int[length];
        int[][] dates = new int[length][2];
        for (int i = 0; i < length; i++) {
            dates[i][0] = arr[i];
            dates[i][1] = i;
        }
        Arrays.sort(dates, (o1, o2) -> o1[0] - o2[0]);
        int index = 1;
        for (int i = 0; i < length; i++) {
            if (i == 0) {
                result[dates[i][1]] = index;
            } else {
                if (dates[i][0] > dates[i - 1][0]) {
                    index++;
                }
                result[dates[i][1]] = index;
            }
        }
        return result;
    }
}
