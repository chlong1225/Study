package com.demo.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2023/11/22
 * @author chenglong
 * description : 传递信息
 *
 * 小朋友A在和ta的小伙伴们玩传信息游戏，游戏规则如下：
 * 有n名玩家，所有玩家编号分别为0～n-1，其中小朋友A的编号为0
 * 每个玩家都有固定的若干个可传信息的其他玩家（也可能没有）。传信息的关系是单向的（比如A可以向B传信息，但B不能向A传信息）。
 * 每轮信息必须需要传递给另一个人，且信息可重复经过同一个人
 * 给定总玩家数n，以及按[玩家编号,对应可传递玩家编号]关系组成的二维数组relation。返回信息从小A(编号0) 经过k轮传递到编号为n-1的小伙伴处的方案数；若不能到达，返回0。
 *
 * 示例 1：
 * 输入：n = 5, relation = [[0,2],[2,1],[3,4],[2,3],[1,4],[2,0],[0,4]], k = 3
 * 输出：3
 * 解释：信息从小 A 编号 0 处开始，经 3 轮传递，到达编号 4。共有 3 种方案，分别是 0->2->0->4， 0->2->1->4， 0->2->3->4。
 *
 * 示例 2：
 * 输入：n = 3, relation = [[0,2],[2,1]], k = 2
 * 输出：0
 * 解释：信息不能从小 A 处经过 2 轮传递到编号 2
 *
 * 限制：
 * 2 <= n <= 10
 * 1 <= k <= 5
 * 1 <= relation.length <= 90, 且 relation[i].length == 2
 * 0 <= relation[i][0],relation[i][1] < n 且 relation[i][0] != relation[i][1]
 */
public class NumWays {

    public int numWays(int n, int[][] relation, int k) {
        //记录玩家之间传递消息的关系
        List<List<Integer>> dates = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            dates.add(new ArrayList<>());
        }
        for (int i = 0; i < relation.length; i++) {
            dates.get(relation[i][0]).add(relation[i][1]);
        }
        int[][] marks = new int[k + 1][n];
        marks[0][0] = 1;
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < n; j++) {
                //此时第j个玩家传递消息
                if (marks[i - 1][j] == 0) {
                    //当前轮次当前玩家手头没有消息，无法向下传递
                    continue;
                }
                //此时玩家有消息可以向下传递，获取到可以传递的人
                List<Integer> items = dates.get(j);
                if (items.size() > 0) {
                    for (int l = 0; l < items.size(); l++) {
                        //此时从j -> items.get(l)
                        marks[i][items.get(l)] += marks[i - 1][j];
                    }
                }
            }
        }
        return marks[k][n - 1];
    }
}
