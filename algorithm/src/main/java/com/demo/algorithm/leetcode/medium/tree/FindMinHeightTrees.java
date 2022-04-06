package com.demo.algorithm.leetcode.medium.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2022/4/6.
 * description : 最小高度树
 *
 * 树是一个无向图，其中任何两个顶点只通过一条路径连接。 换句话说，一个任何没有简单环路的连通图都是一棵树。
 * 给你一棵包含n个节点的树，标记为0到n-1 。给定数字n和一个有n-1条无向边的edges列表（每一个边都是一对标签），
 * 其中edges[i]=[ai, bi]表示树中节点ai和bi之间存在一条无向边。
 * 可选择树中任何一个节点作为根。当选择节点x作为根节点时，设结果树的高度为h。在所有可能的树中，具有最小高度的树（即，min(h)）被称为最小高度树 。
 * 请你找到所有的最小高度树并按任意顺序返回它们的根节点标签列表。
 * 树的高度是指根节点和叶子节点之间最长向下路径上边的数量。
 *
 * 示例 1：
 * 输入：n = 4, edges = [[1,0],[1,2],[1,3]]
 * 输出：[1]
 * 解释：如图所示，当根是标签为 1 的节点时，树的高度是 1 ，这是唯一的最小高度树。
 *
 * 示例 2：
 * 输入：n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
 * 输出：[3,4]
 *
 * 提示：
 * 1 <= n <= 2 * 10^4
 * edges.length == n - 1
 * 0 <= ai, bi < n
 * ai != bi
 * 所有 (ai, bi) 互不相同
 * 给定的输入 保证 是一棵树，并且 不会有重复的边
 */
public class FindMinHeightTrees {

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        //1，构建图的数据结构
        List<List<Integer>> dates = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            dates.add(new ArrayList<>());
        }
        int length = edges.length;
        for (int i = 0; i < length; i++) {
            dates.get(edges[i][0]).add(edges[i][1]);
            dates.get(edges[i][1]).add(edges[i][0]);
        }
        int[] heights = new int[n];
        int min = Integer.MAX_VALUE;
        int count = 0;
        //2，分层广度遍历
        for (int i = 0; i < n; i++) {
            int step = findStep(i, dates);
            heights[i] = step;
            if (step < min) {
                min = step;
            }
        }
        //3，统计最小高度
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (heights[i] == min) {
                result.add(i);
            }
        }
        return result;
    }

    private int findStep(int root, List<List<Integer>> dates) {
        int step = 1;
        List<Integer> cur = new ArrayList<>(dates.get(root));
        boolean[] marks = new boolean[dates.size()];
        marks[root] = true;
        List<Integer> next = new ArrayList<>();
        while (cur.size() > 0) {
            step++;
            for (int i = 0; i < cur.size(); i++) {
                marks[cur.get(i)] = true;
                List<Integer> items = dates.get(cur.get(i));
                for (int j = 0; j < items.size(); j++) {
                    if (!marks[items.get(j)]) {
                        next.add(items.get(j));
                        marks[items.get(j)] = true;
                    }
                }
            }
            cur.clear();
            cur.addAll(next);
            next.clear();
        }
        return step;
    }

    public List<Integer> findMinHeightTrees2(int n, int[][] edges) {
        //1，构建图的数据结构
        List<List<Integer>> dates = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            dates.add(new ArrayList<>());
        }
        int length = edges.length;
        for (int i = 0; i < length; i++) {
            dates.get(edges[i][0]).add(edges[i][1]);
            dates.get(edges[i][1]).add(edges[i][0]);
        }
        //2，获取最大长度的两个节点
        int[] parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = -1;
        }
        int x = findLongPoint(0, parents, dates);
        int y = findLongPoint(x, parents, dates);
        //3，获取x-y之间的路径
        List<Integer> path = new ArrayList<>();
        parents[x] = -1;
        while (y != -1) {
            path.add(y);
            y = parents[y];
        }
        List<Integer> result = new ArrayList<>();
        if (path.size() % 2 == 0) {
            result.add(path.get(path.size() / 2 - 1));
        }
        result.add(path.get(path.size() / 2));
        return result;
    }

    private int findLongPoint(int root, int[] parents, List<List<Integer>> dates) {
        int n = dates.size();
        boolean[] marks = new boolean[n];
        int result = root;
        List<Integer> curs = new ArrayList<>();
        curs.add(root);
        marks[root] = true;
        List<Integer> next = new ArrayList<>();
        while (curs.size() > 0) {
            for (int i = 0; i < curs.size(); i++) {
                result = curs.get(i);
                marks[result] = true;
                List<Integer> items = dates.get(result);
                for (int j = 0; j < items.size(); j++) {
                    if (!marks[items.get(j)]) {
                        marks[items.get(j)] = true;
                        next.add(items.get(j));
                        parents[items.get(j)] = result;
                    }
                }
            }
            curs.clear();
            curs.addAll(next);
            next.clear();
        }
        return result;
    }
}
