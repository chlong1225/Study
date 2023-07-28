package com.demo.algorithm.leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2023/7/28
 * @author chenglong
 * description : 并行课程III
 *
 * 给你一个整数n，表示有n节课，课程编号从1到n。同时给你一个二维整数数组relations，其中relations[j] = [prevCoursej, nextCoursej]，
 * 表示课程prevCoursej必须在课程nextCoursej之前完成（先修课的关系）。同时给你一个下标从0开始的整数数组time，其中time[i]表示完成第(i+1)门课程需要花费的月份数。
 * 请你根据以下规则算出完成所有课程所需要的最少月份数：
 * 如果一门课的所有先修课都已经完成，你可以在任意时间开始这门课程。
 * 你可以同时上任意门课程。
 * 请你返回完成所有课程所需要的最少月份数。
 * 注意：测试数据保证一定可以完成所有课程（也就是先修课的关系构成一个有向无环图）。
 *
 * 示例1:
 * 输入：n = 3, relations = [[1,3],[2,3]], time = [3,2,5]
 * 输出：8
 * 解释：上图展示了输入数据所表示的先修关系图，以及完成每门课程需要花费的时间。
 * 你可以在月份 0 同时开始课程 1 和 2 。
 * 课程 1 花费 3 个月，课程 2 花费 2 个月。
 * 所以，最早开始课程 3 的时间是月份 3 ，完成所有课程所需时间为 3 + 5 = 8 个月。
 *
 * 示例 2：
 * 输入：n = 5, relations = [[1,5],[2,5],[3,5],[3,4],[4,5]], time = [1,2,3,4,5]
 * 输出：12
 * 解释：上图展示了输入数据所表示的先修关系图，以及完成每门课程需要花费的时间。
 * 你可以在月份 0 同时开始课程 1 ，2 和 3 。
 * 在月份 1，2 和 3 分别完成这三门课程。
 * 课程 4 需在课程 3 之后开始，也就是 3 个月后。课程 4 在 3 + 4 = 7 月完成。
 * 课程 5 需在课程 1，2，3 和 4 之后开始，也就是在 max(1,2,3,7) = 7 月开始。
 * 所以完成所有课程所需的最少时间为 7 + 5 = 12 个月。
 *
 * 提示：
 * 1 <= n <= 5 * 10^4
 * 0 <= relations.length <= min(n * (n - 1) / 2, 5 * 10^4)
 * relations[j].length == 2
 * 1 <= prevCoursej, nextCoursej <= n
 * prevCoursej != nextCoursej
 * 所有的先修课程对[prevCoursej, nextCoursej]都是互不相同的。
 * time.length == n
 * 1 <= time[i] <= 104
 * 先修课程图是一个有向无环图。
 */
public class MinimumTime {

    public int minimumTime(int n, int[][] relations, int[] time) {
        //记录当前课程的后置课程
        List<List<Integer>> edges = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }
        List<List<Integer>> preEdges = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            preEdges.add(new ArrayList<>());
        }
        //当前课程前置课程数
        int[] preCounts = new int[n];

        for (int i = 0; i < relations.length; i++) {
            edges.get(relations[i][0] - 1).add(relations[i][1] - 1);
            preEdges.get(relations[i][1] - 1).add(relations[i][0] - 1);
            preCounts[relations[i][1] - 1]++;
        }
        int max = 0;
        //记录学完i+1课程需要花费的时间
        int[] counts = new int[n];
        //当前可以修的课程
        List<Integer> curs = new ArrayList<>();
        List<Integer> nexts = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (preCounts[i] == 0) {
                //当前课程可以立即学习
                curs.add(i);
                counts[i] = time[i];
                if (max < counts[i]) {
                    max = counts[i];
                }
            }
        }
        while (curs.size() > 0) {
            for (int i = 0; i < curs.size(); i++) {
                //当前课程对应的后置课程
                List<Integer> items = edges.get(curs.get(i));
                if (items.size() > 0) {
                    for (int j = 0; j < items.size(); j++) {
                        int next = items.get(j);
                        preCounts[next]--;
                        if (preCounts[next] == 0) {
                            //当前课程可以开始学习，此时需要获取前置课程最晚的时间
                            int maxPre = 0;
                            List<Integer> preItems = preEdges.get(next);
                            if (preItems.size() > 0) {
                                for (int k = 0; k < preItems.size(); k++) {
                                    if (counts[preItems.get(k)] > maxPre) {
                                        maxPre = counts[preItems.get(k)];
                                    }
                                }
                            }
                            counts[next] = time[next] + maxPre;
                            if (max < counts[next]) {
                                max = counts[next];
                            }
                            nexts.add(next);
                        }
                    }
                }
            }
            curs.clear();
            curs.addAll(nexts);
            nexts.clear();
        }
        return max;
    }
}
