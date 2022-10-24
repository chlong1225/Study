package com.demo.algorithm.leetcode.hard;

import java.util.Arrays;

/**
 * create on 2022/10/24
 * @author chenglong
 * description : 规划兼职工作
 *
 * 你打算利用空闲时间来做兼职工作赚些零花钱。
 * 这里有n份兼职工作，每份工作预计从startTime[i]开始到endTime[i]结束，报酬为profit[i]。
 * 给你一份兼职工作表，包含开始时间startTime，结束时间endTime和预计报酬profit三个数组，请你计算并返回可以获得的最大报酬。
 * 注意时间上出现重叠的2份工作不能同时进行。
 * 如果你选择的工作在时间X结束，那么你可以立刻进行在时间X开始的下一份工作。
 *
 * 示例 1：
 * 输入：startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
 * 输出：120
 * 解释：
 * 我们选出第 1 份和第 4 份工作，
 * 时间范围是 [1-3]+[3-6]，共获得报酬 120 = 50 + 70。
 *
 * 示例 2：
 * 输入：startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
 * 输出：150
 * 解释：
 * 我们选择第 1，4，5 份工作。
 * 共获得报酬 150 = 20 + 70 + 60。
 *
 * 示例 3：
 * 输入：startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
 * 输出：6
 *
 * 提示：
 * 1 <= startTime.length == endTime.length ==profit.length<= 5 * 10^4
 * 1 <=startTime[i] <endTime[i] <= 10^9
 * 1 <=profit[i] <= 10^4
 */
public class JobScheduling {

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int length = startTime.length;
        int[][] dates = new int[length][3];
        for (int i = 0; i < length; i++) {
            dates[i][0] = startTime[i];
            dates[i][1] = endTime[i];
            dates[i][2] = profit[i];
        }
        Arrays.sort(dates, (o1, o2) -> o1[1] - o2[1]);
        int[] marks = new int[length + 1];
        marks[1] = dates[0][2];
        for (int i = 2; i <= length; i++) {
            int[] cur = dates[i - 1];
            marks[i] = Math.max(marks[i - 1], marks[searchIndex(dates, i - 1, cur[0])] + cur[2]);
        }
        return marks[length];
    }

    private int searchIndex(int[][] dates, int end, int target) {
        int start = 0;
        while (start < end) {
            int middle = (end - start) / 2 + start;
            if (dates[middle][1] > target) {
                end = middle;
            } else {
                start = middle + 1;
            }
        }
        return start;
    }
}
