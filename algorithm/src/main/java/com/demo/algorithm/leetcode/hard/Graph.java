package com.demo.algorithm.leetcode.hard;

/**
 * create on 2024/3/26
 * @author chenglong
 * description : 设计可以求最短路径的图类
 *
 * 给你一个有n个节点的有向带权图，节点编号为0到n-1。图中的初始边用数组edges表示，其中edges[i] = [fromi, toi, edgeCosti]表示从fromi到toi有一条代价为edgeCosti的边。
 * 请你实现一个Graph类：
 * Graph(int n, int[][] edges) 初始化图有 n 个节点，并输入初始边。
 * addEdge(int[] edge) 向边集中添加一条边，其中 edge = [from, to, edgeCost] 。数据保证添加这条边之前对应的两个节点之间没有有向边。
 * int shortestPath(int node1, int node2)返回从节点node1到node2的路径最小代价。如果路径不存在，返回-1。一条路径的代价是路径中所有边代价之和。
 *
 * 示例 1：
 * 输入：
 * ["Graph", "shortestPath", "shortestPath", "addEdge", "shortestPath"]
 * [[4, [[0, 2, 5], [0, 1, 2], [1, 2, 1], [3, 0, 3]]], [3, 2], [0, 3], [[1, 3, 4]], [0, 3]]
 * 输出：
 * [null, 6, -1, null, 6]
 * 解释：
 * Graph g = new Graph(4, [[0, 2, 5], [0, 1, 2], [1, 2, 1], [3, 0, 3]]);
 * g.shortestPath(3, 2); // 返回 6 。从 3 到 2 的最短路径如第一幅图所示：3 -> 0 -> 1 -> 2 ，总代价为 3 + 2 + 1 = 6 。
 * g.shortestPath(0, 3); // 返回 -1 。没有从 0 到 3 的路径。
 * g.addEdge([1, 3, 4]); // 添加一条节点 1 到节点 3 的边，得到第二幅图。
 * g.shortestPath(0, 3); // 返回 6 。从 0 到 3 的最短路径为 0 -> 1 -> 3 ，总代价为 2 + 4 = 6 。
 *
 * 提示：
 * 1 <= n <= 100
 * 0 <= edges.length <= n * (n - 1)
 * edges[i].length == edge.length == 3
 * 0 <= fromi, toi, from, to, node1, node2 <= n - 1
 * 1 <= edgeCosti, edgeCost <= 10^6
 * 图中任何时候都不会有重边和自环。
 * 调用addEdge 至多100次。
 * 调用shortestPath至多100次。
 */
public class Graph {

    public Graph(int n, int[][] edges) {

    }

    public void addEdge(int[] edge) {

    }

    public int shortestPath(int node1, int node2) {
        return 0;
    }
}
