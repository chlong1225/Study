package com.demo.algorithm.leetcode.easy;

/**
 * create on 2022/1/5
 * @author chenglong
 * description : 替换所有的问号
 *
 * 给你一个仅包含小写英文字母和'?' 字符的字符串s，请你将所有的'?' 转换为若干小写字母，使最终的字符串不包含任何连续重复的字符。
 * 注意：你不能修改非'?'字符。
 * 题目测试用例保证除 '?' 字符之外，不存在连续重复的字符。
 * 在完成所有转换（可能无需转换）后返回最终的字符串。如果有多个解决方案，请返回其中任何一个。可以证明，在给定的约束条件下，答案总是存在的。
 *
 * 示例 1：
 * 输入：s = "?zs"
 * 输出："azs"
 * 解释：该示例共有 25 种解决方案，从 "azs" 到 "yzs" 都是符合题目要求的。只有 "z" 是无效的修改，因为字符串 "zzs" 中有连续重复的两个 'z' 。
 *
 * 示例 2：
 * 输入：s = "ubv?w"
 * 输出："ubvaw"
 * 解释：该示例共有 24 种解决方案，只有替换成 "v" 和 "w" 不符合题目要求。因为 "ubvvw" 和 "ubvww" 都包含连续重复的字符。
 *
 * 示例 3：
 * 输入：s = "j?qg??b"
 * 输出："jaqgacb"
 *
 * 示例 4：
 * 输入：s = "??yw?ipkj?"
 * 输出："acywaipkja"
 *
 * 提示：
 * 1 <= s.length<= 100
 * s 仅包含小写英文字母和 '?' 字符
 */
public class ReplaceSymbol {

    public String modifyString(String s) {
        StringBuilder builder = new StringBuilder();
        int length = s.length();
        char pre = ' ';
        //统计连续的？的数量
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (s.charAt(i) != '?') {
                if (count > 0) {
                    //pre～s.chat(i)之间存在count个？
                    char next = s.charAt(i);
                    while (count > 0) {
                        for (char j = 'a'; j <='z' ; j++) {
                            if (j != pre && j != next) {
                                builder.append(j);
                                count--;
                                if (count == 0) {
                                    break;
                                }
                            }
                        }
                    }
                }
                pre = s.charAt(i);
                builder.append(pre);
            } else {
                count++;
            }
        }
        if (count > 0) {
            while (count > 0) {
                for (char i = 'a'; i <='z' ; i++) {
                    if (i != pre) {
                        builder.append(i);
                        count--;
                        if (count == 0) {
                            break;
                        }
                    }
                }
            }
        }
        return builder.toString();
    }
}
