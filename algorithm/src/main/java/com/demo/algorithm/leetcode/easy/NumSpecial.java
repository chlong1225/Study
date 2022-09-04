package com.demo.algorithm.leetcode.easy;

/**
 * Created by chl on 2022/9/4.
 * description : 二进制矩阵中的特殊位置
 *
 * 给你一个大小为rows x cols的矩阵mat，其中mat[i][j]是0或1，请返回矩阵mat中特殊位置的数目。
 * 特殊位置定义：如果mat[i][j]==1并且第i行和第j列中的所有其他元素均为0（行和列的下标均 从 0 开始 ），则位置 (i, j) 被称为特殊位置。
 *
 * 示例 1：
 * 输入：mat = [[1,0,0],
 *            [0,0,1],
 *            [1,0,0]]
 * 输出：1
 * 解释：(1,2) 是一个特殊位置，因为 mat[1][2] == 1 且所处的行和列上所有其他元素都是 0
 *
 * 示例 2：
 * 输入：mat = [[1,0,0],
 *            [0,1,0],
 *            [0,0,1]]
 * 输出：3
 * 解释：(0,0), (1,1) 和 (2,2) 都是特殊位置
 *
 * 示例 3：
 * 输入：mat = [[0,0,0,1],
 *            [1,0,0,0],
 *            [0,1,1,0],
 *            [0,0,0,0]]
 * 输出：2
 *
 * 示例 4：
 * 输入：mat = [[0,0,0,0,0],
 *            [1,0,0,0,0],
 *            [0,1,0,0,0],
 *            [0,0,1,0,0],
 *            [0,0,0,1,1]]
 * 输出：3
 *
 * 提示：
 * rows == mat.length
 * cols == mat[i].length
 * 1 <= rows, cols <= 100
 * mat[i][j] 是 0 或 1
 */
public class NumSpecial {

   public int numSpecial(int[][] mat) {
      int m = mat.length;
      int n = mat[0].length;
      int count = 0;
      for (int i = 0; i < m; i++) {
         int index = -1;
         for (int j = 0; j < n; j++) {
            if (mat[i][j] == 1) {
               if (index == -1) {
                  index = j;
               } else {
                  index = -1;
                  break;
               }
            }
         }
         if (index != -1) {
            //当前行只有一个1，位置为(i,j)。此时需要判断j列其它位置是否有1
            boolean isFind = false;
            for (int j = 0; j < m; j++) {
               if (j == i) {
                  continue;
               }
               if (mat[j][index] == 1) {
                  isFind = true;
                  break;
               }
            }
            if (!isFind) {
               count++;
            }
         }
      }
      return count;
   }
}
