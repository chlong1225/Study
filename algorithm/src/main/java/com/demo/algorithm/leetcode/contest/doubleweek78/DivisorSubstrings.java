package com.demo.algorithm.leetcode.contest.doubleweek78;

/**
 * Created by chl on 2022/5/15.
 * description : 找到一个数字的K美丽值
 *
 * 一个整数num的k美丽值定义为num中符合以下条件的子字符串数目：
 * 子字符串长度为k。
 * 子字符串能整除 num 。
 * 给你整数num和k，请你返回num的k美丽值。
 *
 * 注意：
 * 允许有前缀0。
 * 0不能整除任何值。
 * 一个子字符串是一个字符串里的连续一段字符序列。
 *
 * 示例 1：
 * 输入：num = 240, k = 2
 * 输出：2
 * 解释：以下是 num 里长度为 k 的子字符串：
 * - "240" 中的 "24" ：24 能整除 240 。
 * - "240" 中的 "40" ：40 能整除 240 。
 * 所以，k 美丽值为 2 。
 *
 * 示例 2：
 * 输入：num = 430043, k = 2
 * 输出：2
 * 解释：以下是 num 里长度为 k 的子字符串：
 * - "430043" 中的 "43" ：43 能整除 430043 。
 * - "430043" 中的 "30" ：30 不能整除 430043 。
 * - "430043" 中的 "00" ：0 不能整除 430043 。
 * - "430043" 中的 "04" ：4 不能整除 430043 。
 * - "430043" 中的 "43" ：43 能整除 430043 。
 * 所以，k 美丽值为 2 。
 *
 * 提示：
 * 1 <= num <= 10^9
 * 1 <= k <= num.length（将num视为字符串）
 */
public class DivisorSubstrings {

    public int divisorSubstrings(int num, int k) {
        String str = "" + num;
        int length = str.length();
        int count = 0;
        for (int i = 0; i <= length - k; i++) {
            int cur = Integer.parseInt(str.substring(i, i + k));
            if (cur > 0) {
                if (num % cur == 0) {
                    count++;
                }
            }
        }
        return count;
    }
}
