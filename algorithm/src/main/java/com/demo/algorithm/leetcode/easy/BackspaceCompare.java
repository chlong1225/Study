package com.demo.algorithm.leetcode.easy;

/**
 * create by chenglong on 2023/12/11
 * description : 比较含退格的字符串
 *
 * 给定s和t两个字符串，当它们分别被输入到空白的文本编辑器后，如果两者相等，返回true。# 代表退格字符。
 * 注意：如果对空文本输入退格字符，文本继续为空。
 *
 * 示例 1：
 * 输入：s = "ab#c", t = "ad#c"
 * 输出：true
 * 解释：s 和 t 都会变成 "ac"。
 *
 * 示例 2：
 * 输入：s = "ab##", t = "c#d#"
 * 输出：true
 * 解释：s 和 t 都会变成 ""。
 *
 * 示例 3：
 * 输入：s = "a#c", t = "b"
 * 输出：false
 * 解释：s 会变成 "c"，但 t 仍然是 "b"。
 *
 * 提示：
 * 1 <= s.length, t.length <= 200
 * s和t只含有小写字母以及字符'#'
 */
public class BackspaceCompare {

    public boolean backspaceCompare(String s, String t) {
        String s1 = getString(s);
        String s2 = getString(t);
        if (s1.length() == 0) {
            return s2.length() == 0;
        }
        return s1.equals(s2);
    }

    private String getString(String s){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '#') {
                if (builder.length() > 0) {
                    builder.delete(builder.length() - 1, builder.length());
                }
            } else {
                builder.append(s.charAt(i));
            }
        }
        return builder.toString();
    }
}
