package com.demo.algorithm.leetcode.medium;

/**
 * create on 2022/2/25
 * @author chenglong
 * description : 复数乘法
 *
 * 复数可以用字符串表示，遵循"实部+虚部i"的形式，并满足下述条件：
 * 实部是一个整数，取值范围是[-100, 100]
 * 虚部也是一个整数，取值范围是[-100, 100]
 * i2 == -1
 * 给你两个字符串表示的复数num1和num2，请你遵循复数表示形式，返回表示它们乘积的字符串。
 *
 * 示例 1：
 * 输入：num1 = "1+1i", num2 = "1+1i"
 * 输出："0+2i"
 * 解释：(1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i ，你需要将它转换为 0+2i 的形式。
 *
 * 示例 2：
 * 输入：num1 = "1+-1i", num2 = "1+-1i"
 * 输出："0+-2i"
 * 解释：(1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i ，你需要将它转换为 0+-2i 的形式。
 *
 * 提示：
 * num1 和 num2 都是有效的复数表示。
 */
public class PluralMultiply {

    public String complexNumberMultiply(String num1, String num2) {
        //1，将两个复数拆分为实数+虚数
        String[] split1 = num1.split("\\+");
        int a = Integer.parseInt(split1[0]);
        int b = Integer.parseInt(split1[1].substring(0, split1[1].length() - 1));

        String[] split2 = num2.split("\\+");
        int c = Integer.parseInt(split2[0]);
        int d = Integer.parseInt(split2[1].substring(0, split2[1].length() - 1));

        //2，利用乘法的计算规则：(a+bi)*(c+di) = (ac-bd)+(ad+bc)i
        int sum1 = (a * c) - (b * d);
        int sum2 = a * d + b * c;
        //3，拼接实数与虚数部分
        StringBuilder builder = new StringBuilder();
        builder.append(sum1);
        builder.append('+');
        builder.append(sum2);
        builder.append('i');
        return builder.toString();
    }
}
