package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/12/7
 * @author chenglong
 * description : 学生出勤记录I
 *
 * 给你一个字符串s表示一个学生的出勤记录，其中的每个字符用来标记当天的出勤情况（缺勤、迟到、到场）。记录中只含下面三种字符：
 * 'A'：Absent，缺勤
 * 'L'：Late，迟到
 * 'P'：Present，到场
 * 如果学生能够同时满足下面两个条件，则可以获得出勤奖励：
 * 按总出勤计，学生缺勤（'A'）严格少于两天。
 * 学生不会存在连续3天或连续3天以上的迟到（'L'）记录。
 * 如果学生可以获得出勤奖励，返回true；否则，返回false。
 *
 * 示例 1：
 * 输入：s = "PPALLP"
 * 输出：true
 * 解释：学生缺勤次数少于2次，且不存在3天或以上的连续迟到记录。
 *
 * 示例 2：
 * 输入：s = "PPALLL"
 * 输出：false
 * 解释：学生最后三天连续迟到，所以不满足出勤奖励的条件。
 *
 * 提示：
 * 1 <= s.length <= 1000
 * s[i] 为 'A'、'L' 或 'P'
 */
public class CheckRecord {

    public boolean checkRecord(String s) {
        int countA = 0;
        int countL = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'L') {
                countL++;
                if (countL >= 3) {
                    return false;
                }
            } else {
                countL = 0;
                if (s.charAt(i) == 'A') {
                    countA++;
                }
            }
        }
        return countA < 2;
    }
}
