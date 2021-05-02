package com.demo.algorithm.algo;

import android.text.TextUtils;

/**
 * Created by w on 2021/5/2.
 *
 * 使用栈使用表达式的计算功能,默认只支持+,-,*,/四则运算。
 *
 */
public class CalculatorByStack {

    private static final String[] NUMBERS = {"0", "1", "2", "3", "4", "5", "6", "7", "8"};
    private static final String[] SYMBOLS = {"+", "-", "*", "/"};

    /**
     * @param expression : 计算表达式的值
     * @return :
     */
    public static int calculator(String expression) {
        if (TextUtils.isEmpty(expression)) {
            return 0;
        }
        //存储数字
        ArrayStack num = new ArrayStack();
        //存放操作符
        ArrayStack symbol = new ArrayStack();
        for (int i = 0; i < expression.length(); i++) {
            String tem = expression.substring(i, i + 1);
            //目前只支持个位数,支持多位数需要拼接数字字符
            if (isNumber(tem)) {
                num.push(tem);
            } else if (isSymbol(tem)) {
                if (symbol.size() == 0) {
                    symbol.push(tem);
                } else {
                    String pop = symbol.pop();
                    if (isHeightSymbol(tem, pop)) {
                        symbol.push(pop);
                        symbol.push(tem);
                    } else {
                        //优先级低的先计算之前的操作符
                        String after = num.pop();
                        String before = num.pop();
                        num.push(compute(before, after, pop));
                        symbol.push(tem);
                    }
                }



            } else {
                throw new IllegalArgumentException("表达式参数异常");
            }
        }
        while (symbol.size() > 0) {
            String after = num.pop();
            String before = num.pop();
            String pop = symbol.pop();
            num.push(compute(before, after, pop));
        }
        return Integer.parseInt(num.pop());
    }

    /**
     * @param before :
     * @param after  :
     * @param symbol : 操作符
     * @return : 计算
     */
    private static String compute(String before, String after, String symbol) {
        int result = 0;
        int a = Integer.parseInt(before);
        int b = Integer.parseInt(after);
        if ("+".equals(symbol)) {
            result = a + b;
        } else if ("-".equals(symbol)) {
            result = a - b;
        } else if ("*".equals(symbol)) {
            result = a * b;
        } else {
            result = a / b;
        }
        return "" + result;
    }

    /**
     * @param tem :
     * @param pop :
     * @return : 判断当前的运算符是否优先级高
     */
    private static boolean isHeightSymbol(String tem, String pop) {
        if (tem.equals("*") || tem.equals("/")) {
            if (pop.equals("+") || pop.equals("-")) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param tem :
     * @return : 判断是否为符号
     */
    private static boolean isSymbol(String tem) {
        for (int i = 0; i < SYMBOLS.length; i++) {
            if (SYMBOLS[i].equals(tem)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param tem :
     * @return : 判断是否为数字
     */
    private static boolean isNumber(String tem) {
        for (int i = 0; i < NUMBERS.length; i++) {
            if (NUMBERS[i].equals(tem)) {
                return true;
            }
        }
        return false;
    }

}
