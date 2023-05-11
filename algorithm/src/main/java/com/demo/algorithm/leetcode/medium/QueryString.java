package com.demo.algorithm.leetcode.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * create on 2023/5/11
 * @author chenglong
 * description : 子串能表示从1到N数字的二进制串
 *
 * 给定一个二进制字符串s和一个正整数n，如果对于[1, n]范围内的每个整数，其二进制表示都是s的子字符串，就返回true，否则返回false。
 * 子字符串是字符串中连续的字符序列。
 *
 * 示例 1：
 * 输入：s = "0110", n = 3
 * 输出：true
 *
 * 示例 2：
 * 输入：s = "0110", n = 4
 * 输出：false
 *
 * 提示：
 * 1 <= s.length <= 1000
 * s[i]不是'0'就是'1'
 * 1 <= n <= 10^9
 */
public class QueryString {

    public boolean queryString(String s, int n) {
        int min = n / 2 + 1;
        //验证范围：[min,max]
        for (int i = min; i <= n; i++) {
            String check = Integer.toBinaryString(i);
            if (!s.contains(check)) {
                return false;
            }
        }
        return true;
    }

    public boolean queryString2(String s, int n) {
        Set<Integer> marks = new HashSet<>();
        int start = 0;
        while (start < s.length()) {
            if (s.charAt(start) == '0') {
                start++;
            } else {
                int cur = 1;
                marks.add(cur);
                for (int i = start + 1; i < s.length(); i++) {
                    cur = cur * 2 + (s.charAt(i) - '0');
                    if (cur > n) {
                        break;
                    }
                    marks.add(cur);
                }
                start++;
            }
        }
        return marks.size() == n;
    }
}
