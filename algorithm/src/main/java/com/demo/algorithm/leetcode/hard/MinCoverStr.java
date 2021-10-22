package com.demo.algorithm.leetcode.hard;

/**
 * create on 10/19/21
 * @author chenglong
 * description : 最小覆盖子串
 *
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 *
 * 注意：
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 *  
 * 示例 1：
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 *
 * 示例 2：
 * 输入：s = "a", t = "a"
 * 输出："a"
 *
 * 示例 3:
 * 输入: s = "a", t = "aa"
 * 输出: ""
 * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
 * 因此没有符合条件的子字符串，返回空字符串。
 *  
 * 提示：
 * 1 <= s.length, t.length <= 105
 * s 和 t 由英文字母组成
 *
 * 进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？
 */
public class MinCoverStr {

    public static String minWindow(String s, String t) {
        int m = s.length();
        int n = t.length();
        if (m < n) {
            return "";
        }
        if (n == 1) {
            for (int i = 0; i < m; i++) {
                if (s.charAt(i) == t.charAt(0)) {
                    return t;
                }
            }
            return "";
        }
        //字符A-z，对应65-122，共计58个
        int[] compare = new int[58];
        //1,将t转成compare数组，存放对应字符的个数
        for (int i = 0; i < n; i++) {
            int index = t.charAt(i) - 'A';
            compare[index]++;
        }
        int resultStart = -1;
        int resultEnd = 0;
        int count;
        int start = -1;
        int end = -1;
        int[] data = new int[58];
        for (int i = 0; i <= m - n; i++) {
            int index = s.charAt(i) - 'A';
            if (compare[index] > 0) {
                if (start == -1) {
                    start = i;
                    data[index]++;
                    count = 1;
                    for (int j = i + 1; j < m; j++) {
                        int tem = s.charAt(j) - 'A';
                        if (compare[tem] > 0) {
                            data[tem]++;
                            if (data[tem] <= compare[tem]) {
                                count++;
                                if (count == n) {
                                    end = j;
                                    int space = end - start;
                                    if (space == n - 1) {
                                        return s.substring(start, end + 1);
                                    } else {
                                        if (resultStart == -1) {
                                            resultStart = start;
                                            resultEnd = end;
                                        } else {
                                            if (resultEnd - resultStart > space) {
                                                resultStart = start;
                                                resultEnd = end;
                                            }
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if (end == -1) {
                        return "";
                    }
                } else {
                    //之前的start被跳过，需要下end后找到相同的字符
                    int findIndex = s.charAt(start) - 'A';
                    data[findIndex]--;
                    start = i;
                    if (data[findIndex] >= compare[findIndex]) {
                        //之前data中相同的字符有多余不用重新查找，重置resultStart
                        int space = end - start;
                        if (space == n - 1) {
                            return s.substring(start, end + 1);
                        } else {
                            if (resultEnd - resultStart > space) {
                                resultStart = start;
                                resultEnd = end;
                            }
                        }
                    } else {
                        boolean hasFind = false;
                        for (int j = end + 1; j < m; j++) {
                            int tem = s.charAt(j) - 'A';
                            if (compare[tem] > 0) {
                                data[tem]++;
                                if (tem == findIndex) {
                                    hasFind = true;
                                    end = j;
                                    int space = end - start;
                                    if (space == n - 1) {
                                        return s.substring(start, end + 1);
                                    } else {
                                        if (resultEnd - resultStart > space) {
                                            resultStart = start;
                                            resultEnd = end;
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                        if (!hasFind) {
                            return s.substring(resultStart, resultEnd + 1);
                        }
                    }
                }
            }
        }
        if (resultStart == -1) {
            return "";
        }
        return s.substring(resultStart, resultEnd + 1);
    }
}
