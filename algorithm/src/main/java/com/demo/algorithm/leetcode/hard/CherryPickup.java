package com.demo.algorithm.leetcode.hard;

/**
 * create on 2022/7/13
 * @author chenglong
 * description : 摘樱桃
 *
 * 一个N x N的网格(grid)代表了一块樱桃地，每个格子由以下三种数字的一种来表示：
 * 0 表示这个格子是空的，所以你可以穿过它。
 * 1 表示这个格子里装着一个樱桃，你可以摘到樱桃然后穿过它。
 * -1 表示这个格子里有荆棘，挡着你的路。
 * 你的任务是在遵守下列规则的情况下，尽可能的摘到最多樱桃：
 * 从位置(0, 0) 出发，最后到达(N-1, N-1)，只能向下或向右走，并且只能穿越有效的格子（即只可以穿过值为0或者1的格子）；
 * 当到达(N-1, N-1)后，你要继续走，直到返回到(0,0)，只能向上或向左走，并且只能穿越有效的格子；
 * 当你经过一个格子且这个格子包含一个樱桃时，你将摘到樱桃并且这个格子会变成空的（值变为0）；
 * 如果在(0, 0)和(N-1, N-1)之间不存在一条可经过的路径，则没有任何一个樱桃能被摘到。
 *
 * 示例 1:
 * 输入: grid =
 * [[0, 1, -1],
 *  [1, 0, -1],
 *  [1, 1,  1]]
 * 输出: 5
 * 解释：
 * 玩家从（0,0）点出发，经过了向下走，向下走，向右走，向右走，到达了点(2, 2)。
 * 在这趟单程中，总共摘到了4颗樱桃，矩阵变成了[[0,1,-1],[0,0,-1],[0,0,0]]。
 * 接着，这名玩家向左走，向上走，向上走，向左走，返回了起始点，又摘到了1颗樱桃。
 * 在旅程中，总共摘到了5颗樱桃，这是可以摘到的最大值了。
 * 说明:
 * grid是一个N * N 的二维数组，N的取值范围是1 <= N <= 50。
 * 每一个grid[i][j]都是集合{-1, 0, 1}其中的一个数。
 * 可以保证起点grid[0][0]和终点grid[N-1][N-1]的值都不会是-1。
 */
public class CherryPickup {

    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int[][][] marks = new int[2 * n - 1][n][n];
        //1，设置默认值，可能
        for (int i = 0; i < 2 * n - 1; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    marks[i][j][k] = Integer.MIN_VALUE;
                }
            }
        }
        marks[0][0][0] = grid[0][0];
        //2，动态规划
        for (int step = 1; step <= 2 * n - 2; step++) {
            int length = Math.min(step, n - 1);
            for (int i = 0; i <= length; i++) {
                int j = step - i;
                if (j >= n) {
                    continue;
                }
                //对应玩家a的位置(i,j)
                if (grid[i][j] == -1) {
                    continue;
                }
                for (int x = 0; x <= length; x++) {
                    int y = step - x;
                    if (y >= n) {
                        continue;
                    }
                    //玩家b的位置(x,y)
                    if (grid[x][y] == -1) {
                        continue;
                    }
                    //同时从上方转移过来
                    int count = marks[step - 1][i][x];
                    //同时从左边转移过来
                    if (i > 0 && x > 0) {
                        if (count < marks[step - 1][i - 1][x - 1]) {
                            count = marks[step - 1][i - 1][x - 1];
                        }
                    }
                    //玩家a从上方转移，b从做左边转移
                    if (step - 1 >= i && x > 0) {
                        if (count < marks[step - 1][i][x - 1]) {
                            count = marks[step - 1][i][x - 1];
                        }
                    }
                    //玩家a从左边转移，b从上方转移
                    if (i > 0 && step - 1 >= x) {
                        if (count < marks[step - 1][i - 1][x]) {
                            count = marks[step - 1][i - 1][x];
                        }
                    }
                    count += grid[i][step - i];
                    if (i != x) {
                        count += grid[x][step - x];
                    }
                    marks[step][i][x] = count;
                }
            }
        }
        return Math.max(marks[2 * n - 2][n - 1][n - 1], 0);
    }
}
