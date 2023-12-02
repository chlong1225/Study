package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/11/30
 * @author chenglong
 * description : 岛屿的周长
 *
 * 给定一个row x col的二维网格地图grid，其中：grid[i][j]=1表示陆地，grid[i][j]=0表示水域。
 * 网格中的格子水平和垂直方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
 * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为1的正方形。网格为长方形，且宽度和高度均不超过100。计算这个岛屿的周长。
 *
 * 示例 1：
 * 输入：grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
 * 输出：16
 * 解释：它的周长是上面图片中的16个黄色的边
 *
 * 示例 2：
 * 输入：grid = [[1]]
 * 输出：4
 *
 * 示例 3：
 * 输入：grid = [[1,0]]
 * 输出：4
 *
 * 提示：
 * row == grid.length
 * col == grid[i].length
 * 1 <= row, col <= 100
 * grid[i][j] 为 0 或 1
 */
public class LandPerimeter {

    private static final int[][] OFFSETS = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public int islandPerimeter(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    for (int k = 0; k < OFFSETS.length; k++) {
                        int nx = i + OFFSETS[k][0];
                        int ny = j + OFFSETS[k][1];
                        if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                            if (grid[nx][ny] == 0) {
                                count++;
                            }
                        } else {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }
}
