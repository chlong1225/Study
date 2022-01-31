package com.demo.algorithm.leetcode.medium;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2022/1/29.
 * description : 地图中的最高点
 *
 * 给你一个大小为m x n的整数矩阵isWater，它代表了一个由陆地和水域单元格组成的地图。
 * 如果isWater[i][j] == 0，格子(i, j)是一个 陆地格子。
 * 如果isWater[i][j] == 1，格子(i, j)是一个 水域格子。
 * 你需要按照如下规则给每个单元格安排高度：
 * 每个格子的高度都必须是非负的。
 * 如果一个格子是是水域，那么它的高度必须为0。
 * 任意相邻的格子高度差至多为1。当两个格子在正东、南、西、北方向上相互紧挨着，就称它们为相邻的格子。（也就是说它们有一条公共边）
 * 找到一种安排高度的方案，使得矩阵中的最高高度值最大。
 * 请你返回一个大小为m x n的整数矩阵height，其中height[i][j]是格子(i, j)的高度。如果有多种解法，请返回任意一个。
 *
 * 示例 1：
 * 输入：isWater = [[0,1],[0,0]]
 * 输出：[[1,0],[2,1]]
 * 解释：上图展示了给各个格子安排的高度。
 * 蓝色格子是水域格，绿色格子是陆地格。
 *
 * 示例 2：
 * 输入：isWater = [[0,0,1],[1,0,0],[0,0,0]]
 * 输出：[[1,1,0],[0,1,1],[1,2,2]]
 * 解释：所有安排方案中，最高可行高度为 2 。
 * 任意安排方案中，只要最高高度为 2 且符合上述规则的，都为可行方案。
 *
 * 提示：
 * m == isWater.length
 * n == isWater[i].length
 * 1 <= m, n <= 1000
 * isWater[i][j]要么是0，要么是1。
 * 至少有1个水域格子。
 */
public class MapHeights {

    //采用广度优先遍历bfs
    @NonNull
    public int[][] highestPeak(@NonNull int[][] isWater) {
        int m = isWater.length;
        int n = isWater[0].length;
        int[][] heights = new int[m][n];
        List<int[]> datas = new ArrayList<>();
        //初始化高度并且获取水域格子
        int height = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isWater[i][j] == 1) {
                    datas.add(new int[]{i, j});
                } else {
                    heights[i][j] = -1;
                }
            }
        }
        List<int[]> next = new ArrayList<>();
        //分别对应当前位置的左上右下偏移
        int[][] offsets = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        while (!datas.isEmpty()) {
            height++;
            for (int i = 0; i < datas.size(); i++) {
                int[] points = datas.get(i);
                for (int j = 0; j < 4; j++) {
                    int nx = points[0] + offsets[j][0];
                    int ny = points[1] + offsets[j][1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                        //防止偏移越界
                        if (heights[nx][ny] == -1) {
                            heights[nx][ny] = height;
                            next.add(new int[]{nx, ny});
                        }
                    }
                }
            }
            datas.clear();
            datas.addAll(next);
            next.clear();
        }
        return heights;
    }
}
