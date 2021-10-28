package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2021/10/27.
 * description : 螺旋矩阵
 *
 * 给你一个m行n列的矩阵matrix，请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 *
 * 示例 1：
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 *
 * 示例 2：
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *  
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 */
public class SpiralMatrix {

    public static List<Integer> spiralOrder(int[][] matrix) {
        //行数
        int m = matrix.length;
        //列数
        int n = matrix[0].length;
        //矩阵元素总数
        int maxCount = m * n;
        List<Integer> result = new ArrayList<>();
        //旋转圈数并且需要错位
        int count = (Math.min(m, n) + 1) / 2;
        for (int i = 0; i < count; i++) {
            //遍历上方
            int x = i;
            int y = i;
            int limit = n - i;
            for (int j = x; j < limit; j++) {
                result.add(matrix[y][j]);
            }
            if (result.size() == maxCount) {
                break;
            }
            //遍历右边
            x = limit - 1;
            y++;
            limit = m - i;
            for (int j = y; j < limit; j++) {
                result.add(matrix[j][x]);
            }
            if (result.size() == maxCount) {
                break;
            }
            //遍历下方
            x--;
            y = limit - 1;
            limit = i;
            for (int j = x; j >= limit; j--) {
                result.add(matrix[y][j]);
            }
            if (result.size() == maxCount) {
                break;
            }
            //遍历左边
            x = limit;
            y--;
            limit = i + 1;
            for (int j = y; j >= limit; j--) {
                result.add(matrix[j][x]);
            }
        }
        return result;
    }

    public static List<Integer> spiralOrder2(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int bottom = matrix.length - 1;
        int count = matrix.length * matrix[0].length;
        while (left <= right && top <= bottom) {
            //从左到右遍历
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            if (result.size() == count) {
                break;
            }
            //从上到下遍历
            for (int i = top + 1; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            if (result.size() == count) {
                break;
            }
            //从右到左遍历
            for (int i = right - 1; i >= left; i--) {
                result.add(matrix[bottom][i]);
            }
            if (result.size() == count) {
                break;
            }
            //从下到上遍历
            for (int i = bottom - 1; i >= top + 1; i--) {
                result.add(matrix[i][left]);
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return result;
    }
}
