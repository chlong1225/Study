package com.demo.algorithm.leetcode.contest.week305;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2022/8/9
 * @author chenglong
 * description : 受限条件下可到达节点的数目
 *
 * 现有一棵由n个节点组成的无向树，节点编号从0到n-1，共有n-1条边。
 * 给你一个二维整数数组edges，长度为n-1，其中edges[i]=[ai, bi]表示树中节点ai和bi之间存在一条边。另给你一个整数数组restricted表示受限节点。
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
 * 2 <= n <= 10^5
 * edges.length == n - 1
 * edges[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * edges 表示一棵有效的树
 * 1 <= restricted.length < n
 * 1 <= restricted[i] < n
 * restricted 中的所有值 互不相同
 */
public class ReachableNodes {

    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        boolean[] marks = new boolean[n];
        int length = restricted.length;
        for (int i = 0; i < length; i++) {
            marks[restricted[i]] = true;
        }
        //构建图的数据
        List<List<Integer>> dates = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            dates.add(new ArrayList<>());
        }
        length = edges.length;
        for (int i = 0; i < length; i++) {
            dates.get(edges[i][0]).add(edges[i][1]);
            dates.get(edges[i][1]).add(edges[i][0]);
        }
        List<Integer> cur = new ArrayList<>();
        List<Integer> next = new ArrayList<>();
        cur.add(0);
        int count = 1;
        marks[0] = true;
        while (cur.size() > 0) {
            for (int i = 0; i < cur.size(); i++) {
                List<Integer> items = dates.get(cur.get(i));
                for (int j = 0; j < items.size(); j++) {
                    int tem = items.get(j);
                    if (marks[tem]) {
                        continue;
                    }
                    marks[tem] = true;
                    count++;
                    next.add(tem);
                }
            }
            cur.clear();
            cur.addAll(next);
            next.clear();
        }
        return count;
    }
}
