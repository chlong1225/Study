package com.demo.algorithm.leetcode.easy;

/**
 * Created by chl on 2022/3/24.
 * description : 图片平滑器
 *
 * 图像平滑器是大小为3x3的过滤器，用于对图像的每个单元格平滑处理，平滑处理后单元格的值为该单元格的平均灰度。
 * 每个单元格的平均灰度定义为：该单元格自身及其周围的8个单元格的平均值，结果需向下取整。（即需要计算蓝色平滑器中9个单元格的平均值）。
 * 如果一个单元格周围存在单元格缺失的情况，则计算平均灰度时不考虑缺失的单元格（即，需要计算红色平滑器中4个单元格的平均值）。
 * 给你一个表示图像灰度的m x n整数矩阵img ，返回对图像的每个单元格平滑处理后的图像。
 *
 * 示例 1:
 * 输入:img = [[1,1,1],[1,0,1],[1,1,1]]
 * 输出:[[0, 0, 0],[0, 0, 0], [0, 0, 0]]
 * 解释:
 * 对于点 (0,0), (0,2), (2,0), (2,2): 平均(3/4) = 平均(0.75) = 0
 * 对于点 (0,1), (1,0), (1,2), (2,1): 平均(5/6) = 平均(0.83333333) = 0
 * 对于点 (1,1): 平均(8/9) = 平均(0.88888889) = 0
 *
 * 示例 2:
 * 输入: img = [[100,200,100],[200,50,200],[100,200,100]]
 * 输出: [[137,141,137],[141,138,141],[137,141,137]]
 * 解释:
 * 对于点 (0,0), (0,2), (2,0), (2,2): floor((100+200+200+50)/4) = floor(137.5) = 137
 * 对于点 (0,1), (1,0), (1,2), (2,1): floor((200+200+50+200+100+100)/6) = floor(141.666667) = 141
 * 对于点 (1,1): floor((50+200+200+200+200+100+100+100+100)/9) = floor(138.888889) = 138
 *
 * 提示:
 * m == img.length
 * n == img[i].length
 * 1 <= m, n <= 200
 * 0 <= img[i][j] <= 255
 */
public class ImageSmoother {

    //由于数据源较小。可以直接暴力枚举
    public int[][] imageSmoother(int[][] img) {
        int m = img.length;
        int n = img[0].length;
        int[][] result = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = getAverage(i, j, img);
            }
        }
        return result;
    }

    private int getAverage(int x, int y, int[][] img) {
        int m = img.length;
        int n = img[0].length;
        int startX = Math.max(0, x - 1);
        int endX = Math.min(x + 1, m - 1);
        int startY = Math.max(0, y - 1);
        int endY = Math.min(y + 1, n - 1);
        int sum = 0;
        for (int i = startX; i <= endX; i++) {
            for (int j = startY; j <= endY; j++) {
                sum += img[i][j];
            }
        }
        int count = (endX - startX + 1) * (endY - startY + 1);
        return sum / count;
    }

    //使用前缀的方式
    public int[][] imageSmoother2(int[][] img) {
        int m = img.length;
        int n = img[0].length;
        //预处理前缀和
        int[][] marks = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                marks[i][j] = marks[i - 1][j] + marks[i][j - 1] + img[i - 1][j - 1] - marks[i - 1][j - 1];
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int startX = Math.max(0, i - 1);
                int endX = Math.min(m - 1, i + 1);
                int startY = Math.max(0, j - 1);
                int endY = Math.min(n - 1, j + 1);
                int count = (endX - startX + 1) * (endY - startY + 1);
                int sum = marks[endX + 1][endY + 1] - marks[startX][endY + 1] - marks[endX + 1][startY] + marks[startX][startY];
                img[i][j] = sum / count;
            }
        }
        return img;
    }
}
