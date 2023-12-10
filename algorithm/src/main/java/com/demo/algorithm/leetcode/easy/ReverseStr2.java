package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/12/6
 * @author chenglong
 * description : 反转字符串II
 *
 * 给定一个字符串s和一个整数k，从字符串开头算起，每计数至2k个字符，就反转这2k字符中的前k个字符。
 * 如果剩余字符少于k个，则将剩余字符全部反转。
 * 如果剩余字符小于2k但大于或等于k个，则反转前k个字符，其余字符保持原样。
 *
 * 示例 1：
 * 输入：s = "abcdefg", k = 2
 * 输出："bacdfeg"
 *
 * 示例 2：
 * 输入：s = "abcd", k = 2
 * 输出："bacd"
 *
 * 提示：
 * 1 <= s.length <= 10^4
 * s 仅由小写英文组成
 * 1 <= k <= 10^4
 */
public class ReverseStr2 {

    public String reverseStr(String s, int k) {
        int n = s.length();
        StringBuilder builder = new StringBuilder();
        int index = 0;
        while (index < n) {
            if (n - index >= 2 * k) {
                //从index～index+k反转。index+k～index+2*k保持原样
                for (int i = index + k - 1; i >= index; i--) {
                    builder.append(s.charAt(i));
                }
                for (int i = index + k; i < index + 2 * k; i++) {
                    builder.append(s.charAt(i));
                }
                index += 2 * k;
            } else if (n - index >= k) {
                //从index～index+k反转，index+k～n保持原样
                for (int i = index + k - 1; i >= index; i--) {
                    builder.append(s.charAt(i));
                }
                for (int i = index + k; i < n; i++) {
                    builder.append(s.charAt(i));
                }
                break;
            } else {
                for (int i = n - 1; i >= index; i--) {
                    builder.append(s.charAt(i));
                }
                break;
            }
        }
        return builder.toString();
    }
}
