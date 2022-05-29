package com.demo.algorithm.leetcode.contest.doubleweek79;

import java.util.Arrays;

/**
 * Created by chl on 2022/5/29.
 * description : 道路的最大总重要性
 *
 * 给你一个整数n，表示一个国家里的城市数目。城市编号为0到n-1。
 * 给你一个二维整数数组roads，其中roads[i] = [ai, bi]表示城市ai和bi之间有一条双向道路。
 * 你需要给每个城市安排一个从1到n之间的整数值，且每个值只能被使用一次。道路的重要性定义为这条道路连接的两座城市数值之和。
 * 请你返回在最优安排下，所有道路重要性之和最大为多少。
 *
 * 示例 1：
 * 输入：n = 5, roads = [[0,1],[1,2],[2,3],[0,2],[1,3],[2,4]]
 * 输出：43
 * 解释：上图展示了国家图和每个城市被安排的值 [2,4,5,3,1] 。
 * - 道路 (0,1) 重要性为 2 + 4 = 6 。
 * - 道路 (1,2) 重要性为 4 + 5 = 9 。
 * - 道路 (2,3) 重要性为 5 + 3 = 8 。
 * - 道路 (0,2) 重要性为 2 + 5 = 7 。
 * - 道路 (1,3) 重要性为 4 + 3 = 7 。
 * - 道路 (2,4) 重要性为 5 + 1 = 6 。
 * 所有道路重要性之和为 6 + 9 + 8 + 7 + 7 + 6 = 43 。
 * 可以证明，重要性之和不可能超过 43 。
 *
 * 示例 2：
 * 输入：n = 5, roads = [[0,3],[2,4],[1,3]]
 * 输出：20
 * 解释：上图展示了国家图和每个城市被安排的值 [4,3,2,5,1] 。
 * - 道路 (0,3) 重要性为 4 + 5 = 9 。
 * - 道路 (2,4) 重要性为 2 + 1 = 3 。
 * - 道路 (1,3) 重要性为 3 + 5 = 8 。
 * 所有道路重要性之和为 9 + 3 + 8 = 20 。
 * 可以证明，重要性之和不可能超过 20 。
 *
 * 提示：
 * 2 <= n <= 5 * 10^4
 * 1 <= roads.length <= 5 * 10^4
 * roads[i].length == 2
 * 0 <= ai, bi <= n - 1
 * ai != bi
 * 没有重复道路。
 */
public class MaximumImportance {

    public long maximumImportance(int n, int[][] roads) {
        //记录当前路连接其它路的数量，连接次数越多，赋值最大
        int[] counts = new int[n];
        int length = roads.length;
        for (int i = 0; i < length; i++) {
            counts[roads[i][0]]++;
            counts[roads[i][1]]++;
        }
        //对数量进行排序
        Arrays.sort(counts);
        long sum = 0;
        long base = 1;
        for (int i = 0; i < n; i++) {
            sum += (base * counts[i] * (i + 1));
        }
        return sum;
    }
}
