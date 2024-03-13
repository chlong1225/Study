package com.demo.algorithm.leetcode.easy;

/**
 * create on 2024/3/13
 * @author chenglong
 * description : 最大二进制奇数
 *
 * 给你一个二进制字符串s，其中至少包含一个'1'。
 * 你必须按某种方式重新排列字符串中的位，使得到的二进制数字是可以由该组合生成的最大二进制奇数。
 * 以字符串形式，表示并返回可以由给定组合生成的最大二进制奇数。
 * 注意返回的结果字符串可以含前导零。
 *
 * 示例 1：
 * 输入：s = "010"
 * 输出："001"
 * 解释：因为字符串 s 中仅有一个 '1' ，其必须出现在最后一位上。所以答案是 "001" 。
 *
 * 示例 2：
 * 输入：s = "0101"
 * 输出："1001"
 * 解释：其中一个 '1' 必须出现在最后一位上。而由剩下的数字可以生产的最大数字是 "100" 。所以答案是 "1001" 。
 *
 * 提示：
 * 1 <= s.length <= 100
 * s 仅由'0'和'1'组成
 * s 中至少包含一个'1'
 */
public class MaximumOddBinaryNumber {

    public String maximumOddBinaryNumber(String s) {
        int count0 = 0;
        int count1 = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                count0++;
            } else {
                count1++;
            }
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count1 - 1; i++) {
            builder.append('1');
        }
        for (int i = 0; i < count0; i++) {
            builder.append('0');
        }
        builder.append('1');
        return builder.toString();
    }
}
