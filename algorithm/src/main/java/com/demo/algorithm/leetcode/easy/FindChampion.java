package com.demo.algorithm.leetcode.easy;

/**
 * create on 2024/4/13
 * @author chenglong
 * description : 找到冠军I
 *
 * 一场比赛中共有n支队伍，按从0到n-1编号。
 * 给你一个下标从0开始、大小为n*n的二维布尔矩阵grid。对于满足0 <= i, j <= n-1 且 i!=j的所有i,j：如果grid[i][j]==1，那么i队比j队强；否则，j队比i队强。
 * 在这场比赛中，如果不存在某支强于a队的队伍，则认为a队将会是冠军。
 * 返回这场比赛中将会成为冠军的队伍。
 *
 * 示例 1：
 * 输入：grid = [[0,1],[0,0]]
 * 输出：0
 * 解释：比赛中有两支队伍。
 * grid[0][1] == 1 表示 0 队比 1 队强。所以 0 队是冠军。
 *
 * 示例 2：
 * 输入：grid = [[0,0,1],[1,0,1],[0,0,0]]
 * 输出：1
 * 解释：比赛中有三支队伍。
 * grid[1][0] == 1 表示 1 队比 0 队强。
 * grid[1][2] == 1 表示 1 队比 2 队强。
 * 所以 1 队是冠军。
 *
 * 提示：
 * n == grid.length
 * n == grid[i].length
 * 2 <= n <= 100
 * grid[i][j] 的值为 0 或 1
 * 对于所有i， grid[i][i]等于0.
 * 对于满足i!=j的所有i,j，grid[i][j] != grid[j][i] 均成立
 * 生成的输入满足：如果a队比b队强，b队比c队强，那么a队比c队强
 */
public class FindChampion {

    public int findChampion(int[][] grid) {
        int n = grid.length;
        //统计当前队是否有比它强的队
        boolean[] hasStrong = new boolean[n];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (grid[i][j] == 1) {
                    //此时i队比j队强
                    hasStrong[j] = true;
                } else {
                    //此时j队比i队强
                    hasStrong[i] = true;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (!hasStrong[i]) {
                return i;
            }
        }
        return 0;
    }
}
