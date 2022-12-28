package com.demo.algorithm.leetcode.medium;

/**
 * create on 2022/12/28
 * @author chenglong
 * description : 统计同构子字符串的数目
 *
 * 给你一个字符串s，返回s中同构子字符串的数目。由于答案可能很大，只需返回对 10^9+7取余后的结果。
 * 同构字符串的定义为：如果一个字符串中的所有字符都相同，那么该字符串就是同构字符串。
 * 子字符串是字符串中的一个连续字符序列。
 *
 * 示例 1：
 * 输入：s = "abbcccaa"
 * 输出：13
 * 解释：同构子字符串如下所列：
 * "a"   出现 3 次。
 * "aa"  出现 1 次。
 * "b"   出现 2 次。
 * "bb"  出现 1 次。
 * "c"   出现 3 次。
 * "cc"  出现 2 次。
 * "ccc" 出现 1 次。
 * 3 + 1 + 2 + 1 + 3 + 2 + 1 = 13
 *
 * 示例 2：
 * 输入：s = "xy"
 * 输出：2
 * 解释：同构子字符串是 "x" 和 "y" 。
 *
 * 示例 3：
 * 输入：s = "zzzzz"
 * 输出：15
 *
 * 提示：
 * 1 <= s.length <= 10^5
 * s 由小写字符串组成
 */
public class CountHomogenous {

    private static final int MOD = 1000000007;

    public int countHomogenous(String s) {
        int length = s.length();
        long base = 1;
        int sum = 0;
        //连续相同字符的数量
        int count = 1;
        char pre = s.charAt(0);
        for (int i = 1; i < length; i++) {
            if (s.charAt(i) == pre) {
                count++;
            } else {
                //计算组合的数量
                long tem = (base + count) * count / 2;
                sum += (tem % MOD);
                sum %= MOD;
                count = 1;
                pre = s.charAt(i);
            }
        }
        long tem = (base + count) * count / 2;
        sum += (tem % MOD);
        sum %= MOD;
        return sum;
    }
}
