package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/11/8
 * @author chenglong
 * description : 最长平衡子字符串
 *
 * 给你一个仅由0和1组成的二进制字符串s。
 * 如果子字符串中所有的0都在1之前且其中0的数量等于1的数量，则认为s的这个子字符串是平衡子字符串。请注意，空子字符串也视作平衡子字符串。
 * 返回s中最长的平衡子字符串长度。
 * 子字符串是字符串中的一个连续字符序列。
 *
 * 示例 1：
 * 输入：s = "01000111"
 * 输出：6
 * 解释：最长的平衡子字符串是 "000111" ，长度为 6 。
 *
 * 示例 2：
 * 输入：s = "00111"
 * 输出：4
 * 解释：最长的平衡子字符串是 "0011" ，长度为  4 。
 *
 * 示例 3：
 * 输入：s = "111"
 * 输出：0
 * 解释：除了空子字符串之外不存在其他平衡子字符串，所以答案为 0 。
 *
 * 提示：
 * 1 <= s.length <= 50
 * '0' <= s[i] <= '1'
 */
public class FindTheLongestBalancedSubstring {

    public int findTheLongestBalancedSubstring(String s) {
        int n = s.length();
        if (n == 1) {
            return 0;
        }
        int startIndex = -1;
        //1，查找第一个0的位置
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                startIndex = i;
                break;
            }
        }
        //特殊场景：当前字符串只有1
        if (startIndex == -1) {
            return 0;
        }
        int max = 0;
        int countZero = 0;
        int countOne = 0;
        boolean collectZero = true;
        for (int i = startIndex; i < n; i++) {
            if (s.charAt(i) == '0') {
                if (!collectZero) {
                    collectZero = true;
                    int count = 2 * Math.min(countZero, countOne);
                    if (count > max) {
                        max = count;
                    }
                    countZero = 0;
                    countOne = 0;
                }
                countZero++;
            } else {
                collectZero = false;
                countOne++;
            }
        }
        int count = Math.min(countZero, countOne) * 2;
        if (count > max) {
            max = count;
        }
        return max;
    }
}
