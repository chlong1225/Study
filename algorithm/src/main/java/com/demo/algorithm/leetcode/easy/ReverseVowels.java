package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/11/29
 * @author chenglong
 * description : 反转字符串中的元音字母
 *
 * 给你一个字符串s，仅反转字符串中的所有元音字母，并返回结果字符串。
 * 元音字母包括'a'、'e'、'i'、'o'、'u'，且可能以大小写两种形式出现不止一次。
 *
 * 示例 1：
 * 输入：s = "hello"
 * 输出："holle"
 * 示例 2：
 *
 * 输入：s = "leetcode"
 * 输出："leotcede"
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 3 * 10^5
 * s由可打印的ASCII字符组成
 */
public class ReverseVowels {

    private boolean[] compare;
    private int lastIndex;

    public String reverseVowels(String s) {
        int n = s.length();
        lastIndex = n - 1;
        compare = new boolean[129];
        //1，统计元音字母，便于后续快速判断
        compare['a'] = true;
        compare['A'] = true;
        compare['e'] = true;
        compare['E'] = true;
        compare['i'] = true;
        compare['I'] = true;
        compare['o'] = true;
        compare['O'] = true;
        compare['u'] = true;
        compare['U'] = true;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (compare[s.charAt(i)]) {
                //当前为元音
                int index = getReplaceChar(lastIndex, s);
                builder.append(s.charAt(index));
                lastIndex = index - 1;
            } else {
                builder.append(s.charAt(i));
            }
        }
        return builder.toString();
    }

    private int getReplaceChar(int end, String s) {
        for (int i = end; i >= 0; i--) {
            if (compare[s.charAt(i)]) {
                return i;
            }
        }
        return 0;
    }
}
