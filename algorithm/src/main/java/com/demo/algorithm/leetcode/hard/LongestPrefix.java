package com.demo.algorithm.leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2022/5/17
 *
 * @author chenglong
 * description : 最长快乐前缀
 * <p>
 * 「快乐前缀」是在原字符串中既是非空前缀也是后缀（不包括原字符串自身）的字符串。
 * 给你一个字符串s，请你返回它的最长快乐前缀。如果不存在满足题意的前缀，则返回一个空字符串""。
 * <p>
 * 示例 1：
 * 输入：s = "level"
 * 输出："l"
 * 解释：不包括 s 自己，一共有 4 个前缀（"l", "le", "lev", "leve"）和 4 个后缀（"l", "el", "vel", "evel"）。最长的既是前缀也是后缀的字符串是 "l" 。
 * <p>
 * 示例 2：
 * 输入：s = "ababab"
 * 输出："abab"
 * 解释："abab" 是最长的既是前缀也是后缀的字符串。题目允许前后缀在原字符串中重叠。
 * <p>
 * 提示：
 * 1 <= s.length <= 10^5
 * s 只含有小写英文字母
 */
public class LongestPrefix {

    private static final int BIG_MOD = 1000000007;
    private static final int MOD = 31;

    public String longestPrefix(String s) {
        //记录前缀与后缀hash值的字符长度，后序需要再次比较，防止hash冲突
        List<Integer> dates = new ArrayList<>();
        int length = s.length();
        long pre = 0;
        long last = 0;
        long base = 1;
        //1，依次计算前缀与后缀的hash值，进行比较
        for (int i = 1; i < length; i++) {
            pre = pre * MOD + (s.charAt(i - 1) - 'a');
            pre %= BIG_MOD;
            last = last + (s.charAt(length - i) - 'a') * base;
            last %= BIG_MOD;
            base *= MOD;
            base %= BIG_MOD;
            if (pre == last) {
                dates.add(i);
            }
        }
        //2，遍历比较hash值相同的子串，防止取模造成的hash冲突
        if (dates.size() > 0) {
            for (int i = dates.size() - 1; i >= 0; i--) {
                int n = dates.get(i);
                String preStr = s.substring(0, n);
                String lastStr = s.substring(length - n, length);
                if (compareStr(preStr, lastStr)) {
                    return preStr;
                }
            }
        }
        return "";
    }

    private boolean compareStr(String pre, String last) {
        int length = pre.length();
        for (int i = 0; i < length; i++) {
            if (pre.charAt(i) != last.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
