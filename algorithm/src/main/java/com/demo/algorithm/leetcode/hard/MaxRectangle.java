package com.demo.algorithm.leetcode.hard;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by chl on 2021/10/22.
 * description : 最大矩形
 *
 * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 *
 * 示例 1：
 * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * 输出：6
 * 解释：最大矩形如上图所示。
 *
 * 示例 2：
 * 输入：matrix = []
 * 输出：0
 *
 * 示例 3：
 * 输入：matrix = [["0"]]
 * 输出：0
 *
 * 示例 4：
 * 输入：matrix = [["1"]]
 * 输出：1
 *
 * 示例 5：
 * 输入：matrix = [["0","0"]]
 * 输出：0
 *  
 * 提示：
 * rows == matrix.length
 * cols == matrix[0].length
 * 0 <= row, cols <= 200
 * matrix[i][j] 为 '0' 或 '1'
 */
public class MaxRectangle {

    //以顶点为基准进行遍历
    public static int maximalRectangle(char[][] matrix) {
        //1,特殊条件的处理
        if (matrix == null) {
            return 0;
        }
        //行数
        int m = matrix.length;
        if (m == 0) {
            return 0;
        }
        //列数
        int n = matrix[0].length;
        if (n == 0) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    //获取顶点最大的矩形
                    int result = getMaxRectangleByPoint(i, j, matrix, max);
                    if (result > max) {
                        max = result;
                    }
                }
            }
        }
        return max;
    }

    //以边为基准进行遍历
    public static int maximalRectangle2(char[][] matrix) {
        //1,特殊条件的处理
        if (matrix == null) {
            return 0;
        }
        //行数
        int m = matrix.length;
        if (m == 0) {
            return 0;
        }
        //列数
        int n = matrix[0].length;
        if (n == 0) {
            return 0;
        }
        //1,初始化第一列为基准的高度,收尾个添加0
        int[] data = new int[m + 2];
        for (int i = 0; i < m; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    count++;
                } else {
                    break;
                }
            }
            data[i + 1] = count;
        }
        int max = getMaxRectangleByHeight(data);
        for (int i = 1; i < n; i++) {
            updateHeights(data, matrix, i);
            int result = getMaxRectangleByHeight(data);
            if (result > max) {
                max = result;
            }
        }
        return max;
    }

    //更新高度信息
    private static void updateHeights(int[] data, char[][] matrix, int row) {
        int length = data.length;
        int n = matrix[0].length;
        for (int i = 1; i < length - 1; i++) {
            data[i]--;
            if (data[i] < 0) {
                //重新计算高度
                int count = 0;
                for (int j = row; j < n; j++) {
                    if (matrix[i - 1][j] == '1') {
                        count++;
                    } else {
                        break;
                    }
                }
                data[i] = count;
            }
        }
    }

    //以边为基准,获取高度计算最大面积
    private static int getMaxRectangleByHeight(int[] heights) {
        int lenght = heights.length;
        Deque<Integer> stack = new ArrayDeque<Integer>();
        stack.addLast(0);
        int max = 0;
        for (int i = 1; i < lenght; i++) {
            while (heights[i] < heights[stack.getLast()]) {
                int height = heights[stack.pollLast()];
                int are = height * (i - stack.getLast() - 1);
                if (are > max) {
                    max = are;
                }
            }
            if (heights[i] == heights[stack.getLast()]) {
                stack.pollLast();
            }
            stack.addLast(i);
        }
        return max;
    }

    private static int getMaxRectangleByPoint(int i, int j, char[][] matrix, int max) {
        int m = matrix.length;
        int n = matrix[0].length;
        int min = n;
        for (int k = i; k < m; k++) {
            int count = 0;
            for (int l = j; l < n; l++) {
                if (matrix[k][l] == '1') {
                    count++;
                    if (count >= min) {
                        break;
                    }
                } else {
                    break;
                }
            }
            if (count == 0) {
               break;
            }
            min = count;
            int are = (k - i + 1) * min;
            if (are > max) {
                max = are;
            }
            if (min * (m - i) <= max) {
                return max;
            }
        }
        return max;
    }
}
