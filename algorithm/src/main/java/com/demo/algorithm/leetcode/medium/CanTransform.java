package com.demo.algorithm.leetcode.medium;

/**
 * create on 2022/10/2
 * @author chenglong
 * description : 在LR字符串中交换相邻字符
 *
 * 在一个由'L','R'和'X'三个字符组成的字符串（例如"RXXLRXRXL"）中进行移动操作。一次移动操作指用一个"LX"替换一个"XL"，或者用一个"XR"替换一个"RX"。
 * 现给定起始字符串start和结束字符串end，请编写代码，当且仅当存在一系列移动操作使得start可以转换成end时， 返回True。
 *
 * 示例 :
 * 输入: start = "RXXLRXRXL", end = "XRLXXRRLX"
 * 输出: True
 * 解释:
 * 我们可以通过以下几步将start转换成end:
 * RXXLRXRXL ->
 * XRXLRXRXL ->
 * XRLXRXRXL ->
 * XRLXXRRXL ->
 * XRLXXRRLX
 *
 * 提示：
 * 1 <= len(start) = len(end) <= 10000。
 * start和end中的字符串仅限于'L', 'R'和'X'。
 */
public class CanTransform {

    public boolean canTransform(String start, String end) {
        int length = start.length();
        int index = 0;
        for (int i = 0; i < length; i++) {
            if (start.charAt(i) == 'X') {
                continue;
            }
            if (start.charAt(i) == 'L') {
                while (index < length) {
                    if (end.charAt(index) == 'X') {
                        index++;
                    } else if (end.charAt(index) == 'L') {
                        break;
                    } else {
                        return false;
                    }
                }
                if (index == length) {
                    return false;
                }
                if (index > i) {
                    return false;
                }
                index++;
            } else {
                while (index < length) {
                    if (end.charAt(index) == 'X') {
                        index++;
                    } else if (end.charAt(index) == 'R') {
                        break;
                    } else {
                        return false;
                    }
                }
                if (index == length) {
                    return false;
                }
                if (index < i) {
                    return false;
                }
                index++;
            }
        }
        for (int i = index; i < length; i++) {
            if (end.charAt(i) == 'L' || end.charAt(i) == 'R') {
                return false;
            }
        }
        return true;
    }
}
