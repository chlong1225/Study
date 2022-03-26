package com.demo.algorithm.leetcode.offer;

/**
 * Created by chl on 2022/3/26.
 * description : 剑指Offer04. 二维数组中的查找
 *
 * 在一个n*m的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 * 示例:
 * 现有矩阵 matrix 如下：
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * 给定 target=5，返回true。
 *
 * 给定target=20，返回false。
 *
 * 限制：
 * 0 <= n <= 1000
 * 0 <= m <= 1000
 */
public class FindNumberIn2DArray {

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) {
            return false;
        }
        int n = matrix[0].length;
        if (n == 0) {
            return false;
        }
        for (int i = 0; i < m; i++) {
            //按照行进行判断
            int[] dates = matrix[i];
            if (dates[0] > target) {
                //当前行最小值大于targe，包含当前行之后的所有数据都会大于targe
                break;
            }
            if (findIndex(dates, target) != -1) {
                return true;
            }
        }
        return false;
    }

    private int findIndex(int[] dates, int target) {
        int start = 0;
        int end = dates.length - 1;
        if (dates[start] > target) {
            return -1;
        }
        if (dates[start] == target) {
            return start;
        }
        if (dates[end] < target) {
            return -1;
        }
        if (dates[end] == target) {
            return end;
        }
        while (start <= end) {
            int middle = (start + end) >> 1;
            if (target == dates[middle]) {
                return middle;
            }
            if (target > dates[middle]) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }
        return -1;
    }
}
