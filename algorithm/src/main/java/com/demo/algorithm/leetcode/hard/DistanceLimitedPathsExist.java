package com.demo.algorithm.leetcode.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create on 2022/12/14
 * @author chenglong
 * description : 检查边长度限制的路径是否存在
 *
 * 给你一个n个点组成的无向图边集edgeList，其中edgeList[i] = [ui, vi, disi]表示点ui和点vi之间有一条长度为disi的边。请注意，两个点之间可能有超过一条边。
 * 给你一个查询数组queries，其中queries[j] = [pj, qj, limitj]，你的任务是对于每个查询queries[j]，判断是否存在从pj到qj的路径，且这条路径上的每一条边都严格小于limitj。
 * 请你返回一个布尔数组answer，其中answer.length == queries.length，当queries[j]的查询结果为true时，answer第j个值为true，否则为false。
 *
 * 示例 1：
 * 输入：n = 3, edgeList = [[0,1,2],[1,2,4],[2,0,8],[1,0,16]], queries = [[0,1,2],[0,2,5]]
 * 输出：[false,true]
 * 解释：上图为给定的输入数据。注意到 0 和 1 之间有两条重边，分别为 2 和 16 。
 * 对于第一个查询，0 和 1 之间没有小于 2 的边，所以我们返回 false 。
 * 对于第二个查询，有一条路径（0 -> 1 -> 2）两条边都小于 5 ，所以这个查询我们返回 true 。
 *
 * 示例 2：
 * 输入：n = 5, edgeList = [[0,1,10],[1,2,5],[2,3,9],[3,4,13]], queries = [[0,4,14],[1,4,13]]
 * 输出：[true,false]
 * 解释：上图为给定数据。
 *
 * 提示：
 * 2 <= n <= 10^5
 * 1 <= edgeList.length, queries.length <= 10^5
 * edgeList[i].length == 3
 * queries[j].length == 3
 * 0 <= ui, vi, pj, qj <= n - 1
 * ui != vi
 * pj != qj
 * 1 <= disi, limitj <= 10^9
 * 两个点之间可能有多条边。
 */
public class DistanceLimitedPathsExist {

    public static final long MOD = 100001;

    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        int length = edgeList.length;
        //构建无向图上点之间的相连关系
        List<List<Integer>> dates = new ArrayList<>(n);
        Map<Long, Integer> paths = new HashMap<>();
        for (int i = 0; i < n; i++) {
            dates.add(new ArrayList<>());
        }
        for (int i = 0; i < length; i++) {
            int a = edgeList[i][0];
            int b = edgeList[i][1];
            int size = edgeList[i][2];
            dates.get(a).add(b);
            dates.get(b).add(a);
            long key1 = MOD * a + b;
            long key2 = MOD * b + a;
            if (paths.containsKey(key1)) {
                if (paths.get(key1) > size) {
                    paths.put(key1, size);
                }
            } else {
                paths.put(key1, size);
            }
            if (paths.containsKey(key2)) {
                if (paths.get(key2) > size) {
                    paths.put(key2, size);
                }
            } else {
                paths.put(key2, size);
            }
        }
        length = queries.length;
        boolean[] result = new boolean[length];
        for (int i = 0; i < length; i++) {
            result[i] = checkPath(queries[i][0], queries[i][1], queries[i][2], paths, dates);
        }
        return result;
    }

    private boolean checkPath(int start, int end, int max, Map<Long, Integer> paths, List<List<Integer>> dates) {
        List<Integer> curs = new ArrayList<>();
        List<Integer> nexts = new ArrayList<>();
        boolean[] marks = new boolean[dates.size()];
        curs.add(start);
        marks[start] = true;
        while (curs.size() > 0) {
            for (int i = 0; i < curs.size(); i++) {
                int cur = curs.get(i);
                List<Integer> items = dates.get(cur);
                for (int j = 0; j < items.size(); j++) {
                    int next = items.get(j);
                    long key = MOD * cur + next;
                    if (!paths.containsKey(key)) {
                        continue;
                    }
                    if (paths.get(key) < max) {
                        if (next == end) {
                            return true;
                        } else {
                            if (marks[next]) {
                                continue;
                            }
                            marks[next] = true;
                            nexts.add(next);
                        }
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
