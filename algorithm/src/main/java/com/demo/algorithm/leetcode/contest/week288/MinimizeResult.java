package com.demo.algorithm.leetcode.contest.week288;

/**
 * Created by chl on 2022/4/12.
 * description : 向表达式添加括号后的最小结果
 *
 * 给你一个下标从0开始的字符串expression，格式为"<num1>+<num2>"，其中<num1>和<num2>表示正整数。
 * 请你向expression中添加一对括号，使得在添加之后，expression仍然是一个有效的数学表达式，并且计算后可以得到最小可能值。
 * 左括号必须添加在'+'的左侧，而右括号必须添加在'+'的右侧。
 * 返回添加一对括号后形成的表达式expression，且满足expression计算得到最小可能值。
 * 如果存在多个答案都能产生相同结果，返回任意一个答案。
 * 生成的输入满足：expression 的原始值和添加满足要求的任一对括号之后expression的值，都符合32-bit带符号整数范围。
 *
 * 示例 1：
 * 输入：expression = "247+38"
 * 输出："2(47+38)"
 * 解释：表达式计算得到 2 * (47 + 38) = 2 * 85 = 170 。
 * 注意 "2(4)7+38" 不是有效的结果，因为右括号必须添加在 '+' 的右侧。
 * 可以证明 170 是最小可能值。
 *
 * 示例 2：
 * 输入：expression = "12+34"
 * 输出："1(2+3)4"
 * 解释：表达式计算得到 1 * (2 + 3) * 4 = 1 * 5 * 4 = 20 。
 *
 * 示例 3：
 * 输入：expression = "999+999"
 * 输出："(999+999)"
 * 解释：表达式计算得到 999 + 999 = 1998 。
 *
 * 提示：
 * 3 <= expression.length <= 10
 * expression 仅由数字 '1' 到 '9' 和 '+' 组成
 * expression 由数字开始和结束
 * expression 恰好仅含有一个 '+'.
 * expression 的原始值和添加满足要求的任一对括号之后 expression 的值，都符合 32-bit 带符号整数范围
 */
public class MinimizeResult {

    public String minimizeResult(String expression) {
        //1，找到+所在的位置
        int middle = 0;
        int length = expression.length();
        for (int i = 0; i < length; i++) {
            if (expression.charAt(i) == '+') {
                middle = i;
                break;
            }
        }
        //字符串分成三部分：0~middle-1之间，middle，middle+1~length-1之间
        int left = middle - 1;
        int right = middle + 1;
        int min = Integer.MAX_VALUE;
        int startIndex = 0;
        int endIndex = length - 1;
        for (int i = left; i >= 0; i--) {
            for (int j = right; j < length; j++) {
                int a = 1;
                if (i > 0) {
                    a = Integer.parseInt(expression.substring(0, i));
                }
                int b = Integer.parseInt(expression.substring(i, middle));
                int c = Integer.parseInt(expression.substring(right, j + 1));
                int d = 1;
                if (j < length - 1) {
                    d = Integer.parseInt(expression.substring(j + 1, length));
                }
                int sum = a * (b + c) * d;
                if (sum < min) {
                    min = sum;
                    startIndex = i;
                    endIndex = j + 1;
                }
            }
        }
        StringBuilder builder = new StringBuilder();
        if (startIndex > 0) {
            builder.append(expression.substring(0, startIndex));
        }
        builder.append("(");
        builder.append(expression.substring(startIndex, endIndex));
        builder.append(")");
        if (endIndex < length) {
            builder.append(expression.substring(endIndex));
        }
        return builder.toString();
    }
}
