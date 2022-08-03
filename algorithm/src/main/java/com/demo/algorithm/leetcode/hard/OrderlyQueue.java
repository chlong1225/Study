package com.demo.algorithm.leetcode.hard;

import java.util.Arrays;

/**
 * create on 2022/8/3
 * @author chenglong
 * description : 有序队列
 *
 * 给定一个字符串s和一个整数k。你可以从s的前k个字母中选择一个，并把它加到字符串的末尾。
 * 返回在应用上述步骤的任意数量的移动后，字典上最小的字符串。
 *
 * 示例 1：
 * 输入：s = "cba", k = 1
 * 输出："acb"
 * 解释：
 * 在第一步中，我们将第一个字符（“c”）移动到最后，获得字符串 “bac”。
 * 在第二步中，我们将第一个字符（“b”）移动到最后，获得最终结果 “acb”。
 *
 * 示例 2：
 * 输入：s = "baaca", k = 3
 * 输出："aaabc"
 * 解释：
 * 在第一步中，我们将第一个字符（“b”）移动到最后，获得字符串 “aacab”。
 * 在第二步中，我们将第三个字符（“c”）移动到最后，获得最终结果 “aaabc”。
 *
 * 提示：
 * 1 <= k<= S.length<= 1000
 * s只由小写字母组成。
 */
public class OrderlyQueue {

    public String orderlyQueue(String s, int k) {
        int length = s.length();
        if (length == 1) {
            return s;
        }
        if (k == 1) {
            //此时需要遍历比较
            String min = s;
            for (int i = 1; i < length; i++) {
                StringBuilder builder = new StringBuilder();
                builder.append(s.substring(i));
                builder.append(s.substring(0, i));
                if (compare(min, builder)) {
                    min = builder.toString();
                }
            }
            return min;
        } else {
            //k>=2时可以在多次移动过程交换前后两个字符的顺序，最终等效于排序
            char[] dates = s.toCharArray();
            Arrays.sort(dates);
            return new String(dates);
        }
    }

    private boolean compare(String compare, StringBuilder dates) {
        int length = compare.length();
        for (int i = 0; i < length; i++) {
            if (dates.charAt(i) < compare.charAt(i)) {
                return true;
            } else if (dates.charAt(i) > compare.charAt(i)) {
                return false;
            }
        }
        return false;
    }
}
