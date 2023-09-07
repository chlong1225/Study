package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/9/7
 * @author chenglong
 * description : 修车的最少时间
 *
 * 给你一个整数数组ranks，表示一些机械工的能力值。ranksi是第i位机械工的能力值。能力值为r的机械工可以在r*n^2分钟内修好n辆车。
 * 同时给你一个整数cars，表示总共需要修理的汽车数目。
 * 请你返回修理所有汽车最少需要多少时间。
 * 注意：所有机械工可以同时修理汽车。
 *
 * 示例 1：
 * 输入：ranks = [4,2,3,1], cars = 10
 * 输出：16
 * 解释：
 * - 第一位机械工修 2 辆车，需要 4 * 2 * 2 = 16 分钟。
 * - 第二位机械工修 2 辆车，需要 2 * 2 * 2 = 8 分钟。
 * - 第三位机械工修 2 辆车，需要 3 * 2 * 2 = 12 分钟。
 * - 第四位机械工修 4 辆车，需要 1 * 4 * 4 = 16 分钟。
 * 16 分钟是修理完所有车需要的最少时间。
 *
 * 示例 2：
 * 输入：ranks = [5,1,8], cars = 6
 * 输出：16
 * 解释：
 * - 第一位机械工修 1 辆车，需要 5 * 1 * 1 = 5 分钟。
 * - 第二位机械工修 4 辆车，需要 1 * 4 * 4 = 16 分钟。
 * - 第三位机械工修 1 辆车，需要 8 * 1 * 1 = 8 分钟。
 * 16 分钟时修理完所有车需要的最少时间。
 *
 * 提示：
 * 1 <= ranks.length <= 10^5
 * 1 <= ranks[i] <= 100
 * 1 <= cars <= 10^6
 */
public class RepairCars {

    public long repairCars(int[] ranks, int cars) {
        long base = 1;
        //1，遍历查找最小能力值
        int minRanks = ranks[0];
        int n = ranks.length;
        for (int i = 1; i < n; i++) {
            if (ranks[i] < minRanks) {
                minRanks = ranks[i];
            }
        }
        //2，最长时间为，能力值最小的人修好所有的车
        long max = base * minRanks * cars * cars;
        long min = 0;
        while (min < max) {
            //预计花的时间并统计当前时间内能够修好的车数
            long middle = (max + min) / 2;
            long sum = 0;
            for (int i = 0; i < n; i++) {
                sum += Math.sqrt(middle / ranks[i]);
            }
            if (sum < cars) {
                min = middle + 1;
            } else {
                max = middle;
            }
        }
        return min;
    }
}
