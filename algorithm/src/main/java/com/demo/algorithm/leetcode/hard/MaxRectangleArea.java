package com.demo.algorithm.leetcode.hard;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * create on 10/20/21
 * @author chenglong
 * description : 柱状图中最大的矩形
 *
 * 给定n个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 * 示例 1:
 * 输入：heights = [2,1,5,6,2,3]
 * 输出：10
 * 解释：最大的矩形为图中红色区域，面积为 10
 *
 * 示例 2：
 * 输入： heights = [2,4]
 * 输出： 4
 *
 * 提示：
 * 1 <= heights.length <=105
 * 0 <= heights[i] <= 104
 */
public class MaxRectangleArea {

    public static int largestRectangleArea(int[] heights) {
        int max = 0;
        int length = heights.length;
        int pre = -1;
        for (int i = 0; i < length; i++) {
            int tem = heights[i];
            if (tem == 0) {
                continue;
            }
            if (tem == pre) {
                continue;
            }
            int startIndex = 0;
            int endIndex = length - 1;
            for (int j = i - 1; j >= 0; j--) {
                if (heights[j] < tem) {
                    startIndex = j + 1;
                    break;
                }
            }
            for (int j = i + 1; j < length; j++) {
                if (heights[j] < tem) {
                    endIndex = j - 1;
                    break;
                }
            }
            int area = tem * (endIndex - startIndex + 1);
            if (max < area) {
                max = area;
            }
            pre = tem;
        }
        return max;
    }

    public static int largestRectangleArea2(int[] heights) {
        int length = heights.length;
        if (length == 1) {
            return heights[0];
        }
        int max = 0;
        //1,前后添加哨兵节点
        int[] newHeights = new int[length + 2];
        for (int i = 0; i < length; i++) {
            newHeights[i + 1] = heights[i];
        }
        heights = newHeights;
        length = length + 2;
        //2，使用单栈遍历
        Deque<Integer> stack = new ArrayDeque<Integer>();
        stack.add(0);
        int pre = -1;
        for (int i = 1; i < length; i++) {
            int tem = heights[i];
            if (pre == tem) {
                stack.pop();
                stack.push(i);
                continue;
            }
            int before = stack.getFirst();
            while (heights[before] > tem) {
                int pop = stack.pop();
                int area = heights[pop] * (i - stack.getFirst() - 1);
                if (max < area) {
                    max = area;
                }
                before = stack.getFirst();
            }
            stack.push(i);
            pre = tem;
        }
        return max;
    }
}
