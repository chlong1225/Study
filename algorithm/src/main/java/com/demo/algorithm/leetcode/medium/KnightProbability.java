package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2022/2/17
 * @author chenglong
 * description : 骑士在棋盘上的概率
 *
 * 在一个n x n的国际象棋棋盘上，一个骑士从单元格(row, column)开始，并尝试进行k次移动。行和列是从0开始的，所以左上单元格是(0,0) ，右下单元格是(n-1, n-1) 。
 * 象棋骑士有8种可能的走法，如下图所示。每次移动在基本方向上是两个单元格，然后在正交方向上是一个单元格。
 * 每次骑士要移动时，它都会随机从8种可能的移动中选择一种(即使棋子会离开棋盘)，然后移动到那里。
 * 骑士继续移动，直到它走了k步或离开了棋盘。
 * 返回骑士在棋盘停止移动后仍留在棋盘上的概率
 *
 * 示例 1：
 * 输入: n = 3, k = 2, row = 0, column = 0
 * 输出: 0.0625
 * 解释: 有两步(到(1,2)，(2,1))可以让骑士留在棋盘上。
 * 在每一个位置上，也有两种移动可以让骑士留在棋盘上。
 * 骑士留在棋盘上的总概率是0.0625。
 *
 * 示例 2：
 * 输入: n = 1, k = 0, row = 0, column = 0
 * 输出: 1.00000
 *
 * 提示:
 * 1 <= n <= 25
 * 0 <= k <= 100
 * 0 <= row, column <= n
 */
public class KnightProbability {

    /**
     * 分别对应8个方向的偏移量
     */
    private int[][] offsets = {{-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}};

    public double knightProbability(int n, int k, int row, int column) {
        if (k == 0) {
            return 1;
        }
        //统计执行k次操作过程中，离开棋盘的次数
        int level = 0;
        //统计执行k次操作后，留在棋盘的次数
        int keep = 0;
        //记录执行操作后留在棋盘的位置
        List<int[]> positions = new ArrayList<>();
        positions.add(new int[]{row, column});
        List<int[]> next = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < positions.size(); j++) {
                int[] position = positions.get(j);
                for (int l = 0; l < 8; l++) {
                    int nx = position[0] + offsets[l][0];
                    int ny = position[1] + offsets[l][1];
                    if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                        //移动后还在棋盘上
                        next.add(new int[]{nx, ny});
                    } else {
                        //离开棋盘
                        level++;
                    }
                }
            }
            positions.clear();
            positions.addAll(next);
            next.clear();
            if (positions.isEmpty()) {
                break;
            }
        }
        keep = positions.size();
        return keep * 1.0d / (level + keep);
    }
}
