package com.demo.algorithm.leetcode.easy;

/**
 * create on 2022/10/11
 * @author chenglong
 * description : 仅执行一次字符串交换能否使两个字符串相等
 *
 * 给你长度相等的两个字符串s1和s2。一次字符串交换操作的步骤如下：选出某个字符串中的两个下标（不必不同），并交换这两个下标所对应的字符。
 * 如果对其中一个字符串 执行最多一次字符串交换就可以使两个字符串相等返回true；否则返回false。
 *
 * 示例 1：
 * 输入：s1 = "bank", s2 = "kanb"
 * 输出：true
 * 解释：例如，交换 s2 中的第一个和最后一个字符可以得到 "bank"
 *
 * 示例 2：
 * 输入：s1 = "attack", s2 = "defend"
 * 输出：false
 * 解释：一次字符串交换无法使两个字符串相等
 *
 * 示例 3：
 * 输入：s1 = "kelb", s2 = "kelb"
 * 输出：true
 * 解释：两个字符串已经相等，所以不需要进行字符串交换
 *
 * 示例 4：
 * 输入：s1 = "abcd", s2 = "dcba"
 * 输出：false
 *
 * 提示：
 * 1 <= s1.length, s2.length <= 100
 * s1.length == s2.length
 * s1 和 s2 仅由小写英文字母组成
 */
public class AreAlmostEqual {

    public boolean areAlmostEqual(String s1, String s2) {
        int length = s1.length();
        int count = 0;
        int firstIndex = -1;
        int secondIndex = -1;
        for (int i = 0; i < length; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                count++;
                if (count == 1) {
                    firstIndex = i;
                } else if (count == 2) {
                    secondIndex = i;
                } else {
                    return false;
                }
            }
        }
        if (count == 0) {
            return true;
        }
        if (count == 1) {
            return false;
        }
        if (s1.charAt(firstIndex) == s2.charAt(secondIndex) && s1.charAt(secondIndex) == s2.charAt(firstIndex)) {
            return true;
        }
        return false;
    }
}
