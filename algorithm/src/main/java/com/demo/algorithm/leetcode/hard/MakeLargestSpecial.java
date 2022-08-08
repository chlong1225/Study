package com.demo.algorithm.leetcode.hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * create on 2022/8/8
 * @author chenglong
 * description : 特殊的二进制序列
 *
 * 特殊的二进制序列是具有以下两个性质的二进制序列：
 * 0的数量与1的数量相等。
 * 二进制序列的每一个前缀码中1的数量要大于等于0的数量。
 * 给定一个特殊的二进制序列S，以字符串形式表示。定义一个操作为首先选择S的两个连续且非空的特殊的子串，然后将它们交换。
 *（两个子串为连续的当且仅当第一个子串的最后一个字符恰好为第二个子串的第一个字符的前一个字符。)
 * 在任意次数的操作之后，交换后的字符串按照字典序排列的最大的结果是什么？
 *
 * 示例 1:
 * 输入: S = "11011000"
 * 输出: "11100100"
 * 解释:
 * 将子串 "10" （在S[1]出现） 和 "1100" （在S[3]出现）进行交换。
 * 这是在进行若干次操作后按字典序排列最大的结果。
 * 说明:
 *
 * S的长度不超过50。
 * S保证为一个满足上述定义的特殊 的二进制序列。
 */
public class MakeLargestSpecial {

    public String makeLargestSpecial(String s) {
        int length = s.length();
        //长度少于等于2，无法交换
        if (length <= 2) {
            return s;
        }
        return dfs(s, 0, length - 1);
    }

    private String dfs(String s, int start, int end) {
        if (start > end) {
            return "";
        }
        List<String> list = new ArrayList<>();
        int count = 0;
        int pre = start;
        for (int i = start; i <= end; i++) {
            if (s.charAt(i) == '1') {
                count++;
            } else {
                count--;
                if (count == 0) {
                    list.add("1" + dfs(s, pre + 1, i - 1) + "0");
                    pre = i + 1;
                }
            }
        }
        Collections.sort(list);
        StringBuilder builder = new StringBuilder();
        for (int i = list.size() - 1; i >= 0; i--) {
            builder.append(list.get(i));
        }
        return builder.toString();
    }
}
