package com.demo.algorithm.leetcode.medium;

/**
 * create on 2022/10/9
 * @author chenglong
 * description : 括号的分数
 *
 * 给定一个平衡括号字符串S，按下述规则计算该字符串的分数：
 * () 得 1 分。
 * AB得A + B分，其中A和B是平衡括号字符串。
 * (A)得2 * A分，其中A是平衡括号字符串。
 *
 * 示例 1：
 * 输入： "()"
 * 输出： 1
 * 示例 2：
 *
 * 输入： "(())"
 * 输出： 2
 *
 * 示例3：
 * 输入： "()()"
 * 输出： 2
 *
 * 示例4：
 * 输入： "(()(()))"
 * 输出： 6
 *
 * 提示：
 * S是平衡括号字符串，且只含有(和)。
 * 2 <= S.length <= 50
 */
public class ScoreOfParentheses {

    public int scoreOfParentheses(String s) {
        return dfs(s.toCharArray(), 0, s.length() - 1);
    }

    private int dfs(char[] dates, int start, int end) {
        //当前只有一个括号
        if (end - start == 1) {
            return 1;
        }
        //判断当前结构是否为()+()
        int left = 0;
        int findIndex = -1;
        for (int i = start; i <= end; i++) {
            if (dates[i] == '(') {
                left++;
            } else {
                left--;
                if (left == 0) {
                    findIndex = i;
                    break;
                }
            }
        }
        if (findIndex == end) {
            //(())结构
            return 2 * dfs(dates, start + 1, end - 1);
        }
        return dfs(dates, start, findIndex) + dfs(dates, findIndex + 1, end);
    }
}
