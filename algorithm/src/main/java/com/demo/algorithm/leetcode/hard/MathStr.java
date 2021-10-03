package com.demo.algorithm.leetcode.hard;

/**
 * Created by chl on 2021/9/28.
 * description : 正则表达式匹配
 *
 *
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *
 * 示例 1：
 * 输入：s = "aa" p = "a"
 * 输出：false
 * 解释："a" 无法匹配 "aa" 整个字符串。
 *
 * 示例 2:
 * 输入：s = "aa" p = "a*"
 * 输出：true
 * 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 *
 * 示例 3：
 * 输入：s = "ab" p = ".*"
 * 输出：true
 * 解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 *
 * 示例 4：
 * 输入：s = "aab" p = "c*a*b"
 * 输出：true
 * 解释：因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 *
 * 示例 5：
 * 输入：s = "mississippi" p = "mis*is*p*."
 * 输出：false
 *
 * 提示：
 * 0 <= s.length <= 20
 * 0 <= p.length <= 30
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 保证每次出现字符 * 时，前面都匹配到有效的字符
 */
public class MathStr {

    public static boolean isMatch(String s, String p) {
        if (s == null || s.length() == 0) {
            if (p == null || p.length() == 0) {
                return true;
            }
            return isPEmpty(p, 0, p.length());
        }
        if (p == null || p.length() == 0) {
            return false;
        }
        int length = s.length();
        //倒着遍历匹配
        int index = p.length() - 1;
        char tem = s.charAt(length - 1);
        int count = 0;
        for (int i = length - 1; i >= 0; i--) {
            if (s.charAt(i) == tem) {
                count++;
            } else {
                int pre = mathChar(p, index, tem, count);
                if (pre == -10) {
                    return false;
                } else if (pre == -1) {
                    if (p.length() >= 2 && p.substring(0, 2).equals(".*")) {
                        index = 1;
                    } else {
                        return false;
                    }
                } else {
                    index = pre;
                }
                tem = s.charAt(i);
                count = 1;
            }
        }
        int pre = mathChar(p, index, tem, count);
        if (pre == -10) {
            return false;
        }
        if (pre == -1) {
            return true;
        }
        return isPEmpty(p, 0, pre + 1);
    }

    private static int mathChar(String p, int index, char c, int count) {
        char check;
        boolean hasMore = false;
        boolean hasNextMore = false;
        boolean hasFind = false;
        for (int i = index; i >= 0; i--) {
            check = p.charAt(i);
            if (check == '*') {
                if (hasFind) {
                    hasNextMore = true;
                } else {
                    hasMore = true;
                }
            } else {
                if (check == c || check == '.') {
                    if (hasMore) {
                        hasFind = true;
                    }
                    count--;
                    if (count == 0) {
                        return i - 1;
                    }
                } else {
                    if (hasMore) {
                        if (hasFind) {
                            return i;
                        } else {
                            hasMore = false;
                            hasFind = false;
                        }
                    } else {
                        return -10;
                    }
                }
            }
        }
        if (hasMore && hasFind) {
            return -1;
        }
        return -10;
    }


    //判断字符串规则是否能够表示空
    private static boolean isPEmpty(String p, int startIndex, int endIndex) {
        int count = 0;
        for (int i = startIndex; i < endIndex; i++) {
            if (p.charAt(i) == '*') {
                if (count > 0) {
                    count--;
                }
            } else {
                count++;
            }
        }
        return count == 0;
    }

}
