package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/12/13
 * @author chenglong
 * description : 加油站
 *
 * 在一条环路上有n个加油站，其中第i个加油站有汽油gas[i]升。
 * 你有一辆油箱容量无限的的汽车，从第i个加油站开往第i+1个加油站需要消耗汽油cost[i]升。你从其中的一个加油站出发，开始时油箱为空。
 * 给定两个整数数组gas和cost，如果你可以按顺序绕环路行驶一周，则返回出发时加油站的编号，否则返回-1。如果存在解，则保证它是唯一的。
 *
 * 示例 1:
 * 输入: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
 * 输出: 3
 * 解释:
 * 从 3 号加油站(索引为3处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
 * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
 * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
 * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
 * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
 * 因此，3 可为起始索引。
 *
 * 示例 2:
 * 输入: gas = [2,3,4], cost = [3,4,3]
 * 输出: -1
 * 解释:
 * 你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
 * 我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
 * 开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
 * 你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
 * 因此，无论怎样，你都不可能绕环路行驶一周。
 *
 * 提示:
 * gas.length == n
 * cost.length == n
 * 1 <= n <= 10^5
 * 0 <= gas[i], cost[i] <= 10^4
 */
public class CompleteCircuit {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int[] diff = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            //记录从i～i+1时剩余的油，如果为负数则表示当前加油不够到下一个加油站。不能作为起点
            diff[i] = gas[i] - cost[i];
            sum += diff[i];
        }
        if (sum < 0) {
            return -1;
        }
        //1，获取起始位置
        int start = -1;
        for (int i = 0; i < n; i++) {
            if (diff[i] > 0) {
                start = i;
                break;
            }
        }
        if (start == -1) {
            //此时diff中所有值都为0
            return 0;
        }
        while (start < n) {
            if (diff[start] <= 0) {
                start++;
            } else {
                sum = diff[start];
                int count = 1;
                while (count < n) {
                    start++;
                    sum += diff[start % n];
                    if (sum < 0) {
                        break;
                    } else {
                        count++;
                    }
                }
                if (count == n) {
                    return (start + 1) % n;
                }
            }
        }
        return -1;
    }
}
