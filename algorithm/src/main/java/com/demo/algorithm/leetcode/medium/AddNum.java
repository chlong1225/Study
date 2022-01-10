package com.demo.algorithm.leetcode.medium;

/**
 * create on 2022/1/10
 * @author chenglong
 * description : 累加数
 *
 * 累加数是一个字符串，组成它的数字可以形成累加序列。
 * 一个有效的累加序列必须至少包含3个数。除了最开始的两个数以外，字符串中的其他数都等于它之前两个数相加的和。
 * 给你一个只包含数字'0'-'9'的字符串，编写一个算法来判断给定输入是否是累加数 。如果是，返回 true ；否则，返回 false 。
 * 说明：累加序列里的数 不会以0开头，所以不会出现1,2,03或者1,02,3的情况。
 *
 * 示例 1：
 * 输入："112358"
 * 输出：true
 * 解释：累加序列为: 1, 1, 2, 3, 5, 8 。1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 *
 * 示例2：
 * 输入："199100199"
 * 输出：true
 * 解释：累加序列为: 1, 99, 100, 199。1 + 99 = 100, 99 + 100 = 199
 *
 * 提示：
 * 1 <= num.length <= 35
 * num 仅由数字（0 - 9）组成
 *
 * 进阶：你计划如何处理由过大的整数输入导致的溢出?
 */
public class AddNum {

    public boolean isAdditiveNumber(String num) {
        int length = num.length();
        if (length < 3) {
            return false;
        }
        //1，限定拆分数字最大的长度
        int maxLength = (length - 1) >> 1;
        for (int i = 1; i <= maxLength; i++) {
            //2，遍历拆分第一个数字
            String str1 = num.substring(0, i);
            if (str1.startsWith("0") && str1.length() > 1) {
                break;
            }
            long a = Long.parseLong(str1);
            for (int j = 1; j <= maxLength; j++) {
                //3，拆分第二个数字之前先判断第三个数字的最大长度
                int third = length - i - j;
                if (third < i || third < j) {
                    break;
                }
                //4，遍历拆分第二个数字
                String str2 = num.substring(i, i + j);
                if (str2.startsWith("0") && str2.length() > 1) {
                    break;
                }
                long b = Long.parseLong(str2);
                //5，判断已a，b为起始数据，剩余的是否满足条件
                if (checkNun(a, b, i + j, num)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkNun(long a1, long a2, int start, String num) {
        int length = num.length();
        while (start < length) {
            long a3 = a1 + a2;
            String compare = "" + a3;
            int compareLength = compare.length();
            if (start + compareLength <= length) {
                if (num.substring(start, start + compareLength).equals(compare)) {
                    start += compareLength;
                    a1 = a2;
                    a2 = a3;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }
}
