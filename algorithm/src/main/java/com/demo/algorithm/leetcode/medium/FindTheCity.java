package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/11/14
 * @author chenglong
 * description : 阈值距离内邻居最少的城市
 *
 * 有n个城市，按从0到n-1编号。给你一个边数组edges，其中edges[i]=[fromi,toi,weighti]代表fromi和toi两个城市之间的双向加权边，距离阈值是一个整数distanceThreshold。
 * 返回能通过某些路径到达其他城市数目最少、且路径距离最大为distanceThreshold的城市。如果有多个这样的城市，则返回编号最大的城市。
 * 注意，连接城市i和j的路径的距离等于沿该路径的所有边的权重之和。
 *
 * 示例 1：
 * 输入：n = 4, edges = [[0,1,3],[1,2,1],[1,3,4],[2,3,1]], distanceThreshold = 4
 * 输出：3
 * 解释：城市分布图如上。
 * 每个城市阈值距离 distanceThreshold = 4 内的邻居城市分别是：
 * 城市 0 -> [城市 1, 城市 2]
 * 城市 1 -> [城市 0, 城市 2, 城市 3]
 * 城市 2 -> [城市 0, 城市 1, 城市 3]
 * 城市 3 -> [城市 1, 城市 2]
 * 城市 0 和 3 在阈值距离 4 以内都有 2 个邻居城市，但是我们必须返回城市 3，因为它的编号最大。
 *
 * 示例 2：
 * 输入：n = 5, edges = [[0,1,2],[0,4,8],[1,2,3],[1,4,2],[2,3,1],[3,4,1]], distanceThreshold = 2
 * 输出：0
 * 解释：城市分布图如上。
 * 每个城市阈值距离 distanceThreshold = 2 内的邻居城市分别是：
 * 城市 0 -> [城市 1]
 * 城市 1 -> [城市 0, 城市 4]
 * 城市 2 -> [城市 3, 城市 4]
 * 城市 3 -> [城市 2, 城市 4]
 * 城市 4 -> [城市 1, 城市 2, 城市 3]
 * 城市 0 在阈值距离 2 以内只有 1 个邻居城市。
 *
 * 提示：
 * 2 <= n <= 100
 * 1 <= edges.length <= n * (n - 1) / 2
 * edges[i].length == 3
 * 0 <= fromi < toi < n
 * 1 <= weighti, distanceThreshold <= 10^4
 * 所有 (fromi, toi) 都是不同的。
 */
public class FindTheCity {

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        //1，统计城市间距，没有直接相连的默认初始值
        int[][] marks = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                marks[i][j] = Integer.MAX_VALUE >> 1;
            }
        }
        for (int i = 0; i < edges.length; i++) {
            marks[edges[i][0]][edges[i][1]] = edges[i][2];
            marks[edges[i][1]][edges[i][0]] = edges[i][2];
        }
        //2，计算所有城市之间的最小值
        int[][][] dates = new int[n + 1][n][n];
        dates[0] = marks;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    dates[i + 1][j][k] = Math.min(dates[i][j][k], dates[i][j][i] + dates[i][i][k]);
                }
            }
        }
        //3，统计每一个城市满足条件的相连城市数
        int[] counts = new int[n];
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && dates[n][i][j] <= distanceThreshold) {
                    count++;
                }
            }
            counts[i] = count;
        }
        //4，获取最小数量的id
        int min = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            if (counts[i] < counts[min]) {
                min = i;
            }
        }
        return min;
    }
}
