package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * create on 2023/2/2
 * @author chenglong
 * description : 颜色交替的最短路径
 *
 * 在一个有向图中，节点分别标记为0, 1, ..., n-1。图中每条边为红色或者蓝色，且存在自环或平行边。
 * red_edges中的每一个[i, j]对表示从节点 i 到节点 j 的红色有向边。类似地，blue_edges中的每一个[i, j]对表示从节点 i 到节点 j 的蓝色有向边。
 * 返回长度为n的数组answer，其中answer[X]是从节点0到节点X的红色边和蓝色边交替出现的最短路径的长度。如果不存在这样的路径，那么 answer[x] = -1。
 *
 * 示例 1：
 * 输入：n = 3, red_edges = [[0,1],[1,2]], blue_edges = []
 * 输出：[0,1,-1]
 *
 * 示例 2：
 * 输入：n = 3, red_edges = [[0,1]], blue_edges = [[2,1]]
 * 输出：[0,1,-1]
 *
 * 示例 3：
 * 输入：n = 3, red_edges = [[1,0]], blue_edges = [[2,1]]
 * 输出：[0,-1,-1]
 *
 * 示例 4：
 * 输入：n = 3, red_edges = [[0,1]], blue_edges = [[1,2]]
 * 输出：[0,1,2]
 *
 * 示例 5：
 * 输入：n = 3, red_edges = [[0,1],[0,2]], blue_edges = [[1,0]]
 * 输出：[0,1,1]
 *
 * 提示：
 * 1 <= n <= 100
 * red_edges.length <= 400
 * blue_edges.length <= 400
 * red_edges[i].length == blue_edges[i].length == 2
 * 0 <= red_edges[i][j], blue_edges[i][j] < n
 */
public class ShortestAlternatingPaths {

    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        //构建红蓝边的数据
        List<List<Integer>> reds = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            reds.add(new ArrayList<>());
        }
        for (int i = 0; i < redEdges.length; i++) {
            int cur = redEdges[i][0];
            int next = redEdges[i][1];
            if (cur < next) {
                reds.get(cur).add(next);
            }
        }
        List<List<Integer>> blues = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            blues.add(new ArrayList<>());
        }
        for (int i = 0; i < blueEdges.length; i++) {
            int cur = blueEdges[i][0];
            int next = blueEdges[i][1];
            if (cur < next) {
                blues.get(cur).add(next);
            }
        }
        int[] result = new int[n];
        result[0] = 0;
        for (int i = 1; i < n; i++) {
            //从红边开始
            int step1 = getMinStep(i, reds, blues);
            //从蓝边开始
            int step2 = getMinStep(i, blues, reds);
            if (step1 == -1) {
                result[i] = step2;
            } else {
                if (step2 == -1) {
                    result[i] = step1;
                } else {
                    result[i] = Math.min(step1, step2);
                }
            }
        }
        return result;
    }

    private int getMinStep(int target, List<List<Integer>> dates1, List<List<Integer>> dates2) {
        List<Integer> curs = new ArrayList<>(dates1.get(0));
        int step = 1;
        Set<Integer> nexts = new HashSet<>();
        while (curs.size() > 0) {
            for (int i = 0; i < curs.size(); i++) {
                int cur = curs.get(i);
                if (cur == target) {
                    return step;
                }
                if (step % 2 == 0) {
                    //在dates1中查找
                    nexts.addAll(dates1.get(cur));
                } else {
                    //在dates2中查找
                    nexts.addAll(dates2.get(cur));
                }
            }
            curs.clear();
            curs.addAll(nexts);
            nexts.clear();
            step++;
        }
        return -1;
    }
}
