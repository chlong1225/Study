package com.demo.algorithm.leetcode.interview;

/**
 * Created by chl on 2022/6/3.
 * description : 面试题17.23. 最大黑方阵
 *
 * 给定一个方阵，其中每个单元(像素)非黑即白。设计一个算法，找出4条边皆为黑色像素的最大子方阵。
 * 返回一个数组[r, c, size]，其中r,c分别代表子方阵左上角的行号和列号，size是子方阵的边长。
 * 若有多个满足条件的子方阵，返回r最小的，若r相同，返回c最小的子方阵。若无满足条件的子方阵，返回空数组。
 *
 * 示例 1:
 * 输入:
 * [
 *   [1,0,1],
 *   [0,0,1],
 *   [0,0,1]
 * ]
 * 输出: [1,0,2]
 * 解释: 输入中 0 代表黑色，1 代表白色，标粗的元素即为满足条件的最大子方阵
 *
 * 示例 2:
 * 输入:
 * [
 *   [0,1,1],
 *   [1,0,1],
 *   [1,1,0]
 * ]
 * 输出: [0,0,1]
 *
 * 提示：
 * matrix.length == matrix[0].length <= 200
 */
public class FindSquare {

    public int[] findSquare(int[][] matrix) {
        //1，处理异常的数据
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }
        int max = 0;
        int[] result = new int[3];
        int n = matrix.length;
        //2，遍历统计x方向，y方向的前缀和
        int[][] xSums = new int[n][n + 1];
        int[][] ySums = new int[n + 1][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                xSums[i][j + 1] = xSums[i][j] + matrix[i][j];
                ySums[i + 1][j] = ySums[i][j] + matrix[i][j];
            }

        }
        int total = 0;
        for (int i = 0; i < n; i++) {
            total += xSums[i][n];
        }
        if (total == n * n) {
            return new int[0];
        }
        //3，遍历查找最大子方阵
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    //此时起点：(i,j),size = 1
                    int start = Math.max(i, j);
                    int size = 1;
                    for (int k = n - start - 1; k >= 1; k--) {
                        //对角坐标(i+k,j+k)
                        //检查4条边
                        int left = ySums[i + k + 1][j] - ySums[i][j];
                        if (left > 0) {
                            continue;
                        }
                        int top = xSums[i][j + k + 1] - xSums[i][j];
                        if (top > 0) {
                            continue;
                        }
                        int right = ySums[i + k + 1][j + k] - ySums[i][j + k];
                        if (right > 0) {
                            continue;
                        }
                        int bottom = xSums[i + k][j + k + 1] - xSums[i + k][j];
                        if (bottom > 0) {
                            continue;
                        }
                        size = k + 1;
                        break;
                    }
                    //此时对应结果：(i,j,size)
                    if (size > max) {
                        result[0] = i;
                        result[1] = j;
                        result[2] = size;
                        max = size;
                    } else if (size == max) {
                        if (result[0] > i) {
                            result[0] = i;
                            result[1] = j;
                        } else if (result[0] == i) {
                            if (result[1] > j) {
                                result[1] = j;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
}
