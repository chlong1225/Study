package com.demo.algorithm.leetcode.contest.week304;

/**
 * create on 2022/8/4
 * @author chenglong
 * description : 图中的最长环
 *
 * 给你一个n个节点的有向图，节点编号为0到n-1，其中每个节点至多有一条出边。
 * 图用一个大小为n下标从0开始的数组edges表示，节点i到节点edges[i]之间有一条有向边。如果节点i没有出边，那么edges[i] == -1。
 * 请你返回图中的最长环，如果没有任何环请返回-1。
 * 一个环指的是起点和终点是同一个节点的路径。
 *
 * 示例 1：
 * 输入：edges = [3,3,4,2,3]
 * 输出去：3
 * 解释：图中的最长环是：2 -> 4 -> 3 -> 2 。
 * 这个环的长度为 3 ，所以返回 3 。
 *
 * 示例 2：
 * 输入：edges = [2,-1,3,1]
 * 输出：-1
 * 解释：图中没有任何环。
 *
 * 提示：
 * n == edges.length
 * 2 <= n <= 10^5
 * -1 <= edges[i] < n
 * edges[i] != i
 */
public class LongestCycle {

    public int longestCycle(int[] edges) {
        return 0;
    }
}
