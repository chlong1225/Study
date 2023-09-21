package com.demo.algorithm.leetcode.hard.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2023/9/21
 * @author chenglong
 * description : 收集树中金币
 *
 * 给你一个n个节点的无向无根树，节点编号从0到n-1。给你整数n和一个长度为n-1的二维整数数组edges ，其中edges[i]=[ai,bi]表示树中节点ai和bi之间有一条边。
 * 再给你一个长度为n的数组coins，其中coins[i]可能为0也可能为1，1表示节点i处有一个金币。
 * 一开始，你需要选择树中任意一个节点出发。你可以执行下述操作任意次：
 * 收集距离当前节点距离为2以内的所有金币，或者
 * 移动到树中一个相邻节点。
 * 你需要收集树中所有的金币，并且回到出发节点，请你返回最少经过的边数。
 * 如果你多次经过一条边，每一次经过都会给答案加一。
 *
 * 示例 1：
 * 输入：coins = [1,0,0,0,0,1], edges = [[0,1],[1,2],[2,3],[3,4],[4,5]]
 * 输出：2
 * 解释：从节点 2 出发，收集节点 0 处的金币，移动到节点 3 ，收集节点 5 处的金币，然后移动回节点 2 。
 *
 * 示例 2：
 * 输入：coins = [0,0,0,1,1,0,0,1], edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[5,6],[5,7]]
 * 输出：2
 * 解释：从节点 0 出发，收集节点 4 和 3 处的金币，移动到节点 2 处，收集节点 7 处的金币，移动回节点 0 。
 *
 * 提示：
 * n == coins.length
 * 1 <= n <= 3 * 10^4
 * 0 <= coins[i] <= 1
 * edges.length == n - 1
 * edges[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * edges 表示一棵合法的树。
 */
public class CollectTheCoins {

    public int collectTheCoins(int[] coins, int[][] edges) {
        int n = coins.length;
        //1，计算每一个节点的度
        List<List<Integer>> dates = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            dates.add(new ArrayList<>());
        }
        int[] counts = new int[n];
        for (int i = 0; i < edges.length; i++) {
            dates.get(edges[i][0]).add(edges[i][1]);
            dates.get(edges[i][1]).add(edges[i][0]);
            counts[edges[i][0]]++;
            counts[edges[i][1]]++;
        }
        //2，删除无效的叶子节点
        List<Integer> curs = new ArrayList<>();
        List<Integer> nexts = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (counts[i] == 1 && coins[i] == 0) {
                curs.add(i);
            }
        }
        //删除的节点数
        int deleteCount = 0;
        while (curs.size() > 0) {
            deleteCount += curs.size();
            for (int i = 0; i < curs.size(); i++) {
                //删除的节点度修改为0
                counts[curs.get(i)] = 0;
                List<Integer> item = dates.get(curs.get(i));
                if (item.size() > 0) {
                    for (int j = 0; j < item.size(); j++) {
                        int next = item.get(j);
                        counts[next]--;
                        if (counts[next] == 1 && coins[next] == 0) {
                            nexts.add(next);
                        }
                    }
                }
            }
            curs.clear();
            curs.addAll(nexts);
            nexts.clear();
        }
        //连续删除两次
        for (int i = 0; i < n; i++) {
            if (counts[i] == 1) {
                curs.add(i);
            }
        }
        int step = 0;
        while (curs.size() > 0) {
            deleteCount += curs.size();
            step++;
            for (int i = 0; i < curs.size(); i++) {
                counts[curs.get(i)] = 0;
                List<Integer> item = dates.get(curs.get(i));
                if (item.size() > 0) {
                    for (int j = 0; j < item.size(); j++) {
                        int next = item.get(j);
                        counts[next]--;
                        if (counts[next] == 1) {
                            nexts.add(next);
                        }
                    }
                }
            }
            curs.clear();
            curs.addAll(nexts);
            nexts.clear();
            if (step == 2) {
                break;
            }
        }
        int lastNode = n - deleteCount;
        if (lastNode == 0) {
            return 0;
        }
        return (lastNode - 1) * 2;
    }
}
