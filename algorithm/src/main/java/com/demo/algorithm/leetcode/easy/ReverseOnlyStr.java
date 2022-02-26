package com.demo.algorithm.leetcode.easy;

/**
 * create on 2022/2/23
 * @author chenglong
 * description : 仅仅反转字母
 *
 * 给你一个字符串s，根据下述规则反转字符串：
 * 所有非英文字母保留在原有位置。
 * 所有英文字母（小写或大写）位置反转。
 * 返回反转后的s 。
 *
 * 示例 1：
 * 输入：s = "ab-cd"
 * 输出："dc-ba"
 *
 * 示例 2：
 * 输入：s = "a-bC-dEf-ghIj"
 * 输出："j-Ih-gfE-dCba"
 *
 * 示例 3：
 * 输入：s = "Test1ng-Leet=code-Q!"
 * 输出："Qedo1ct-eeLg=ntse-T!"
 *
 * 提示
 * 1 <= s.length <= 100
 * s 仅由 ASCII 值在范围 [33, 122] 的字符组成
 * s 不含 '\"' 或 '\\'
 */
public class ReverseOnlyStr {

    public String reverseOnlyLetters(String s) {
        char[] chars = s.toCharArray();
        //使用双指针的方式
        int start = 0;
        int end = chars.length - 1;
        while (start < end) {
            if (isLetter(chars[start]) && isLetter(chars[end])) {
                //start与end位置都是字母，此时交换
                char tem = chars[start];
                chars[start] = chars[end];
                chars[end] = tem;
                start++;
                end--;
            } else if (isLetter(chars[start])) {
                //只有start位置为字母时，end需要向前遍历
                end--;
            } else if (isLetter(chars[end])) {
                //只有end位置为字母时，start需要向后遍历
                start++;
            } else {
                //两端都不是字母时，start向后遍历，end向前遍历
                start++;
                end--;
            }
        }
        return new String(chars);
    }

    //判断当前字符是否为字母
    private boolean isLetter(char c) {
        if (c >= 'A' && c <= 'Z') {
            return true;
        }
        if (c >= 'a' && c <= 'z') {
            return true;
        }
        return false;
    }
}
