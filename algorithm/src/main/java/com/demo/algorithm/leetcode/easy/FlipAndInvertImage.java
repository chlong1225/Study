package com.demo.algorithm.leetcode.easy;

/**
 * create by chenglong on 2023/12/3
 * description : 翻转图像
 *
 * 给定一个n x n的二进制矩阵image，先水平翻转图像，然后反转图像并返回结果。
 * 水平翻转图片就是将图片的每一行都进行翻转，即逆序。
 * 例如，水平翻转 [1,1,0] 的结果是 [0,1,1]。
 * 反转图片的意思是图片中的0全部被1替换， 1全部被0替换。
 * 例如，反转 [0,1,1] 的结果是 [1,0,0]。
 *
 * 示例 1：
 * 输入：image = [[1,1,0],[1,0,1],[0,0,0]]
 * 输出：[[1,0,0],[0,1,0],[1,1,1]]
 * 解释：首先翻转每一行: [[0,1,1],[1,0,1],[0,0,0]]；
 *      然后反转图片: [[1,0,0],[0,1,0],[1,1,1]]
 *
 * 示例 2：
 * 输入：image = [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
 * 输出：[[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 * 解释：首先翻转每一行: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]]；
 *      然后反转图片: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 *
 * 提示：
 * n == image.length
 * n == image[i].length
 * 1 <= n <= 20
 * images[i][j] == 0 或 1.
 */
public class FlipAndInvertImage {

    public int[][] flipAndInvertImage(int[][] image) {
        int n = image.length;
        //1，水平翻转
        for (int i = 0; i < n; i++) {
            int count = n / 2;
            for (int j = 0; j < count; j++) {
                int tem = image[i][j];
                image[i][j] = image[i][n - 1 - j];
                image[i][n - 1 - j] = tem;
            }
        }
        //2，反转
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (image[i][j] == 0) {
                    image[i][j] = 1;
                } else {
                    image[i][j] = 0;
                }
            }
        }
        return image;
    }
}
