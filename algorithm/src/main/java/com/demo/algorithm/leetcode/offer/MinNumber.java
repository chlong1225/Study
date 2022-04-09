package com.demo.algorithm.leetcode.offer;

import java.util.Arrays;

/**
 * Created by chl on 2022/3/27.
 * description : 剑指Offer45. 把数组排成最小的数
 *
 * 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 *
 * 示例 1:
 * 输入: [10,2]
 * 输出: "102"
 *
 * 示例2:
 * 输入: [3,30,34,5,9]
 * 输出: "3033459"
 *
 * 提示:
 * 0 < nums.length <= 100
 * 说明:
 *
 * 输出结果可能非常大，所以你需要返回一个字符串而不是整数
 * 拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0
 */
public class MinNumber {

    public String minNumber(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return "" + nums[0];
        }
        String[] dates = new String[length];
        for (int i = 0; i < length; i++) {
            dates[i] = "" + nums[i];
        }
        Arrays.sort(dates, (o1, o2) -> (o1 + o2).compareTo(o2 + o1));
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(dates[i]);
        }
        return builder.toString();
    }
}
