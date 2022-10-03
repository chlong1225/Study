package com.demo.algorithm.leetcode.easy;

/**
 * create on 2022/10/3
 * @author chenglong
 * description : 检查二进制字符串字段
 *
 * 给你一个二进制字符串s，该字符串不含前导零 。
 * 如果s包含零个或一个由连续的'1'组成的字段，返回true。否则返回false。
 * 如果s中由连续若干个'1'组成的字段数量不超过1，返回true。否则返回false。
 *
 * 示例 1：
 * 输入：s = "1001"
 * 输出：false
 * 解释：由连续若干个'1'组成的字段数量为2，返回false
 *
 * 示例 2：
 * 输入：s = "110"
 * 输出：true
 *
 * 提示：
 * 1 <= s.length <= 100
 * s[i]为'0'或'1'
 * s[0]为'1'
 */
public class CheckOnesSegment {

    public boolean checkOnesSegment(String s) {
        int count = 0;
        int length = s.length();
        char pre = s.charAt(0);
        for (int i = 1; i < length; i++) {
            if (s.charAt(i) == pre) {
                continue;
            }
            if (s.charAt(i) == '0') {
                count++;
            }
            pre = s.charAt(i);
        }
        if (pre == '1') {
            count++;
        }
        return count == 1;
    }
}
