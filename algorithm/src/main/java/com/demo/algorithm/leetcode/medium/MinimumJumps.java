package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2023/8/30
 * @author chenglong
 * description : 到家的最少跳跃次数
 *
 * 有一只跳蚤的家在数轴上的位置x处。请你帮助它从位置0出发，到达它的家。
 * 跳蚤跳跃的规则如下：
 * 它可以往前跳恰好a个位置（即往右跳）。
 * 它可以往后跳恰好b个位置（即往左跳）。
 * 它不能连续往后跳2次。
 * 它不能跳到任何forbidden数组中的位置。
 * 跳蚤可以往前跳超过它的家的位置，但是它不能跳到负整数的位置。
 *
 * 给你一个整数数组forbidden，其中forbidden[i]是跳蚤不能跳到的位置，同时给你整数a，b和x，请你返回跳蚤到家的最少跳跃次数。如果没有恰好到达x的可行方案，请你返回-1。
 *
 * 示例 1：
 * 输入：forbidden = [14,4,18,1,15], a = 3, b = 15, x = 9
 * 输出：3
 * 解释：往前跳 3 次（0 -> 3 -> 6 -> 9），跳蚤就到家了。
 *
 * 示例 2：
 * 输入：forbidden = [8,3,16,6,12,20], a = 15, b = 13, x = 11
 * 输出：-1
 *
 * 示例 3：
 * 输入：forbidden = [1,6,2,14,5,17,4], a = 16, b = 9, x = 7
 * 输出：2
 * 解释：往前跳一次（0 -> 16），然后往回跳一次（16 -> 7），跳蚤就到家了。
 *
 * 提示：
 * 1 <= forbidden.length <= 1000
 * 1 <= a, b, forbidden[i] <= 2000
 * 0 <= x <= 2000
 * forbidden 中所有位置互不相同。
 * 位置 x 不在 forbidden 中。
 */
public class MinimumJumps {

    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        //最大有效位置
        int max = 2000 + a + b;
        //记录不可到达的位置
        boolean[] marks = new boolean[max + 1];
        for (int i = 0; i < forbidden.length; i++) {
            marks[forbidden[i]] = true;
        }
        int[][] dates = new int[max + 1][2];
        for (int i = 0; i <= max; i++) {
            dates[i][0] = Integer.MAX_VALUE;
            dates[i][1] = Integer.MAX_VALUE;
        }
        dates[0][0] = 0;
        dates[0][1] = 0;
        List<Integer> curs = new ArrayList<>();
        List<Integer> nexts = new ArrayList<>();
        curs.add(0);
        while (curs.size() > 0) {
            for (int i = 0; i < curs.size(); i++) {
                int cur = curs.get(i);
                if (dates[cur][0] != Integer.MAX_VALUE) {
                    //此时cur位置被向前跳跃过
                    int nextStep = dates[cur][0] + 1;
                    //向前跳跃
                    int next1 = cur + a;
                    if (next1 <= max && (!marks[next1])) {
                        if (dates[next1][0] > nextStep) {
                            dates[next1][0] = nextStep;
                            nexts.add(next1);
                        }
                    }
                    //向后跳跃
                    int next2 = cur - b;
                    if (next2 >= 0 && (!marks[next2])) {
                        if (dates[next2][1] > nextStep) {
                            dates[next2][1] = nextStep;
                            nexts.add(next2);
                        }
                    }
                }
                if (dates[cur][1] != Integer.MAX_VALUE) {
                    int nextStep = dates[cur][1] + 1;
                    //此时只能向前跳跃
                    int next = cur + a;
                    if (next <= max && (!marks[next])) {
                        if (dates[next][0] > nextStep) {
                            dates[next][0] = nextStep;
                            nexts.add(next);
                        }
                    }
                }
            }
            curs.clear();
            curs.addAll(nexts);
            nexts.clear();
        }
        int min = Math.min(dates[x][0], dates[x][1]);
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
