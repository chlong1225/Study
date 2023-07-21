package com.demo.algorithm.leetcode.hard;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * create on 2023/7/21
 * @author chenglong
 * description : 满足不等式的最大值
 *
 * 给你一个数组points和一个整数k。数组中每个元素都表示二维平面上的点的坐标，并按照横坐标x的值从小到大排序。也就是说points[i]=[xi, yi]，并且在1 <= i < j <= points.length 的前提下，xi < xj 总成立。
 * 请你找出yi + yj + |xi- xj|的最大值，其中|xi- xj|<= k且1 <= i < j <= points.length。
 * 题目测试数据保证至少存在一对能够满足|xi- xj|<= k的点。
 *
 * 示例 1：
 * 输入：points = [[1,3],[2,0],[5,10],[6,-10]], k = 1
 * 输出：4
 * 解释：前两个点满足 |xi- xj| <= 1 ，代入方程计算，则得到值 3 + 0 + |1 - 2| = 4 。第三个和第四个点也满足条件，得到值 10 + -10 + |5 - 6| = 1 。
 * 没有其他满足条件的点，所以返回4和1中最大的那个。
 *
 * 示例 2：
 * 输入：points = [[0,0],[3,0],[9,2]], k = 3
 * 输出：3
 * 解释：只有前两个点满足 |xi- xj| <= 3 ，代入方程后得到值 0 + 0 + |0 - 3| = 3 。
 *
 * 提示：
 * 2 <= points.length <= 10^5
 * points[i].length == 2
 * -10^8<= points[i][0], points[i][1] <= 10^8
 * 0 <= k <= 2 * 10^8
 * 对于所有的1 <= i < j <= points.length ，points[i][0] < points[j][0] 都成立。也就是说，xi 是严格递增的。
 */
public class FindMaxValueOfEquation {

    public int findMaxValueOfEquation(int[][] points, int k) {
        int n = points.length;
        int max = Integer.MIN_VALUE;
        //dates中存放单调递减
        Deque<int[]> dates = new ArrayDeque<int[]>();
        dates.add(new int[]{points[0][0], points[0][1] - points[0][0]});
        for (int i = 1; i < n; i++) {
            int[] cur = points[i];
            while (dates.size() > 0) {
                int[] first = dates.peekFirst();
                if (cur[0] - first[0] > k) {
                    dates.pollFirst();
                } else {
                    break;
                }
            }
            //此时dates中的数据都满足：xj-xi<=k
            if (dates.size() > 0) {
                int sum = cur[0] + cur[1] + dates.peekFirst()[1];
                if (sum > max) {
                    max = sum;
                }
            }
            int tem = cur[1] - cur[0];
            while (dates.size() > 0) {
                if (dates.peekLast()[1] <= tem) {
                    dates.pollLast();
                } else {
                    break;
                }
            }
            dates.add(new int[]{cur[0], tem});
        }
        return max;
    }
}
