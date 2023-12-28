package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/12/28
 * @author chenglong
 * description : 找出缺失和重复的数字
 *
 * 给你一个下标从0开始的二维整数矩阵grid，大小为n * n，其中的值在[1, n^2] 范围内。除了a出现两次，b缺失之外，每个整数都恰好出现一次。
 * 任务是找出重复的数字a和缺失的数字b。
 * 返回一个下标从0开始、长度为2的整数数组ans，其中ans[0]等于a，ans[1]等于b。
 *
 * 示例 1：
 * 输入：grid = [[1,3],[2,2]]
 * 输出：[2,4]
 * 解释：数字 2 重复，数字 4 缺失，所以答案是 [2,4] 。
 *
 * 示例 2：
 * 输入：grid = [[9,1,7],[8,9,2],[3,4,6]]
 * 输出：[9,5]
 * 解释：数字 9 重复，数字 5 缺失，所以答案是 [9,5] 。
 *
 * 提示：
 * 2 <= n == grid.length == grid[i].length <= 50
 * 1 <= grid[i][j] <= n * n
 * 对于所有满足1 <= x <= n * n 的 x ，恰好存在一个 x 与矩阵中的任何成员都不相等。
 * 对于所有满足1 <= x <= n * n 的 x ，恰好存在一个 x 与矩阵中的两个成员相等。
 * 除上述的两个之外，对于所有满足1 <= x <= n * n 的 x ，都恰好存在一对 i, j 满足 0 <= i, j <= n - 1 且 grid[i][j] == x 。
 */
public class FindMissingAndRepeatedValues {

    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int target = i * n + j + 1;
                while (grid[i][j] != target) {
                    //此时进行交换(i,j) -> (x,y)
                    int x = (grid[i][j] - 1) / n;
                    int y = (grid[i][j] - 1) % n;
                    if (grid[i][j] == grid[x][y]) {
                        //此时重复数字为：grid[i][j]
                        break;
                    }
                    int tem = grid[x][y];
                    grid[x][y] = grid[i][j];
                    grid[i][j] = tem;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int target = i * n + j + 1;
                if (target != grid[i][j]) {
                    return new int[]{grid[i][j],target};
                }
            }
        }
        return null;
    }
}
