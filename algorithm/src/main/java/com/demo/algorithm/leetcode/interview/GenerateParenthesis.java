package com.demo.algorithm.leetcode.interview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2022/6/4.
 * description : 面试题08.09. 括号
 *
 * 括号。设计一种算法，打印n对括号的所有合法的（例如，开闭一一对应）组合。
 * 说明：解集不能包含重复的子集。
 *
 * 例如，给出n = 3，生成结果为：
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 */
public class GenerateParenthesis {

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        if (n == 0) {
            return result;
        }
        StringBuilder path = new StringBuilder();
        dfs(0, 0, n, path, result);
        return result;
    }

    private void dfs(int left, int right, int n, StringBuilder path, List<String> result) {
        if (left > n || right > n || left < right) {
            return;
        }
        if (left == right && left == n) {
            result.add(path.toString());
        }
        path.append("(");
        dfs(left + 1, right, n, path, result);
        path.deleteCharAt(path.length() - 1);
        path.append(")");
        dfs(left, right + 1, n, path, result);
        path.deleteCharAt(path.length() - 1);
    }
}
