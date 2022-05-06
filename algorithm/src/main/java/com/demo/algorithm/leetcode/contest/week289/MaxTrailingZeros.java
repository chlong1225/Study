package com.demo.algorithm.leetcode.contest.week289;

/**
 * Created by chl on 2022/5/6.
 * description : 转角路径的乘积中最多能有几个尾随零
 *
 * 给你一个二维整数数组grid，大小为m x n，其中每个单元格都含一个正整数。
 * 转角路径定义为：包含至多一个弯的一组相邻单元。具体而言，路径应该完全向水平方向或者向竖直方向移动过弯（如果存在弯），而不能访问之前访问过的单元格。
 * 在过弯之后，路径应当完全朝另一个方向行进：如果之前是向水平方向，那么就应该变为向竖直方向；反之亦然。当然，同样不能访问之前已经访问过的单元格。
 * 一条路径的乘积定义为：路径上所有值的乘积。
 *
 * 请你从 grid 中找出一条乘积中尾随零数目最多的转角路径，并返回该路径中尾随零的数目。
 *
 * 注意：
 * 水平 移动是指向左或右移动。
 * 竖直 移动是指向上或下移动。
 *
 * 示例 1：
 * 输入：grid = [[23,17,15,3,20],[8,1,20,27,11],[9,4,6,2,21],[40,9,1,10,6],[22,7,4,5,3]]
 * 输出：3
 * 解释：左侧的图展示了一条有效的转角路径。
 * 其乘积为 15 * 20 * 6 * 1 * 10 = 18000 ，共计 3 个尾随零。
 * 可以证明在这条转角路径的乘积中尾随零数目最多。
 * 中间的图不是一条有效的转角路径，因为它有不止一个弯。
 * 右侧的图也不是一条有效的转角路径，因为它需要重复访问已经访问过的单元格。
 *
 * 示例 2：
 * 输入：grid = [[4,3,2],[7,6,1],[8,8,8]]
 * 输出：0
 * 解释：网格如上图所示。
 * 不存在乘积含尾随零的转角路径。
 *
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 10^5
 * 1 <= m * n <= 10^5
 * 1 <= grid[i][j] <= 1000
 */
public class MaxTrailingZeros {

    private static final int MOD = 10;
    private static final long BIG_MOD = 1000001;

    public int maxTrailingZeros(int[][] grid) {
        /**
         * 分析：grid[i][j]最大值为1000相当于转换后3个5中，最多转换9个。直接可以使用10进制记录2和5的个数
         */
        //1，预处理数据，记录当前数字可以转换为2,5的数量
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int num = grid[i][j];
                int count2 = 0;
                int count5 = 0;
                while (num % 2 == 0) {
                    count2++;
                    num /= 2;
                }
                while (num % 5 == 0) {
                    count5++;
                    num /= 5;
                }
                grid[i][j] = count5 * MOD + count2;
            }
        }
        //2，使用前缀和统计水平方向的数量
        long[][] horizontalMarks = new long[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0) {
                    horizontalMarks[i][j] = BIG_MOD * (grid[i][j] / MOD) + grid[i][j] % MOD;
                } else {
                    long count2 = grid[i][j] % MOD + horizontalMarks[i][j - 1] % BIG_MOD;
                    long count5 = grid[i][j] / MOD + horizontalMarks[i][j - 1] / BIG_MOD;
                    horizontalMarks[i][j] = count5 * BIG_MOD + count2;
                }
            }
        }
        //3，使用前缀和统计垂直方向的数量
        long[][] verticalMarks = new long[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (j == 0) {
                    verticalMarks[j][i] = BIG_MOD * (grid[j][i] / MOD) + grid[j][i] % MOD;
                } else {
                    long count2 = grid[j][i] % MOD + verticalMarks[j - 1][i] % BIG_MOD;
                    long count5 = grid[j][i] / MOD + verticalMarks[j - 1][i] / BIG_MOD;
                    verticalMarks[j][i] = BIG_MOD * count5 + count2;
                }
            }
        }
        //3，枚举转折点
        long max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //当前转折点位置(i,j)
                int count2 = grid[i][j] % MOD;
                int count5 = grid[i][j] / MOD;
                long count2Left = (j == 0 ? 0 : horizontalMarks[i][j - 1] % BIG_MOD);
                long count5Left = (j == 0 ? 0 : horizontalMarks[i][j - 1] / BIG_MOD);
                long count2right = (j == n - 1 ? 0 : horizontalMarks[i][n - 1] % BIG_MOD - horizontalMarks[i][j] % BIG_MOD);
                long count5right = (j == n - 1 ? 0 : horizontalMarks[i][n - 1] / BIG_MOD - horizontalMarks[i][j] / BIG_MOD);
                long count2top = (i == 0 ? 0 : verticalMarks[i - 1][j] % BIG_MOD);
                long count5top = (i == 0 ? 0 : verticalMarks[i - 1][j] / BIG_MOD);
                long count2bottom = (i == m - 1 ? 0 : verticalMarks[m - 1][j] % BIG_MOD - verticalMarks[i][j] % BIG_MOD);
                long count5bottom = (i == m - 1 ? 0 : verticalMarks[m - 1][j] / BIG_MOD - verticalMarks[i][j] / BIG_MOD);
                //转折方向：左下方
                long zero = Math.min(count2Left + count2bottom + count2, count5Left + count5bottom + count5);
                if (zero > max) {
                    max = zero;
                }
                //左上方
                zero = Math.min(count2Left + count2top + count2, count5Left + count5top + count5);
                if (zero > max) {
                    max = zero;
                }
                //右上方
                zero = Math.min(count2right + count2top + count2, count5right + count5top + count5);
                if (zero > max) {
                    max = zero;
                }
                //右下方
                zero = Math.min(count2right + count2bottom + count2, count5right + count5bottom + count5);
                if (zero > max) {
                    max = zero;
                }
            }
        }
        return (int) max;
    }
}
