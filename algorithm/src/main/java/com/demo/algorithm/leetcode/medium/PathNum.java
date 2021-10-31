package com.demo.algorithm.leetcode.medium;

/**
 * Created by chl on 2021/10/31.
 * description : 不同路径
 *
 * 一个机器人位于一个 m x n网格的左上角 （起始点在下图中标记为 “Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 问总共有多少条不同的路径？
 *
 * 示例 1：
 * 输入：m = 3, n = 7
 * 输出：28
 *
 * 示例 2：
 * 输入：m = 3, n = 2
 * 输出：3
 * 解释：
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向下
 *
 * 示例 3：
 * 输入：m = 7, n = 3
 * 输出：28
 *
 * 示例 4：
 * 输入：m = 3, n = 3
 * 输出：6
 *
 * 提示：
 * 1 <= m, n <= 100
 * 题目数据保证答案小于等于 2 * 109
 */
public class PathNum {

    public static int uniquePaths(int m, int n) {
        //向右走的步数
        int right = m - 1;
        //向下走的步数
        int bottom = n - 1;
        if (right == 0 || bottom == 0) {
            return 1;
        }
        int select = Math.min(right, bottom);
        int count = right + bottom;
        //在count中select位置填入相同的值的排列数
        int start = count - select + 1;
        long sum = start;
        start++;
        while (start <= count || select > 1) {
            if (select > 1) {
                if (sum % select == 0) {
                    sum /= select;
                    select--;
                } else {
                    if (start <= count) {
                        sum *= start;
                        start++;
                    }
                }
            } else {
                if (start <= count) {
                    sum *= start;
                    start++;
                }
            }
        }
        return (int)sum;
    }

    //使用动态规划
    public static int uniquePaths2(int m, int n) {
        if (m == 1 || n == 1) {
            return 1;
        }
        int[][] result = new int[m][n];
        //1,边界条件
        for (int i = 0; i < m; i++) {
            result[i][0] = 1;
        }
        for (int i = 1; i < n; i++) {
            result[0][i] = 1;
        }
        //2,遍历
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                result[i][j] = result[i][j - 1] + result[i - 1][j];
            }
        }
        return result[m - 1][n - 1];
    }
}
