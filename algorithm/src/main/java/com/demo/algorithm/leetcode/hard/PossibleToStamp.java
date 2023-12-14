package com.demo.algorithm.leetcode.hard;

/**
 * create on 2023/12/14
 * @author chenglong
 * description : 用邮票贴满网格图
 *
 * 给你一个m x n的二进制矩阵grid，每个格子要么为0（空）要么为1（被占据）。
 * 给你邮票的尺寸为stampHeight x stampWidth。我们想将邮票贴进二进制矩阵中，且满足以下限制和要求：
 * 覆盖所有空格子。
 * 不覆盖任何被占据的格子。
 * 我们可以放入任意数目的邮票。
 * 邮票可以相互有重叠部分。
 * 邮票不允许旋转。
 * 邮票必须完全在矩阵内。
 * 如果在满足上述要求的前提下，可以放入邮票，请返回true，否则返回false。
 *
 * 示例 1：
 * 输入：grid = [[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0]], stampHeight = 4, stampWidth = 3
 * 输出：true
 * 解释：我们放入两个有重叠部分的邮票（图中标号为1和2），它们能覆盖所有与空格子。
 *
 * 示例 2：
 * 输入：grid = [[1,0,0,0],[0,1,0,0],[0,0,1,0],[0,0,0,1]], stampHeight = 2, stampWidth = 2
 * 输出：false
 * 解释：没办法放入邮票覆盖所有的空格子，且邮票不超出网格图以外。
 *
 * 提示：
 * m == grid.length
 * n == grid[r].length
 * 1 <= m, n <= 10^5
 * 1 <= m * n <= 2 * 105
 * grid[r][c]要么是0 ，要么是1。
 * 1 <= stampHeight, stampWidth <= 10^5
 */
public class PossibleToStamp {

    public boolean possibleToStamp(int[][] grid, int stampHeight, int stampWidth) {
        int m = grid.length;
        int n = grid[0].length;
        //用于统计空格的数量
        int count = 0;
        //统计矩阵的前缀和，便于快速判断空格数。这里记录空格为1，占有为0
        int[][] sums = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sums[i + 1][j + 1] = sums[i][j + 1] + sums[i + 1][j] - sums[i][j];
                if (grid[i][j] == 0) {
                    //当前位置为空格
                    count++;
                    sums[i + 1][j + 1]++;
                }
            }
        }
        if (count == 0) {
            //没有空格时不需要放置邮票，此时默认为true
            return true;
        }
        int stampSize = stampWidth * stampHeight;
        if (m < stampHeight || n < stampWidth || count < stampSize) {
            //邮票大小超过了空格区间，无法放置
            return false;
        }
        //使用二维差分数组统计邮票添加情况
        int[][] diff = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    //当前位置为空格，可以尝试填充邮票，(i,j)对应邮票的左上角。右下角为（i+stampHeight,j+stampWidth）
                    int ni = i + stampHeight;
                    int nj = j + stampWidth;
                    if (ni <= m && nj <= n) {
                        //放置超出矩阵
                        int emptyCount = sums[ni][nj] + sums[i][j] - sums[ni][j] - sums[i][nj];
                        if (emptyCount == stampSize) {
                            //当前位置可以放置邮票
                            diff[i][j]++;
                            diff[ni][nj]++;
                            diff[ni][j]--;
                            diff[i][nj]--;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    if (j > 0) {
                        diff[i][j] += diff[i][j - 1];
                    }
                } else {
                    if (j == 0) {
                        diff[i][j] += diff[i - 1][j];
                    } else {
                        diff[i][j] += diff[i][j - 1] + diff[i - 1][j] - diff[i - 1][j - 1];
                    }
                }
                if (grid[i][j] == 0 && diff[i][j] <= 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
