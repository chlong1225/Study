package com.demo.algorithm.leetcode.contest.week286;

import java.util.List;

/**
 * Created by chl on 2022/3/29.
 * description : 从栈中取出K个硬币的最大面值和
 *
 * 一张桌子上总共有n个硬币栈。每个栈有正整数个带面值的硬币。
 * 每一次操作中，你可以从任意一个栈的顶部取出1个硬币，从栈中移除它，并放入你的钱包里。
 * 给你一个列表piles，其中piles[i]是一个整数数组，分别表示第i个栈里从顶到底的硬币面值。
 * 同时给你一个正整数k，请你返回在恰好进行k次操作的前提下，你钱包里硬币面值之和最大为多少。
 *
 * 示例 1：
 * 输入：piles = [[1,100,3],[7,8,9]], k = 2
 * 输出：101
 * 解释：
 * 上图展示了几种选择 k 个硬币的不同方法。
 * 我们可以得到的最大面值为 101 。
 *
 * 示例 2：
 * 输入：piles = [[100],[100],[100],[100],[100],[100],[1,1,1,1,1,1,700]], k = 7
 * 输出：706
 * 解释：
 * 如果我们所有硬币都从最后一个栈中取，可以得到最大面值和。
 *
 * 提示：
 * n == piles.length
 * 1 <= n <= 1000
 * 1 <= piles[i][j] <= 10^5
 * 1 <= k <= sum(piles[i].length) <= 2000
 */
public class MaxValueOfCoins {

    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        /**
         * 分析：使用动态规划。依次统计获取0~k个硬币的最大值
         */
        int[][] marks = new int[2][k + 1];
        int n = piles.size();
        for (int i = 0; i < n; i++) {
            List<Integer> dates = piles.get(i);
            //依次统计硬币数对应的价值
            int count = Math.min(k, dates.size());
            //记录硬币数量与价格
            int[] values = new int[count + 1];
            for (int j = 1; j <= count; j++) {
                values[j] = values[j - 1] + dates.get(j - 1);
            }
            int cur = i % 2;
            int pre = (i + 1) % 2;
            for (int j = 1; j <= k; j++) {
                //上一轮取j个硬币最大值
                int max = marks[pre][j];
                for (int l = 1; l <= count; l++) {
                    //遍历当前硬币栈中获取硬币数，从1~count个
                    if (l <= j) {
                        //当前硬币栈中获取l个硬币+上一次获取j-l的最大值
                        int tem = values[l] + marks[pre][j - l];
                        if (max < tem) {
                            max = tem;
                        }
                    } else {
                        break;
                    }
                }
                marks[cur][j] = max;
            }
        }
        return marks[(n - 1) % 2][k];
    }
}
