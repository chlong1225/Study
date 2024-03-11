package com.demo.algorithm.leetcode.easy;

/**
 * create on 2024/3/11
 * @author chenglong
 * description : 将标题首字母大写
 *
 * 给你一个字符串title，它由单个空格连接一个或多个单词组成，每个单词都只包含英文字母。请你按以下规则将每个单词的首字母大写：
 * 如果单词的长度为1或者2，所有字母变成小写。
 * 否则，将单词首字母大写，剩余字母变成小写。
 * 请你返回大写后的title 。
 *
 * 示例 1：
 * 输入：title = "capiTalIze tHe titLe"
 * 输出："Capitalize The Title"
 * 解释：
 * 由于所有单词的长度都至少为 3 ，将每个单词首字母大写，剩余字母变为小写。
 *
 * 示例 2：
 * 输入：title = "First leTTeR of EACH Word"
 * 输出："First Letter of Each Word"
 * 解释：
 * 单词 "of" 长度为 2 ，所以它保持完全小写。
 * 其他单词长度都至少为 3 ，所以其他单词首字母大写，剩余字母小写。
 *
 * 示例 3：
 * 输入：title = "i lOve leetcode"
 * 输出："i Love Leetcode"
 * 解释：
 * 单词 "i" 长度为 1 ，所以它保留小写。
 * 其他单词长度都至少为 3 ，所以其他单词首字母大写，剩余字母小写。
 *
 * 提示：
 * 1 <= title.length <= 100
 * title 由单个空格隔开的单词组成，且不含有任何前导或后缀空格。
 * 每个单词由大写和小写英文字母组成，且都是非空的。
 */
public class CapitalizeTitle {

    public String capitalizeTitle(String title) {
        char[] dates = title.toCharArray();
        int n = title.length();
        int startIndex = 0;
        int endIndex = 1;
        while (endIndex < n) {
            if (dates[endIndex] == ' ') {
                //此时单词区间为：[startIndex,endIndex)
                int wordLength = endIndex - startIndex;
                if (wordLength < 3) {
                    //转换为小写
                    check(dates, startIndex, endIndex - 1);
                } else {
                    //首字母startIndex转大写，其它转小写
                    if (dates[startIndex] >= 'a' && dates[startIndex] <= 'z') {
                        dates[startIndex] = (char) (dates[startIndex] - 'a' + 'A');
                    }
                    check(dates, startIndex + 1, endIndex - 1);
                }
                startIndex = endIndex + 1;
                endIndex = startIndex + 1;
            } else {
                endIndex++;
            }
        }
        if (startIndex < n) {
            //防止最后一个字符为空格
            int wordLength = endIndex - startIndex;
            if (wordLength < 3) {
                //转换为小写
                check(dates, startIndex, endIndex - 1);
            } else {
                //首字母startIndex转大写，其它转小写
                if (dates[startIndex] >= 'a' && dates[startIndex] <= 'z') {
                    dates[startIndex] = (char) (dates[startIndex] - 'a' + 'A');
                }
                check(dates, startIndex + 1, endIndex - 1);
            }
        }
        return new String(dates);
    }

    private void check(char[] dates, int startIndex, int endIndex) {
        for (int i = startIndex; i <= endIndex; i++) {
            if (dates[i] >= 'A' && dates[i] <= 'Z') {
                dates[i] = (char) (dates[i] - 'A' + 'a');
            }
        }
    }
}
