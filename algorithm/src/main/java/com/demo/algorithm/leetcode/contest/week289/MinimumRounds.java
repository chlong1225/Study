package com.demo.algorithm.leetcode.contest.week289;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * create on 2022/5/5
 * @author chenglong
 * description : 完成所有任务需要的最少轮数
 *
 * 给你一个下标从0开始的整数数组tasks，其中tasks[i]表示任务的难度级别。在每一轮中你可以完成2个或者3个相同难度级别的任务。
 *
 * 返回完成所有任务需要的最少轮数，如果无法完成所有任务，返回 -1 。
 *
 * 示例 1：
 * 输入：tasks = [2,2,3,3,2,4,4,4,4,4]
 * 输出：4
 * 解释：要想完成所有任务，一个可能的计划是：
 * - 第一轮，完成难度级别为 2 的 3 个任务。
 * - 第二轮，完成难度级别为 3 的 2 个任务。
 * - 第三轮，完成难度级别为 4 的 3 个任务。
 * - 第四轮，完成难度级别为 4 的 2 个任务。
 * 可以证明，无法在少于 4 轮的情况下完成所有任务，所以答案为 4 。
 *
 * 示例 2：
 * 输入：tasks = [2,3,3]
 * 输出：-1
 * 解释：难度级别为 2 的任务只有 1 个，但每一轮执行中，只能选择完成 2 个或者 3 个相同难度级别的任务。因此，无法完成所有任务，答案为 -1 。
 *
 * 提示：
 * 1 <= tasks.length <= 10^5
 * 1 <= tasks[i] <= 10^9
 */
public class MinimumRounds {

    public int minimumRounds(int[] tasks) {
        int length = tasks.length;
        if (length == 1) {
            return -1;
        }
        //1，统计任务对应的数量
        Map<Integer, Integer> marks = new HashMap<>();
        for (int i = 0; i < length; i++) {
            int count = 1;
            if (marks.containsKey(tasks[i])) {
                count = marks.get(tasks[i]) + 1;
            }
            marks.put(tasks[i], count);
        }
        //2，遍历统计的数据
        int sum = 0;
        for (int key : marks.keySet()) {
            int count = marks.get(key);
            if (count == 1) {
                return -1;
            }
            if (count % 3 == 0) {
                sum += (count / 3);
            } else {
                sum += (count / 3);
                sum++;
            }
        }
        return sum;
    }

    public int minimumRounds2(int[] tasks) {
        int length = tasks.length;
        if (length == 1) {
            return -1;
        }
        //1，任务级别进行排序
        Arrays.sort(tasks);
        //2，遍历统计相同数量
        int sum = 0;
        int pre = tasks[0];
        int count = 1;
        for (int i = 1; i < length; i++) {
            if (tasks[i] == pre) {
                count++;
            } else {
                if (count == 1) {
                    return -1;
                }
                if (count % 3 == 0) {
                    sum += (count / 3);
                } else {
                    sum += (count / 3);
                    sum++;
                }
                pre = tasks[i];
                count = 1;
            }
        }
        if (count == 1) {
            return -1;
        }
        if (count % 3 == 0) {
            sum += (count / 3);
        } else {
            sum += (count / 3);
            sum++;
        }
        return sum;
    }
}
