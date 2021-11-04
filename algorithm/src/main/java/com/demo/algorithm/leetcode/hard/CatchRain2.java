package com.demo.algorithm.leetcode.hard;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.PriorityQueue;

/**
 * Created by chl on 2021/11/3.
 * description : 接雨水II
 *
 * 给你一个 m x n 的矩阵，其中的值均为非负整数，代表二维高度图每个单元的高度，请计算图中形状最多能接多少体积的雨水。
 *
 * 示例 1:
 * 输入: heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
 * 输出: 4
 * 解释: 下雨后，雨水将会被上图蓝色的方块中。总的接雨水量为1+2+1=4。
 *
 * 示例 2:
 * 输入: heightMap = [[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3]]
 * 输出: 10
 *  
 * 提示:
 * m == heightMap.length
 * n == heightMap[i].length
 * 1 <= m, n <= 200
 * 0 <= heightMap[i][j] <= 2 * 104
 */
@RequiresApi(api = Build.VERSION_CODES.N)
public class CatchRain2 {

    public static int trapRainWater(int[][] heightMap) {
        int m = heightMap.length;
        int n = heightMap[0].length;
        if (m <= 2 || n <= 2) {
            return 0;
        }
        int sum = 0;
        boolean[][] marks = new boolean[m][n];
        //int[]对应位置x,y,高度height
        PriorityQueue<int[]> datas = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
        //1,添加四周无法灌水的位置
        for (int i = 0; i < m; i++) {
            datas.offer(new int[]{i, 0, heightMap[i][0]});
            marks[i][0] = true;
            datas.offer(new int[]{i, n - 1, heightMap[i][n - 1]});
            marks[i][n - 1] = true;
        }
        for (int i = 1; i < n - 1; i++) {
            datas.offer(new int[]{0, i, heightMap[0][i]});
            marks[0][i] = true;
            datas.offer(new int[]{m - 1, i, heightMap[m - 1][i]});
            marks[m - 1][i] = true;
        }
        //2,遍历检查最小位置四周是否可以灌水
        //分别对应左上右下四个位置
        int[][] dirs = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
        while (!datas.isEmpty()) {
            //获取已经确认的最小高度位置
            int[] poll = datas.poll();
            for (int i = 0; i < 4; i++) {
                int positionX = poll[0] + dirs[i][0];
                int positionY = poll[1] + dirs[i][1];
                if (positionX >= 0 && positionX < m && positionY >= 0 && positionY < n && !marks[positionX][positionY]) {
                    if (poll[2] > heightMap[positionX][positionY]) {
                        sum += (poll[2] - heightMap[positionX][positionY]);
                    }
                    datas.offer(new int[]{positionX, positionY, Math.max(poll[2], heightMap[positionX][positionY])});
                    marks[positionX][positionY] = true;
                }
            }
        }
        return sum;
    }

}
