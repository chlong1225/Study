package com.demo.algorithm.leetcode.easy;

/**
 * Created by chl on 2021/11/13.
 * description : 检测大写字母
 *
 * 我们定义，在以下情况时，单词的大写用法是正确的：
 * 全部字母都是大写，比如 "USA" 。
 * 单词中所有字母都不是大写，比如 "leetcode" 。
 * 如果单词不只含有一个字母，只有首字母大写， 比如 "Google" 。
 * 给你一个字符串 word 。如果大写用法正确，返回 true ；否则，返回 false 。
 *
 * 示例 1：
 * 输入：word = "USA"
 * 输出：true
 *
 * 示例 2：
 * 输入：word = "FlaG"
 * 输出：false
 *  
 * 提示：
 * 1 <= word.length <= 100
 * word 由小写和大写英文字母组成
 */
public class CheckLetter {

    public boolean detectCapitalUse(String word) {
        int length = word.length();
        if (length == 1) {
            return true;
        }
        boolean isFirst = isUppercaseLetter(word.charAt(0));
        if (isFirst) {
            //首字母大写,可能所有字母大写,可能后面字母都是小写
            if (length == 2) {
                return true;
            }
            isFirst = isUppercaseLetter(word.charAt(1));
            //第二个字符大小时,后面必须都是大写;第二个字符小写时后面必须都是小写
            if (isFirst) {
                for (int i = 2; i < length; i++) {
                    if (!isUppercaseLetter(word.charAt(i))) {
                        return false;
                    }
                }
                return true;
            } else {
                for (int i = 2; i < length; i++) {
                    if (isUppercaseLetter(word.charAt(i))) {
                        return false;
                    }
                }
                return true;
            }
        } else {
            //首字母小写, 必须所有字母小写
            for (int i = 1; i < length; i++) {
                if (isUppercaseLetter(word.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
    }

    //判断当前字符是否为大写
    private boolean isUppercaseLetter(char c) {
        if (c >= 'A' && c <= 'Z') {
            return true;
        }
        return false;
    }
}
