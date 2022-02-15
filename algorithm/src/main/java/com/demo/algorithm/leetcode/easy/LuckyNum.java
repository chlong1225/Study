package com.demo.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2022/2/15
 * @author chenglong
 * description : 矩阵中的幸运数
 *
 * 给你一个m * n的矩阵，矩阵中的数字各不相同。请你按任意顺序返回矩阵中的所有幸运数。
 * 幸运数是指矩阵中满足同时下列两个条件的元素：
 * 在同一行的所有元素中最小
 * 在同一列的所有元素中最大
 *
 * 示例 1：
 * 输入：matrix = [[3,7,8],[9,11,13],[15,16,17]]
 * 输出：[15]
 * 解释：15 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
 *
 * 示例 2：
 * 输入：matrix = [[1,10,4,2],[9,3,8,7],[15,16,17,12]]
 * 输出：[12]
 * 解释：12 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
 *
 * 示例 3：
 * 输入：matrix = [[7,8],[1,2]]
 * 输出：[7]
 *
 * 提示：
 * m == mat.length
 * n == mat[i].length
 * 1 <= n, m <= 50
 * 1 <=matrix[i][j]<= 10^5
 * 矩阵中的所有元素都是不同的
 */
public class LuckyNum {

    public List<Integer> luckyNumbers(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            //1，在当前行中找出最小值的index
            int minIndex = 0;
            for (int j = 1; j < n; j++) {
                if (matrix[i][minIndex] > matrix[i][j]) {
                    minIndex = j;
                }
            }
            //2，获取minIndex列的最大值
            int maxIndex = 0;
            for (int j = 1; j < m; j++) {
                if (matrix[j][minIndex] > matrix[maxIndex][minIndex]) {
                    maxIndex = j;
                }
            }
            //3，判断行的最小值与列的最大值是否为同一个
            if (maxIndex == i) {
                result.add(matrix[maxIndex][minIndex]);
            }
        }
        return result;
    }
}
