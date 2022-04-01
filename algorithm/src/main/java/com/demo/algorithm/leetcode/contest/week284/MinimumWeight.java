package com.demo.algorithm.leetcode.contest.week284;

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
public class MinimumWeight {

    public long minimumWeight(int n, int[][] edges, int src1, int src2, int dest) {
        return 0;
    }
}
