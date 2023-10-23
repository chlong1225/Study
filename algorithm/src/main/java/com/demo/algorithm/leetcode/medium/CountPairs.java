package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2023/10/23
 * @author chenglong
 * description : 统计无向图中无法互相到达点对数
 *
 * 给你一个整数n，表示一张无向图中有n个节点，编号为0到n-1。同时给你一个二维整数数组edges，其中edges[i]=[ai,bi]表示节点ai和bi之间有一条无向边。
 * 请你返回无法互相到达的不同点对数目。
 *
 * 示例 1：
 * 输入：n = 3, edges = [[0,1],[0,2],[1,2]]
 * 输出：0
 * 解释：所有点都能互相到达，意味着没有点对无法互相到达，所以我们返回0。
 *
 * 示例 2：
 * 输入：n = 7, edges = [[0,2],[0,5],[2,4],[1,6],[5,4]]
 * 输出：14
 * 解释：总共有 14 个点对互相无法到达：
 * [[0,1],[0,3],[0,6],[1,2],[1,3],[1,4],[1,5],[2,3],[2,6],[3,4],[3,5],[3,6],[4,6],[5,6]]
 * 所以我们返回 14 。
 *
 * 提示：
 * 1 <= n <= 10^5
 * 0 <= edges.length <= 2 * 10^5
 * edges[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * 不会有重复边。
 */
public class CountPairs {

    public long countPairs(int n, int[][] edges) {
        //1，统计节点之间的关系
        List<List<Integer>> dates = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            dates.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            dates.get(edges[i][0]).add(edges[i][1]);
            dates.get(edges[i][1]).add(edges[i][0]);
        }
        //2，对节点进行分组，不同组的节点无法到达
        //记录当前节点的分组数
        int[] marks = new int[n];
        List<Integer> counts = new ArrayList<>();
        int index = 1;
        for (int i = 0; i < n; i++) {
            if (marks[i] == 0) {
                //当前节点没有被分组
                List<Integer> curs = new ArrayList<>();
                List<Integer> nexts = new ArrayList<>();
                marks[i] = index;
                int count = 1;
                curs.add(i);
                while (curs.size() > 0) {
                    for (int j = 0; j < curs.size(); j++) {
                        //当前节点关联的下一个节点
                        List<Integer> items = dates.get(curs.get(j));
                        for (int k = 0; k < items.size(); k++) {
                            int next = items.get(k);
                            if (marks[next] == 0) {
                                nexts.add(next);
                                marks[next] = index;
                                count++;
                            }
                        }
                    }
                    curs.clear();
                    curs.addAll(nexts);
                    nexts.clear();
                }
                counts.add(count);
                index++;
            }
        }
        //3，统计数量
        /**
         * 总结n堆，数量 = a0*(a1+a2+a3+a4+a5+..+an-1) + a1*(a2+a3+...+an-1)+...+an-2*(an-1)
         */
        if (counts.size() == 1) {
            return 0;
        }
        int size = counts.size();
        long[] sums = new long[size - 1];
        sums[size - 2] = counts.get(size - 1);
        for (int i = size - 3; i >= 0; i--) {
            sums[i] = sums[i + 1] + counts.get(i + 1);
        }
        long total = 0;
        for (int i = 0; i < size - 1; i++) {
            total += (counts.get(i) * sums[i]);
        }
        return total;
    }
}
