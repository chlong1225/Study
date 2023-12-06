package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/12/6
 * @author chenglong
 * description : 密钥格式化
 *
 * 给定一个许可密钥字符串s，仅由字母、数字字符和破折号组成。字符串由n个破折号分成n+1组。你也会得到一个整数k。
 * 我们想要重新格式化字符串s，使每一组包含k个字符，除了第一组，它可以比k短，但仍然必须包含至少一个字符。
 * 此外，两组之间必须插入破折号，并且应该将所有小写字母转换为大写字母。
 * 返回重新格式化的许可密钥。
 *
 * 示例 1：
 * 输入：S = "5F3Z-2e-9-w", k = 4
 * 输出："5F3Z-2E9W"
 * 解释：字符串 S 被分成了两个部分，每部分 4 个字符；
 *      注意，两个额外的破折号需要删掉。
 *
 * 示例 2：
 * 输入：S = "2-5g-3-J", k = 2
 * 输出："2-5G-3J"
 * 解释：字符串 S 被分成了 3 个部分，按照前面的规则描述，第一部分的字符可以少于给定的数量，其余部分皆为 2 个字符。
 *
 * 提示:
 * 1 <= s.length <= 10^5
 * s 只包含字母、数字和破折号 '-'.
 * 1 <= k <= 10^4
 */
public class LicenseKeyFormatting {

    public String licenseKeyFormatting(String s, int k) {
        StringBuilder builder = new StringBuilder();
        int n = s.length();
        int count = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) != '-') {
                count++;
                builder.append(checkChar(s.charAt(i)));
                if (count == k && i > 0) {
                    builder.append('-');
                    count = 0;
                }
            }
        }
        int m = builder.length();
        if (m > 0 && builder.charAt(m - 1) == '-') {
            builder.delete(m - 1, m);
        }
        return builder.reverse().toString();
    }

    private char checkChar(char c) {
        if (c >= 'a' && c <= 'z') {
            return (char) ('A' + (c - 'a'));
        }
        return c;
    }
}
