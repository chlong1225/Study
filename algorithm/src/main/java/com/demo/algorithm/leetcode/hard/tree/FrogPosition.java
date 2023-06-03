package com.demo.algorithm.leetcode.hard.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2023/6/1
 * @author chenglong
 * description : T秒后青蛙的位置
 *
 * 给你一棵由n个顶点组成的无向树，顶点编号从1到n。青蛙从顶点1开始起跳。规则如下：
 * 在一秒内，青蛙从它所在的当前顶点跳到另一个未访问过的顶点（如果它们直接相连）。
 * 青蛙无法跳回已经访问过的顶点。
 * 如果青蛙可以跳到多个不同顶点，那么它跳到其中任意一个顶点上的机率都相同。
 * 如果青蛙不能跳到任何未访问过的顶点上，那么它每次跳跃都会停留在原地。
 * 无向树的边用数组edges描述，其中edges[i]=[ai, bi]意味着存在一条直接连通ai和bi两个顶点的边。
 * 返回青蛙在t秒后位于目标顶点target上的概率。与实际答案相差不超过 10^-5 的结果将被视为正确答案。
 *
 * 示例 1：
 * 输入：n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 2, target = 4
 * 输出：0.16666666666666666
 * 解释：上图显示了青蛙的跳跃路径。青蛙从顶点 1 起跳，第 1 秒 有 1/3 的概率跳到顶点 2 ，然后第 2 秒 有 1/2 的概率跳到顶点 4，因此青蛙在 2 秒后位于顶点 4 的概率是 1/3 * 1/2 = 1/6 = 0.16666666666666666 。
 *
 * 示例 2：
 * 输入：n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 1, target = 7
 * 输出：0.3333333333333333
 * 解释：上图显示了青蛙的跳跃路径。青蛙从顶点 1 起跳，有 1/3 = 0.3333333333333333 的概率能够 1 秒 后跳到顶点 7 。
 *
 * 提示：
 * 1 <= n <= 100
 * edges.length == n - 1
 * edges[i].length == 2
 * 1 <= ai, bi<= n
 * 1 <= t <= 50
 * 1 <= target <= n
 */
public class FrogPosition {

    public double frogPosition(int n, int[][] edges, int t, int target) {
        //1，构建树的结构
        List<List<Integer>> dates = new ArrayList<>(n + 1);
        for (int i = 0; i < n + 1; i++) {
            dates.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            dates.get(edges[i][0]).add(edges[i][1]);
            dates.get(edges[i][1]).add(edges[i][0]);
        }
        //添加特殊场景
        if (n == 1 && target == 1) {
            return 1.0;
        }
        //2，遍历搜索
        boolean[] marks = new boolean[n + 1];
        List<PointInfo> curs = new ArrayList<>();
        curs.add(new PointInfo(1, 1));
        marks[1] = true;
        List<PointInfo> nexts = new ArrayList<>();
        while (curs.size() > 0 && t > 0) {
            t--;
            for (int i = 0; i < curs.size(); i++) {
                PointInfo pointInfo = curs.get(i);
                List<Integer> tem = dates.get(pointInfo.position);
                if (tem.size() > 0) {
                    //跳转到下一个节点的概率
                    int count = 0;
                    for (int j = 0; j < tem.size(); j++) {
                        if (!marks[tem.get(j)]) {
                            count++;
                        }
                    }
                    if (count == 0) {
                        continue;
                    }
                    double p = 1.0 / count;
                    for (int j = 0; j < tem.size(); j++) {
                        if (!marks[tem.get(j)]) {
                            marks[tem.get(j)] = true;
                            if (tem.get(j) == target) {
                                if (t == 0) {
                                    //跳跃次数消耗完
                                    return p * pointInfo.probability;
                                } else {
                                    //还有跳跃次数时，如果当前位置没有其它访问点时，停留在当前位置
                                    List<Integer> tems = dates.get(target);
                                    boolean hasFind = false;
                                    for (int k = 0; k < tems.size(); k++) {
                                        if (!marks[tems.get(k)]) {
                                            hasFind = true;
                                            break;
                                        }
                                    }
                                    if (hasFind) {
                                        return 0;
                                    }
                                    return p * pointInfo.probability;
                                }
                            }
                            nexts.add(new PointInfo(tem.get(j), p * pointInfo.probability));
                        }
                    }
                }
            }
            curs.clear();
            curs.addAll(nexts);
        }
        return 0;
    }

    private class PointInfo{

        private int position;
        private double probability;

        public PointInfo(int position, double probability) {
            this.position = position;
            this.probability = probability;
        }
    }
}
