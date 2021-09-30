package com.demo.algorithm.leetcode.medium;

import com.chl.common.utils.LogUtil;

/**
 * create on 9/29/21
 *
 * @author chenglong
 * description : 盛最多水的容器
 *
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 说明：你不能倾斜容器。
 *
 * 示例 1：
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 *
 * 示例 2：
 * 输入：height = [1,1]
 * 输出：1
 *
 * 示例 3：
 * 输入：height = [4,3,2,1,4]
 * 输出：16
 *
 * 示例 4：
 * 输入：height = [1,2,1]
 * 输出：2
 *  
 * 提示：
 * n == height.length
 * 2 <= n <= 105
 * 0 <= height[i] <= 104
 */
public class MaxArea {

    public static int maxArea(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }
        int length = height.length;
        int max = 0;
        for (int i = 1; i < length; i++) {
            //循序遍历不超过的最大值
            int check = i * height[i];
            if (max < check) {
                for (int j = 0; j < i; j++) {
                    int area = (i - j) * Math.min(height[j], height[i]);
                    if (area > max) {
                        max = area;
                    }
                }
            }
        }
        return max;
    }
}
