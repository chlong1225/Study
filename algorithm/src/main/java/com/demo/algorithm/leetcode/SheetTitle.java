package com.demo.algorithm.leetcode;

/**
 * create by chenglong on 9/14/21
 * description : Excel表列名称
 *
 * 给你一个整数 columnNumber ，返回它在 Excel 表中相对应的列名称。
 * 例如：
 *
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 *
 * 示例 1：
 * 输入：columnNumber = 1
 * 输出："A"
 *
 * 示例 2：
 * 输入：columnNumber = 28
 * 输出："AB"
 *
 * 示例 3：
 * 输入：columnNumber = 701
 * 输出："ZY"
 *
 * 示例 4：
 * 输入：columnNumber = 2147483647
 * 输出："FXSHRXW"
 *
 * 提示：
 * 1 <= columnNumber <= 231 - 1
 */
public class SheetTitle {

    private static final String[] NAME = {"A", "B", "C", "D", "E", "F", "G",
            "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y","Z"};

    public static String convertToTitle(int columnNumber) {
        if (columnNumber < 1) {
            return "";
        }
        columnNumber--;
        String result = "";
        while (columnNumber / 26 != 0) {
            result = NAME[columnNumber % 26] + result;
            columnNumber /= 26;
            columnNumber--;
        }
        if (columnNumber >= 0) {
            result = NAME[columnNumber] + result;
        }
        return result;
    }
}
