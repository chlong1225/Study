package com.demo.algorithm.leetcode.offer;

/**
 * Created by chl on 2022/4/19.
 * description : 剑指Offer20. 表示数值的字符串
 *
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 * 数值（按顺序）可以分成以下几个部分：
 * 若干空格
 * 一个小数或者整数
 * （可选）一个'e'或'E'，后面跟着一个整数
 * 若干空格
 *
 * 小数（按顺序）可以分成以下几个部分：
 * （可选）一个符号字符（'+' 或 '-'）
 * 下述格式之一：
 * 至少一位数字，后面跟着一个点 '.'
 * 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
 * 一个点 '.' ，后面跟着至少一位数字
 *
 * 整数（按顺序）可以分成以下几个部分：
 * （可选）一个符号字符（'+' 或 '-'）
 * 至少一位数字
 * 部分数值列举如下：
 *
 * ["+100", "5e2", "-123", "3.1416", "-1E-16", "0123"]
 * 部分非数值列举如下：
 *
 * ["12e", "1a3.14", "1.2.3", "+-5", "12e+5.4"]
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
 * 输入：s = "   .1"
 * 输出：true
 *
 * 提示：
 * 1 <= s.length <= 20
 * s 仅含英文字母（大写和小写），数字（0-9），加号 '+' ，减号 '-' ，空格 ' ' 或者点 '.' 。
 */
public class CheckNumber {

    public boolean isNumber(String s) {
        int n = s.length();
        int start = 0;
        int end = n - 1;
        //1，去掉前后空格
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == ' ') {
                start = i + 1;
            } else {
                break;
            }
        }
        if (start > end) {
            //全部为空格
            return false;
        }
        for (int i = n - 1; i >= start; i--) {
            if (s.charAt(i) == ' ') {
                end = i - 1;
            } else {
                break;
            }
        }
        //特殊场景只有一个有效的字符时，必须是数字
        if (start == end) {
            if (s.charAt(start) >= '0' && s.charAt(start) <= '9') {
                return true;
            }
            return false;
        }
        //s去掉前后空格后有效范围：start~end之间
        //2，遍历校验字符并获取特殊字符的位置
        //e出现的位置
        int eIndex = -1;
        //小数点出现的位置
        int pointIndex = -1;
        //符号的位置
        int index1 = -1;
        int index2 = -1;
        for (int i = start; i <= end; i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                //数值中间不能有空格
                return false;
            }
            if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
                //不能有其它字母
                if (c == 'e' || c == 'E') {
                    if (eIndex == -1) {
                        eIndex = i;
                    } else {
                        //不能有超过两个e
                        return false;
                    }
                } else {
                    return false;
                }
            }
            if (c == '.') {
                if (pointIndex == -1) {
                    pointIndex = i;
                } else {
                    //只能出现一个小数点
                    return false;
                }
            }
            if (c == '+' || c == '-') {
                if (index1 == -1) {
                    index1 = i;
                } else {
                    if (index2 == -1) {
                        index2 = i;
                    } else {
                        //最多只能有两个符号
                        return false;
                    }
                }
            }
        }
        if (eIndex == -1) {
            //数值中不存在e。此时符号只能有一个，并且在开头
            if (index2 != -1) {
                return false;
            }
            if (index1 != -1) {
                if (index1 != start) {
                    //符号不在首位，或只有符号
                    return false;
                }
                if (pointIndex == start + 1) {
                    //点在符号后面
                    return pointIndex != end;
                }
                return true;
            } else {
                //至少有两位有效数字时，有一个点或没有都是合法的
                return true;
            }
        } else {
            if (eIndex == start) {
                //e的前面必须有小数或整数
                return false;
            }
            //存在e时，可以有两个符号
            if (index2 != -1) {
                //此时index2必须在e之后,index1在首位
                if (start != index1 || eIndex + 1 != index2) {
                    return false;
                }
                //e后面必须有整数:index2之后必须有整数
                if (index2 >= end || pointIndex > index2) {
                    return false;
                }
                //此时验证到e之后合法，需要验证前面
                if (pointIndex == -1 && isNum(s.charAt(eIndex - 1))) {
                    return true;
                } else {
                    if (isNum(s.charAt(pointIndex + 1)) || isNum(s.charAt(pointIndex - 1))) {
                        return true;
                    }
                    return false;
                }
            } else {
                if (index1 != -1) {
                    //只有一个符号
                    if (index1 == start) {
                        //起始位置为符号，e后面只能是整数
                        if (eIndex == end || pointIndex > eIndex) {
                            return false;
                        }
                        if (pointIndex == -1 && isNum(s.charAt(eIndex - 1))) {
                            return true;
                        }
                        if (isNum(s.charAt(pointIndex + 1)) || (pointIndex >= 1 && isNum(s.charAt(pointIndex - 1)))) {
                            return true;
                        }
                        return false;
                    } else {
                        if (index1 != eIndex + 1) {
                            return false;
                        }
                        //符号在e后面
                        if (index1 == end || pointIndex > index1) {
                            return false;
                        }
                        if (pointIndex == -1) {
                            return true;
                        }
                        if (isNum(s.charAt(pointIndex + 1)) || (pointIndex >= 1 && isNum(s.charAt(pointIndex - 1)))) {
                            return true;
                        }
                        return false;
                    }
                } else {
                    // 此时没有符号,e后面必须整数
                    if (eIndex == end || pointIndex > eIndex) {
                        return false;
                    }
                    if (pointIndex == -1) {
                        return true;
                    }
                    if (isNum(s.charAt(pointIndex + 1)) || (pointIndex >= 1 && isNum(s.charAt(pointIndex - 1)))) {
                        return true;
                    }
                    return false;
                }
            }
        }
    }

    private boolean isNum(char c) {
        return c >= '0' && c <= '9';
    }

}
