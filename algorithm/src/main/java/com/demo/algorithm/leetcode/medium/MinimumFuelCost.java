package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2023/12/5
 * @author chenglong
 * description : 到达首都的最少油耗
 *
 * 给你一棵n个节点的树（一个无向、连通、无环图），每个节点表示一个城市，编号从0到n-1，且恰好有n-1条路。0是首都。
 * 给你一个二维整数数组roads，其中roads[i]=[ai,bi]，表示城市ai和bi之间有一条双向路。
 * 每个城市里有一个代表，他们都要去首都参加一个会议。
 * 每座城市里有一辆车。给你一个整数seats表示每辆车里面座位的数目。
 * 城市里的代表可以选择乘坐所在城市的车，或者乘坐其他城市的车。相邻城市之间一辆车的油耗是一升汽油。
 * 请你返回到达首都最少需要多少升汽油。
 *
 * 示例 1：
 * 输入：roads = [[0,1],[0,2],[0,3]], seats = 5
 * 输出：3
 * 解释：
 * - 代表 1 直接到达首都，消耗 1 升汽油。
 * - 代表 2 直接到达首都，消耗 1 升汽油。
 * - 代表 3 直接到达首都，消耗 1 升汽油。
 * 最少消耗 3 升汽油。
 *
 * 示例 2：
 * 输入：roads = [[3,1],[3,2],[1,0],[0,4],[0,5],[4,6]], seats = 2
 * 输出：7
 * 解释：
 * - 代表 2 到达城市 3 ，消耗 1 升汽油。
 * - 代表 2 和代表 3 一起到达城市 1 ，消耗 1 升汽油。
 * - 代表 2 和代表 3 一起到达首都，消耗 1 升汽油。
 * - 代表 1 直接到达首都，消耗 1 升汽油。
 * - 代表 5 直接到达首都，消耗 1 升汽油。
 * - 代表 6 到达城市 4 ，消耗 1 升汽油。
 * - 代表 4 和代表 6 一起到达首都，消耗 1 升汽油。
 * 最少消耗 7 升汽油。
 *
 * 示例 3：
 * 输入：roads = [], seats = 1
 * 输出：0
 * 解释：没有代表需要从别的城市到达首都。
 *
 * 提示：
 * 1 <= n <= 10^5
 * roads.length == n - 1
 * roads[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * roads 表示一棵合法的树。
 * 1 <= seats <= 10^5
 */
public class MinimumFuelCost {

    private boolean[] marks;

    public long minimumFuelCost(int[][] roads, int seats) {
        int n = roads.length + 1;
        //1，构建城市之间关系的数据
        List<List<Integer>> dates = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            dates.add(new ArrayList<>());
        }
        for (int i = 0; i < roads.length; i++) {
            dates.get(roads[i][0]).add(roads[i][1]);
            dates.get(roads[i][1]).add(roads[i][0]);
        }
        marks = new boolean[n];
        //记录每个节点经过的城市数
        int[] counts = new int[n];
        marks[0] = true;
        dfs(0, counts, dates);
        long sum = 0;
        for (int i = 1; i < n; i++) {
            sum += (counts[i] / seats);
            if (counts[i] % seats != 0) {
                sum++;
            }
        }
        return sum;
    }

    private int dfs(int node, int[] counts, List<List<Integer>> dates) {
        int sum = 1;
        List<Integer> nexts = dates.get(node);
        for (int i = 0; i < nexts.size(); i++) {
            if (!marks[nexts.get(i)]) {
                marks[nexts.get(i)] = true;
                sum += dfs(nexts.get(i), counts, dates);
            }
        }
        counts[node] = sum;
        return sum;
    }
}
