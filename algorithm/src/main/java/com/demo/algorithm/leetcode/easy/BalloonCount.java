package com.demo.algorithm.leetcode.easy;

/**
 * Created by chl on 2022/2/13.
 * description : 气球的最大数量
 *
 * 给你一个字符串text，你需要使用text中的字母来拼凑尽可能多的单词"balloon"（气球）。
 * 字符串text中的每个字母最多只能被使用一次。请你返回最多可以拼凑出多少个单词"balloon"。
 *
 * 示例 1：
 * 输入：text = "nlaebolko"
 * 输出：1
 *
 * 示例 2：
 * 输入：text = "loonbalxballpoon"
 * 输出：2
 *
 * 示例 3：
 * 输入：text = "leetcode"
 * 输出：0
 *
 * 提示：
 * 1 <= text.length <= 10^4
 * text全部由小写英文字母组成
 */
public class BalloonCount {

    public int maxNumberOfBalloons(String text) {
        int b = 0;
        int a = 0;
        int l = 0;
        int o = 0;
        int n = 0;
        int length = text.length();
        if (length < 7) {
            return 0;
        }
        for (int i = 0; i < length; i++) {
            if (text.charAt(i) == 'b') {
                b++;
            } else if (text.charAt(i) == 'a') {
                a++;
            } else if (text.charAt(i) == 'l') {
                l++;
            } else if (text.charAt(i) == 'o') {
                o++;
            } else if (text.charAt(i) == 'n') {
                n++;
            }
        }
        int count = Math.min(Math.min(b, a), n);
        count = Math.min(count, Math.min(l, o) >> 1);
        return count;
    }
}
