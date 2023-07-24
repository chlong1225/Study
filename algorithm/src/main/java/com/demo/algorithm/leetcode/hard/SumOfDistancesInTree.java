package com.demo.algorithm.leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2023/7/24
 * @author chenglong
 * description : 树中距离之和
 *
 * 给定一个无向、连通的树。树中有n个标记为0...n-1的节点以及n-1条边。
 * 给定整数n和数组edges，edges[i] = [ai, bi]表示树中的节点ai和bi之间有一条边。
 * 返回长度为n的数组answer，其中answer[i]是树中第i个节点与所有其他节点之间的距离之和。
 *
 * 示例 1:
 * 输入: n = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
 * 输出: [8,12,6,10,10,10]
 * 解释: 树如图所示。
 * 我们可以计算出 dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
 * 也就是 1 + 1 + 2 + 2 + 2 = 8。 因此，answer[0] = 8，以此类推。
 *
 * 示例 2:
 * 输入: n = 1, edges = []
 * 输出: [0]
 *
 * 示例 3:
 * 输入: n = 2, edges = [[1,0]]
 * 输出: [1,1]
 *
 * 提示:
 * 1 <= n <= 3 * 10^4
 * edges.length == n - 1
 * edges[i].length == 2
 * 0 <= ai, bi< n
 * ai!= bi
 * 给定的输入保证为有效的树
 */
public class SumOfDistancesInTree {

    private List<List<Integer>> dates;
    private int[] marks;

    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        dates = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            dates.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            dates.get(edges[i][0]).add(edges[i][1]);
            dates.get(edges[i][1]).add(edges[i][0]);
        }
        //用于记录当前节点的子节点+自身数量
        marks = new int[n];
        int[] result = new int[n];
        dfs(0, -1, 0, result);
        calculate(0, n, result);
        return result;
    }

    private void calculate(int parent, int n, int[] result) {
        List<Integer> nexts = dates.get(parent);
        for (int i = 0; i < nexts.size(); i++) {
            int cur = nexts.get(i);
            if (result[cur] == 0) {
                result[cur] = result[parent] + n - 2 * marks[cur];
                calculate(cur,n,result);
             }
        }
    }

    //height:cur~1之间的距离
    private void dfs(int cur, int parent, int height, int[] result) {
        result[0] += height;
        marks[cur] = 1;
        List<Integer> nexts = dates.get(cur);
        for (int i = 0; i < nexts.size(); i++) {
            if (nexts.get(i) != parent) {
                dfs(nexts.get(i), cur, height + 1, result);
                marks[cur] += marks[nexts.get(i)];
            }
        }
    }
}
