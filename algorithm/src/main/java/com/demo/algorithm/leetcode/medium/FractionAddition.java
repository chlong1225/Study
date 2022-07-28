package com.demo.algorithm.leetcode.medium;

/**
 * Created by chl on 2022/7/28.
 * description : 分数加减运算
 *
 * 给定一个表示分数加减运算的字符串expression，你需要返回一个字符串形式的计算结果。
 * 这个结果应该是不可约分的分数，即最简分数。如果最终结果是一个整数，例如2，你需要将它转换成分数形式，其分母为1。所以在上述例子中, 2应该被转换为2/1。
 *
 * 示例1:
 * 输入:expression= "-1/2+1/2"
 * 输出: "0/1"
 *
 * 示例 2:
 * 输入:expression= "-1/2+1/2+1/3"
 * 输出: "1/3"
 *
 * 示例 3:
 * 输入:expression= "1/3-1/2"
 * 输出: "-1/6"
 *
 *
 * 提示:
 * 输入和输出字符串只包含'0' 到'9'的数字，以及'/', '+' 和'-'。
 * 输入和输出分数格式均为±分子/分母。如果输入的第一个分数或者输出的分数是正数，则'+'会被省略掉。
 * 输入只包含合法的最简分数，每个分数的分子与分母的范围是[1,10]。如果分母是1，意味着这个分数实际上是一个整数。
 * 输入的分数个数范围是 [1,10]。
 * 最终结果的分子与分母保证是 32 位整数范围内的有效整数。
 */
public class FractionAddition {

    public String fractionAddition(String expression) {
        int[] cur = new int[2];
        int length = expression.length();
        int index = 0;
        int a = 0;
        int b = 0;
        boolean isAdd = false;
        for (int i = 0; i < length; i++) {
            if (expression.charAt(i) == '+' || expression.charAt(i) == '-') {
                if (i == 0) {
                    //此时是刚开始的正负号
                    continue;
                }
                b = Integer.parseInt(expression.substring(index, i));
                index = i + 1;
                if (cur[1] == 0) {
                    cur[0] = a;
                    cur[1] = b;
                } else {
                    //此时需要进行计算：cur与[a,b]
                    calculate(cur, new int[]{a, b}, isAdd);
                }
                if (expression.charAt(i) == '+') {
                    isAdd = true;
                } else {
                    isAdd = false;
                }
            } else if (expression.charAt(i) == '/') {
                a = Integer.parseInt(expression.substring(index, i));
                index = i + 1;
            }
        }
        b = Integer.parseInt(expression.substring(index, length));
        if (cur[1] == 0) {
            cur[0] = a;
            cur[1] = b;
        } else {
            calculate(cur, new int[]{a, b}, isAdd);
        }
        if (cur[1] == 0) {
            return "";
        }
        return cur[0] + "/" + cur[1];
    }

    private void calculate(int[] cur, int[] next, boolean isAdd) {
        int total = cur[1] * next[1];
        int a = cur[0] * next[1];
        int b = next[0] * cur[1];
        if (isAdd) {
            a += b;
        } else {
            a -= b;
        }
        //a,total进行约分，获取最大公约数
        int div = 1;
        if (a != 0) {
            div = gcd(total, Math.abs(a));
            cur[0] = a / div;
            cur[1] = total / div;
        } else {
            cur[0] = 0;
            cur[1] = 1;
        }
    }

    private int gcd(int a, int b) {
        return b != 0 ? gcd(b, a % b) : a;
    }
}
