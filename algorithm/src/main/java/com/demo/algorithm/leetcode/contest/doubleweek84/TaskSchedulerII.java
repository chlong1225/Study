package com.demo.algorithm.leetcode.contest.doubleweek84;

import java.util.HashMap;
import java.util.Map;

/**
 * create on 2022/8/12
 * @author chenglong
 * description : 任务调度器II
 *
 * 给你一个下标从0开始的正整数数组tasks，表示需要按顺序完成的任务，其中tasks[i]表示第i件任务的类型。
 * 同时给你一个正整数space，表示一个任务完成后，另一个相同类型任务完成前需要间隔的最少天数。
 * 在所有任务完成前的每一天，你都必须进行以下两种操作中的一种：
 * 完成tasks中的下一个任务
 * 休息一天
 * 请你返回完成所有任务所需的最少天数。
 *
 * 示例 1：
 * 输入：tasks = [1,2,1,2,3,1], space = 3
 * 输出：9
 * 解释：
 * 9 天完成所有任务的一种方法是：
 * 第 1 天：完成任务 0 。
 * 第 2 天：完成任务 1 。
 * 第 3 天：休息。
 * 第 4 天：休息。
 * 第 5 天：完成任务 2 。
 * 第 6 天：完成任务 3 。
 * 第 7 天：休息。
 * 第 8 天：完成任务 4 。
 * 第 9 天：完成任务 5 。
 * 可以证明无法少于9天完成所有任务。
 *
 * 示例 2：
 * 输入：tasks = [5,8,8,5], space = 2
 * 输出：6
 * 解释：
 * 6 天完成所有任务的一种方法是：
 * 第 1 天：完成任务 0 。
 * 第 2 天：完成任务 1 。
 * 第 3 天：休息。
 * 第 4 天：休息。
 * 第 5 天：完成任务 2 。
 * 第 6 天：完成任务 3 。
 * 可以证明无法少于 6 天完成所有任务。
 *
 * 提示：
 * 1 <= tasks.length <= 10^5
 * 1 <= tasks[i] <= 10^9
 * 1 <= space <= tasks.length
 */
public class TaskSchedulerII {

    public long taskSchedulerII(int[] tasks, int space) {
        //记录当前任务的能够开始的时间
        Map<Integer, Long> marks = new HashMap<>();
        long count = 0;
        long day = 1;
        int length = tasks.length;
        for (int i = 0; i < length; i++) {
            int cur = tasks[i];
            if (marks.containsKey(cur)) {
                long start = marks.get(cur);
                if (start <= day) {
                    //当天直接完成任务
                    count++;
                    day++;
                    marks.put(cur, day + space);
                } else {
                    //此时需要休息start-day天
                    count += (start - day + 1);
                    day = start + 1;
                    marks.put(cur, day + space);
                }
            } else {
                //当天完成任务
                count++;
                day++;
                marks.put(cur, day + space);
            }
        }
        return count;
    }
}
