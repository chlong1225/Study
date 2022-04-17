package com.demo.algorithm.leetcode.contest.week284;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by chl on 2022/4/1.
 * description : 得到要求路径的最小带权子图
 *
 * 给你一个整数n，它表示一个带权有向图的节点数，节点编号为0到n - 1。
 * 同时给你一个二维整数数组edges，其中edges[i]=[fromi, toi, weighti]，表示从fromi到toi有一条边权为weighti的有向边。
 * 最后，给你三个互不相同的整数src1，src2和dest，表示图中三个不同的点。
 * 请你从图中选出一个边权和最小的子图，使得从src1和src2出发，在这个子图中，都可以到达dest。如果这样的子图不存在，请返回-1。
 * 子图中的点和边都应该属于原图的一部分。子图的边权和定义为它所包含的所有边的权值之和。
 *
 * 示例 1：
 * 输入：n = 6, edges = [[0,2,2],[0,5,6],[1,0,3],[1,4,5],[2,1,1],[2,3,3],[2,3,4],[3,4,2],[4,5,1]], src1 = 0, src2 = 1, dest = 5
 * 输出：9
 * 解释：
 * 上图为输入的图。
 * 蓝色边为最优子图之一。
 * 注意，子图 [[1,0,3],[0,5,6]] 也能得到最优解，但无法在满足所有限制的前提下，得到更优解。
 *
 * 示例 2：
 * 输入：n = 3, edges = [[0,1,1],[2,1,1]], src1 = 0, src2 = 1, dest = 2
 * 输出：-1
 * 解释：
 * 上图为输入的图。
 * 可以看到，不存在从节点 1 到节点 2 的路径，所以不存在任何子图满足所有限制。
 *
 * 提示：
 * 3 <= n <= 10^5
 * 0 <= edges.length <= 10^5
 * edges[i].length == 3
 * 0 <= fromi, toi, src1, src2, dest <= n - 1
 * fromi != toi
 * src1，src2和dest两两不同。
 * 1 <= weight[i] <= 10^5
 */
@RequiresApi(api = Build.VERSION_CODES.N)
public class MinimumWeight {

    public long minimumWeight(int n, int[][] edges, int src1, int src2, int dest) {
        //1，构建图的数据
        List<List<int[]>> graph = new ArrayList<>(n);
        List<List<int[]>> rgraph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
            rgraph.add(new ArrayList<>());
        }
        int length = edges.length;
        for (int i = 0; i < length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            int w = edges[i][2];
            graph.get(from).add(new int[]{to, w});
            rgraph.get(to).add(new int[]{from, w});
        }
        //2，遍历计算src1，src2，dest到其它点的最短距离
        long[] ds1 = dfs(src1, graph);
        long[] ds2 = dfs(src2, graph);
        long[] d = dfs(dest, rgraph);
        long result = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (ds1[i] != Long.MAX_VALUE && ds2[i] != Long.MAX_VALUE && d[i] != Long.MAX_VALUE) {
                long sum = ds1[i] + ds2[i] + d[i];
                if (sum < result) {
                    result = sum;
                }
            }
        }
        return result == Long.MAX_VALUE ? -1 : result;
    }

    private long[] dfs(int start, List<List<int[]>> graph) {
        int n = graph.size();
        long[] result = new long[n];
        for (int i = 0; i < n; i++) {
            result[i] = Long.MAX_VALUE;
        }
        result[start] = 0;
        boolean[] marks = new boolean[n];
        PriorityQueue<long[]> heap = new PriorityQueue<>((o1, o2) -> Long.compare(o1[1], o2[1]));
        heap.offer(new long[]{start, 0});
        while (!heap.isEmpty()) {
            long[] cur = heap.poll();
            if (marks[(int) cur[0]]) {
                continue;
            }
            marks[(int) cur[0]] = true;
            List<int[]> dates = graph.get((int) cur[0]);
            for (int i = 0; i < dates.size(); i++) {
                int[] next = dates.get(i);
                long tem = cur[1] + next[1];
                if (tem < result[next[0]]) {
                    result[next[0]] = tem;
                    heap.offer(new long[]{next[0], tem});
                }
            }
        }
        return result;
    }
}
