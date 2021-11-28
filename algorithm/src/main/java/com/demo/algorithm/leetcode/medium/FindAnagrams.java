package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2021/11/28.
 * description : 找到字符串中所有字母异位词
 *
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 *
 * 示例 1:
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 *
 *  示例 2:
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 *  
 * 提示:
 * 1 <= s.length, p.length <= 3 * 104
 * s 和 p 仅包含小写字母
 */
public class FindAnagrams {

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int length = s.length();
        if (p.length() > length) {
            //1，如果子串更长，则不可能找到符合的子串
            return result;
        }
        //2，统计被比较子串的字符
        int[] compare = strToCounts(p);
        //3,使用双指针的方式
        int start = 0;
        int end = 0;
        int[] counts = new int[26];
        while (end < length) {
            int index = s.charAt(end) - 'a';
            if (compare[index] == 0) {
                //字串p中不存在当前的字符
                end++;
                start = end;
                counts = new int[26];
            } else {
                counts[index]++;
                end++;
                if (end - start == p.length()) {
                    if (compare(compare, counts)) {
                        result.add(start);
                    }
                    //移除起始的字符串
                    counts[s.charAt(start) - 'a']--;
                    start++;
                }
            }
        }
        return result;
    }

    private boolean compare(int[] compare, int[] counts) {
        int length = counts.length;
        for (int i = 0; i < length; i++) {
            if (compare[i] != counts[i]) {
                return false;
            }
        }
        return true;
    }

    private int[] strToCounts(String str) {
        int[] counts = new int[26];
        int length = str.length();
        for (int i = 0; i < length; i++) {
            counts[str.charAt(i) - 'a']++;
        }
        return counts;
    }

}
