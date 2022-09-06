package com.demo.algorithm.leetcode.hard;

/**
 * create on 2022/9/6
 * @author chenglong
 * description : 统计子串中的唯一字符
 *
 * 我们定义了一个函数countUniqueChars(s)来统计字符串s中的唯一字符，并返回唯一字符的个数。
 * 例如：s = "LEETCODE" ，则其中 "L", "T","C","O","D" 都是唯一字符，因为它们只出现一次，所以 countUniqueChars(s) = 5 。
 * 本题将会给你一个字符串s，我们需要返回countUniqueChars(t)的总和，其中t是s的子字符串。输入用例保证返回值为32位整数。
 * 注意，某些子字符串可能是重复的，但你统计时也必须算上这些重复的子字符串（也就是说，你必须统计s的所有子字符串中的唯一字符）。
 *
 * 示例 1：
 * 输入: s = "ABC"
 * 输出: 10
 * 解释: 所有可能的子串为："A","B","C","AB","BC" 和 "ABC"。
 *      其中，每一个子串都由独特字符构成。
 *      所以其长度总和为：1 + 1 + 1 + 2 + 2 + 3 = 10
 *
 * 示例 2：
 * 输入: s = "ABA"
 * 输出: 8
 * 解释: 除了countUniqueChars("ABA")=1之外，其余与示例1相同。
 *
 * 示例 3：
 * 输入：s = "LEETCODE"
 * 输出：92
 *
 * 提示：
 * 1 <= s.length <= 10^5
 * s 只包含大写英文字符
 */
public class UniqueLetterString {

    public int uniqueLetterString(String s) {
        //统计相同字符出现的位置，只需要统计前两次的位置
        int[][] marks = new int[26][2];
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 2; j++) {
                marks[i][j] = -1;
            }
        }
        int sum = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            int index = s.charAt(i) - 'A';
            if (marks[index][0] == -1) {
                marks[index][0] = i;
            } else {
                if (marks[index][1] == -1) {
                    sum += (marks[index][0] + 1) * (i - marks[index][0]);
                    marks[index][1] = i;
                } else {
                    //计算marks[index][1]位置的贡献
                    sum += (marks[index][1] - marks[index][0]) * (i - marks[index][1]);
                    marks[index][0] = marks[index][1];
                    marks[index][1] = i;
                }
            }
        }
        for (int i = 0; i < 26; i++) {
            if (marks[i][0] == -1) {
                continue;
            }
            if (marks[i][1] == -1) {
                //i只出现了一次
                sum += (marks[i][0] + 1) * (length - marks[i][0]);
            } else {
                //i出现两次以上
                sum += (marks[i][1] - marks[i][0]) * (length - marks[i][1]);
            }
        }
        return sum;
    }
}
