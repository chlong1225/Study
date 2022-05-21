package com.demo.algorithm.leetcode.interview;

/**
 * Created by chl on 2022/5/21.
 * description : 面试题05.02. 二进制数转字符串
 *
 * 二进制数转字符串。给定一个介于0和1之间的实数（如0.72），类型为double，打印它的二进制表达式。
 * 如果该数字无法精确地用32位以内的二进制表示，则打印“ERROR”。
 *
 * 示例1:
 *  输入：0.625
 *  输出："0.101"
 *
 * 示例2:
 *  输入：0.1
 *  输出："ERROR"
 *  提示：0.1无法被二进制准确表示
 *
 * 提示：
 * 32位包括输出中的 "0." 这两位。
 * 题目保证输入用例的小数位数最多只有6位
 */
public class PrintBin {

    public String printBin(double num) {
        if (num == 0) {
            return "0";
        }
        if (num == 1) {
            return "1";
        }
        StringBuilder builder = new StringBuilder();
        builder.append("0.");
        int step = 0;
        while (step < 30) {
            step++;
            num *= 2;
            if (num >= 1) {
                builder.append("1");
                num -= 1;
            } else {
                builder.append("0");
            }
            if (num == 0) {
                return builder.toString();
            }
        }
        return "ERROR";
    }
}
