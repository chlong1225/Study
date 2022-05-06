package com.demo.algorithm.leetcode.contest.doubleweek76;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2022/4/19.
 * description : 节点序列的最大得分
 *
 * 给你一个n个节点的无向图，节点编号为0到n - 1。
 * 给你一个下标从0开始的整数数组scores，其中scores[i]是第i个节点的分数。同时给你一个二维整数数组edges，
 * 其中edges[i] = [ai, bi]，表示节点ai和bi之间有一条无向边。
 * 一个合法的节点序列如果满足以下条件，我们称它是 合法的：
 * 序列中每相邻节点之间有边相连。
 * 序列中没有节点出现超过一次。
 * 节点序列的分数定义为序列中节点分数之和 。
 * 请你返回一个长度为4的合法节点序列的最大分数。如果不存在这样的序列，请你返回 -1。
 *
 * 示例 1：
 * 输入：scores = [5,2,9,8,4], edges = [[0,1],[1,2],[2,3],[0,2],[1,3],[2,4]]
 * 输出：24
 * 解释：上图为输入的图，节点序列为 [0,1,2,3] 。
 * 节点序列的分数为 5 + 2 + 9 + 8 = 24 。
 * 观察可知，没有其他节点序列得分和超过 24 。
 * 注意节点序列 [3,1,2,0] 和 [1,0,2,3] 也是合法的，且分数为 24 。
 * 序列 [0,3,2,4] 不是合法的，因为没有边连接节点 0 和 3 。
 *
 * 示例 2：
 * 输入：scores = [9,20,6,4,11,12], edges = [[0,3],[5,3],[2,4],[1,3]]
 * 输出：-1
 * 解释：上图为输入的图。
 * 没有长度为 4 的合法序列，所以我们返回 -1 。
 *
 * 提示：
 * n == scores.length
 * 4 <= n <= 5 * 10^4
 * 1 <= scores[i] <= 10^8
 * 0 <= edges.length <= 5 * 10^4
 * edges[i].length == 2
 * 0 <= ai, bi <= n - 1
 * ai != bi
 * 不会有重边。
 */
public class MaximumScore {

    public int maximumScore(int[] scores, int[][] edges) {
        int max = -1;
        int n = scores.length;
        //构建图的数据，只记录三个最大分数的节点。并且0位置放最大值，1位置放次大值，2位置最小
        List<List<Integer>> dates = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            dates.add(new ArrayList<>());
        }
        int length = edges.length;
        for (int i = 0; i < length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            List<Integer> items = dates.get(from);
            if (items.size() == 0) {
                items.add(to);
            } else if (items.size() == 1) {
                if (scores[to] > scores[items.get(0)]) {
                    items.add(0, to);
                } else {
                    items.add(to);
                }
            } else if (items.size() == 2) {
                if (scores[to] <= scores[items.get(1)]) {
                    items.add(to);
                } else {
                    if (scores[to] <= scores[items.get(0)]) {
                        items.add(items.get(1));
                        items.set(1, to);
                    } else {
                        items.add(items.get(1));
                        items.set(1, items.get(0));
                        items.set(0, to);
                    }
                }
            } else {
                if (scores[to] > scores[items.get(2)]) {
                    if (scores[to] <= scores[items.get(1)]) {
                        items.set(2, to);
                    } else {
                        if (scores[to] <= scores[items.get(0)]) {
                            items.set(2, items.get(1));
                            items.set(1, to);
                        } else {
                            items.set(2, items.get(1));
                            items.set(1, items.get(0));
                            items.set(0, to);
                        }
                    }
                }
            }

            List<Integer> items2 = dates.get(to);
            if (items2.size() == 0) {
                items2.add(from);
            } else if (items2.size() == 1) {
                if (scores[from] > scores[items2.get(0)]) {
                    items2.add(0, from);
                } else {
                    items2.add(from);
                }
            } else if (items2.size() == 2) {
                if (scores[from] <= scores[items2.get(1)]) {
                    items2.add(from);
                } else {
                    if (scores[from] <= scores[items2.get(0)]) {
                        items2.add(items2.get(1));
                        items2.set(1, from);
                    } else {
                        items2.add(items2.get(1));
                        items2.set(1, items2.get(0));
                        items2.set(0, from);
                    }
                }
            } else {
                if (scores[from] > scores[items2.get(2)]) {
                    if (scores[from] <= scores[items2.get(1)]) {
                        items2.set(2, from);
                    } else {
                        if (scores[from] <= scores[items2.get(0)]) {
                            items2.set(2, items2.get(1));
                            items2.set(1, from);
                        } else {
                            items2.set(2, items2.get(1));
                            items2.set(1, items2.get(0));
                            items2.set(0, from);
                        }
                    }
                }
            }
        }
        //枚举边
        for (int i = 0; i < length; i++) {
            //此时已经有两个点，然后从左从右分别找一个点
            int left = edges[i][0];
            int right = edges[i][1];
            List<Integer> items = dates.get(left);
            int a = items.get(0);
            if (a == right) {
                if (items.size() == 1) {
                    continue;
                }
                a = items.get(1);
            }
            List<Integer> items2 = dates.get(right);
            int b = items2.get(0);
            if (b == left) {
                if (items2.size() == 1) {
                    continue;
                }
                b = items2.get(1);
            }
            //特殊场景：a=b
            if (a == b) {
                //需要重新查找节点
                int a2 = -1;
                for (int j = 1; j < items.size(); j++) {
                    if (items.get(j) != right && items.get(j) != a) {
                        a2 = items.get(j);
                        break;
                    }
                }
                int b2 = -1;
                for (int j = 1; j < items2.size(); j++) {
                    if (items2.get(j) != left && items2.get(j) != b) {
                        b2 = items2.get(j);
                        break;
                    }
                }
                if (a2 == -1) {
                    if (b2 != -1) {
                        int sum = scores[a] + scores[left] + scores[right] + scores[b2];
                        if (sum > max) {
                            max = sum;
                        }
                    }
                } else {
                    if (b2 == -1) {
                        int sum = scores[a2] + scores[left] + scores[right] + scores[b];
                        if (sum > max) {
                            max = sum;
                        }
                    } else {
                        int sum = scores[a] + scores[left] + scores[right] + Math.max(scores[a2], scores[b2]);
                        if (sum > max) {
                            max = sum;
                        }
                    }
                }
            } else {
                int sum = scores[a] + scores[left] + scores[right] + scores[b];
                if (sum > max) {
                    max = sum;
                }
            }
        }
        return max;
    }
}
