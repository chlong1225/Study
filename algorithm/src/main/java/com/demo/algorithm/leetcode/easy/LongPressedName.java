package com.demo.algorithm.leetcode.easy;

/**
 * create by chenglong on 2023/12/11
 * description : 长按键入
 *
 * 你的朋友正在使用键盘输入他的名字name。偶尔，在键入字符c时，按键可能会被长按，而字符可能被输入1次或多次。
 * 你将会检查键盘输入的字符typed。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回True。
 *
 * 示例 1：
 * 输入：name = "alex", typed = "aaleex"
 * 输出：true
 * 解释：'alex' 中的 'a' 和 'e' 被长按。
 *
 * 示例 2：
 * 输入：name = "saeed", typed = "ssaaedd"
 * 输出：false
 * 解释：'e' 一定需要被键入两次，但在 typed 的输出中不是这样。
 *
 * 提示：
 * 1 <= name.length, typed.length <= 1000
 * name 和 typed 的字符都是小写字母
 */
public class LongPressedName {

    public boolean isLongPressedName(String name, String typed) {
        if (name.length() > typed.length()) {
            return false;
        }
        if (name.charAt(0) != typed.charAt(0) || name.charAt(name.length() - 1) != typed.charAt(typed.length() - 1)) {
            return false;
        }
        //依次统计name中出现的字符数
        int count = 1;
        char pre = name.charAt(0);
        int startIndex = 0;
        for (int i = 1; i < name.length(); i++) {
            if (name.charAt(i) == pre) {
                count++;
            } else {
                //此时统计到有count个pre字符。typed至少有count个相同的字符
                int compareCount = 1;
                if (startIndex >= typed.length()) {
                    return false;
                }
                char c = typed.charAt(startIndex);
                if (c != pre) {
                    return false;
                }
                while (startIndex + 1 < typed.length()) {
                    if (typed.charAt(startIndex + 1) == c) {
                        compareCount++;
                        startIndex++;
                    } else {
                        break;
                    }
                }
                if (compareCount < count) {
                    return false;
                }
                startIndex++;
                count = 1;
            }
            pre = name.charAt(i);
        }
        //最后剩余count个pre结尾
        if (startIndex < typed.length()) {
            if (typed.charAt(startIndex) != pre) {
                return false;
            }
            //统计数量
            int compareCount = 1;
            while (startIndex + 1 < typed.length()) {
                if (typed.charAt(startIndex + 1) == pre) {
                    compareCount++;
                    startIndex++;
                } else {
                    break;
                }
            }
            if (startIndex != typed.length() - 1) {
                return false;
            }
            if (compareCount < count) {
                return false;
            }
            return true;
        }
        return false;
    }
}
