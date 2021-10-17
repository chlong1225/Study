package com.demo.algorithm.leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 10/11/21
 *
 * @author chenglong
 * description : 接雨水
 *
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * 示例 1：
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 *
 * 示例 2：
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 *  
 * 提示：
 * n == height.length
 * 1 <= n <= 2 * 104
 * 0 <= height[i] <= 105
 */
public class CatchRain {

    public static int trap(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }
        int length = height.length;
        int[] left = new int[length];
        int[] right = new int[length];
        int maxLeft = height[0];
        left[0] = maxLeft;
        for (int i = 1; i < length; i++) {
            if (height[i] > maxLeft) {
                maxLeft = height[i];
            }
            left[i] = maxLeft;
        }
        int maxRight = height[length - 1];
        right[length - 1] = maxRight;
        for (int i = length - 2; i >= 0; i--) {
            if (height[i] > maxRight) {
                maxRight = height[i];
            }
            right[i] = maxRight;
        }
        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum += (Math.min(left[i], right[i]) - height[i]);
        }
        return sum;
    }
}
