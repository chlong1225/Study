package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2021/10/4.
 * description : 括号生成
 *
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 有效括号组合需满足：左括号必须以正确的顺序闭合。
 *
 * 示例 1：
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：["()"]
 *  
 * 提示：
 * 1 <= n <= 8
 */
public class Brackets {

    public List<String> generateParenthesis(int n) {
        if (n == 0) {
            return null;
        }
        List<String> result = new ArrayList<>();
        if (n == 1) {
            result.add("()");
            return result;
        }
        if (n == 2) {
            result.add("()()");
            result.add("(())");
            return result;
        }
        //n-1个1,n-1个0进行排列组合
        int sum = 1;
        for (int i = 0; i < n - 1; i++) {
            sum *= 2;
        }
        int min = sum - 1;
        int max = sum * min;
        for (int i = min; i <= max; i++) {
            boolean[] datas = intToBoolean(i, n);
            if (datas != null) {
                String s = checkAndBuild(datas);
                if (s != null) {
                    result.add(s);
                }
            }
        }
        return result;
    }

    private String checkAndBuild(boolean[] datas) {
        StringBuilder builder = new StringBuilder();
        int length = datas.length;
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (datas[i]) {
                builder.append("(");
                count++;
            } else {
                builder.append(")");
                count--;
                if (count < 0) {
                    return null;
                }
            }
        }
        return builder.toString();
    }

    private boolean[] intToBoolean(int sum, int n) {
        int length = 2 * n;
        boolean[] result = new boolean[length];
        result[0] = true;
        result[length - 1] = false;
        int count = 1;
        int index = length - 2;
        while (sum > 0) {
            if (sum % 2 == 1) {
                result[index] = true;
                count++;
                if (count > n) {
                    return null;
                }
            }
            sum /= 2;
            index--;
        }
        if (count == n) {
            return result;
        }
        return null;
    }
}
