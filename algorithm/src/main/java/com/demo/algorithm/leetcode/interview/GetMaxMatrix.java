package com.demo.algorithm.leetcode.interview;

/**
 * Created by chl on 2022/6/3.
 * description : 面试题17.24. 最大子矩阵
 *
 * 给定一个正整数、负整数和0组成的N × M矩阵，编写代码找出元素总和最大的子矩阵。
 * 返回一个数组 [r1, c1, r2, c2]，其中 r1, c1 分别代表子矩阵左上角的行号和列号，r2, c2 分别代表右下角的行号和列号。若有多个满足条件的子矩阵，返回任意一个均可。
 *
 * 示例：
 * 输入：
 * [
 *   [-1,0],
 *   [0,-1]
 * ]
 * 输出：[0,1,0,1]
 * 解释：输入中标粗的元素即为输出所表示的矩阵
 *
 * 说明：
 * 1 <= matrix.length, matrix[0].length <= 200
 */
public class GetMaxMatrix {

    public int[] getMaxMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        //1，计算矩阵的前缀和
        int[][] sums = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                sums[i][j] = sums[i - 1][j] + sums[i][j - 1] - sums[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
        int max = Integer.MIN_VALUE;
        int[] result = new int[4];
        //2，遍历查找最大矩阵
        for (int top = 0; top < m; top++) {
            for (int bottom = top; bottom < m; bottom++) {
                //遍历计算i行到j行之间的最大值。此时高度方向确认：i~j。遍历宽度方向
                int left = 0;
                for (int right = 0; right < n; right++) {
                    int total = sums[bottom + 1][right + 1] + sums[top][left] - sums[bottom + 1][left] - sums[top][right + 1];
                    if (total > max) {
                        max = total;
                        result[0] = top;
                        result[1] = left;
                        result[2] = bottom;
                        result[3] = right;
                    }
                    if (total < 0) {
                        left = right + 1;
                    }
                }

            }
        }
        return result;
    }
}
