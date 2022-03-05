package com.demo.algorithm.leetcode.hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by chl on 2022/1/31.
 * description : 到达目的地的第二短时间
 *
 * 城市用一个双向连通图表示，图中有n个节点，从1到n编号（包含1和n）。图中的边用一个二维整数数组edges表示，
 * 其中每个edges[i] = [ui, vi]表示一条节点ui和节点vi之间的双向连通边。每组节点对由最多一条边连通，
 * 顶点不存在连接到自身的边。穿过任意一条边的时间是time分钟。
 * 每个节点都有一个交通信号灯，每change分钟改变一次，从绿色变成红色，再由红色变成绿色，循环往复。所有信号灯都同时改变。你可以在任何时候进入某个节点，
 * 但是只能在节点信号灯是绿色时才能离开。如果信号灯是绿色，你不能在节点等待，必须离开。
 * 第二小的值 是严格大于 最小值的所有值中最小的值。
 * 例如，[2, 3, 4] 中第二小的值是 3 ，而 [2, 2, 4] 中第二小的值是 4 。
 * 给你 n、edges、time 和 change ，返回从节点 1 到节点 n 需要的 第二短时间 。
 *
 * 注意：
 * 你可以 任意次 穿过任意顶点，包括 1 和 n 。
 * 你可以假设在 启程时 ，所有信号灯刚刚变成 绿色 。
 *
 * 示例 1：
 * 输入：n = 5, edges = [[1,2],[1,3],[1,4],[3,4],[4,5]], time = 3, change = 5
 * 输出：13
 * 解释：
 * 上面的左图展现了给出的城市交通图。
 * 右图中的蓝色路径是最短时间路径。
 * 花费的时间是：
 * - 从节点 1 开始，总花费时间=0
 * - 1 -> 4：3 分钟，总花费时间=3
 * - 4 -> 5：3 分钟，总花费时间=6
 * 因此需要的最小时间是 6 分钟。
 *
 * 右图中的红色路径是第二短时间路径。
 * - 从节点 1 开始，总花费时间=0
 * - 1 -> 3：3 分钟，总花费时间=3
 * - 3 -> 4：3 分钟，总花费时间=6
 * - 在节点 4 等待 4 分钟，总花费时间=10
 * - 4 -> 5：3 分钟，总花费时间=13
 * 因此第二短时间是 13 分钟。
 *
 * 示例 2：
 * 输入：n = 2, edges = [[1,2]], time = 3, change = 2
 * 输出：11
 * 解释：
 * 最短时间路径是 1 -> 2 ，总花费时间 = 3 分钟
 * 最短时间路径是 1 -> 2 -> 1 -> 2 ，总花费时间 = 11 分钟
 *
 * 提示：
 * 2 <= n <= 10^4
 * n - 1 <= edges.length <= min(2 * 10^4, n * (n - 1) / 2)
 * edges[i].length == 2
 * 1 <= ui, vi <= n
 * ui != vi
 * 不含重复边
 * 每个节点都可以从其他节点直接或者间接到达
 * 1 <= time, change <= 10^3
 */
public class FindSecondTime {

    public int secondMinimum(int n, int[][] edges, int time, int change) {
        //1，构建图的数据结构
        List<List<Integer>> graphs = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            graphs.add(new ArrayList<>());
        }
        int length = edges.length;
        for (int i = 0; i < length; i++) {
            graphs.get(edges[i][0]).add(edges[i][1]);
            graphs.get(edges[i][1]).add(edges[i][0]);
        }
        //2，使用bfs搜索第二短路径

        int secondStep = findSceondPath(n, graphs);
        //3，根据红绿灯规则计算时间
        int sum = time;
        //从绿灯开始花费的时间
        int spend = time;
        for (int i = 1; i < secondStep; i++) {
            spend %= (2 * change);
            if (spend >= change) {
                //此时变成红色，需要等待
                sum += (2 * change - spend);
                sum += time;
                spend = time;
            } else {
                spend += time;
                sum += time;
            }
        }
        return sum;
    }

    //记录节点访问的次数：这里规定同一层一个节点只能访问一次，不同层节点访问不能超过两次
    private int findSceondPath(int n, List<List<Integer>> graphs) {
        //用于记录节点访问的次数
        int[] marks = new int[n + 1];
        int minPath = -1;
        int step = 0;
        List<Integer> dates = new ArrayList<>();
        dates.add(1);
        marks[1]++;
        Set<Integer> next = new HashSet<>();
        while (!dates.isEmpty()) {
            step++;
            if (minPath != -1 && step == minPath + 2) {
                return step;
            }
            for (int i = 0; i < dates.size(); i++) {
                //当前节点：dates.get(i)可以访问的所有下一个节点
                List<Integer> points = graphs.get(dates.get(i));
                for (int j = 0; j < points.size(); j++) {
                    int point = points.get(j);
                    if (point == n) {
                        //到达节点n了
                        if (minPath == -1) {
                            //此时获取的是最小次数
                            minPath = step;
                        } else {
                            if (step > minPath) {
                                return step;
                            }
                        }
                    }
                    if (next.contains(point)) {
                        //同一层中相同节点不能访问两次，去重
                        continue;
                    }
                    if (marks[point] >= 2) {
                        //当前节点访问超过两次不处理
                        continue;
                    }
                    next.add(point);
                    marks[point]++;
                }
            }
            dates.clear();
            dates.addAll(next);
            next.clear();
        }
        return minPath + 2;
    }
}
