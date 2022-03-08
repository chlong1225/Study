package com.demo.algorithm.leetcode.contest.doubleweek73;

/**
 * Created by chl on 2022/3/8.
 * description : 得到回文串的最少操作次数
 *
 * 给你一个只包含小写英文字母的字符串s。
 * 每一次操作，你可以选择s中两个相邻的字符，并将它们交换。
 * 请你返回将s变成回文串的最少操作次数。
 * 注意，输入数据会确保s一定能变成一个回文串。
 *
 * 示例 1：
 * 输入：s = "aabb"
 * 输出：2
 * 解释：
 * 我们可以将 s 变成 2 个回文串，"abba" 和 "baab" 。
 * - 我们可以通过 2 次操作得到 "abba" ："aabb" -> "abab" -> "abba" 。
 * - 我们可以通过 2 次操作得到 "baab" ："aabb" -> "abab" -> "baab" 。
 * 因此，得到回文串的最少总操作次数为 2 。
 *
 * 示例 2：
 * 输入：s = "letelt"
 * 输出：2
 * 解释：
 * 通过 2 次操作从 s 能得到回文串 "lettel" 。
 * 其中一种方法是："letelt" -> "letetl" -> "lettel" 。
 * 其他回文串比方说 "tleelt" 也可以通过 2 次操作得到。
 * 可以证明少于 2 次操作，无法得到回文串。
 *
 * 提示：
 * 1 <= s.length <= 2000
 * s只包含小写英文字母。
 * s可以通过有限次操作得到一个回文串。
 */
public class MinStepToMakePalindrome {

    public int minMovesToMakePalindrome(String s) {
        return 0;
    }
}
