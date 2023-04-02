package com.demo.algorithm.leetcode.medium;

import java.util.Arrays;
import java.util.Comparator;

/**
 * create on 2023/3/30
 * @author chenglong
 * description : 两点之间不包含任何点的最宽垂直区域
 *
 * 给你n个二维平面上的点points，其中points[i]=[xi, yi]，请你返回两点之间内部不包含任何点的最宽垂直区域的宽度。
 * 垂直区域的定义是固定宽度，而y轴上无限延伸的一块区域（也就是高度为无穷大）。最宽垂直区域为宽度最大的一个垂直区域。
 * 请注意，垂直区域边上的点不在区域内。
 *
 * 示例 1：
 * 输入：points = [[8,7],[9,9],[7,4],[9,7]]
 * 输出：1
 * 解释：红色区域和蓝色区域都是最优区域。
 *
 * 示例 2：
 * 输入：points = [[3,1],[9,0],[1,0],[1,4],[5,3],[8,8]]
 * 输出：3
 *
 * 提示：
 * n == points.length
 * 2 <= n <= 10^5
 * points[i].length == 2
 * 0 <= xi, yi<= 10^9
 */
public class MaxWidthOfVerticalArea {

    public int maxWidthOfVerticalArea(int[][] points) {
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int preX = points[0][0];
        int max = 0;
        for (int i = 1; i < points.length; i++) {
            int size = points[i][0] - preX;
            if (size > max) {
                max = size;
            }
            preX = points[i][0];
        }
        return max;
    }
}
