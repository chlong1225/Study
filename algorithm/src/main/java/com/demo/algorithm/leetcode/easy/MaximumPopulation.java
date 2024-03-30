package com.demo.algorithm.leetcode.easy;

/**
 * create by chenglong on 2024/2/12
 * description : 人口最多的年份
 *
 * 给你一个二维整数数组logs，其中每个logs[i]=[birthi,deathi]表示第i个人的出生和死亡年份。
 * 年份x的人口定义为这一年期间活着的人的数目。第i个人被计入年份x的人口需要满足：x在闭区间[birthi,deathi-1]内。
 * 注意，人不应当计入他们死亡当年的人口中。
 * 返回 人口最多 且 最早 的年份。
 *
 *
 *
 * 示例 1：
 *
 * 输入：logs = [[1993,1999],[2000,2010]]
 * 输出：1993
 * 解释：人口最多为 1 ，而 1993 是人口为 1 的最早年份。
 * 示例 2：
 *
 * 输入：logs = [[1950,1961],[1960,1971],[1970,1981]]
 * 输出：1960
 * 解释：
 * 人口最多为 2 ，分别出现在 1960 和 1970 。
 * 其中最早年份是 1960 。
 *
 *
 * 提示：
 *
 * 1 <= logs.length <= 100
 * 1950 <= birthi < deathi <= 2050
 */
public class MaximumPopulation {

    public int maximumPopulation(int[][] logs) {
        int offset = 1950;
        int[] counts = new int[101];
        for (int i = 0; i < logs.length; i++) {
            counts[logs[i][0] - offset]++;
            counts[logs[i][1] - offset]--;
        }
        for (int i = 1; i < 101; i++) {
            counts[i] += counts[i - 1];
        }
        int max = counts[0];
        int index = 0;
        for (int i = 1; i < 101; i++) {
            if (counts[i] > max) {
                max = counts[i];
                index = i;
            }
        }
        return offset + index;
    }
}


