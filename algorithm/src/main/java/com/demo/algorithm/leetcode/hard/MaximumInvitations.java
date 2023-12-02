package com.demo.algorithm.leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2023/11/2
 * @author chenglong
 * description : 参加会议的最多员工数
 *
 * 一个公司准备组织一场会议，邀请名单上有n位员工。公司准备了一张圆形的桌子，可以坐下任意数目的员工。
 * 员工编号为0到n-1。每位员工都有一位喜欢的员工，每位员工当且仅当他被安排在喜欢员工的旁边，他才会参加会议。每位员工喜欢的员工不会是他自己。
 * 给你一个下标从0开始的整数数组favorite，其中favorite[i]表示第i位员工喜欢的员工。请你返回参加会议的最多员工数目。
 *
 * 示例 1：
 * 输入：favorite = [2,2,1,2]
 * 输出：3
 * 解释：
 * 上图展示了公司邀请员工 0，1 和 2 参加会议以及他们在圆桌上的座位。
 * 没办法邀请所有员工参与会议，因为员工 2 没办法同时坐在 0，1 和 3 员工的旁边。
 * 注意，公司也可以邀请员工 1，2 和 3 参加会议。
 * 所以最多参加会议的员工数目为 3 。
 *
 * 示例 2：
 * 输入：favorite = [1,2,0]
 * 输出：3
 * 解释：
 * 每个员工都至少是另一个员工喜欢的员工。所以公司邀请他们所有人参加会议的前提是所有人都参加了会议。
 * 座位安排同图 1 所示：
 * - 员工 0 坐在员工 2 和 1 之间。
 * - 员工 1 坐在员工 0 和 2 之间。
 * - 员工 2 坐在员工 1 和 0 之间。
 * 参与会议的最多员工数目为 3 。
 *
 * 示例 3：
 * 输入：favorite = [3,0,1,4,1]
 * 输出：4
 * 解释：
 * 上图展示了公司可以邀请员工 0，1，3 和 4 参加会议以及他们在圆桌上的座位。
 * 员工 2 无法参加，因为他喜欢的员工 0 旁边的座位已经被占领了。
 * 所以公司只能不邀请员工 2 。
 * 参加会议的最多员工数目为 4 。
 *
 * 提示：
 * n == favorite.length
 * 2 <= n <= 10^5
 * 0 <= favorite[i] <= n - 1
 * favorite[i] != i
 */
public class MaximumInvitations {

    public int maximumInvitations(int[] favorite) {
        int n = favorite.length;
        //1，统计员工的入度，即喜欢当前员工的人数
        int[] counts = new int[n];
        for (int i = 0; i < n; i++) {
            counts[favorite[i]]++;
        }
        //2，统计入度为0的员工
        List<Integer> curs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (counts[i] == 0) {
                curs.add(i);
            }
        }
        //3，统计员工链长，默认长度为1即员工本身
        int[] dates = new int[n];
        for (int i = 0; i < n; i++) {
            dates[i] = 1;
        }
        List<Integer> nexts = new ArrayList<>();
        while (curs.size() > 0) {
            for (int i = 0; i < curs.size(); i++) {
                //当前员工与下一个员工
                int cur = curs.get(i);
                int next = favorite[cur];
                dates[next] = Math.max(dates[next], dates[cur] + 1);
                counts[next]--;
                if (counts[next] <= 0) {
                    nexts.add(next);
                }
            }
            curs.clear();
            curs.addAll(nexts);
            nexts.clear();
        }
        //3，统计链长总和与最大环长度
        int sumLine = 0;
        int maxRing = 0;
        for (int i = 0; i < n; i++) {
            if (counts[i] == 0) {
                continue;
            }
            counts[i] = 0;
            if (favorite[favorite[i]] == i) {
                //两个节点构成环
                sumLine += (dates[i] + dates[favorite[i]]);
                counts[favorite[i]] = 0;
            } else {
                //两个以上节点
                int cur = i;
                int count = 1;
                while (favorite[cur] != i) {
                    count++;
                    cur = favorite[cur];
                    counts[cur] = 0;
                }
                if (count > maxRing) {
                    maxRing = count;
                }
            }
        }
        return Math.max(sumLine, maxRing);
    }
}
