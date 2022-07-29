package com.demo.algorithm.leetcode.medium;

import java.util.Arrays;

/**
 * Created by chl on 2022/7/29.
 * description : 有效的正方形
 *
 * 给定2D空间中四个点的坐标p1,p2,p3和p4，如果这四个点构成一个正方形，则返回true 。
 * 点的坐标pi表示为[xi, yi]。输入不是按任何顺序给出的。
 * 一个 有效的正方形有四条等边和四个等角(90度角)。
 *
 * 示例 1:
 * 输入: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
 * 输出: True
 *
 * 示例 2:
 * 输入：p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,12]
 * 输出：false
 *
 * 示例 3:
 * 输入：p1 = [1,0], p2 = [-1,0], p3 = [0,1], p4 = [0,-1]
 * 输出：true
 *
 * 提示:
 * p1.length == p2.length == p3.length == p4.length == 2
 * -10^4<= xi, yi<= 10^4
 */
public class ValidSquare {

   public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
      int[][] dates = new int[4][2];
      dates[0] = p1;
      dates[1] = p2;
      dates[2] = p3;
      dates[3] = p4;
      Arrays.sort(dates, (o1, o2) -> {
         if (o1[0] == o2[0]) {
            return o1[1] - o2[1];
         }
         return o1[0] - o2[0];
      });
      //特殊场景，如果存在坐标相同，肯定无法构成正方形
      for (int i = 1; i < 4; i++) {
         if (dates[i][0] == dates[i - 1][0] && dates[i][1] == dates[i - 1][1]) {
            return false;
         }
      }
      //1，判断是否与x轴，y轴平行
      if (dates[0][0] == dates[1][0]) {
         if (dates[2][0] != dates[3][0]) {
            return false;
         }
         //边长
         int size = dates[2][0] - dates[0][0];
         if (dates[1][1] - dates[0][1] != size) {
            return false;
         }
         if (dates[3][1] - dates[2][1] != size) {
            return false;
         }
         return dates[0][1] == dates[2][1];
      }
      //2，此时正方形与坐标轴不平行
      long base = 1;
      long a1 = base * (dates[1][0] - dates[0][0]) * (dates[1][0] - dates[0][0]) + base * (dates[1][1] - dates[0][1]) * (dates[1][1] - dates[0][1]);
      long a2 = base * (dates[2][0] - dates[0][0]) * (dates[2][0] - dates[0][0]) + base * (dates[2][1] - dates[0][1]) * (dates[2][1] - dates[0][1]);
      if (a1 != a2) {
         return false;
      }
      long a3 = base * (dates[3][0] - dates[1][0]) * (dates[3][0] - dates[1][0]) + base * (dates[3][1] - dates[1][1]) * (dates[3][1] - dates[1][1]);
      if (a1 != a3) {
         return false;
      }
      long a4 = base * (dates[3][0] - dates[2][0]) * (dates[3][0] - dates[2][0]) + base * (dates[3][1] - dates[2][1]) * (dates[3][1] - dates[2][1]);
      if (a1 != a4) {
         return false;
      }
      //3，此时四条边相同，需要判断是否相邻两条边是否垂直
      long a5 = base * (dates[2][0] - dates[1][0]) * (dates[2][0] - dates[1][0]) + base * (dates[2][1] - dates[1][1]) * (dates[2][1] - dates[1][1]);
      return a5 == 2 * a1;
   }
}
