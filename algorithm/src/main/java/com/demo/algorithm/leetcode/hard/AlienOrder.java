package com.demo.algorithm.leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2022/5/31
 * @author chenglong
 * description : 剑指OfferII 114. 外星文字典
 *
 * 现有一种使用英语字母的外星文语言，这门语言的字母顺序与英语顺序不同。
 * 给定一个字符串列表words，作为这门语言的词典，words中的字符串已经按这门新语言的字母顺序进行了排序 。
 * 请你根据该词典还原出此语言中已知的字母顺序，并按字母递增顺序排列。若不存在合法字母顺序，返回""。若存在多种可能的合法字母顺序，返回其中任意一种顺序即可。
 *
 * 字符串s字典顺序小于字符串t有两种情况：
 * 在第一个不同字母处，如果s中的字母在这门外星语言的字母顺序中位于t中字母之前，那么s的字典顺序小于t 。
 * 如果前面 min(s.length, t.length) 字母都相同，那么s.length < t.length时，s的字典顺序也小于 t 。
 *
 * 示例 1：
 * 输入：words = ["wrt","wrf","er","ett","rftt"]
 * 输出："wertf"
 *
 * 示例 2：
 * 输入：words = ["z","x"]
 * 输出："zx"
 *
 * 示例 3：
 * 输入：words = ["z","x","z"]
 * 输出：""
 * 解释：不存在合法字母顺序，因此返回 "" 。
 *
 * 提示：
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 100
 * words[i] 仅由小写英文字母组成
 */
public class AlienOrder {

    public String alienOrder(String[] words) {
        int length = words.length;
        if (length == 1) {
            String word = words[0];
            //需要去重字符
            boolean[] letters = new boolean[128];
            for (int i = 0; i < word.length(); i++) {
                letters[word.charAt(i)] = true;
            }
            StringBuilder builder = new StringBuilder();
            for (char i = 'a'; i <= 'z'; i++) {
                if (letters[i]) {
                    builder.append(i);
                }
            }
            return builder.toString();
        }
        //记录字符比较对，防止重复添加
        boolean[][] marks = new boolean[26][26];
        //记录出现的字符
        boolean[] letters = new boolean[26];
        //记录比当前字符小的数量，对应的入度
        int[] counts = new int[26];
        //记录字符的关系，对应的出度
        List<List<Integer>> dates = new ArrayList<>(26);
        for (int i = 0; i < 26; i++) {
            dates.add(new ArrayList<>());
        }
        //1，统计所有出现的字符
        boolean[] last = new boolean[26];
        for (int i = 0; i < length; i++) {
            String word = words[i];
            for (int j = 0; j < word.length(); j++) {
                last[word.charAt(j) - 'a'] = true;
            }
        }
        //2，构建字母排序对
        for (int i = 1; i < length; i++) {
            int[] diff = getDiff(words[i - 1], words[i]);
            if (diff.length == 0) {
                return "";
            }
            if (marks[diff[0]][diff[1]]) {
                continue;
            }
            if (diff[0] == diff[1]) {
                letters[diff[0]] = true;
                continue;
            }
            if (marks[diff[1]][diff[0]]) {
                return "";
            }
            marks[diff[0]][diff[1]] = true;
            letters[diff[0]] = true;
            letters[diff[1]] = true;
            dates.get(diff[0]).add(diff[1]);
            counts[diff[1]]++;
        }
        //记录入度为0的字符位置
        List<Integer> cur = new ArrayList<>();
        //记录有效字符的数量
        int num = 0;
        //3，统计字符数量与初始化入度为0的字符
        for (int i = 0; i < 26; i++) {
            if (letters[i]) {
                //所有的字符减去有比较关系的字符
                last[i] = false;
                num++;
                if (counts[i] == 0) {
                    cur.add(i);
                    counts[i] = -1;
                }
            }
        }
        //4，进行拓扑排序
        StringBuilder builder = new StringBuilder();
        while (cur.size() > 0) {
            for (int i = 0; i < cur.size(); i++) {
                int index = cur.get(i);
                builder.append((char) ('a' + index));
                //index对应的下一个字符入度都--
                List<Integer> items = dates.get(index);
                for (int j = 0; j < items.size(); j++) {
                    counts[items.get(j)]--;
                }
            }
            cur.clear();
            //重置入度为0的位置
            for (int i = 0; i < 26; i++) {
                if (letters[i] && counts[i] == 0) {
                    counts[i] = -1;
                    cur.add(i);
                }
            }
        }
        if (builder.length() != num) {
            return "";
        }
        for (int i = 0; i < 26; i++) {
            if (last[i]) {
                builder.append((char) ('a' + i));
            }
        }
        return builder.toString();
    }

    //检查两个字符串的差异
    private int[] getDiff(String pre, String cur) {
        int diffIndex = -1;
        int length = pre.length();
        for (int i = 0; i < length; i++) {
            if (i >= cur.length()) {
                //此时为非法字符对
                return new int[0];
            }
            if (pre.charAt(i) != cur.charAt(i)) {
                diffIndex = i;
                break;
            }
        }
        int[] result = new int[2];
        if (diffIndex == -1) {
            result[0] = pre.charAt(0) - 'a';
            result[1] = pre.charAt(0) - 'a';
        } else {
            result[0] = pre.charAt(diffIndex) - 'a';
            result[1] = cur.charAt(diffIndex) - 'a';
        }
        return result;
    }
}
