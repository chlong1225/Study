package com.demo.algorithm.leetcode.medium;

import java.util.Arrays;

/**
 * create on 2022/2/7
 * @author chenglong
 * description : 最长快乐字符串
 *
 * 如果字符串中不含有任何'aaa'，'bbb'或'ccc'这样的字符串作为子串，那么该字符串就是一个「快乐字符串」。
 * 给你三个整数a，b，c，请你返回任意一个满足下列全部条件的字符串s：
 * s 是一个尽可能长的快乐字符串。
 * s 中最多有a个字母'a'、b个字母'b'、c个字母'c' 。
 * s 中只含有'a'、'b'、'c'三种字母。
 * 如果不存在这样的字符串s ，请返回一个空字符串 ""。
 *
 * 示例 1：
 * 输入：a = 1, b = 1, c = 7
 * 输出："ccaccbcc"
 * 解释："ccbccacc" 也是一种正确答案。
 *
 * 示例 2：
 * 输入：a = 2, b = 2, c = 1
 * 输出："aabbc"
 *
 * 示例 3：
 * 输入：a = 7, b = 1, c = 0
 * 输出："aabaa"
 * 解释：这是该测试用例的唯一正确答案。
 *
 * 提示：
 * 0 <= a, b, c <= 100
 * a + b + c > 0
 */
public class LongHappyStr {

    public String longestDiverseString(int a, int b, int c) {
        StringBuilder builder = new StringBuilder();
        //记录字符a，b，c的数量，0，1，2分别代表a，b，c
        int[][] counts = {{0, a}, {1, b}, {2, c}};
        Arrays.sort(counts, (o1, o2) -> o2[1] - o1[1]);
        int letter = -1;
        while (true) {
            int maxIndex = 0;
            if (letter != -1 && letter == counts[maxIndex][0]) {
                maxIndex = 1;
                letter = counts[1][0];
            } else {
                letter = counts[0][0];
            }
            if (counts[maxIndex][1] >= 2) {
                //添加次大数量字母时只能添加一个
                if (maxIndex == 1) {
                    builder.append((char) ('a' + letter));
                    counts[maxIndex][1] -= 1;
                } else {
                    builder.append((char) ('a' + letter));
                    builder.append((char) ('a' + letter));
                    counts[maxIndex][1] -= 2;
                }
                Arrays.sort(counts, (o1, o2) -> o2[1] - o1[1]);
            } else if (counts[maxIndex][1] == 1) {
                builder.append((char) ('a' + letter));
                counts[maxIndex][1] = 0;
                Arrays.sort(counts, (o1, o2) -> o2[1] - o1[1]);
            } else {
                return builder.toString();
            }
        }
    }
}
