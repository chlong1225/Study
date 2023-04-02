package com.demo.algorithm.leetcode.medium;

/**
 * create on 2022/12/28
 * @author chenglong
 * description : 删除字符串两端相同字符后的最短长度
 *
 * 给你一个只包含字符'a'，'b'和'c'的字符串s，你可以执行下面这个操作（5 个步骤）任意次：
 * 选择字符串s一个非空的前缀，这个前缀的所有字符都相同。
 * 选择字符串s一个非空的后缀，这个后缀的所有字符都相同。
 * 前缀和后缀在字符串中任意位置都不能有交集。
 * 前缀和后缀包含的所有字符都要相同。
 * 同时删除前缀和后缀。
 * 请你返回对字符串s执行上面操作任意次以后（可能0次），能得到的最短长度。
 *
 * 示例 1：
 * 输入：s = "ca"
 * 输出：2
 * 解释：你没法删除任何一个字符，所以字符串长度仍然保持不变。
 *
 * 示例 2：
 * 输入：s = "cabaabac"
 * 输出：0
 * 解释：最优操作序列为：
 * - 选择前缀 "c" 和后缀 "c" 并删除它们，得到 s = "abaaba" 。
 * - 选择前缀 "a" 和后缀 "a" 并删除它们，得到 s = "baab" 。
 * - 选择前缀 "b" 和后缀 "b" 并删除它们，得到 s = "aa" 。
 * - 选择前缀 "a" 和后缀 "a" 并删除它们，得到 s = "" 。
 *
 * 示例 3：
 * 输入：s = "aabccabba"
 * 输出：3
 * 解释：最优操作序列为：
 * - 选择前缀 "aa" 和后缀 "a" 并删除它们，得到 s = "bccabb" 。
 * - 选择前缀 "b" 和后缀 "bb" 并删除它们，得到 s = "cca" 。
 *
 * 提示：
 * 1 <= s.length <= 10^5
 * s只包含字符'a'，'b'和'c'。
 */
public class MinimumLength {

    public int minimumLength(String s) {
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            if (s.charAt(start) == s.charAt(end)) {
                int count1 = 1;
                for (int i = start + 1; i < end; i++) {
                    if (s.charAt(i) == s.charAt(start)) {
                        count1++;
                    } else {
                        break;
                    }
                }
                int count2 = 1;
                for (int i = end - 1; i > start; i--) {
                    if (s.charAt(i) == s.charAt(end)) {
                        count2++;
                    } else {
                        break;
                    }
                }
                start += count1;
                end -= count2;
            } else {
                break;
            }
        }
        if (end < start) {
            return 0;
        }
        return end - start + 1;
    }
}
