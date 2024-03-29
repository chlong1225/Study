package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2024/3/5
 * @author chenglong
 * description : 到达目的地的方案数
 *
 * 你在一个城市里，城市由n个路口组成，路口编号为0到n-1，某些路口之间有双向道路。输入保证你可以从任意路口出发到达其他任意路口，且任意两个路口之间最多有一条路。
 * 给你一个整数n和二维整数数组roads，其中roads[i]=[ui,vi,timei]表示在路口ui和vi之间有一条需要花费timei时间才能通过的道路。你想知道花费最少时间从路口0出发到达路口n-1的方案数。
 * 请返回花费最少时间到达目的地的路径数目。由于答案可能很大，将结果对10^9+7取余后返回。
 *
 * 示例 1：
 * 输入：n = 7, roads = [[0,6,7],[0,1,2],[1,2,3],[1,3,3],[6,3,3],[3,5,1],[6,5,1],[2,5,1],[0,4,5],[4,6,2]]
 * 输出：4
 * 解释：从路口 0 出发到路口 6 花费的最少时间是 7 分钟。
 * 四条花费 7 分钟的路径分别为：
 * - 0 ➝ 6
 * - 0 ➝ 4 ➝ 6
 * - 0 ➝ 1 ➝ 2 ➝ 5 ➝ 6
 * - 0 ➝ 1 ➝ 3 ➝ 5 ➝ 6
 *
 * 示例 2：
 * 输入：n = 2, roads = [[1,0,10]]
 * 输出：1
 * 解释：只有一条从路口 0 到路口 1 的路，花费 10 分钟。
 *
 * 提示：
 * 1 <= n <= 200
 * n - 1 <= roads.length <= n * (n - 1) / 2
 * roads[i].length == 3
 * 0 <= ui, vi <= n - 1
 * 1 <= timei <= 10^9
 * ui != vi
 * 任意两个路口之间至多有一条路。
 * 从任意路口出发，你能够到达其他任意路口。
 */
public class CountPaths {

    public static final int MOD = 1000000007;

    public int countPaths(int n, int[][] roads) {
        List<List<Integer>> dates = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            dates.add(new ArrayList<>());
        }
        for (int i = 0; i < roads.length; i++) {
            dates.get(roads[i][0]).add(roads[i][1]);
            dates.get(roads[i][1]).add(roads[i][0]);
        }
        boolean[] visits = new boolean[n];
        visits[0] = true;
        int[][] marks = new int[n][2];
        List<Integer> curs = new ArrayList<>();
        curs.add(0);
        List<Integer> nexts = new ArrayList<>();
        while (curs.size() > 0) {
            for (int i = 0; i < curs.size(); i++) {
                List<Integer> items = dates.get(curs.get(i));
                for (int j = 0; j < items.size(); j++) {
                    //下一个节点
                    int next = items.get(j);
                    if (!visits[next]) {
                        visits[next] = true;
                    }
                }
            }
        }
        return marks[n - 1][1];
    }
}
