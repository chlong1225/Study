package com.demo.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2022/12/19
 * @author chenglong
 * description : 寻找图中是否存在路径
 *
 * 有一个具有n个顶点的双向图，其中每个顶点标记从0到n-1（包含0和n-1）。图中的边用一个二维整数数组edges表示，其中edges[i] = [ui, vi]表示顶点ui和顶点vi之间的双向边。
 * 每个顶点对由最多一条边连接，并且没有顶点存在与自身相连的边。
 * 请你确定是否存在从顶点source开始，到顶点destination结束的有效路径。
 * 给你数组edges和整数n、source和destination，如果从source到destination存在有效路径，则返回true，否则返回false。
 *
 * 示例 1：
 * 输入：n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
 * 输出：true
 * 解释：存在由顶点 0 到顶点 2 的路径:
 * - 0 → 1 → 2
 * - 0 → 2
 *
 * 示例 2：
 * 输入：n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0, destination = 5
 * 输出：false
 * 解释：不存在由顶点 0 到顶点 5 的路径.
 *
 * 提示：
 * 1 <= n <= 2 * 10^5
 * 0 <= edges.length <= 2 * 10^5
 * edges[i].length == 2
 * 0 <= ui, vi <= n - 1
 * ui != vi
 * 0 <= source, destination <= n - 1
 * 不存在重复边
 * 不存在指向顶点自身的边
 */
public class ValidPath {

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        if (source == destination) {
            return true;
        }
        int length = edges.length;
        if (length == 0) {
            return false;
        }
        List<List<Integer>> dates = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            dates.add(new ArrayList<>());
        }
        for (int i = 0; i < length; i++) {
            dates.get(edges[i][0]).add(edges[i][1]);
            dates.get(edges[i][1]).add(edges[i][0]);
        }
        boolean[] marks = new boolean[n];
        List<Integer> curs = new ArrayList<>();
        List<Integer> nexts = new ArrayList<>();
        curs.add(source);
        marks[source] = true;
        while (curs.size() > 0) {
            for (int i = 0; i < curs.size(); i++) {
                List<Integer> items = dates.get(curs.get(i));
                if (items.size() > 0) {
                    for (int j = 0; j < items.size(); j++) {
                        int next = items.get(j);
                        if (next == destination) {
                            return true;
                        }
                        if (marks[next]) {
                            continue;
                        }
                        marks[next] = true;
                        nexts.add(next);
                    }
                }
            }
            curs.clear();
            curs.addAll(nexts);
            nexts.clear();
        }
        return false;
    }
}
