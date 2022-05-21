package com.demo.algorithm.leetcode.interview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2022/5/21.
 * description : 面试题08.12. 八皇后
 *
 * 设计一种算法，打印N皇后在N × N棋盘上的各种摆法，其中每个皇后都不同行、不同列，也不在对角线上。
 * 这里的“对角线”指的是所有的对角线，不只是平分整个棋盘的那两条对角线。
 *
 * 示例:
 *  输入：4
 *  输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 *  解释: 4 皇后问题存在如下两个不同的解法。
 * [
 * [".Q..", // 解法 1
 *  "...Q",
 *  "Q...",
 *  "..Q."],
 *
 * ["..Q.", // 解法 2
 *  "Q...",
 *  "...Q",
 *  ".Q.."]
 * ]
 */
public class SolveNQueens {

    //使用广度优先遍历
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        if (n == 1) {
            List<String> items = new ArrayList<>();
            items.add("Q");
            result.add(items);
            return result;
        }
        if (n < 4) {
            return result;
        }
        List<List<Integer>> dates = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> items = new ArrayList<>();
            items.add(i);
            dates.add(items);
        }
        List<List<Integer>> next = new ArrayList<>();
        int step = 1;
        while (step < n) {
            step++;
            for (int i = 0; i < dates.size(); i++) {
                List<Integer> items = dates.get(i);
                for (int j = 0; j < n; j++) {
                    boolean check = true;
                    for (int l = 0; l < items.size(); l++) {
                        int pre = items.get(l);
                        if (pre == j) {
                            check = false;
                            break;
                        }
                        //检查对角线（step-1，j）与(l,pre)
                        if (Math.abs(step - 1 - l) == Math.abs(j - pre)) {
                            check = false;
                            break;
                        }
                    }
                    if (check) {
                        List<Integer> tem = new ArrayList<>(items);
                        tem.add(j);
                        next.add(tem);
                    }
                }
            }
            dates.clear();
            dates.addAll(next);
            next.clear();
        }
        //dates转换为result
        for (int i = 0; i < dates.size(); i++) {
            List<Integer> items = dates.get(i);
            List<String> ans = new ArrayList<>();
            for (int j = 0; j < items.size(); j++) {
                int cur = items.get(j);
                StringBuilder builder = new StringBuilder();
                for (int l = 0; l < n; l++) {
                    if (l == cur) {
                        builder.append("Q");
                    } else {
                        builder.append(".");
                    }
                }
                ans.add(builder.toString());
            }
            result.add(ans);
        }
        return result;
    }
}
