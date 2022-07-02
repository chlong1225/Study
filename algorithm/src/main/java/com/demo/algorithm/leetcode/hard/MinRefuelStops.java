package com.demo.algorithm.leetcode.hard;

/**
 * create on 2022/7/2
 * @author chenglong
 * description : 最低加油次数
 *
 * 汽车从起点出发驶向目的地，该目的地位于出发位置东面target英里处。
 * 沿途有加油站，每个station[i]代表一个加油站，它位于出发位置东面station[i][0]英里处，并且有station[i][1]升汽油。
 * 假设汽车油箱的容量是无限的，其中最初有startFuel升燃料。它每行驶1英里就会用掉1升汽油。
 * 当汽车到达加油站时，它可能停下来加油，将所有汽油从加油站转移到汽车中。
 * 为了到达目的地，汽车所必要的最低加油次数是多少？如果无法到达目的地，则返回 -1 。
 * 注意：如果汽车到达加油站时剩余燃料为0，它仍然可以在那里加油。如果汽车到达目的地时剩余燃料为0，仍然认为它已经到达目的地。
 *
 * 示例 1：
 * 输入：target = 1, startFuel = 1, stations = []
 * 输出：0
 * 解释：我们可以在不加油的情况下到达目的地。
 *
 * 示例 2：
 * 输入：target = 100, startFuel = 1, stations = [[10,100]]
 * 输出：-1
 * 解释：我们无法抵达目的地，甚至无法到达第一个加油站。
 *
 * 示例 3：
 * 输入：target = 100, startFuel = 10, stations = [[10,60],[20,30],[30,30],[60,40]]
 * 输出：2
 * 解释：
 * 我们出发时有 10 升燃料。
 * 我们开车来到距起点 10 英里处的加油站，消耗 10 升燃料。将汽油从 0 升加到 60 升。
 * 然后，我们从 10 英里处的加油站开到 60 英里处的加油站（消耗 50 升燃料），
 * 并将汽油从 10 升加到 50 升。然后我们开车抵达目的地。
 * 我们沿途在1两个加油站停靠，所以返回 2 。
 *
 * 提示：
 * 1 <= target, startFuel, stations[i][1] <= 10^9
 * 0 <= stations.length <= 500
 * 0 < stations[0][0] < stations[1][0] < ... < stations[stations.length-1][0] < target
 */
public class MinRefuelStops {

    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        //1，处理特殊场景：没有加油站时，判断初始燃油是否可以到达目的地
        if (stations == null || stations.length == 0) {
            if (startFuel >= target) {
                return 0;
            } else {
                return -1;
            }
        }
        if (startFuel >= target) {
            return 0;
        }
        int length = stations.length;
        //2，使用动态规划，定义两个状态数组进行滚动处理
        int[] dates = new int[length + 1];
        int[] next = new int[length + 1];
        dates[0] = startFuel;
        next[0] = startFuel;
        int start = 0;
        for (int i = 0; i < length; i++) {
            int position = stations[i][0];
            int num = stations[i][1];
            for (int j = start; j <= i; j++) {
                if (i % 2 == 0) {
                    //从dates转移到next
                    if (dates[j] < position) {
                        start++;
                        next[j + 1] = dates[j + 1];
                    } else {
                        next[j + 1] = Math.max(dates[j + 1], dates[j] + num);
                        //防止数据叠加越界
                        if (next[j + 1] > target) {
                            next[j + 1] = target;
                        }
                    }
                } else {
                    //从next转移到dates
                    if (next[j] < position) {
                        start++;
                        dates[j + 1] = next[j + 1];
                    } else {
                        dates[j + 1] = Math.max(next[j + 1], next[j] + num);
                        if (dates[j + 1] > target) {
                            dates[j + 1] = target;
                        }
                    }
                }
            }
        }
        int step = -1;
        for (int i = 0; i <= length; i++) {
            if (length % 2 == 0) {
                if (dates[i] >= target) {
                    step = i;
                    break;
                }
            } else {
                if (next[i] >= target) {
                    step = i;
                    break;
                }
            }
        }
        return step;
    }
}
