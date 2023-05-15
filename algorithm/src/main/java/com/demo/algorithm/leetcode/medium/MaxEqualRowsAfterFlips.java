package com.demo.algorithm.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * create on 2023/5/15
 * @author chenglong
 * description : 按列翻转得到最大值等行数
 *
 * 给定m x n矩阵matrix。
 * 你可以从中选出任意数量的列并翻转其上的每个单元格。（即翻转后，单元格的值从0变成1，或者从1变为0。）
 * 返回经过一些翻转后，行与行之间所有值都相等的最大行数。
 *
 * 示例 1：
 * 输入：matrix = [[0,1],[1,1]]
 * 输出：1
 * 解释：不进行翻转，有 1 行所有值都相等。
 *
 * 示例 2：
 * 输入：matrix = [[0,1],[1,0]]
 * 输出：2
 * 解释：翻转第一列的值之后，这两行都由相等的值组成。
 *
 * 示例 3：
 * 输入：matrix = [[0,0,0],[0,0,1],[1,1,0]]
 * 输出：2
 * 解释：翻转前两列的值之后，后两行由相等的值组成。
 *
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 300
 * matrix[i][j] == 0或1
 */
public class MaxEqualRowsAfterFlips {

    public int maxEqualRowsAfterFlips(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        Map<String, Integer> marks = new HashMap<>();
        for (int i = 0; i < m; i++) {
            boolean isChange = matrix[i][0] == 1;
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (isChange) {
                    builder.append((matrix[i][j] + 1) % 2);
                } else {
                    builder.append(matrix[i][j]);
                }
            }
            String date = builder.toString();
            if (marks.containsKey(date)) {
                marks.put(date, marks.get(date) + 1);
            } else {
                marks.put(date, 1);
            }
        }
        int max = 0;
        for (String key : marks.keySet()) {
            if (marks.get(key) > max) {
                max = marks.get(key);
            }
        }
        return max;
    }
}
