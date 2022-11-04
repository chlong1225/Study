package com.demo.algorithm.leetcode.hard;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * create on 2022/11/5
 * @author chenglong
 * description : 解析布尔表达式
 *
 * 给你一个以字符串形式表述的布尔表达式（boolean）expression，返回该式的运算结果。
 * 有效的表达式需遵循以下约定：
 * "t"，运算结果为 True
 * "f"，运算结果为 False
 * "!(expr)"，运算过程为对内部表达式expr进行逻辑非的运算（NOT）
 * "&(expr1,expr2,...)"，运算过程为对2个或以上内部表达式 expr1, expr2, ... 进行逻辑与的运算（AND）
 * "|(expr1,expr2,...)"，运算过程为对2个或以上内部表达式 expr1, expr2, ... 进行逻辑或的运算（OR）
 *
 * 示例 1：
 * 输入：expression = "!(f)"
 * 输出：true
 *
 * 示例 2：
 * 输入：expression = "|(f,t)"
 * 输出：true
 *
 * 示例 3：
 * 输入：expression = "&(t,f)"
 * 输出：false
 *
 * 示例 4：
 * 输入：expression = "|(&(t,f,t),!(t))"
 * 输出：false
 *
 * 提示：
 * 1 <= expression.length <= 20000
 * expression[i] 由 {'(', ')', '&', '|', '!', 't', 'f', ','} 中的字符组成。
 * expression 是以上述形式给出的有效表达式，表示一个布尔值。
 */
public class ParseBoolExpr {

    //操作符号
    private static final char AND = '&';
    private static final char OR = '|';

    //括号
    private static final char LEFT = '(';
    private static final char RIGHT = ')';

    //状态值
    private static final char T = 't';
    private static final char F = 'f';

    public boolean parseBoolExpr(String expression) {
        Deque<Character> stack = new ArrayDeque<Character>();
        int length = expression.length();
        for (int i = 0; i < length; i++) {
            char c = expression.charAt(i);
            if (c == ',') {
                continue;
            } else if (c == RIGHT) {
                //弹出栈
                int countT = 0;
                int countF = 0;
                while (stack.size() > 0) {
                    char pre = stack.pollLast();
                    if (pre == T) {
                        countT++;
                    } else if (pre == F) {
                        countF++;
                    } else if (pre == LEFT) {
                        //此时计算()之间的结果
                        char op = stack.pollLast();
                        if (op == AND) {
                            if (countF > 0) {
                                stack.offer(F);
                            } else {
                                stack.offer(T);
                            }
                        } else if (op == OR) {
                            if (countT > 0) {
                                stack.offer(T);
                            } else {
                                stack.offer(F);
                            }
                        } else {
                            if (countT == 1) {
                                stack.offer(F);
                            } else {
                                stack.offer(T);
                            }
                        }
                        break;
                    }
                }
            } else {
                stack.offer(c);
            }
        }
        return stack.poll() == T ? true : false;
    }
}
