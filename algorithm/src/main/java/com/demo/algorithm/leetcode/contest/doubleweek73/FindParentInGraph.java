package com.demo.algorithm.leetcode.contest.doubleweek73;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by chl on 2022/3/7.
 * description : 有向无环图中一个节点的所有祖先
 *
 * 给你一个正整数n，它表示一个有向无环图中节点的数目，节点编号为0到n - 1（包括两者）。
 * 给你一个二维整数数组edges，其中edges[i]=[fromi, toi]表示图中一条从fromi到toi的单向边。
 * 请你返回一个数组answer，其中answer[i]是第i个节点的所有祖先，这些祖先节点升序排序。
 * 如果u通过一系列边，能够到达v，那么我们称节点u是节点 v的祖先节点。
 *
 * 示例 1：
 * 输入：n = 8, edgeList = [[0,3],[0,4],[1,3],[2,4],[2,7],[3,5],[3,6],[3,7],[4,6]]
 * 输出：[[],[],[],[0,1],[0,2],[0,1,3],[0,1,2,3,4],[0,1,2,3]]
 * 解释：
 * 上图为输入所对应的图。
 * - 节点 0 ，1 和 2 没有任何祖先。
 * - 节点 3 有 2 个祖先 0 和 1 。
 * - 节点 4 有 2 个祖先 0 和 2 。
 * - 节点 5 有 3 个祖先 0 ，1 和 3 。
 * - 节点 6 有 5 个祖先 0 ，1 ，2 ，3 和 4 。
 * - 节点 7 有 4 个祖先 0 ，1 ，2 和 3 。
 *
 * 示例 2：
 * 输入：n = 5, edgeList = [[0,1],[0,2],[0,3],[0,4],[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
 * 输出：[[],[0],[0,1],[0,1,2],[0,1,2,3]]
 * 解释：
 * 上图为输入所对应的图。
 * - 节点 0 没有任何祖先。
 * - 节点 1 有 1 个祖先 0 。
 * - 节点 2 有 2 个祖先 0 和 1 。
 * - 节点 3 有 3 个祖先 0 ，1 和 2 。
 * - 节点 4 有 4 个祖先 0 ，1 ，2 和 3 。
 *
 * 提示：
 * 1 <= n <= 1000
 * 0 <= edges.length <= min(2000, n * (n - 1) / 2)
 * edges[i].length == 2
 * 0 <= fromi, toi <= n - 1
 * fromi != toi
 * 图中不会有重边。
 * 图是有向且无环的。
 */
public class FindParentInGraph {

    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        //1，构建图的数据
        Map<Integer, List<Integer>> graphs = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            graphs.put(i, new ArrayList<>());
        }
        int length = edges.length;
        for (int i = 0; i < length; i++) {
            graphs.get(edges[i][1]).add(edges[i][0]);
        }
        List<List<Integer>> result = new ArrayList<>();
        //2，循环广度遍历搜索
        for (int i = 0; i < n; i++) {
            Set<Integer> item = new HashSet<>();
            List<Integer> dates = new ArrayList<>(graphs.get(i));
            Set<Integer> next = new HashSet<>();
            while (dates.size() > 0) {
                for (int j = 0; j < dates.size(); j++) {
                    if (item.add(dates.get(j))) {
                        next.addAll(graphs.get(dates.get(j)));
                    }
                }
                dates.clear();
                dates.addAll(next);
                next.clear();
            }
            List<Integer> tem = new ArrayList<>(item);
            Collections.sort(tem);
            result.add(tem);
        }
        return result;
    }
}
