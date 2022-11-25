package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2022/11/25
 * @author chenglong
 * description :  情感丰富的文字
 *
 * 有时候人们会用重复写一些字母来表示额外的感受，比如 "hello" -> "heeellooo", "hi" -> "hiii"。我们将相邻字母都相同的一串字符定义为相同字母组，例如："h", "eee", "ll", "ooo"。
 * 对于一个给定的字符串S，如果另一个单词能够通过将一些字母组扩张从而使其和S相同，我们将这个单词定义为可扩张的（stretchy）。
 * 扩张操作定义如下：选择一个字母组（包含字母c），然后往其中添加相同的字母c使其长度达到3或以上。
 * 例如，以"hello"为例，我们可以对字母组"o"扩张得到"hellooo"，但是无法以同样的方法得到"helloo"因为字母组"oo"长度小于3。
 * 此外，我们可以进行另一种扩张 "ll" -> "lllll" 以获得"helllllooo"。如果S = "helllllooo"，那么查询词"hello"是可扩张的，因为可以对它执行这两种扩张操作使得query="hello"->"hellooo"->"helllllooo" = S。
 * 输入一组查询单词，输出其中可扩张的单词数量。
 *
 * 示例：
 * 输入：
 * S = "heeellooo"
 * words = ["hello", "hi", "helo"]
 * 输出：1
 * 解释：
 * 我们能通过扩张"hello"的"e"和"o"来得到"heeellooo"。
 * 我们不能通过扩张"helo"来得到"heeellooo"因为"ll"的长度小于3。
 *
 * 提示：
 * 0 <= len(S) <= 100。
 * 0 <= len(words) <= 100。
 * 0 <= len(words[i]) <= 100。
 * S和所有在words中的单词都只由小写字母组成。
 */
public class ExpressiveWords {

    public int expressiveWords(String s, String[] words) {
        int count = 0;
        int length = words.length;
        if (s == null || s.length() == 0) {
            for (int i = 0; i < length; i++) {
                String word = words[i];
                if (word == null || word.length() == 0) {
                    count++;
                }
            }
            return count;
        }
        //1，预处理s字符串
        List<int[]> dates = new ArrayList<>();
        int pre = s.charAt(0) - 'a';
        int preCount = 1;
        for (int i = 1; i < s.length(); i++) {
            int cur = s.charAt(i) - 'a';
            if (cur == pre) {
                preCount++;
            } else {
                dates.add(new int[]{pre, preCount});
                pre = cur;
                preCount = 1;
            }
        }
        if (preCount != 0) {
            dates.add(new int[]{pre, preCount});
        }
        //2，依次比较words字符串
        for (int i = 0; i < length; i++) {
            if (check(s, dates, words[i])) {
                count++;
            }
        }
        return count;
    }

    private boolean check(String s, List<int[]> dates, String word) {
        //1，处理特殊场景
        if (word == null || word.length() == 0) {
            return false;
        }
        //2，word长度大于s时，无法扩张
        if (word.length() > s.length()) {
            return false;
        }
        //3，如果word与s长度相同时，必须相等才能扩张，此时扩张数为0
        if (word.length() == s.length()) {
            return s.equals(word);
        }
        //4，依次比较字符串
        int index = 0;
        int pre = word.charAt(0) - 'a';
        int preCount = 1;
        if (dates.get(index)[0] != pre) {
            return false;
        }
        for (int i = 1; i < word.length(); i++) {
            int cur = word.charAt(i) - 'a';
            if (cur == pre) {
                preCount++;
            } else {
                //此时比较字符[pre,preCount]
                if (preCount > dates.get(index)[1]) {
                    return false;
                }
                if (preCount < dates.get(index)[1]) {
                    //此时需要扩张
                    if (dates.get(index)[1] < 3) {
                        return false;
                    }
                }
                index++;
                pre = cur;
                preCount = 1;
                if (index == dates.size()) {
                    return false;
                }
                if (dates.get(index)[0] != pre) {
                    return false;
                }
            }
        }
        //比较最后一个
        if (preCount > dates.get(index)[1]) {
            return false;
        }
        if (preCount < dates.get(index)[1]) {
            if (dates.get(index)[1] < 3) {
                return false;
            }
        }
        return index == dates.size() - 1;
    }
}
