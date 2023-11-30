package com.demo.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2023/11/30
 * @author chenglong
 * description : 键盘行
 *
 * 给你一个字符串数组words，只返回可以使用在美式键盘同一行的字母打印出来的单词。键盘如下图所示。
 * 美式键盘 中：
 * 第一行由字符 "qwertyuiop" 组成。
 * 第二行由字符 "asdfghjkl" 组成。
 * 第三行由字符 "zxcvbnm" 组成。
 * American keyboard
 *
 * 示例 1：
 * 输入：words = ["Hello","Alaska","Dad","Peace"]
 * 输出：["Alaska","Dad"]
 *
 * 示例 2：
 * 输入：words = ["omk"]
 * 输出：[]
 *
 * 示例 3：
 * 输入：words = ["adsdf","sfd"]
 * 输出：["adsdf","sfd"]
 *
 * 提示：
 * 1 <= words.length <= 20
 * 1 <= words[i].length <= 100
 * words[i]由英文字母（小写和大写字母）组成
 */
public class FindWords {

    private static final int[] lines = new int[128];

    static {
        int offset = 'A' - 'a';
        String str1 = "qwertyuiop";
        String str2 = "asdfghjkl";
        String str3 = "zxcvbnm";
        for (int i = 0; i < str1.length(); i++) {
            lines[str1.charAt(i)] = 1;
            lines[str1.charAt(i) + offset] = 1;
        }
        for (int i = 0; i < str2.length(); i++) {
            lines[str2.charAt(i)] = 2;
            lines[str2.charAt(i) + offset] = 2;
        }
        for (int i = 0; i < str3.length(); i++) {
            lines[str3.charAt(i)] = 3;
            lines[str3.charAt(i) + offset] = 3;
        }
    }

    public String[] findWords(String[] words) {
        List<String> dates = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            String cur = words[i];
            if (isSameLine(cur)) {
                dates.add(cur);
            }
        }
        String[] answer = new String[dates.size()];
        for (int i = 0; i < dates.size(); i++) {
            answer[i] = dates.get(i);
        }
        return answer;
    }

    private boolean isSameLine(String word) {
        if (word.length() == 1) {
            return true;
        }
        int compare = lines[word.charAt(0)];
        for (int i = 1; i < word.length(); i++) {
            if (lines[word.charAt(i)] != compare) {
                return false;
            }
        }
        return true;
    }
}
