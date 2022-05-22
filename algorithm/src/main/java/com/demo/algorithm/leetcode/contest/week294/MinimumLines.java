package com.demo.algorithm.leetcode.contest.week294;

import java.util.Arrays;

/**
 * Created by chl on 2022/5/22.
 * description : 表示一个折线图的最少线段数
 *
 * 给你一个二维整数数组stockPrices，其中stockPrices[i] = [dayi, pricei]表示股票在dayi的价格为pricei。
 * 折线图是一个二维平面上的若干个点组成的图，横坐标表示日期，纵坐标表示价格，折线图由相邻的点连接而成。比方说下图是一个例子：
 * 请你返回要表示一个折线图所需要的 最少线段数。
 *
 * 示例 1：
 * 输入：stockPrices = [[1,7],[2,6],[3,5],[4,4],[5,4],[6,3],[7,2],[8,1]]
 * 输出：3
 * 解释：
 * 上图为输入对应的图，横坐标表示日期，纵坐标表示价格。
 * 以下 3 个线段可以表示折线图：
 * - 线段 1 （红色）从 (1,7) 到 (4,4) ，经过 (1,7) ，(2,6) ，(3,5) 和 (4,4) 。
 * - 线段 2 （蓝色）从 (4,4) 到 (5,4) 。
 * - 线段 3 （绿色）从 (5,4) 到 (8,1) ，经过 (5,4) ，(6,3) ，(7,2) 和 (8,1) 。
 * 可以证明，无法用少于 3 条线段表示这个折线图。
 *
 * 示例 2：
 * 输入：stockPrices = [[3,4],[1,2],[7,8],[2,3]]
 * 输出：1
 * 解释：
 * 如上图所示，折线图可以用一条线段表示。
 *
 * 提示：
 * 1 <= stockPrices.length <= 10^5
 * stockPrices[i].length == 2
 * 1 <= dayi, pricei <= 10^9
 * 所有dayi互不相同。
 */
public class MinimumLines {

    public int minimumLines(int[][] stockPrices) {
        int length = stockPrices.length;
        if (length == 1) {
            return 0;
        }
        if (length == 2) {
            return 1;
        }
        Arrays.sort(stockPrices, (o1, o2) -> o1[0] - o2[0]);
        int count = 1;
        int start = 0;
        int end = 1;
        long base = 1;
        for (int i = 2; i < length; i++) {
            int[] cur = stockPrices[i];
            //判断start，end，cur三点是否在一条线上
            long a1 = base * (stockPrices[end][1] - stockPrices[start][1]) * (cur[0] - stockPrices[end][0]);
            long a2 = base * (cur[1] - stockPrices[end][1]) * (stockPrices[end][0] - stockPrices[start][0]);
            if (a1 != a2) {
                count++;
            }
            start = end;
            end = i;
        }
        return count;
    }
}
