package com.demo.algorithm.leetcode.hard.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2023/12/7
 * @author chenglong
 * description : 最小化旅行的价格总和
 *
 * 现有一棵无向、无根的树，树中有n个节点，按从0到n-1编号。给你一个整数n和一个长度为n-1的二维整数数组edges，
 * 其中edges[i]=[ai,bi]表示树中节点ai和bi之间存在一条边。
 * 每个节点都关联一个价格。给你一个整数数组price，其中price[i]是第i个节点的价格。
 * 给定路径的价格总和是该路径上所有节点的价格之和。
 * 另给你一个二维整数数组trips，其中trips[i]=[starti,endi]表示您从节点starti开始第i次旅行，并通过任何你喜欢的路径前往节点endi。
 * 在执行第一次旅行之前，你可以选择一些非相邻节点并将价格减半。
 * 返回执行所有旅行的最小价格总和。
 *
 * 示例 1：
 * 输入：n = 4, edges = [[0,1],[1,2],[1,3]], price = [2,2,10,6], trips = [[0,3],[2,1],[2,3]]
 * 输出：23
 * 解释：
 * 上图表示将节点 2 视为根之后的树结构。第一个图表示初始树，第二个图表示选择节点 0 、2 和 3 并使其价格减半后的树。
 * 第 1 次旅行，选择路径 [0,1,3] 。路径的价格总和为 1 + 2 + 3 = 6 。
 * 第 2 次旅行，选择路径 [2,1] 。路径的价格总和为 2 + 5 = 7 。
 * 第 3 次旅行，选择路径 [2,1,3] 。路径的价格总和为 5 + 2 + 3 = 10 。
 * 所有旅行的价格总和为 6 + 7 + 10 = 23 。可以证明，23 是可以实现的最小答案。
 *
 * 示例 2：
 * 输入：n = 2, edges = [[0,1]], price = [2,2], trips = [[0,0]]
 * 输出：1
 * 解释：
 * 上图表示将节点 0 视为根之后的树结构。第一个图表示初始树，第二个图表示选择节点 0 并使其价格减半后的树。
 * 第 1 次旅行，选择路径 [0] 。路径的价格总和为 1 。
 * 所有旅行的价格总和为 1 。可以证明，1 是可以实现的最小答案。
 *
 * 提示：
 * 1 <= n <= 50
 * edges.length == n - 1
 * 0 <= ai, bi <= n - 1
 * edges表示一棵有效的树
 * price.length == n
 * price[i]是一个偶数
 * 1 <= price[i] <= 1000
 * 1 <= trips.length <= 100
 * 0 <= starti, endi <= n-1
 */
public class MinimumTotalPrice {

    private List<Integer> paths = new ArrayList<>();
    List<List<Integer>> dates;

    public int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {
        //1，构建数的数据源
        dates = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            dates.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            dates.get(edges[i][0]).add(edges[i][1]);
            dates.get(edges[i][1]).add(edges[i][0]);
        }
        //2，统计旅行经过节点的频率
        int[] counts = new int[n];
        for (int i = 0; i < trips.length; i++) {
            int start = trips[i][0];
            int end = trips[i][1];
            paths.clear();
            findPaths(start, end, dates);
            for (int j = 0; j < paths.size(); j++) {
                counts[paths.get(j)]++;
            }
        }
        //3，统计不减价时的花费
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (counts[i] > 0) {
                sum += (counts[i] * price[i]);
            }
        }
        int[] answer = dfsPrice(0, -1, price, counts);
        return Math.min(answer[0], answer[1]);
    }

    private int[] dfsPrice(int cur, int parent, int[] prices, int[] counts) {
        //分别计算当前节点不减半与减半的花费
        int noHalve = counts[cur] * prices[cur];
        int halve = noHalve / 2;
        List<Integer> nexts = dates.get(cur);
        for (int i = 0; i < nexts.size(); i++) {
            if (nexts.get(i) != parent) {
                int[] answer = dfsPrice(nexts.get(i), cur, prices, counts);
                halve += answer[0];
                noHalve += Math.min(answer[0], answer[1]);
            }
        }
        return new int[]{noHalve, halve};
    }

    private void findPaths(int start, int end, List<List<Integer>> dates) {
        List<Integer> curs = new ArrayList<>();
        curs.add(start);
        boolean[] marks = new boolean[dates.size()];
        marks[start] = true;
        dfs(start, end, curs, marks, dates);
    }

    //合法的数路径唯一
    private void dfs(int cur, int compare, List<Integer> curs, boolean[] marks, List<List<Integer>> dates) {
        if (cur == compare) {
            paths.addAll(curs);
            return;
        }
        List<Integer> nexts = dates.get(cur);
        if (nexts.size() > 0) {
            for (int i = 0; i < nexts.size(); i++) {
                int next = nexts.get(i);
                if (marks[next]) {
                    continue;
                }
                curs.add(next);
                marks[next] = true;
                dfs(next, compare, curs, marks, dates);
                marks[next] = false;
                curs.remove(curs.size() - 1);
            }
        }
    }
}
