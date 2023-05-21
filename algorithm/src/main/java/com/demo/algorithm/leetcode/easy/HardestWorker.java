package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/5/5
 * @author chenglong
 * description : 处理用时最长的那个任务的员工
 *
 * 共有n位员工，每位员工都有一个从0到n-1的唯一id。
 * 给你一个二维整数数组logs，其中logs[i]=[idi, leaveTimei]：
 * idi是处理第i个任务的员工的id，且
 * leaveTimei是员工完成第i个任务的时刻。所有leaveTimei的值都是唯一的。
 * 注意，第i个任务在第(i-1)个任务结束后立即开始，且第0个任务从时刻0开始。
 * 返回处理用时最长的那个任务的员工的id。如果存在两个或多个员工同时满足，则返回几人中最小的id。
 *
 * 示例 1：
 * 输入：n = 10, logs = [[0,3],[2,5],[0,9],[1,15]]
 * 输出：1
 * 解释：
 * 任务 0 于时刻 0 开始，且在时刻 3 结束，共计 3 个单位时间。
 * 任务 1 于时刻 3 开始，且在时刻 5 结束，共计 2 个单位时间。
 * 任务 2 于时刻 5 开始，且在时刻 9 结束，共计 4 个单位时间。
 * 任务 3 于时刻 9 开始，且在时刻 15 结束，共计 6 个单位时间。
 * 时间最长的任务是任务 3 ，而 id 为 1 的员工是处理此任务的员工，所以返回 1 。
 *
 * 示例 2：
 * 输入：n = 26, logs = [[1,1],[3,7],[2,12],[7,17]]
 * 输出：3
 * 解释：
 * 任务 0 于时刻 0 开始，且在时刻 1 结束，共计 1 个单位时间。
 * 任务 1 于时刻 1 开始，且在时刻 7 结束，共计 6 个单位时间。
 * 任务 2 于时刻 7 开始，且在时刻 12 结束，共计 5 个单位时间。
 * 任务 3 于时刻 12 开始，且在时刻 17 结束，共计 5 个单位时间。
 * 时间最长的任务是任务 1 ，而 id 为 3 的员工是处理此任务的员工，所以返回 3 。
 *
 * 示例 3：
 * 输入：n = 2, logs = [[0,10],[1,20]]
 * 输出：0
 * 解释：
 * 任务 0 于时刻 0 开始，且在时刻 10 结束，共计 10 个单位时间。
 * 任务 1 于时刻 10 开始，且在时刻 20 结束，共计 10 个单位时间。
 * 时间最长的任务是任务 0 和 1 ，处理这两个任务的员工的 id 分别是 0 和 1 ，所以返回最小的 0 。
 *
 * 提示：
 * 2 <= n <= 500
 * 1 <= logs.length <= 500
 * logs[i].length == 2
 * 0 <= idi <= n - 1
 * 1 <= leaveTimei <= 500
 * idi != idi + 1
 * leaveTimei 按严格递增顺序排列
 */
public class HardestWorker {

    public int hardestWorker(int n, int[][] logs) {
        int maxTime = logs[0][1];
        int id = logs[0][0];
        for (int i = 1; i < logs.length; i++) {
            int time = logs[i][1] - logs[i - 1][1];
            if (time > maxTime) {
                maxTime = time;
                id = logs[i][0];
            } else if (time == maxTime) {
                if (id > logs[i][0]) {
                    id = logs[i][0];
                }
            }
        }
        return id;
    }
}
