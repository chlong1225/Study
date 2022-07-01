package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2022/7/1
 * @author chenglong
 * description : 为运算表达式设计优先级
 *
 * 给你一个由数字和运算符组成的字符串expression，按不同优先级组合数字和运算符，计算并返回所有可能组合的结果。你可以按任意顺序返回答案。
 * 生成的测试用例满足其对应输出值符合32位整数范围，不同结果的数量不超过10^4 。
 *
 * 示例 1：
 * 输入：expression = "2-1-1"
 * 输出：[0,2]
 * 解释：
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 *
 * 示例 2：
 * 输入：expression = "2*3-4*5"
 * 输出：[-34,-14,-10,-10,10]
 * 解释：
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 *
 * 提示：
 * 1 <= expression.length <= 20
 * expression 由数字和算符 '+'、'-' 和 '*' 组成。
 * 输入表达式中的所有整数值在范围 [0, 99]
 */
public class DiffWaysToCompute {

    private final List<Integer> nums = new ArrayList<>();
    private final List<Character> ops = new ArrayList<>();

    public List<Integer> diffWaysToCompute(String expression) {
        nums.clear();
        ops.clear();
        int length = expression.length();
        //1，将字符串拆分为数字与符号
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = expression.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                //此时为操作符
                ops.add(c);
                nums.add(Integer.parseInt(builder.toString()));
                builder.delete(0, builder.length());
            } else {
                builder.append(c);
            }
        }
        if (builder.length() > 0) {
            nums.add(Integer.parseInt(builder.toString()));
        }
        //2，处理特殊场景
        if (ops.size() == 0) {
            return nums;
        }
        return dfs(0, ops.size() - 1);
    }

    private List<Integer> dfs(int start, int end) {
        List<Integer> result = new ArrayList<>();
        if (start > end) {
            result.add(nums.get(start));
            return result;
        }
        for (int i = start; i <= end; i++) {
            List<Integer> left = dfs(start, i - 1);
            List<Integer> right = dfs(i + 1, end);
            for (int j = 0; j < left.size(); j++) {
                int a = left.get(j);
                for (int l = 0; l < right.size(); l++) {
                    int b = right.get(l);
                    int total = calculate(a, b, ops.get(i));
                    result.add(total);
                }
            }
        }
        return result;
    }

    //计算
    private int calculate(int a, int b, char ops) {
        if (ops == '+') {
            return a + b;
        }
        if (ops == '-') {
            return a - b;
        }
        return a * b;
    }
}
