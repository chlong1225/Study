package com.demo.algorithm.leetcode.contest.week304;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * create on 2022/8/4
 * @author chenglong
 * description : 找到离给定两个节点最近的节点
 *
 * 给你一个n个节点的有向图，节点编号为0到n-1，每个节点至多有一条出边。
 * 有向图用大小为n下标从0开始的数组edges表示，表示节点i有一条有向边指向edges[i]。如果节点i没有出边，那么edges[i]==-1。
 * 同时给你两个节点node1和node2。
 * 请你返回一个从node1和node2都能到达节点的编号，使节点node1和节点node2到这个节点的距离较大值最小化。如果有多个答案，请返回最小的节点编号。如果答案不存在，返回 -1。
 * 注意edges可能包含环。
 *
 * 示例 1：
 * 输入：edges = [2,2,3,-1], node1 = 0, node2 = 1
 * 输出：2
 * 解释：从节点 0 到节点 2 的距离为 1 ，从节点 1 到节点 2 的距离为 1 。
 * 两个距离的较大值为 1 。我们无法得到一个比 1 更小的较大值，所以我们返回节点 2 。
 *
 * 示例 2：
 * 输入：edges = [1,2,-1], node1 = 0, node2 = 2
 * 输出：2
 * 解释：节点 0 到节点 2 的距离为 2 ，节点 2 到它自己的距离为 0 。
 * 两个距离的较大值为 2 。我们无法得到一个比 2 更小的较大值，所以我们返回节点 2 。
 *
 * 提示：
 * n == edges.length
 * 2 <= n <= 10^5
 * -1 <= edges[i] < n
 * edges[i] != i
 * 0 <= node1, node2 < n
 */
public class ClosestMeetingNode {

    public int closestMeetingNode(int[] edges, int node1, int node2) {
        //记录node1节点构建的排序
        Map<Integer, Integer> dates1 = new HashMap<>();
        int index = 0;
        dates1.put(node1, index);
        index++;
        //用于去重，防止有环
        Set<Integer> marks = new HashSet<>();
        marks.add(node1);
        int cur = node1;
        while (true) {
            int next = edges[cur];
            if (next == -1) {
                break;
            }
            if (marks.add(next)) {
                dates1.put(next, index);
                index++;
                cur = next;
            } else {
                break;
            }
        }
        //获取node2构建的排序
        Map<Integer, Integer> dates2 = new HashMap<>();
        index = 0;
        dates2.put(node2, index);
        index++;
        marks.clear();
        marks.add(node2);
        cur = node2;
        while (true) {
            int next = edges[cur];
            if (next == -1) {
                break;
            }
            if (marks.add(next)) {
                dates2.put(next, index);
                index++;
                cur = next;
            } else {
                break;
            }
        }
        //遍历node1可达的节点，然后查找node2是否可达
        int min = Integer.MAX_VALUE;
        int result = -1;
        for (int key : dates1.keySet()) {
            if (dates2.containsKey(key)) {
                int step = Math.max(dates1.get(key), dates2.get(key));
                if (step < min) {
                    min = step;
                    result = key;
                } else if (step == min) {
                    if (result > key) {
                        result = key;
                    }
                }
            }
        }
        return result;
    }
}
