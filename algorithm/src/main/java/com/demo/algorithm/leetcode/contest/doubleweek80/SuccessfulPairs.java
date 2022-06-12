package com.demo.algorithm.leetcode.contest.doubleweek80;

import java.util.Arrays;

/**
 * Created by chl on 2022/6/12.
 * description : 咒语和药水的成功对数
 *
 * 给你两个正整数数组spells和potions，长度分别为n和m，其中spells[i]表示第i个咒语的能量强度，potions[j]表示第j瓶药水的能量强度。
 * 同时给你一个整数success。一个咒语和药水的能量强度相乘如果大于等于success，那么它们视为一对成功的组合。
 * 请你返回一个长度为n的整数数组pairs，其中pairs[i]是能跟第 i个咒语成功组合的药水数目。
 *
 * 示例 1：
 * 输入：spells = [5,1,3], potions = [1,2,3,4,5], success = 7
 * 输出：[4,0,3]
 * 解释：
 * - 第 0 个咒语：5 * [1,2,3,4,5] = [5,10,15,20,25] 。总共 4 个成功组合。
 * - 第 1 个咒语：1 * [1,2,3,4,5] = [1,2,3,4,5] 。总共 0 个成功组合。
 * - 第 2 个咒语：3 * [1,2,3,4,5] = [3,6,9,12,15] 。总共 3 个成功组合。
 * 所以返回 [4,0,3] 。
 *
 * 示例 2：
 * 输入：spells = [3,1,2], potions = [8,5,8], success = 16
 * 输出：[2,0,2]
 * 解释：
 * - 第 0 个咒语：3 * [8,5,8] = [24,15,24] 。总共 2 个成功组合。
 * - 第 1 个咒语：1 * [8,5,8] = [8,5,8] 。总共 0 个成功组合。
 * - 第 2 个咒语：2 * [8,5,8] = [16,10,16] 。总共 2 个成功组合。
 * 所以返回 [2,0,2] 。
 *
 * 提示：
 * n == spells.length
 * m == potions.length
 * 1 <= n, m <= 10^5
 * 1 <= spells[i], potions[i] <= 10^5
 * 1 <= success <= 10^10
 */
public class SuccessfulPairs {

    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int n = spells.length;
        int m = potions.length;
        int[] result = new int[n];
        //1，对药水强度进行排序
        Arrays.sort(potions);
        //2，遍历获取查询
        for (int i = 0; i < n; i++) {
            //3，当前咒语匹配的最小药水强度
            long min = success / spells[i];
            if (success % spells[i] != 0) {
                min++;
            }
            result[i] = getCount(min, potions);
        }
        return result;
    }

    private int getCount(long min, int[] potions) {
        int m = potions.length;
        if (min <= potions[0]) {
            return m;
        }
        if (min > potions[m - 1]) {
            return 0;
        }
        int start = 0;
        int end = m - 1;
        while (start < end) {
            int middle = (end - start) / 2 + start;
            if (potions[middle] >= min) {
                end = middle;
            } else {
                start = middle + 1;
            }
        }
        return m - start;
    }
}
