package com.demo.algorithm.leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 10/18/21
 * @author chenglong
 * description : 文本左右对齐
 *
 * 给定一个单词数组和一个长度maxWidth，重新排版单词，使其成为每行恰好有maxWidth个字符，且左右两端对齐的文本。
 * 你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格' '填充，使得每行恰好有 maxWidth个字符。
 * 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
 * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
 *
 * 说明:
 * 单词是指由非空格字符组成的字符序列。
 * 每个单词的长度大于 0，小于等于maxWidth。
 * 输入单词数组 words至少包含一个单词。
 *
 * 示例:
 * 输入:
 * words = ["This", "is", "an", "example", "of", "text", "justification."]
 * maxWidth = 16
 * 输出:
 * [
 * "This  is  an",
 * "example of text",
 * "justification. "
 * ]
 *
 * 示例2:
 * 输入:
 * words = ["What","must","be","acknowledgment","shall","be"]
 * maxWidth = 16
 * 输出:
 * [
 * "What  must  be",
 * "acknowledgment ",
 * "shall be "
 * ]
 * 解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
 *    因为最后一行应为左对齐，而不是左右两端对齐。
 *      第二行同样为左对齐，这是因为这行只包含一个单词。
 *
 * 示例3:
 * 输入:
 * words = ["Science","is","what","we","understand","well","enough","to","explain",
 *         "to","a","computer.","Art","is","everything","else","we","do"]
 * maxWidth = 20
 * 输出:
 * [
 * "Science is what we",
 *   "understand well",
 * "enough to explain to",
 * "a computer. Art is",
 * "everything else we",
 * "do "
 * ]
 */
public class AlignText {

    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        if (words == null || words.length == 0) {
            return result;
        }
        List<String> lines = new ArrayList<>();
        int length = words.length;
        int sum = 0;
        for (int i = 0; i < length; i++) {
            if (sum + words[i].length() <= maxWidth) {
                lines.add(words[i]);
                sum += words[i].length();
                if (sum + 1 >= maxWidth) {
                    if (i == length - 1) {
                        result.add(buildLastLine(lines, sum, maxWidth));
                    } else {
                        result.add(buildLine(lines, sum, maxWidth));
                    }
                    lines.clear();
                    sum = 0;
                } else {
                    sum++;
                }
            } else {
                result.add(buildLine(lines, sum - 1, maxWidth));
                lines.clear();
                if (words[i].length() == maxWidth) {
                    result.add(words[i]);
                    sum = 0;
                } else {
                    lines.add(words[i]);
                    sum = words[i].length() + 1;
                }
            }
        }
        if (lines.size() > 0) {
            result.add(buildLastLine(lines, sum - 1, maxWidth));
        }
        return result;
    }

    private static String buildLastLine(List<String> lines, int sum, int maxWidth) {
        StringBuilder builder = new StringBuilder();
        int length = lines.size();
        for (int i = 0; i < length; i++) {
            builder.append(lines.get(i));
            if (i < length - 1) {
                builder.append(" ");
            }
        }
        int last = maxWidth - sum;
        for (int i = 0; i < last; i++) {
            builder.append(" ");
        }
        return builder.toString();
    }

    private static String buildLine(List<String> lines, int sum, int maxWidth) {
        int length = lines.size();
        StringBuilder builder = new StringBuilder();
        if (length == 1) {
            builder.append(lines.get(0));
            int last = maxWidth - lines.get(0).length();
            for (int i = 0; i < last; i++) {
                builder.append(" ");
            }
            return builder.toString();
        }
        int last = maxWidth - sum;
        int empty = last / (length - 1) + 1;
        //额外不能够均分的空格，需要从左到右依次填充，而不是直接放在第一个单词后
        int extraEmpty = last % (length - 1);
        for (int i = 0; i < length; i++) {
            builder.append(lines.get(i));
            if (i < extraEmpty) {
                for (int j = 0; j < empty + 1; j++) {
                    builder.append(" ");
                }
            } else if (i < length - 1) {
                for (int j = 0; j < empty; j++) {
                    builder.append(" ");
                }
            }
        }
        return builder.toString();
    }
}
