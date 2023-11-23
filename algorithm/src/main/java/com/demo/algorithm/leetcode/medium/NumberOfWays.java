package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2023/11/23
 * @author chenglong
 * description : 恰好移动k步到达某一位置的方法数目
 *
 * 给你两个正整数startPos和endPos。最初，你站在无限数轴上位置startPos处。在一步移动中，你可以向左或者向右移动一个位置。
 * 给你一个正整数k，返回从startPos出发、恰好移动k步并到达endPos的不同方法数目。由于答案可能会很大，返回对 10^9+7取余的结果。
 * 如果所执行移动的顺序不完全相同，则认为两种方法不同。
 * 注意：数轴包含负整数。
 *
 * 示例 1：
 * 输入：startPos = 1, endPos = 2, k = 3
 * 输出：3
 * 解释：存在 3 种从 1 到 2 且恰好移动 3 步的方法：
 * - 1 -> 2 -> 3 -> 2.
 * - 1 -> 2 -> 1 -> 2.
 * - 1 -> 0 -> 1 -> 2.
 * 可以证明不存在其他方法，所以返回 3 。
 *
 * 示例 2：
 * 输入：startPos = 2, endPos = 5, k = 10
 * 输出：0
 * 解释：不存在从2到5且恰好移动10步的方法。
 *
 * 提示：
 * 1 <= startPos, endPos, k <= 1000
 */
public class NumberOfWays {

    private static final int MOD = 1000000007;

    public int numberOfWays(int startPos, int endPos, int k) {
        //1，从start到end与end到start等效，可以先保证startPos小于endPost
        if (endPos < startPos) {
            return numberOfWays(endPos, startPos, k);
        }
        //2，计算最小需要的步数到达，用于进行裁剪
        int minStep = endPos - startPos;
        if (k < minStep) {
            return 0;
        }
        if (minStep == k) {
            return 1;
        }
        if ((k - minStep) % 2 != 0) {
            return 0;
        }
        //3，分别获取向做向右的移动次数
        int left = (k - minStep) / 2;
        int right = k - left;
        //4，可以考虑排列组合。k次移动中left次向左的方案数。
        if (left == 1) {
            return k;
        }
        List<Integer> dates = new ArrayList<>();
        for (int i = k - left + 1; i <= k; i++) {
            dates.add(i);
        }
        for (int i = 2; i <= left; i++) {
            int cur = i;
            for (int j = 0; j < dates.size(); j++) {
                int compare = dates.get(j);
                if (compare == 1) {
                    continue;
                }
                if (cur == compare) {
                    dates.set(j, 1);
                    break;
                } else if (compare > cur) {
                    int tem = gcd(compare, cur);
                    if (tem > 1) {
                        cur /= tem;
                        dates.set(j, compare / tem);
                        if (cur == 1) {
                            break;
                        }
                    }
                } else {
                    int tem = gcd(cur, compare);
                    if (tem > 1) {
                        cur /= tem;
                        dates.set(j, compare / tem);
                    }
                }
            }
        }
        long sum = dates.get(0);
        for (int i = 1; i < dates.size(); i++) {
            sum *= dates.get(i);
            sum %= MOD;
        }
        return (int) sum;
    }

    private int gcd(int a, int b) {
        return b != 0 ? gcd(b, a % b) : a;
    }
}
