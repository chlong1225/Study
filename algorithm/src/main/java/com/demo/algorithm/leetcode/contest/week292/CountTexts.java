package com.demo.algorithm.leetcode.contest.week292;

/**
 * Created by chl on 2022/5/8.
 * description : 统计打字方案数
 *
 * Alice在给Bob用手机打字。数字到字母的对应如下图所示。
 * 为了打出一个字母，Alice需要按对应字母i次，i是该字母在这个按键上所处的位置。
 * 比方说，为了按出字母's'，Alice 需要按'7'四次。类似的，Alice需要按'5'两次得到字母'k'。
 * 注意，数字'0'和'1'不映射到任何字母，所以Alice不使用它们。
 * 但是由于传输的错误，Bob没有收到Alice打字的字母信息，反而收到了按键的字符串信息。
 * 比方说Alice发出的信息为"bob"，Bob 将收到字符串"2266622"。
 * 给你一个字符串pressedKeys，表示Bob收到的字符串，请你返回Alice总共可能发出多少种文字信息。
 * 由于答案可能很大，将它对10^9 + 7取余后返回。
 *
 * 示例 1：
 * 输入：pressedKeys = "22233"
 * 输出：8
 * 解释：
 * Alice 可能发出的文字信息包括：
 * "aaadd", "abdd", "badd", "cdd", "aaae", "abe", "bae" 和 "ce" 。
 * 由于总共有 8 种可能的信息，所以我们返回 8 。
 *
 * 示例 2：
 * 输入：pressedKeys = "222222222222222222222222222222222222"
 * 输出：82876089
 * 解释：
 * 总共有 2082876103 种 Alice 可能发出的文字信息。
 * 由于我们需要将答案对 109 + 7 取余，所以我们返回 2082876103 % (109 + 7) = 82876089 。
 *
 * 提示：
 * 1 <= pressedKeys.length <= 10^5
 * pressedKeys 只包含数字'2'到'9'。
 */
public class CountTexts {

    private static final int MOD = 1000000007;

    public int countTexts(String pressedKeys) {
        /**
         * 分析：由于字母前后有关联，这里可以使用动态规划
         */
        int length = pressedKeys.length();
        int[] marks = new int[length + 1];
        marks[0] = 1;
        marks[1] = 1;
        for (int i = 2; i <= length; i++) {
            if (pressedKeys.charAt(i - 1) != pressedKeys.charAt(i - 2)) {
                marks[i] = marks[i - 1];
            } else {
                marks[i] = marks[i - 1] + marks[i - 2];
                marks[i] %= MOD;
                if (i >= 3 && pressedKeys.charAt(i - 1) == pressedKeys.charAt(i - 3)) {
                    marks[i] += marks[i - 3];
                    marks[i] %= MOD;
                    if (pressedKeys.charAt(i - 1) == '7' || pressedKeys.charAt(i - 1) == '9') {
                        if (i >= 4 && pressedKeys.charAt(i - 1) == pressedKeys.charAt(i - 4)) {
                            marks[i] += marks[i - 4];
                            marks[i] %= MOD;
                        }
                    }
                }
            }
        }
        return marks[length];
    }
}
