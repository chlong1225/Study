package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/4/10
 * @author chenglong
 * description : 检查相同字母间的距离
 *
 * 给你一个下标从0开始的字符串s，该字符串仅由小写英文字母组成，s中的每个字母都恰好出现两次。另给你一个下标从0开始、长度为26的的整数数组distance。
 * 字母表中的每个字母按从0到25依次编号（即，'a' -> 0, 'b' -> 1, 'c' -> 2, ... , 'z' -> 25）。
 * 在一个匀整字符串中，第i个字母的两次出现之间的字母数量是distance[i]。如果第i个字母没有在s中出现，那么distance[i]可以忽略。
 * 如果s是一个匀整字符串，返回true；否则返回false。
 *
 * 示例 1：
 * 输入：s = "abaccb", distance = [1,3,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
 * 输出：true
 * 解释：
 * - 'a' 在下标 0 和下标 2 处出现，所以满足 distance[0] = 1 。
 * - 'b' 在下标 1 和下标 5 处出现，所以满足 distance[1] = 3 。
 * - 'c' 在下标 3 和下标 4 处出现，所以满足 distance[2] = 0 。
 * 注意 distance[3] = 5 ，但是由于 'd' 没有在 s 中出现，可以忽略。
 * 因为 s 是一个匀整字符串，返回 true 。
 *
 * 示例 2：
 * 输入：s = "aa", distance = [1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
 * 输出：false
 * 解释：
 * - 'a' 在下标 0 和 1 处出现，所以两次出现之间的字母数量为 0 。
 * 但是 distance[0] = 1 ，s 不是一个匀整字符串。
 *
 * 提示：
 * 2 <= s.length <= 52
 * s 仅由小写英文字母组成
 * s 中的每个字母恰好出现两次
 * distance.length == 26
 * 0 <= distance[i] <= 50
 */
public class CheckDistances {

    public boolean checkDistances(String s, int[] distance) {
        //1，记录s中相同字母的间距
        int[] marks = new int[26];
        for (int i = 0; i < 26; i++) {
            marks[i] = -1;
        }
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            if (marks[index] == -1) {
                marks[index] = i;
            } else {
                marks[index] = i - marks[index] - 1;
            }
        }
        //2，判断distance是否有效
        for (int i = 0; i < 26; i++) {
            if (marks[i] == -1) {
                continue;
            }
            if (marks[i] != distance[i]) {
                return false;
            }
        }
        return true;
    }
}
