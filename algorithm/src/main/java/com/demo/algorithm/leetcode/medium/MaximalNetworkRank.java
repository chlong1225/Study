package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/3/15
 * @author chenglong
 * description : 最大网络秩
 *
 * n座城市和一些连接这些城市的道路roads共同组成一个基础设施网络。每个roads[i]=[ai,bi]都表示在城市ai和bi之间有一条双向道路。
 * 两座不同城市构成的城市对的网络秩定义为：与这两座城市直接相连的道路总数。如果存在一条道路直接连接这两座城市，则这条道路只计算一次 。
 * 整个基础设施网络的最大网络秩是所有不同城市对中的最大网络秩 。
 * 给你整数n和数组roads，返回整个基础设施网络的最大网络秩 。
 *
 * 示例 1：
 * 输入：n = 4, roads = [[0,1],[0,3],[1,2],[1,3]]
 * 输出：4
 * 解释：城市 0 和 1 的网络秩是 4，因为共有 4 条道路与城市 0 或 1 相连。位于 0 和 1 之间的道路只计算一次。
 *
 * 示例 2：
 * 输入：n = 5, roads = [[0,1],[0,3],[1,2],[1,3],[2,3],[2,4]]
 * 输出：5
 * 解释：共有 5 条道路与城市 1 或 2 相连。
 *
 * 示例 3：
 * 输入：n = 8, roads = [[0,1],[1,2],[2,3],[2,4],[5,6],[5,7]]
 * 输出：5
 * 解释：2 和 5 的网络秩为 5，注意并非所有的城市都需要连接起来。
 *
 * 提示：
 * 2 <= n <= 100
 * 0 <= roads.length <= n * (n - 1) / 2
 * roads[i].length == 2
 * 0 <= ai, bi<= n-1
 * ai!=bi
 * 每对城市之间最多只有一条道路相连
 */
public class MaximalNetworkRank {

    public int maximalNetworkRank(int n, int[][] roads) {
        int[] counts = new int[n];
        boolean[][] marks = new boolean[n][n];
        int length = roads.length;
        for (int i = 0; i < length; i++) {
            counts[roads[i][0]]++;
            counts[roads[i][1]]++;
            marks[roads[i][0]][roads[i][1]] = true;
            marks[roads[i][1]][roads[i][0]] = true;
        }
        int max = -1;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                //此时选取的两个城市为i，j
                int total = counts[i] + counts[j];
                if (marks[i][j]) {
                    total--;
                }
                if (total > max) {
                    max = total;
                }
            }
        }
        return max;
    }
}
