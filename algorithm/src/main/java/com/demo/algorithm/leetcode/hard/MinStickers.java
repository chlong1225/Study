package com.demo.algorithm.leetcode.hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by chl on 2022/5/14.
 * description : 贴纸拼词
 *
 * 我们有n种不同的贴纸。每个贴纸上都有一个小写的英文单词。
 * 您想要拼写出给定的字符串target，方法是从收集的贴纸中切割单个字母并重新排列它们。如果你愿意，你可以多次使用每个贴纸，每个贴纸的数量是无限的。
 * 返回你需要拼出target的最小贴纸数量。如果任务不可能，则返回 -1 。
 * 注意：在所有的测试用例中，所有的单词都是从1000个最常见的美国英语单词中随机选择的，并且target被选择为两个随机单词的连接。
 *
 * 示例 1：
 * 输入： stickers = ["with","example","science"], target = "thehat"
 * 输出：3
 * 解释：
 * 我们可以使用 2 个 "with" 贴纸，和 1 个 "example" 贴纸。
 * 把贴纸上的字母剪下来并重新排列后，就可以形成目标 “thehat“ 了。
 * 此外，这是形成目标字符串所需的最小贴纸数量。
 *
 * 示例 2:
 * 输入：stickers = ["notice","possible"], target = "basicbasic"
 * 输出：-1
 * 解释：我们不能通过剪切给定贴纸的字母来形成目标“basicbasic”。
 *
 * 提示:
 * n == stickers.length
 * 1 <= n <= 50
 * 1 <= stickers[i].length <= 10
 * 1 <= target <= 15
 * stickers[i]和target由小写英文单词组成
 */
public class MinStickers {

    public int minStickers(String[] stickers, String target) {
        /**
         * 分析：当目标中存在字母是所有贴纸都没有时，不可能拼接成功
         * 贴纸中不存在目前中的字母时，当前贴纸无效
         */
        //1，统计目标中字符数量
        int[] counts = new int[26];
        for (int i = 0; i < target.length(); i++) {
            counts[target.charAt(i) - 'a']++;
        }
        //2，剔出无效的贴纸与字母，同时统计贴纸中有效的字符
        int[] totals = new int[26];
        List<String> dates = new ArrayList<>();
        for (int i = 0; i < stickers.length; i++) {
            String str = checkStr(stickers[i], counts, totals);
            if (str.length() > 0) {
                dates.add(str);
            }
        }
        if (dates.size() == 0) {
            return -1;
        }
        //3，判断target中的字母是否在贴纸中都能找到
        for (int i = 0; i < 26; i++) {
            if (counts[i] > 0) {
                if (totals[i] == 0) {
                    return -1;
                }
            }
        }
        //4，重置排序后的target
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (counts[i] > 0) {
                char tem = (char) ('a' + i);
                for (int j = 0; j < counts[i]; j++) {
                    builder.append(tem);
                }
            }
        }
        target = builder.toString();
        //5，分层遍历bfs
        int step = 0;
        Set<String> marks = new HashSet<>();
        marks.add(target);
        List<String> curs = new ArrayList<>();
        curs.add(target);
        List<String> next = new ArrayList<>();
        while (curs.size() > 0) {
            step++;
            for (int i = 0; i < curs.size(); i++) {
                String str = curs.get(i);
                for (int j = 0; j < dates.size(); j++) {
                    String tem = deleteStr(str, dates.get(j));
                    if (tem.length() == 0) {
                        return step;
                    }
                    if (tem.length() == str.length()) {
                        //没有被删除的字母
                        continue;
                    }
                    if (marks.contains(tem)) {
                        continue;
                    }
                    marks.add(tem);
                    next.add(tem);
                }
            }
            curs.clear();
            curs.addAll(next);
            next.clear();
        }
        return step;
    }

    private String deleteStr(String str, String delete) {
        int[] counts = new int[26];
        for (int i = 0; i < str.length(); i++) {
            counts[str.charAt(i) - 'a']++;
        }
        for (int i = 0; i < delete.length(); i++) {
            int index = delete.charAt(i) - 'a';
            if (counts[index] > 0) {
                counts[index]--;
            }
        }
        return countToStr(counts);
    }

    private String checkStr(String str, int[] compares, int[] totals) {
        int[] counts = new int[26];
        for (int i = 0; i < str.length(); i++) {
            int index = str.charAt(i) - 'a';
            if (compares[index] > 0) {
                counts[index]++;
                totals[index]++;
            }
        }
        return countToStr(counts);
    }

    private String countToStr(int[] counts) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (counts[i] > 0) {
                char tem = (char) ('a' + i);
                for (int j = 0; j < counts[i]; j++) {
                    builder.append(tem);
                }
            }
        }
        return builder.toString();
    }
}
