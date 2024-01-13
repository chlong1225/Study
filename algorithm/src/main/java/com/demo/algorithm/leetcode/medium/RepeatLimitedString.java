package com.demo.algorithm.leetcode.medium;

/**
 * create on 2024/1/13
 * @author chenglong
 * description : 构造限制重复的字符串
 *
 * 给你一个字符串s和一个整数repeatLimit，用s中的字符构造一个新字符串repeatLimitedString，使任何字母连续出现的次数都不超过repeatLimit次。你不必使用s中的全部字符。
 * 返回字典序最大的repeatLimitedString。
 * 如果在字符串a和b不同的第一个位置，字符串a中的字母在字母表中出现时间比字符串b对应的字母晚，则认为字符串a比字符串b字典序更大。
 * 如果字符串中前min(a.length, b.length)个字符都相同，那么较长的字符串字典序更大。
 *
 * 示例 1：
 * 输入：s = "cczazcc", repeatLimit = 3
 * 输出："zzcccac"
 * 解释：使用 s 中的所有字符来构造 repeatLimitedString "zzcccac"。
 * 字母 'a' 连续出现至多 1 次。
 * 字母 'c' 连续出现至多 3 次。
 * 字母 'z' 连续出现至多 2 次。
 * 因此，没有字母连续出现超过 repeatLimit 次，字符串是一个有效的 repeatLimitedString 。
 * 该字符串是字典序最大的 repeatLimitedString ，所以返回 "zzcccac" 。
 * 注意，尽管 "zzcccca" 字典序更大，但字母 'c' 连续出现超过 3 次，所以它不是一个有效的 repeatLimitedString 。
 *
 * 示例 2：
 * 输入：s = "aababab", repeatLimit = 2
 * 输出："bbabaa"
 * 解释：
 * 使用 s 中的一些字符来构造 repeatLimitedString "bbabaa"。
 * 字母 'a' 连续出现至多 2 次。
 * 字母 'b' 连续出现至多 2 次。
 * 因此，没有字母连续出现超过 repeatLimit 次，字符串是一个有效的 repeatLimitedString 。
 * 该字符串是字典序最大的 repeatLimitedString ，所以返回 "bbabaa" 。
 * 注意，尽管 "bbabaaa" 字典序更大，但字母 'a' 连续出现超过 2 次，所以它不是一个有效的 repeatLimitedString 。
 *
 * 提示：
 * 1 <= repeatLimit <= s.length <= 10^5
 * s 由小写英文字母组成
 */
public class RepeatLimitedString {

    public String repeatLimitedString(String s, int repeatLimit) {
        //1，统计字符出现的次数
        int[] counts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 25; i >= 0; i--) {
            while (counts[i] > 0) {
                char cur = (char) ('a' + i);
                //此时需要依次添加cur
                if (builder.length() == 0) {
                    int addCount = Math.min(counts[i], repeatLimit);
                    counts[i] -= addCount;
                    for (int j = 0; j < addCount; j++) {
                        builder.append(cur);
                    }
                } else {
                    //上一个字符
                    char last = builder.charAt(builder.length() - 1);
                    if (last == cur) {
                        //需要在中间插入其它字符隔开
                        int nextIndex = -1;
                        for (int j = i - 1; j >= 0; j--) {
                            if (counts[j] > 0) {
                                nextIndex = j;
                                break;
                            }
                        }
                        if (nextIndex == -1) {
                            return builder.toString();
                        }
                        char next = (char) ('a' + nextIndex);
                        builder.append(next);
                        counts[nextIndex]--;
                    }
                    int addCount = Math.min(counts[i], repeatLimit);
                    counts[i] -= addCount;
                    for (int j = 0; j < addCount; j++) {
                        builder.append(cur);
                    }
                }
            }
        }
        return builder.toString();
    }
}
