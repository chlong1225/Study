package com.demo.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2023/12/1
 * @author chenglong
 * description : 图像渲染
 *
 * 有一幅以m x n的二维整数数组表示的图画image，其中image[i][j]表示该图画的像素值大小。
 * 你也被给予三个整数sr,sc和newColor。你应该从像素image[sr][sc]开始对图像进行上色填充 。
 * 为了完成上色工作，从初始像素开始，记录初始坐标的上下左右四个方向上像素值与初始坐标相同的相连像素点，
 * 接着再记录这四个方向上符合条件的像素点与他们对应四个方向上像素值与初始坐标相同的相连像素点，……，重复该过程。将所有有记录的像素点的颜色值改为newColor。
 * 最后返回经过上色渲染后的图像 。
 *
 * 示例 1:
 * 输入: image = [[1,1,1],[1,1,0],[1,0,1]]，sr = 1, sc = 1, newColor = 2
 * 输出: [[2,2,2],[2,2,0],[2,0,1]]
 * 解析: 在图像的正中间，(坐标(sr,sc)=(1,1)),在路径上所有符合条件的像素点的颜色都被更改成2。
 * 注意，右下角的像素没有更改为2，因为它不是在上下左右四个方向上与初始点相连的像素点。
 *
 * 示例 2:
 * 输入: image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, newColor = 2
 * 输出: [[2,2,2],[2,2,2]]
 *
 * 提示:
 * m == image.length
 * n == image[i].length
 * 1 <= m, n <= 50
 * 0 <= image[i][j], newColor < 216
 * 0 <= sr < m
 * 0 <= sc < n
 */
public class FloodFill {

    private static final int[][] offsets = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int compare = image[sr][sc];
        if (compare == color) {
            return image;
        }
        int m = image.length;
        int n = image[0].length;
        List<int[]> curs = new ArrayList<>();
        curs.add(new int[]{sr, sc});
        image[sr][sc] = color;
        List<int[]> nexts = new ArrayList<>();
        while (curs.size() > 0) {
            for (int i = 0; i < curs.size(); i++) {
                int x = curs.get(i)[0];
                int y = curs.get(i)[1];
                for (int j = 0; j < offsets.length; j++) {
                    int nx = x + offsets[j][0];
                    int ny = y + offsets[j][1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                        if (image[nx][ny] == compare) {
                            nexts.add(new int[]{nx, ny});
                            image[nx][ny] = color;
                        }
                    }
                }
            }
            curs.clear();
            curs.addAll(nexts);
            nexts.clear();
        }
        return image;
    }
}
