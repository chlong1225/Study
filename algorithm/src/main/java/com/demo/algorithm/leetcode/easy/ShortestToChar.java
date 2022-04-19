package com.demo.algorithm.leetcode.easy;

/**
 * Created by chl on 2022/4/19.
 * description : 字符的最短距离
 *
 * 给你一个字符串s和一个字符c ，且c是s中出现过的字符。
 * 返回一个整数数组answer ，其中answer.length==s.length且answer[i]是s中从下标i到离它最近的字符c的距离 。
 * 两个下标i和j之间的 距离为 abs(i - j) ，其中abs是绝对值函数。
 *
 * 示例 1：
 * 输入：s = "loveleetcode", c = "e"
 * 输出：[3,2,1,0,1,0,0,1,2,2,1,0]
 * 解释：字符 'e' 出现在下标 3、5、6 和 11 处（下标从 0 开始计数）。
 * 距下标 0 最近的 'e' 出现在下标 3 ，所以距离为 abs(0 - 3) = 3 。
 * 距下标 1 最近的 'e' 出现在下标 3 ，所以距离为 abs(1 - 3) = 2 。
 * 对于下标 4 ，出现在下标 3 和下标 5 处的 'e' 都离它最近，但距离是一样的 abs(4 - 3) == abs(4 - 5) = 1 。
 * 距下标 8 最近的 'e' 出现在下标 6 ，所以距离为 abs(8 - 6) = 2 。
 *
 * 示例 2：
 * 输入：s = "aaab", c = "b"
 * 输出：[3,2,1,0]
 *
 * 提示：
 * 1 <= s.length <= 10^4
 * s[i] 和 c 均为小写英文字母
 * 题目数据保证 c 在 s 中至少出现一次
 */
public class ShortestToChar {

    public int[] shortestToChar(String s, char c) {
        int length = s.length();
        int[] result = new int[length];
        int leftIndex = -1;
        int rightIndex = -1;
        int start = 0;
        while (start < length) {
            //遍历查找字符c
            for (int i = start; i < length; i++) {
                //遍历查找字符为c的位置
                if (s.charAt(i) == c) {
                    if (leftIndex == -1) {
                        leftIndex = i;
                    } else {
                        if (rightIndex == -1) {
                            rightIndex = i;
                        } else {
                            leftIndex = rightIndex;
                            rightIndex = i;
                        }
                    }
                    break;
                }
            }
            if (rightIndex == -1) {
                //左边为基准
                if (start <= leftIndex) {
                    for (int i = start; i < leftIndex; i++) {
                        result[i] = leftIndex - i;
                    }
                    result[leftIndex] = 0;
                    start = leftIndex + 1;
                } else {
                    for (int i = start; i < length; i++) {
                        result[i] = i - leftIndex;
                    }
                    start = length;
                }
            } else {
                if (leftIndex <= start && start <= rightIndex) {
                    //遍历位置在leftIndex与rightIndex之间
                    for (int i = start; i < rightIndex; i++) {
                        result[i] = Math.min(i - leftIndex, rightIndex - i);
                    }
                    result[rightIndex] = 0;
                    start = rightIndex + 1;
                } else {
                    for (int i = start; i < length; i++) {
                        result[i] = i - rightIndex;
                    }
                    start = length;
                }
            }
        }
        return result;
    }
}
