package com.demo.algorithm.leetcode.easy;

/**
 * create by chenglong on 2023/12/10
 * description : 字符串的最大公因子
 *
 * 对于字符串s和t，只有在s = t + ... + t（t自身连接1次或多次）时，我们才认定“t能除尽s”。
 * 给定两个字符串str1和str2。返回最长字符串x，要求满足x能除尽str1且x能除尽str2。
 *
 * 示例 1：
 * 输入：str1 = "ABCABC", str2 = "ABC"
 * 输出："ABC"
 *
 * 示例 2：
 * 输入：str1 = "ABABAB", str2 = "ABAB"
 * 输出："AB"
 *
 * 示例 3：
 * 输入：str1 = "LEET", str2 = "CODE"
 * 输出：""
 *
 * 提示：
 * 1 <= str1.length, str2.length <= 1000
 * str1和str2由大写英文字母组成
 */
public class GcdOfStrings {

    public String gcdOfStrings(String str1, String str2) {
        //1，统计字符数。有相同的共因子时，str1中存在的字符，str2中必须存在。并且数量等比
        int[] count1 = new int[26];
        for (int i = 0; i < str1.length(); i++) {
            count1[str1.charAt(i) - 'A']++;
        }
        int[] count2 = new int[26];
        for (int i = 0; i < str2.length(); i++) {
            count2[str2.charAt(i) - 'A']++;
        }
        int letterCount = 0;
        int compare1 = 0;
        int compare2 = 0;
        for (int i = 0; i < 26; i++) {
            if (count1[i] > 0 && count2[i] == 0) {
                return "";
            }
            if (count1[i] == 0 && count2[i] > 0) {
                return "";
            }
            if (count1[i] > 0 && count2[i] > 0) {
                if (letterCount == 0) {
                    compare1 = count1[i];
                    compare2 = count2[i];
                } else {
                    //此时：compare1/compare2 = count1[i]/count[2]
                    if (compare1 * count2[i] != compare2 * count1[i]) {
                        return "";
                    }
                }
                letterCount++;
            }
        }
        int maxLength = 0;
        if (str1.length() == str2.length()) {
            maxLength = str1.length();
        } else if (str1.length() > str2.length()) {
            maxLength = Math.min(str2.length(), str1.length() - str2.length());
        } else {
            maxLength = Math.min(str1.length(), str2.length() - str1.length());
        }
        //枚举最大公因子长度
        for (int i = maxLength; i >= letterCount; i--) {
            //1，判断长度是否整除
            if (str1.length() % i == 0 && str2.length() % i == 0) {
                String compare = str1.substring(0, i);
                if (isChild(compare, str1, count1) && isChild(compare, str2, count2)) {
                    return compare;
                }
            }
        }
        return "";
    }

    private boolean isChild(String child, String s, int[] count) {
        int n = child.length();
        int repeat = s.length() / n;
        if (repeat == 1) {
            return s.equals(child);
        }
        //统计child数量
        int[] childCount = new int[26];
        for (int i = 0; i < child.length(); i++) {
            childCount[child.charAt(i) - 'A']++;
        }
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0 && childCount[i] == 0) {
                return false;
            }
            if (count[i] == 0 && childCount[i] > 0) {
                return false;
            }
            if (count[i] > 0 && childCount[i] > 0) {
                if (count[i] != childCount[i] * repeat) {
                    return false;
                }
            }
        }
        for (int i = 0; i < repeat; i++) {
            String cur = s.substring(i * n, i * n + n);
            if (!child.equals(cur)) {
                return false;
            }
        }
        return true;
    }
}
