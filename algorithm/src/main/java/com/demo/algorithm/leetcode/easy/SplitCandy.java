package com.demo.algorithm.leetcode.easy;

import java.util.Arrays;

/**
 * create on 11/1/21
 * @author chenglong
 * description : 分糖果
 *
 * 给定一个偶数长度的数组，其中不同的数字代表着不同种类的糖果，每一个数字代表一个糖果。你需要把这些糖果平均分给一个弟弟和一个妹妹。返回妹妹可以获得的最大糖果的种类数。
 *
 * 示例 1:
 * 输入: candies = [1,1,2,2,3,3]
 * 输出: 3
 * 解析: 一共有三种种类的糖果，每一种都有两个。
 *      最优分配方案：妹妹获得[1,2,3],弟弟也获得[1,2,3]。这样使妹妹获得糖果的种类数最多。
 *
 * 示例 2 :
 * 输入: candies = [1,1,2,3]
 * 输出: 2
 * 解析: 妹妹获得糖果[2,3],弟弟获得糖果[1,1]，妹妹有两种不同的糖果，弟弟只有一种。这样使得妹妹可以获得的糖果种类数最多。
 * 注意:
 *
 * 数组的长度为[2, 10,000]，并且确定为偶数。
 */
public class SplitCandy {

    public static int distributeCandies(int[] candyType) {
        int length = candyType.length;
        if (length == 2) {
            return 1;
        }
        Arrays.sort(candyType);
        int max = length / 2;
        int count = 1;
        int pre = candyType[0];
        for (int i = 1; i < length; i++) {
            if (pre == candyType[i]) {
                continue;
            }
            count++;
            pre = candyType[i];
        }
        return Math.min(max, count);
    }
}
