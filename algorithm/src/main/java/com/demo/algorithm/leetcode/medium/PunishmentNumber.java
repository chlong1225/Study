package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/10/25
 * @author chenglong
 * description : 求一个整数的惩罚数
 *
 * 给你一个正整数n，请你返回n的惩罚数。
 * n的惩罚数定义为所有满足以下条件i的数的平方和：
 * 1 <= i <= n
 * i * i 的十进制表示的字符串可以分割成若干连续子字符串，且这些子字符串对应的整数值之和等于 i 。
 *
 * 示例 1：
 * 输入：n = 10
 * 输出：182
 * 解释：总共有 3 个整数 i 满足要求：
 * - 1 ，因为 1 * 1 = 1
 * - 9 ，因为 9 * 9 = 81 ，且 81 可以分割成 8 + 1 。
 * - 10 ，因为 10 * 10 = 100 ，且 100 可以分割成 10 + 0 。
 * 因此，10 的惩罚数为 1 + 81 + 100 = 182
 *
 * 示例 2：
 * 输入：n = 37
 * 输出：1478
 * 解释：总共有 4 个整数 i 满足要求：
 * - 1 ，因为 1 * 1 = 1
 * - 9 ，因为 9 * 9 = 81 ，且 81 可以分割成 8 + 1 。
 * - 10 ，因为 10 * 10 = 100 ，且 100 可以分割成 10 + 0 。
 * - 36 ，因为 36 * 36 = 1296 ，且 1296 可以分割成 1 + 29 + 6 。
 * 因此，37 的惩罚数为 1 + 81 + 100 + 1296 = 1478
 *
 * 提示：
 * 1 <= n <= 1000
 */
public class PunishmentNumber {

    private static int[] sums;

    static {

        int max = 1000;

        boolean[] marks = new boolean[max + 1];
        for (int i = 1; i <= max; i++) {
            marks[i] = isAvailable(i);
        }
        sums = new int[max + 1];
        for (int i = 1; i <= max; i++) {
            sums[i] = sums[i - 1];
            if (marks[i]) {
                sums[i] += i*i;
            }
        }
    }

    private static boolean isAvailable(int i) {
        int num = i * i;
        //将num进行分割求和
        String s = "" + num;
        boolean[] result = new boolean[1];
        dfs(result, 0, 0, i, s);
        return result[0];
    }

    private static void dfs(boolean[] result, int startIndex, int sum, int compare, String s) {
        if (result[0]) {
            return;
        }
        if (startIndex == s.length()) {
            if (sum == compare) {
                result[0] = true;
            }
            return;
        }
        for (int i = startIndex + 1; i <= s.length(); i++) {
            int cur = Integer.parseInt(s.substring(startIndex, i));
            dfs(result, i, sum + cur, compare, s);
        }
    }

    public int punishmentNumber(int n) {
        return sums[n];
    }
}
