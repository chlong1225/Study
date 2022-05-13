package com.demo.algorithm.leetcode.medium;

/**
 * create on 2022/5/13
 * @author chenglong
 * description : 面试题01.05. 一次编辑
 *
 * 字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。
 *
 * 示例1:
 * 输入:
 * first = "pale"
 * second = "ple"
 * 输出: True
 *
 * 示例2:
 * 输入:
 * first = "pales"
 * second = "pal"
 * 输出: False
 */
public class OneEditAway {

    public boolean oneEditAway(String first, String second) {
        /**
         * 对于字符串操作，替换一个字符时两个字符的长度必须相等
         * 插入和删除操作可以归类同一个，并且两个字符的长度相差为1
         */
        if (first == null || second == null) {
            return false;
        }
        int m = first.length();
        int n = second.length();
        if (m == n) {
            if (m == 0) {
                return true;
            }
            return compareStr(first, second);
        }
        if (m - n == 1) {
            return addStr(second, first);
        }
        if (n - m == 1) {
            return addStr(first, second);
        }
        return false;
    }

    private boolean addStr(String first, String second) {
        int length = first.length();
        int index = 0;
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (first.charAt(i) == second.charAt(index)) {
                index++;
            } else {
                i--;
                index++;
                count++;
                if (count > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    //比较两个字符串，判断相同位置处不同字符的数量是否超过1
    private boolean compareStr(String first, String second) {
        int diff = 0;
        int length = first.length();
        for (int i = 0; i < length; i++) {
            if (first.charAt(i) != second.charAt(i)) {
                diff++;
                if (diff > 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
