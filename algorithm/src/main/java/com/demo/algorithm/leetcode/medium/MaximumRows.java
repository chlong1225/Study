package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2024/1/4
 * @author chenglong
 * description : 被列覆盖的最多行数
 *
 * 给你一个下标从0开始、大小为m x n的二进制矩阵matrix；另给你一个整数numSelect，表示你必须从matrix中选择的不同列的数量。
 * 如果一行中所有的1都被你选中的列所覆盖，则认为这一行被覆盖了。
 * 形式上，假设s = {c1, c2, ...., cnumSelect}是你选择的列的集合。对于矩阵中的某一行row，如果满足下述条件，则认为这一行被集合s覆盖：
 * 对于满足matrix[row][col]==1的每个单元格matrix[row][col]（0 <= col <= n - 1），col均存在于s中，或者
 * row中不存在值为1的单元格。
 * 你需要从矩阵中选出numSelect个列，使集合覆盖的行数最大化。
 * 返回一个整数，表示可以由 numSelect 列构成的集合覆盖的最大行数。
 *
 * 示例 1：
 * 输入：matrix = [[0,0,0],[1,0,1],[0,1,1],[0,0,1]], numSelect = 2
 * 输出：3
 * 解释：
 * 图示中显示了一种覆盖 3 行的可行办法。
 * 选择 s = {0, 2} 。
 * - 第 0 行被覆盖，因为其中没有出现 1 。
 * - 第 1 行被覆盖，因为值为 1 的两列（即 0 和 2）均存在于 s 中。
 * - 第 2 行未被覆盖，因为 matrix[2][1] == 1 但是 1 未存在于 s 中。
 * - 第 3 行被覆盖，因为 matrix[2][2] == 1 且 2 存在于 s 中。
 * 因此，可以覆盖 3 行。
 * 另外 s = {1, 2} 也可以覆盖 3 行，但可以证明无法覆盖更多行。
 *
 * 示例 2：
 * 输入：matrix = [[1],[0]], numSelect = 1
 * 输出：2
 * 解释：
 * 选择唯一的一列，两行都被覆盖了，因为整个矩阵都被覆盖了。
 * 所以我们返回 2 。
 *
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 12
 * matrix[i][j] 要么是 0 要么是 1
 * 1 <= numSelect <= n
 */
public class MaximumRows {

    public int maximumRows(int[][] matrix, int numSelect) {
        int m = matrix.length;
        int n = matrix[0].length;
        if (numSelect == n) {
            return m;
        }
        //1，使用位运算预处理当前列1的状态
        int[] marks = new int[n];
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < m; j++) {
                if (matrix[j][i] == 1) {
                    sum |= (1 << j);
                }
            }
            marks[i] = sum;
        }
        //2，选中numSelect列，未选中的列为：n-numSelect
        int numUnSelect = n - numSelect;
        List<Integer> paths = new ArrayList<>();
//        dfs(0,paths,numUnSelect,)
        return 0;
    }
}
