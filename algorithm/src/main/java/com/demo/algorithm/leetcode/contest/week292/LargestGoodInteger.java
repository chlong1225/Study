package com.demo.algorithm.leetcode.contest.week292;

/**
 * Created by chl on 2022/5/8.
 * description : 字符串中最大的3位相同数字
 *
 * 给你一个字符串num，表示一个大整数。如果一个整数满足下述所有条件，则认为该整数是一个优质整数 ：
 * 该整数是num的一个长度为3的子字符串。
 * 该整数由唯一一个数字重复 3 次组成。
 * 以字符串形式返回最大的优质整数 。如果不存在满足要求的整数，则返回一个空字符串"" 。
 *
 * 注意：
 * 子字符串是字符串中的一个连续字符序列。
 * num 或优质整数中可能存在 前导零 。
 *
 * 示例 1：
 * 输入：num = "6777133339"
 * 输出："777"
 * 解释：num 中存在两个优质整数："777" 和 "333" 。
 * "777" 是最大的那个，所以返回 "777" 。
 *
 * 示例 2：
 * 输入：num = "2300019"
 * 输出："000"
 * 解释："000" 是唯一一个优质整数。
 *
 * 示例 3：
 * 输入：num = "42352338"
 * 输出：""
 * 解释：不存在长度为 3 且仅由一个唯一数字组成的整数。因此，不存在优质整数。
 *
 * 提示：
 * 3 <= num.length <= 1000
 * num仅由数字（0-9）组成
 */
public class LargestGoodInteger {

    public String largestGoodInteger(String num) {
        char max = 0;
        int length = num.length();
        char pre = num.charAt(0);
        int count = 1;
        for (int i = 1; i < length; i++) {
            if (num.charAt(i) == pre) {
                count++;
            } else {
                if (count >= 3) {
                    if (max < pre) {
                        max = pre;
                    }
                }
                pre = num.charAt(i);
                count = 1;
            }
        }
        if (count >= 3) {
            if (pre > max) {
                max = pre;
            }
        }
        if (max == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            builder.append(max);
        }
        return builder.toString();
    }
}
