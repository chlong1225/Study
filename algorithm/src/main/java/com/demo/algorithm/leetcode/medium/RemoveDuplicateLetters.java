package com.demo.algorithm.leetcode.medium;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * create on 2023/12/15
 * @author chenglong
 * description : 去除重复字母
 *
 * 给你一个字符串s，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 *
 * 示例 1：
 * 输入：s = "bcabc"
 * 输出："abc"
 *
 * 示例 2：
 * 输入：s = "cbacdcbc"
 * 输出："acdb"
 *
 * 提示：
 * 1 <= s.length <= 10^4
 * s 由小写英文字母组成
 */
public class RemoveDuplicateLetters {

    public String removeDuplicateLetters(String s) {
        int n = s.length();
        if (n == 1) {
            return s;
        }
        //1，统计字符的数量
        int[] counts = new int[26];
        for (int i = 0; i < n; i++) {
            counts[s.charAt(i) - 'a']++;
        }
        //2，依次模拟去除字符
        Deque<Integer> stack = new ArrayDeque<Integer>();
        boolean[] isAdd = new boolean[26];
        for (int i = 0; i < n; i++) {
            int index = s.charAt(i) - 'a';
            while (stack.size() > 0 && (!isAdd[index])) {
                if (stack.peekLast() > index && counts[stack.peekLast()] > 0) {
                    isAdd[stack.peekLast()] = false;
                    stack.pollLast();
                } else {
                    break;
                }
            }
            if (!isAdd[index]) {
                stack.addLast(index);
                isAdd[index] = true;
            }
            counts[index]--;
        }
        StringBuilder builder = new StringBuilder();
        while (stack.size() > 0) {
            builder.append((char) ('a' + stack.pollFirst()));
        }
        return builder.toString();
    }
}
