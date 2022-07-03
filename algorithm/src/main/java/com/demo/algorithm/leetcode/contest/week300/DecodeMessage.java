package com.demo.algorithm.leetcode.contest.week300;

/**
 * Created by chl on 2022/7/3.
 * description : 解密消息
 *
 * 给你字符串key和message，分别表示一个加密密钥和一段加密消息。解密message的步骤如下：
 * 使用 key 中 26 个英文小写字母第一次出现的顺序作为替换表中的字母 顺序 。
 * 将替换表与普通英文字母表对齐，形成对照表。
 * 按照对照表 替换 message 中的每个字母。
 * 空格 ' ' 保持不变。
 * 例如，key = "happy boy"（实际的加密密钥会包含字母表中每个字母 至少一次），据此，可以得到部分对照表（'h' -> 'a'、'a' -> 'b'、'p' -> 'c'、'y' -> 'd'、'b' -> 'e'、'o' -> 'f'）。
 * 返回解密后的消息。
 *
 * 示例 1：
 * 输入：key = "the quick brown fox jumps over the lazy dog", message = "vkbs bs t suepuv"
 * 输出："this is a secret"
 * 解释：对照表如上图所示。
 * 提取 "the quick brown fox jumps over the lazy dog" 中每个字母的首次出现可以得到替换表。
 *
 * 示例 2：
 * 输入：key = "eljuxhpwnyrdgtqkviszcfmabo", message = "zwx hnfx lqantp mnoeius ycgk vcnjrdb"
 * 输出："the five boxing wizards jump quickly"
 * 解释：对照表如上图所示。
 * 提取 "eljuxhpwnyrdgtqkviszcfmabo" 中每个字母的首次出现可以得到替换表。
 *
 * 提示：
 * 26 <= key.length <= 2000
 * key 由小写英文字母及 ' ' 组成
 * key 包含英文字母表中每个字符（'a' 到 'z'）至少一次
 * 1 <= message.length <= 2000
 * message 由小写英文字母和 ' ' 组成
 */
public class DecodeMessage {

    public String decodeMessage(String key, String message) {
        //1，构建对照表
        char[] marks = new char[26];
        int length = key.length();
        char replace = 'a';
        for (int i = 0; i < length; i++) {
            if (key.charAt(i) != ' ') {
                int index = key.charAt(i) - 'a';
                if (marks[index] == 0) {
                    marks[index] = replace;
                    replace++;
                }
            }
        }
        //2，按照对照表转换
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            if (message.charAt(i) == ' ') {
                builder.append(' ');
            } else {
                builder.append(marks[message.charAt(i) - 'a']);
            }
        }
        return builder.toString();
    }
}
