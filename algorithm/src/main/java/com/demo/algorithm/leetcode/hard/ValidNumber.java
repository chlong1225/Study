package com.demo.algorithm.leetcode.hard;

/**
 * Created by chl on 2021/10/17.
 * description : 有效数字
 *
 * 有效数字（按顺序）可以分成以下几个部分：
 * 一个 小数 或者 整数
 * （可选）一个 'e' 或 'E' ，后面跟着一个 整数
 * 小数（按顺序）可以分成以下几个部分：
 *
 * （可选）一个符号字符（'+' 或 '-'）
 * 下述格式之一：
 * 至少一位数字，后面跟着一个点 '.'
 * 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
 * 一个点 '.' ，后面跟着至少一位数字
 * 整数（按顺序）可以分成以下几个部分：
 *
 * （可选）一个符号字符（'+' 或 '-'）
 * 至少一位数字
 * 部分有效数字列举如下：
 *
 * ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"]
 * 部分无效数字列举如下：
 *
 * ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"]
 * 给你一个字符串 s ，如果 s 是一个 有效数字 ，请返回 true 。
 *
 * 示例 1：
 * 输入：s = "0"
 * 输出：true
 *
 * 示例 2：
 * 输入：s = "e"
 * 输出：false
 *
 * 示例 3：
 * 输入：s = "."
 * 输出：false
 *
 * 示例 4：
 * 输入：s = ".1"
 * 输出：true
 *  
 * 提示：
 * 1 <= s.length <= 20
 * s 仅含英文字母（大写和小写），数字（0-9），加号 '+' ，减号 '-' ，或者点 '.' 。
 */
public class ValidNumber {

    public static boolean isNumber(String s) {
        //e的前后必须有数字,后面必须是整数,不能有点;+-左边只能是e,右边是数字
        char[] datas = s.toCharArray();
        int length = datas.length;
        if (length == 1) {
            return isNum(datas[0]);
        }
        //是否有e
        boolean hasE = false;
        //是否有.
        boolean hasPoint = false;
        char pre = datas[0];
        if (isLetter(pre) || isE(pre)) {
            return false;
        }
        if (pre == '.') {
            hasPoint = true;
        }
        char current;
        for (int i = 1; i < length; i++) {
            current = datas[i];
            if (isLetter(current)) {
                return false;
            }
            if (isE(current)) {
                if (hasE) {
                    return false;
                } else {
                    if (isNum(pre)) {
                        hasE = true;
                    } else {
                        if (pre == '.') {
                            if (i - 2 >= 0 && isNum(datas[i - 2])) {
                                hasE = true;
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    }
                }
            }
            if (current == '.') {
                if (hasPoint) {
                    return false;
                } else {
                    if (hasE) {
                        return false;
                    }
                    if (isSymbol(pre)) {
                        if (i + 1 < length && isNum(datas[i + 1])) {
                            hasPoint = true;
                        } else {
                            return false;
                        }
                    } else {
                        hasPoint = true;
                    }
                }
            }
            if (isSymbol(current)) {
                if (!isE(pre)) {
                    return false;
                }
            }
            pre = current;
        }
        if (isE(pre)) {
            return false;
        }
        return !isSymbol(pre);
    }

    //字符是否为+/-
    private static boolean isSymbol(char c) {
        return c == '+' || c == '-';
    }

    //字符是否为E/e
    private static boolean isE(char c) {
        return c == 'e' || c == 'E';
    }

    //字符是否为数字
    private static boolean isNum(char c) {
        return c >= '0' && c <= '9';
    }

    //字符是否为E,e外其它的字母
    private static boolean isLetter(char c) {
        if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
            return !isE(c);
        }
        return false;
    }


}
