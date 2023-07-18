package com.demo.algorithm.leetcode.hard;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * create on 2023/7/18
 * @author chenglong
 * description : 包含每个查询的最小区间
 *
 * 给你一个二维整数数组intervals，其中intervals[i] = [lefti, righti] 表示第i个区间开始于lefti、结束于righti（包含两侧取值，闭区间）。
 * 区间的长度定义为区间中包含的整数数目，更正式地表达是righti-lefti+1。
 * 再给你一个整数数组queries。第j个查询的答案是满足lefti <= queries[j] <= righti 的长度最小区间i的长度。如果不存在这样的区间，那么答案是-1。
 * 以数组形式返回对应查询的所有答案。
 *
 * 示例 1：
 * 输入：intervals = [[1,4],[2,4],[3,6],[4,4]], queries = [2,3,4,5]
 * 输出：[3,3,1,4]
 * 解释：查询处理如下：
 * - Query = 2 ：区间 [2,4] 是包含 2 的最小区间，答案为 4 - 2 + 1 = 3 。
 * - Query = 3 ：区间 [2,4] 是包含 3 的最小区间，答案为 4 - 2 + 1 = 3 。
 * - Query = 4 ：区间 [4,4] 是包含 4 的最小区间，答案为 4 - 4 + 1 = 1 。
 * - Query = 5 ：区间 [3,6] 是包含 5 的最小区间，答案为 6 - 3 + 1 = 4 。
 *
 * 示例 2：
 * 输入：intervals = [[2,3],[2,5],[1,8],[20,25]], queries = [2,19,5,22]
 * 输出：[2,-1,4,6]
 * 解释：查询处理如下：
 * - Query = 2 ：区间 [2,3] 是包含 2 的最小区间，答案为 3 - 2 + 1 = 2 。
 * - Query = 19：不存在包含 19 的区间，答案为 -1 。
 * - Query = 5 ：区间 [2,5] 是包含 5 的最小区间，答案为 5 - 2 + 1 = 4 。
 * - Query = 22：区间 [20,25] 是包含 22 的最小区间，答案为 25 - 20 + 1 = 6 。
 *
 * 提示：
 * 1 <= intervals.length <= 10^5
 * 1 <= queries.length <= 10^5
 * queries[i].length == 2
 * 1 <= lefti <= righti <= 10^7
 * 1 <= queries[j] <= 10^7
 */
public class MinInterval {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int[] minInterval(int[][] intervals, int[] queries) {
        int n = queries.length;
        int[][] dates = new int[n][2];
        for (int i = 0; i < n; i++) {
            dates[i][0] = queries[i];
            dates[i][1] = i;
        }
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        Arrays.sort(dates, (o1, o2) -> o1[0] - o2[0]);
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        int[] result = new int[n];
        int index = 0;
        for (int i = 0; i < n; i++) {
            int compare = dates[i][0];
            if (i > 0 && compare == dates[i - 1][0]) {
                result[dates[i][1]] = result[dates[i - 1][1]];
                continue;
            }
            while (index < intervals.length) {
                int left = intervals[index][0];
                int right = intervals[index][1];
                if (left <= compare) {
                    queue.offer(new int[]{right - left + 1, left, right});
                    index++;
                } else {
                    break;
                }
            }
            while (!queue.isEmpty()) {
                if (queue.peek()[2] < compare) {
                    queue.poll();
                } else {
                    break;
                }
            }
            if (queue.isEmpty()) {
                result[dates[i][1]] = -1;
            } else {
                result[dates[i][1]] = queue.peek()[0];
            }
        }
        return result;
    }
}
