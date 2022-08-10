package com.demo.algorithm.leetcode.medium;

/**
 * create on 2022/8/10
 * @author chenglong
 * description : 求解方程
 *
 * 求解一个给定的方程，将x以字符串"x=#value"的形式返回。该方程仅包含'+'，'-'操作，变量x和其对应系数。
 * 如果方程没有解，请返回"No solution"。如果方程有无限解，则返回“Infinite solutions”。
 * 如果方程中只有一个解，要保证返回值'x'是一个整数。
 *
 * 示例 1：
 * 输入: equation = "x+5-3+x=6+x-2"
 * 输出: "x=2"
 *
 * 示例 2:
 * 输入: equation = "x=x"
 * 输出: "Infinite solutions"
 *
 * 示例 3:
 * 输入: equation = "2x=x"
 * 输出: "x=0"
 *
 * 提示:
 * 3 <= equation.length <= 1000
 * equation只有一个'='.
 */
public class SolveEquation {

    private static final String NO_SOLUTION = "No solution";
    private static final String INFINITE_SOLUTIONS = "Infinite solutions";
    private static final char X = 'x';
    private static final char PLUS = '+';
    private static final char MINUS = '-';
    private static final char EQUAL = '=';

    public String solveEquation(String equation) {
        /**
         * 分析：分别解析"="左右两边的字符串，构建成a*x+b的结构，然后右边的转移到左边并计算
         * 如果：a=0并且b=0，此时有无限个解。如果a=0，b!=0,此时无解。其它的判断解是否为整数
         */
        //记录运算符号是否为"+"
        boolean isLeft = true;
        //解析记录左边的值
        int a = 0;
        int b = 0;
        //解析记录右边的值
        int c = 0;
        int d = 0;
        int length = equation.length();
        int startIndex = 0;
        for (int i = 0; i < length; i++) {
            if (equation.charAt(i) == X) {
                if (isLeft) {
                    a += paresInt(equation, startIndex, i);
                } else {
                    c += paresInt(equation, startIndex, i);
                }
                startIndex = i + 1;
            } else if (equation.charAt(i) == PLUS || equation.charAt(i) == MINUS) {
                if (startIndex == i) {
                    if (equation.charAt(i) == PLUS) {
                        startIndex++;
                    }
                    continue;
                }
                if (isLeft) {
                    b += paresInt(equation, startIndex, i);
                } else {
                    d += paresInt(equation, startIndex, i);
                }
                if (equation.charAt(i) == PLUS) {
                    startIndex = i + 1;
                } else {
                    startIndex = i;
                }
            } else if (equation.charAt(i) == EQUAL) {
                if (startIndex == i) {
                    startIndex++;
                    isLeft = false;
                    continue;
                }
                if (isLeft) {
                    b += paresInt(equation, startIndex, i);
                } else {
                    d += paresInt(equation, startIndex, i);
                }
                isLeft = false;
                startIndex = i + 1;
            }
        }
        if (startIndex < length) {
            d += paresInt(equation, startIndex, length);
        }
        a -= c;
        b -= d;
        if (a == 0 && b == 0) {
            return INFINITE_SOLUTIONS;
        }
        if (a == 0) {
            return NO_SOLUTION;
        }
        //此时转换为：a*x+b=0的结构
        if (b % a == 0) {
            return "x=" + ((-b) / a);
        }
        return NO_SOLUTION;
    }

    private int paresInt(String equation, int start, int end) {
        if (start > end) {
            return 0;
        }
        if (start == end) {
            return 1;
        }
        if (start + 1 == end) {
            if (equation.charAt(start) == PLUS) {
                return 1;
            }
            if (equation.charAt(start) == MINUS) {
                return -1;
            }
        }
        return Integer.parseInt(equation.substring(start, end));
    }
}
