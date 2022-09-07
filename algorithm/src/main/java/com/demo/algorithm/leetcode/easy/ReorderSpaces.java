package com.demo.algorithm.leetcode.easy;

/**
 * create on 2022/9/7
 * @author chenglong
 * description : 重新排列单词间的空格
 *
 * 给你一个字符串text，该字符串由若干被空格包围的单词组成。每个单词由一个或者多个小写英文字母组成，并且两个单词之间至少存在一个空格。题目测试用例保证text至少包含一个单词 。
 * 请你重新排列空格，使每对相邻单词之间的空格数目都相等，并尽可能最大化该数目。如果不能重新平均分配所有空格，请将多余的空格放置在字符串末尾，这也意味着返回的字符串应当与原text字符串的长度相等。
 * 返回重新排列空格后的字符串 。
 *
 * 示例 1：
 * 输入：text = "  this   is  a sentence "
 * 输出："this   is   a   sentence"
 * 解释：总共有 9 个空格和 4 个单词。可以将 9 个空格平均分配到相邻单词之间，相邻单词间空格数为：9 / (4-1) = 3 个。
 *
 * 示例 2：
 * 输入：text = " practice   makes   perfect"
 * 输出："practice   makes   perfect "
 * 解释：总共有 7 个空格和 3 个单词。7 / (3-1) = 3 个空格加上 1 个多余的空格。多余的空格需要放在字符串的末尾。
 *
 * 示例 3：
 * 输入：text = "hello   world"
 * 输出："hello   world"
 *
 * 示例 4：
 * 输入：text = "  walks  udp package   into  bar a"
 * 输出："walks  udp  package  into  bar  a "
 *
 * 示例 5：
 * 输入：text = "a"
 * 输出："a"
 *
 * 提示：
 * 1 <= text.length <= 100
 * text由小写英文字母和' '组成
 * text中至少包含一个单词
 */
public class ReorderSpaces {

    public String reorderSpaces(String text) {
        int length = text.length();
        int count = 0;
        int wordCount = 0;
        int wordStartIndex = -1;
        //1，统计空格总数与单词总数
        for (int i = 0; i < length; i++) {
            if (text.charAt(i) == ' ') {
                if (wordStartIndex != -1) {
                    wordStartIndex = -1;
                    wordCount++;
                }
                count++;
            } else {
                if (wordStartIndex == -1) {
                    wordStartIndex = i;
                }
            }
        }
        if (wordStartIndex != -1) {
            wordCount++;
        }
        //2，没有空格时不需要重新排列
        if (count == 0) {
            return text;
        }
        //3，如果只有一个单词
        if (wordCount == 1) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < length; i++) {
                if (text.charAt(i) != ' ') {
                    builder.append(text.charAt(i));
                }
            }
            for (int i = 0; i < count; i++) {
                builder.append(' ');
            }
            return builder.toString();
        }
        //4，其它场景平均分配空格
        int space = count / (wordCount - 1);
        int last = count % (wordCount - 1);
        StringBuilder builder = new StringBuilder();
        wordStartIndex = -1;
        for (int i = 0; i < length; i++) {
            if (text.charAt(i) == ' ') {
                if (wordStartIndex != -1) {
                    //单词位置：wordStartIndex～i
                    builder.append(text.substring(wordStartIndex, i));
                    wordStartIndex = -1;
                    wordCount--;
                    if (wordCount == 0) {
                        for (int j = 0; j < last; j++) {
                            builder.append(' ');
                        }
                    } else {
                        for (int j = 0; j < space; j++) {
                            builder.append(' ');
                        }
                    }
                }
            } else {
                if (wordStartIndex == -1) {
                    wordStartIndex = i;
                }
            }
        }
        //5，最后不是空格时
        if (wordStartIndex != -1) {
            builder.append(text.substring(wordStartIndex, length));
            for (int j = 0; j < last; j++) {
                builder.append(' ');
            }
        }
        return builder.toString();
    }
}
