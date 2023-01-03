package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/1/3
 * @author chenglong
 * description : 检查句子中的数字是否递增
 *
 * 句子是由若干token组成的一个列表，token间用单个空格分隔，句子没有前导或尾随空格。每个token要么是一个由数字0-9组成的不含前导零的正整数，要么是一个由小写英文字母组成的单词 。
 * 示例，"a puppy has 2 eyes 4 legs" 是一个由7个token组成的句子："2" 和 "4" 是数字，其他像"puppy"这样的tokens属于单词。
 * 给你一个表示句子的字符串s，你需要检查s中的全部数字是否从左到右严格递增（即，除了最后一个数字，s中的每个数字都严格小于它右侧的数字）。
 * 如果满足题目要求，返回true，否则，返回false。
 *
 * 示例 1：
 * 输入：s = "1 box has 3 blue 4 red 6 green and 12 yellow marbles"
 * 输出：true
 * 解释：句子中的数字是：1, 3, 4, 6, 12 。
 * 这些数字是按从左到右严格递增的 1 < 3 < 4 < 6 < 12 。
 *
 * 示例 2：
 * 输入：s = "hello world 5 x 5"
 * 输出：false
 * 解释：句子中的数字是：5, 5 。这些数字不是严格递增的。
 *
 * 示例 3：
 * 输入：s = "sunset is at 7 51 pm overnight lows will be in the low 50 and 60 s"
 * 输出：false
 * 解释：s 中的数字是：7, 51, 50, 60 。这些数字不是严格递增的。
 *
 * 示例 4：
 * 输入：s = "4 5 11 26"
 * 输出：true
 * 解释：s 中的数字是：4, 5, 11, 26 。
 * 这些数字是按从左到右严格递增的：4 < 5 < 11 < 26 。
 *
 * 提示：
 * 3 <= s.length <= 200
 * s 由小写英文字母、空格和数字 0 到 9 组成（包含 0 和 9）
 * s 中数字 token 的数目在 2 和 100 之间（包含 2 和 100）
 * s 中的 token 之间由单个空格分隔
 * s 中至少有两个数字
 * s 中的每个数字都是一个小于100的正数，且不含前导零
 * s 不含前导或尾随空格
 */
public class AreNumbersAscending {

    public boolean areNumbersAscending(String s) {
        int pre = -1;
        int start = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                if (s.charAt(start) >= '0' && s.charAt(start) <= '9') {
                    int num = Integer.parseInt(s.substring(start, i));
                    if (pre >= num) {
                        return false;
                    }
                    pre = num;
                }
                start = i + 1;
            }
        }
        if (s.charAt(start) >= '0' && s.charAt(start) <= '9') {
            int num = Integer.parseInt(s.substring(start));
            if (num <= pre) {
                return false;
            }
        }
        return true;
    }
}
