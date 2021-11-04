package com.demo.algorithm.leetcode.medium;

/**
 * create on 11/2/21
 *
 * @author chenglong
 * description : 搜索二维矩阵
 *
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 *  
 * 示例 1：
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * 输出：true
 *
 * 示例 2：
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * 输出：false
 *  
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -104 <= matrix[i][j], target <= 104
 */
public class SearchMatrix {

    public static boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        int column = matrix[0].length;
        //1，使用二分查找法定位在哪一行
        int min = 0;
        int max = row - 1;
        int line = 0;
        if (target < matrix[min][0]) {
            return false;
        }
        if (target == matrix[min][0]) {
            return true;
        }
        if (target == matrix[max][0]) {
            return true;
        }
        if (target > matrix[max][0]) {
            line = row - 1;
        } else {
            while (max - min > 1) {
                int middle = (max + min) / 2;
                if (target == matrix[middle][0]) {
                    return true;
                }
                if (target < matrix[middle][0]) {
                    max = middle;
                } else {
                    min = middle;
                }
            }
            line = min;
        }
        //2，在行中进行查找 matrix[line][0] ~ matrix[line][column]之间
        min = 0;
        max = column - 1;
        if (target > matrix[line][max]) {
            return false;
        }
        while (max >= min) {
            int middle = (max + min) / 2;
            if (target == matrix[line][middle]) {
                return true;
            }
            if (target < matrix[line][middle]) {
                max = middle - 1;
            } else {
                min = middle + 1;
            }
        }
        return false;
    }

}
