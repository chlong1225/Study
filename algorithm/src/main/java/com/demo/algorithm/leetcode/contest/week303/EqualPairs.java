package com.demo.algorithm.leetcode.contest.week303;

/**
 * create on 2022/8/1
 * @author chenglong
 * description : 相等行列对
 *
 * 给你一个下标从0开始、大小为 n x n 的整数矩阵grid ，返回满足Ri行和Cj列相等的行列对(Ri, Cj)的数目。
 * 如果行和列以相同的顺序包含相同的元素（即相等的数组），则认为二者是相等的。
 *
 * 示例 1：
 * 输入：grid = [[3,2,1],[1,7,6],[2,7,7]]
 * 输出：1
 * 解释：存在一对相等行列对：
 * - (第 2 行，第 1 列)：[2,7,7]
 *
 * 示例 2：
 * 输入：grid = [[3,1,2,2],[1,4,4,5],[2,4,2,2],[2,4,2,2]]
 * 输出：3
 * 解释：存在三对相等行列对：
 * - (第 0 行，第 0 列)：[3,1,2,2]
 * - (第 2 行, 第 2 列)：[2,4,2,2]
 * - (第 3 行, 第 2 列)：[2,4,2,2]
 *
 * 提示：
 * n == grid.length == grid[i].length
 * 1 <= n <= 200
 * 1 <= grid[i][j] <= 10^5
 */
public class EqualPairs {

    public int equalPairs(int[][] grid) {
        int n = grid.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            //用于对应的行数据
            int[] compare = grid[i];
            for (int j = 0; j < n; j++) {
                boolean isSame = true;
                for (int k = 0; k < n; k++) {
                    if (grid[k][j] != compare[k]) {
                        isSame = false;
                        break;
                    }
                }
                if (isSame) {
                    count++;
                }
            }
        }

        return count;
    }
}
