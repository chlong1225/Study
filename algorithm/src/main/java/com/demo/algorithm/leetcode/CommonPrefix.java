package com.demo.algorithm.leetcode;

/**
 * create by chenglong on 9/2/21
 * description : 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1：
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 *
 * 示例 2：
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 */
public class CommonPrefix {

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        String str = strs[0];
        if (str == null || str.length() == 0) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        String tem = "";
        out:for (int i = 0; i < str.length(); i++) {
            tem = str.substring(i, i + 1);
            for (int j = 1; j < strs.length; j++) {
                String check = strs[j];
                if (i >= check.length()) {
                    break out;
                }
                if (!tem.equals(check.substring(i, i + 1))) {
                    break out;
                }
            }
            result.append(tem);
        }
        return result.toString();
    }

}
