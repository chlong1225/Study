package com.demo.algorithm.leetcode.easy;

import java.net.Inet4Address;

/**
 * create by chenglong on 9/2/21
 * description : 罗马数字转换
 *
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
 *
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 *
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 *
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 *
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
 *
 * 示例 1:
 * 输入: "III"
 * 输出: 3
 *
 * 示例 2:
 * 输入: "IV"
 * 输出: 4
 *
 * 示例 3:
 * 输入: "IX"
 * 输出: 9
 *
 * 示例 4:
 * 输入: "LVIII"
 * 输出: 58
 * 解释: L = 50, V= 5, III = 3.
 *
 * 示例 5:
 * 输入: "MCMXCIV"
 * 输出: 1994
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 *  
 * 提示：
 * 1 <= s.length <= 15
 * s 仅含字符 ('I', 'V', 'X', 'L', 'C', 'D', 'M')
 * 题目数据保证 s 是一个有效的罗马数字，且表示整数在范围 [1, 3999] 内
 * 题目所给测试用例皆符合罗马数字书写规则，不会出现跨位等情况。
 * IL 和 IM 这样的例子并不符合题目要求，49 应该写作 XLIX，999 应该写作 CMXCIX 。
 * 关于罗马数字的详尽书写规则，可以参考 罗马数字 - Mathematics 。
 */
public class NumberConversion {

    private static final String I = "I";
    private static final String V = "V";
    private static final String X = "X";
    private static final String L = "L";
    private static final String C = "C";
    private static final String D = "D";
    private static final String M = "M";

    private static final String IV = "IV";
    private static final String IX = "IX";
    private static final String XL = "XL";
    private static final String XC = "XC";
    private static final String CD = "CD";
    private static final String CM = "CM";

    public static int romanToInt(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return getSingleNumber(s);
        }
        int result = 0;
        String current = "";
        int index = 0;
        while (index < s.length()) {
            if (index + 2 <= s.length()) {
                current = s.substring(index, index + 2);
                if (isMatch(current)) {
                    result += getDoubleNumber(current);
                    index = index + 2;
                } else {
                    result += getSingleNumber(current.substring(0, 1));
                    index++;
                }
            } else {
                current = s.substring(index, index + 1);
                result += getSingleNumber(current);
                index++;
            }
        }
        return result;
    }

    private static boolean isMatch(String str) {
        if (IV.equals(str)) {
            return true;
        }
        if (IX.equals(str)) {
            return true;
        }
        if (XL.equals(str)) {
            return true;
        }
        if (XC.equals(str)) {
            return true;
        }
        if (CD.equals(str)) {
            return true;
        }
        if (CM.equals(str)) {
            return true;
        }
        return false;
    }

    private static int getSingleNumber(String str) {
        if (I.equals(str)) {
            return 1;
        }
        if (V.equals(str)) {
            return 5;
        }
        if (X.equals(str)) {
            return 10;
        }
        if (L.equals(str)) {
            return 50;
        }
        if (C.equals(str)) {
            return 100;
        }
        if (D.equals(str)) {
            return 500;
        }
        if (M.equals(str)) {
            return 1000;
        }
        return 0;
    }

    private static int getDoubleNumber(String str) {
        if (IV.equals(str)) {
            return 4;
        }
        if (IX.equals(str)) {
            return 9;
        }
        if (XL.equals(str)) {
            return 40;
        }
        if (XC.equals(str)) {
            return 90;
        }
        if (CD.equals(str)) {
            return 400;
        }
        if (CM.equals(str)) {
            return 900;
        }
        return 0;
    }

}
