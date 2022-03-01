package com.demo.algorithm.leetcode.medium;

/**
 * create on 9/27/21
 * @author chenglong
 * description : Z字形变换
 *
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行Z 字形排列。
 * 比如输入字符串为 "PAYPALISHIRING"行数为 3 时，排列如下：
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 * 请你实现这个将字符串进行指定行数变换的函数：
 * string convert(string s, int numRows);
 *
 * 示例 1：
 * 输入：s = "PAYPALISHIRING", numRows = 3
 * 输出："PAHNAPLSIIGYIR"
 *
 * 示例 2：
 * 输入：s = "PAYPALISHIRING", numRows = 4
 * 输出："PINALSIGYAHRPI"
 * 解释：
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 *
 * 示例 3：
 * 输入：s = "A", numRows = 1
 * 输出："A"
 *
 * 提示：
 * 1 <= s.length <= 1000
 * s 由英文字母（小写和大写）、',' 和 '.' 组成
 * 1 <= numRows <= 1000
 */
public class ZTransform {

    public String convert(String s, int numRows) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        if (numRows <= 1) {
            return s;
        }
        int length = s.length();
        StringBuilder builder = new StringBuilder();
        if (numRows == 2) {
            for (int i = 0; i < length; i += 2) {
                builder.append(s.charAt(i));
            }
            for (int i = 1; i < length; i += 2) {
                builder.append(s.charAt(i));
            }
            return builder.toString();
        }
        int n = 2 * numRows - 2;
        int m = length / n + (length % n == 0 ? 0 : 1);
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || i == numRows - 1) {
                    int index = i + n * j;
                    if (index < length) {
                        builder.append(s.charAt(index));
                    }
                } else {
                    int index1 = i + n * j;
                    int index2 = n - i + n * j;
                    if (index1 < length) {
                        builder.append(s.charAt(index1));
                    }
                    if (index2 < length) {
                        builder.append(s.charAt(index2));
                    }
                }
            }
        }
        return builder.toString();
    }

    public String convert2(String s, int numRows) {
        int length = s.length();
        if (length == 1 || numRows == 1) {
            return s;
        }
        StringBuilder builder = new StringBuilder();
        //1，记录Z字形一个重复使用的字符数量
        int count = numRows * 2 - 2;
        //2，计算重复Z字形的次数
        int time = length / count;
        for (int i = 0; i < numRows; i++) {
            //添加完整的time次
            for (int j = 0; j < time; j++) {
                int index = i + j * count;
                builder.append(s.charAt(index));
                if (i > 0 && i < numRows - 1) {
                    int index2 = (j + 1) * count - i;
                    builder.append(s.charAt(index2));
                }
            }
            //添加最后不完整的一行
            int lastIndex = i + time * count;
            if (lastIndex < length) {
                builder.append(s.charAt(lastIndex));
                if (i > 0 && i < numRows - 1) {
                    int lastIndex2 = (time + 1) * count - i;
                    if (lastIndex2 < length) {
                        builder.append(s.charAt(lastIndex2));
                    }
                }
            }
        }
        return builder.toString();
    }
}
