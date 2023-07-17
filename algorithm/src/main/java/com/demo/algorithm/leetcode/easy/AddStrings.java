package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/7/17
 * @author chenglong
 * description : 字符串相加
 *
 * 给定两个字符串形式的非负整数num1和num2，计算它们的和并同样以字符串形式返回。
 * 你不能使用任何內建的用于处理大整数的库（比如 BigInteger），也不能直接将输入的字符串转换为整数形式。
 *
 * 示例 1：
 * 输入：num1 = "11", num2 = "123"
 * 输出："134"
 *
 * 示例 2：
 * 输入：num1 = "456", num2 = "77"
 * 输出："533"
 *
 * 示例 3：
 * 输入：num1 = "0", num2 = "0"
 * 输出："0"
 *
 *
 * 提示：
 * 1 <= num1.length, num2.length <= 10^4
 * num1 和num2 都只包含数字0-9
 * num1 和num2 都不包含任何前导零
 */
public class AddStrings {

    public String addStrings(String num1, String num2) {
        int add = 0;
        int index1 = num1.length() - 1;
        int index2 = num2.length() - 1;
        StringBuilder builder = new StringBuilder();
        while (index1 >= 0 && index2 >= 0) {
            int sum = add + (num1.charAt(index1) - '0') + (num2.charAt(index2) - '0');
            if (sum >= 10) {
                sum -= 10;
                add = 1;
            } else {
                add = 0;
            }
            builder.append(sum);
            index1--;
            index2--;
        }
        if (index1 >= 0) {
            while (index1 >= 0) {
                int sum = add + (num1.charAt(index1) - '0');
                if (sum >= 10) {
                    sum -= 10;
                    add = 1;
                } else {
                    add = 0;
                }
                builder.append(sum);
                index1--;
            }
        } else if (index2 >= 0) {
            while (index2 >= 0) {
                int sum = add + (num2.charAt(index2) - '0');
                if (sum >= 10) {
                    sum -= 10;
                    add = 1;
                } else {
                    add = 0;
                }
                builder.append(sum);
                index2--;
            }
        }
        if (add > 0) {
            builder.append(add);
        }
        return builder.reverse().toString();
    }
}
