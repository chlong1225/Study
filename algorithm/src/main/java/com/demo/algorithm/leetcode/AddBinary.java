package com.demo.algorithm.leetcode;

/**
 * Created by chl on 2021/9/5.
 * description : 二进制求和
 *
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 * 输入为 非空 字符串且只包含数字 1 和 0。
 *
 * 示例 1:
 * 输入: a = "11", b = "1"
 * 输出: "100"
 *
 * 示例 2:
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 */
public class AddBinary {

    public static String addBinary(String a, String b) {
        if (a == null || a.length() == 0) {
            return b;
        }
        if (b == null || b.length() == 0) {
            return a;
        }
        boolean isAddNext = false;
        int max = Math.max(a.length(), b.length());
        int[] result = new int[max + 1];
        int aNum;
        int bNum;
        int tem;
        for (int i = 0; i < max; i++) {
            if (a.length() - 1 < i) {
                aNum = 0;
            } else {
                aNum = Integer.parseInt(a.substring(a.length() - 1 - i, a.length() - i));
            }
            if (b.length() - 1 < i) {
                bNum = 0;
            } else {
                bNum = Integer.parseInt(b.substring(b.length() - 1 - i, b.length() - i));
            }
            tem = aNum + bNum + (isAddNext ? 1 : 0);
            if (tem >= 2) {
                isAddNext = true;
                tem = tem - 2;
            } else {
                isAddNext = false;
            }
            result[max - i] = tem;
        }
        result[0] = isAddNext ? 1 : 0;
        StringBuffer buffer = new StringBuffer();
        boolean isZero = true;
        for (int i = 0; i < result.length; i++) {
            if (isZero) {
                if (result[i] != 0) {
                    isZero = false;
                }
            }
            if (!isZero) {
                buffer.append(result[i]);
            }
        }
        String s = buffer.toString();
        if (s.length() == 0) {
            return "0";
        }
        return s;
    }
}
