package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/6/7
 *
 * @author chenglong
 * description : 单字符重复子串的最大长度
 *
 * 如果字符串中的所有字符都相同，那么这个字符串是单字符重复的字符串。
 *
 * 给你一个字符串text，你只能交换其中两个字符一次或者什么都不做，然后得到一些单字符重复的子串。返回其中最长的子串的长度。
 *
 * 示例 1：
 * 输入：text = "ababa"
 * 输出：3
 *
 * 示例 2：
 * 输入：text = "aaabaaa"
 * 输出：6
 *
 * 示例 3：
 * 输入：text = "aaabbaaa"
 * 输出：4
 *
 * 示例 4：
 * 输入：text = "aaaaa"
 * 输出：5
 *
 * 示例 5：
 * 输入：text = "abcdef"
 * 输出：1
 *
 * 提示：
 * 1 <= text.length <= 20000
 * text 仅由小写英文字母组成。
 */
public class MaxRepOpt1 {

    public int maxRepOpt1(String text) {
        //1，统计每个字符的数量
        int[] counts = new int[26];
        int n = text.length();
        for (int i = 0; i < n; i++) {
            counts[text.charAt(i) - 'a']++;
        }
        int max = 0;
        //2，统计连续区间
        char cur = text.charAt(0);
        int count = 1;
        for (int i = 1; i < n; i++) {
            if (text.charAt(i) == cur) {
                count++;
            } else {
                if (i + 1 < n) {
                    if (text.charAt(i + 1) == cur) {
                        //此时i可以替换cur，继续统计
                        count++;
                        int start = i + 1;
                        while (start < n) {
                            if (text.charAt(start) == cur) {
                                count++;
                            } else {
                                break;
                            }
                            start++;
                        }
                        if (count > counts[cur - 'a']) {
                            count = counts[cur - 'a'];
                        }
                        if (count > max) {
                            max = count;
                        }
                    } else {
                        //此时最多替换一个
                        int tem = Math.min(counts[cur - 'a'], count + 1);
                        if (tem > max) {
                            max = tem;
                        }
                    }
                } else {
                    int tem = Math.min(counts[cur - 'a'], count + 1);
                    if (tem > max) {
                        max = tem;
                    }
                }
                cur = text.charAt(i);
                count = 1;
            }
        }
        int tem = Math.min(counts[cur - 'a'], count + 1);
        if (tem > max) {
            max = tem;
        }
        return max;
    }
}
