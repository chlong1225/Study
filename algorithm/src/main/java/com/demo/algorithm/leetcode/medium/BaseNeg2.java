package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/4/6
 * @author chenglong
 * description : 负二进制转换
 *
 * 给你一个整数n，以二进制字符串的形式返回该整数的负二进制（base-2）表示。
 * 注意，除非字符串就是"0"，否则返回的字符串中不能含有前导零。
 *
 * 示例 1：
 * 输入：n = 2
 * 输出："110"
 * 解释：(-2)2 + (-2)1 = 2
 *
 * 示例 2：
 * 输入：n = 3
 * 输出："111"
 * 解释：(-2)2 + (-2)1 + (-2)0 = 3
 *
 * 示例 3：
 * 输入：n = 4
 * 输出："100"
 * 解释：(-2)2 = 4
 *
 * 提示：
 * 0 <= n <= 10^9
 */
public class BaseNeg2 {

    public String baseNeg2(int n) {
        if (n == 0) {
            return "0";
        }
        StringBuilder builder = new StringBuilder();
        while (n != 0) {
            if (n % 2 == 0) {
                builder.append("0");
                n /= -2;
            } else {
                builder.append("1");
                n--;
                n /= -2;
            }
        }
        builder.reverse();
        return builder.toString();
    }
}
