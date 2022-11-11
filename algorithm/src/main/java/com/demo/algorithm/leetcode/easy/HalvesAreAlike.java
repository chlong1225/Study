package com.demo.algorithm.leetcode.easy;

/**
 * create on 2022/11/11
 * @author chenglong
 * description : 判断字符串的两半是否相似
 *
 * 给你一个偶数长度的字符串s。将其拆分成长度相同的两半，前一半为a，后一半为b。
 * 两个字符串相似的前提是它们都含有相同数目的元音（'a'，'e'，'i'，'o'，'u'，'A'，'E'，'I'，'O'，'U'）。注意，s可能同时含有大写和小写字母。
 * 如果a和b相似返回true ；否则返回false。
 *
 * 示例 1：
 * 输入：s = "book"
 * 输出：true
 * 解释：a = "bo" 且 b = "ok" 。a 中有 1 个元音，b 也有 1 个元音。所以，a 和 b 相似。
 *
 * 示例 2：
 * 输入：s = "textbook"
 * 输出：false
 * 解释：a = "text" 且 b = "book" 。a 中有 1 个元音，b 中有 2 个元音。因此，a 和 b 不相似。
 * 注意，元音 o 在 b 中出现两次，记为 2 个。
 *
 * 提示：
 * 2 <= s.length <= 1000
 * s.length 是偶数
 * s 由大写和小写字母组成
 */
public class HalvesAreAlike {

    public boolean halvesAreAlike(String s) {
        boolean[] marks = new boolean[128];
        marks['a'] = true;
        marks['e'] = true;
        marks['i'] = true;
        marks['o'] = true;
        marks['u'] = true;
        marks['A'] = true;
        marks['E'] = true;
        marks['I'] = true;
        marks['O'] = true;
        marks['U'] = true;
        int n = s.length() / 2;
        int total = 0;
        for (int i = 0; i < n; i++) {
            if (marks[s.charAt(i)]) {
                total++;
            }
        }
        for (int i = n; i < 2 * n; i++) {
            int index = s.charAt(i);
            if (marks[index]) {
                total--;
            }
        }
        return total == 0;
    }
}
