package com.demo.algorithm.leetcode.easy;

/**
 * Created by chl on 2022/4/3.
 * description : 寻找比目标字母大的最小字母
 *
 * 给你一个排序后的字符列表letters，列表中只包含小写英文字母。另给出一个目标字母target，请你寻找在这一有序列表里比目标字母大的最小字母。
 * 在比较时，字母是依序循环出现的。举个例子：
 * 如果目标字母target = 'z' 并且字符列表为letters = ['a', 'b']，则答案返回'a'
 *
 * 示例 1：
 * 输入: letters = ["c", "f", "j"]，target = "a"
 * 输出: "c"
 *
 * 示例 2:
 * 输入: letters = ["c","f","j"], target = "c"
 * 输出: "f"
 *
 * 示例 3:
 * 输入: letters = ["c","f","j"], target = "d"
 * 输出: "f"
 *
 * 提示：
 * 2 <= letters.length <= 104
 * letters[i]是一个小写字母
 * letters 按非递减顺序排序
 * letters 最少包含两个不同的字母
 * target 是一个小写字母
 */
public class NextGreatestLetter {

    //线性遍历
    public char nextGreatestLetter(char[] letters, char target) {
        if (target == 'z') {
            return letters[0];
        }
        int length = letters.length;
        for (int i = 0; i < length; i++) {
            if (letters[i] > target) {
                return letters[i];
            }
        }
        return letters[0];
    }

    //使用二分法查找
    public char nextGreatestLetter2(char[] letters, char target) {
        //1，特殊场景：target比最大字符都大，返回第一个字符
        int length = letters.length;
        if (target >= letters[length-1]) {
            return letters[0];
        }
        int start = 0;
        int end = letters.length - 1;
        while (start < end) {
            int middle = (end - start) / 2 + start;
            if (letters[middle] > target) {
                end = middle;
            } else {
                start = middle + 1;
            }
        }
        return letters[start];
    }
}
