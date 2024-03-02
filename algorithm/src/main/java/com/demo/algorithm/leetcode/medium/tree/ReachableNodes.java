package com.demo.algorithm.leetcode.medium.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * create by chenglong on 2024/3/2
 * description : 受限条件下可到达节点的数目
 *
 * 现有一棵由n个节点组成的无向树，节点编号从0到n-1，共有n-1条边。
 * 给你一个二维整数数组edges，长度为n-1，其中edges[i]=[ai,bi]表示树中节点ai和bi之间存在一条边。
 * 另给你一个整数数组restricted表示受限节点。
 * 在不访问受限节点的前提下，返回你可以从节点0到达的最多节点数目。
 * 注意，节点0不会标记为受限节点。
 *
 * 示例 1：
 * 输入：n = 7, edges = [[0,1],[1,2],[3,1],[4,0],[0,5],[5,6]], restricted = [4,5]
 * 输出：4
 * 解释：上图所示正是这棵树。
 * 在不访问受限节点的前提下，只有节点 [0,1,2,3] 可以从节点 0 到达。
 *
 * 示例 2：
 * 输入：n = 7, edges = [[0,1],[0,2],[0,5],[0,4],[3,2],[6,5]], restricted = [4,2,1]
 * 输出：3
 * 解释：上图所示正是这棵树。
 * 在不访问受限节点的前提下，只有节点 [0,5,6] 可以从节点 0 到达。
 *
 * 提示：
 * 2 <= n <= 105
 * edges.length == n-1
 * edges[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * edges 表示一棵有效的树
 * 1 <= restricted.length < n
 * 1 <= restricted[i] < n
 * restricted中的所有值互不相同
 */
public class ReachableNodes {

    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        //1，构建树的连接数据
        List<List<Integer>> dates = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            dates.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            dates.get(edges[i][0]).add(edges[i][1]);
            dates.get(edges[i][1]).add(edges[i][0]);
        }
        boolean[] restrice = new boolean[n];
        for (int i = 0; i < restricted.length; i++) {
            restrice[restricted[i]] = true;
        }
        boolean[] marks = new boolean[n];
        int count = 1;
        marks[0] = true;
        List<Integer> curs = new ArrayList<>();
        List<Integer> nexts = new ArrayList<>();
        curs.add(0);
        while (curs.size() > 0) {
            for (int i = 0; i < curs.size(); i++) {
                List<Integer> items = dates.get(curs.get(i));
                for (int j = 0; j < items.size(); j++) {
                    int next = items.get(j);
                    if (!restrice[next]) {
                        if (!marks[next]) {
                            nexts.add(next);
                            marks[next] = true;
                            count++;
                        }
                    }
                }
            }
            curs.clear();
            curs.addAll(nexts);
            nexts.clear();
        }
        return count;
    }
}
