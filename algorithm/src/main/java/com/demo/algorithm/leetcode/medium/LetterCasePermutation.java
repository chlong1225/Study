package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2022/10/30.
 * description : 字母大小写全排列
 *
 * 给定一个字符串s，通过将字符串s中的每个字母转变大小写，我们可以获得一个新的字符串。
 * 返回所有可能得到的字符串集合 。以任意顺序返回输出。
 *
 * 示例 1：
 * 输入：s = "a1b2"
 * 输出：["a1b2", "a1B2", "A1b2", "A1B2"]
 *
 * 示例 2:
 * 输入: s = "3z4"
 * 输出: ["3z4","3Z4"]
 *
 * 提示:
 * 1 <= s.length <= 12
 * s由小写英文字母、大写英文字母和数字组成
 */
public class LetterCasePermutation {

    public List<String> letterCasePermutation(String s) {
        List<String> dates = new ArrayList<>();
        List<String> next = new ArrayList<>();
        dates.add(s);
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                //位置i处为字母
                for (int j = 0; j < dates.size(); j++) {
                    String tem = dates.get(j);
                    StringBuilder builder = new StringBuilder();
                    if (i > 0) {
                        builder.append(tem.substring(0, i));
                    }
                    if (c >= 'a' && c <= 'z') {
                        builder.append((char) (c - 32));
                    } else {
                        builder.append((char) (c + 32));
                    }
                    if (i < length - 1) {
                        builder.append(tem.substring(i + 1));
                    }
                    next.add(builder.toString());
                }
                dates.addAll(next);
                next.clear();
            }
        }
        return dates;
    }
}
