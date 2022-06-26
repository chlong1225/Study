package com.demo.algorithm.leetcode.offer2;

/**
 * Created by chl on 2022/6/25.
 * description : 剑指OfferII 091. 粉刷房子
 *
 * 假如有一排房子共n个，每个房子可以被粉刷成红色、蓝色或者绿色这三种颜色中的一种，你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。
 * 当然，因为市场上不同颜色油漆的价格不同，所以房子粉刷成不同颜色的花费成本也是不同的。每个房子粉刷成不同颜色的花费是以一个n x 3的正整数矩阵costs来表示的。
 * 例如，costs[0][0]表示第0号房子粉刷成红色的成本花费；costs[1][2]表示第1号房子粉刷成绿色的花费，以此类推。
 * 请计算出粉刷完所有房子最少的花费成本。
 *
 * 示例 1：
 * 输入: costs = [[17,2,17],[16,16,5],[14,3,19]]
 * 输出: 10
 * 解释: 将 0 号房子粉刷成蓝色，1 号房子粉刷成绿色，2 号房子粉刷成蓝色。
 * 最少花费: 2 + 5 + 3 = 10。
 *
 * 示例 2：
 * 输入: costs = [[7,6,2]]
 * 输出: 2
 *
 * 提示:
 * costs.length == n
 * costs[i].length == 3
 * 1 <= n <= 100
 * 1 <= costs[i][j] <= 20
 */
public class MinCost {

   public int minCost(int[][] costs) {
      int n = costs.length;
      int preRed = 0;
      int preBlue = 0;
      int preGreen = 0;
      int red = 0;
      int blue = 0;
      int green = 0;
      for (int i = 0; i < n; i++) {
         red = Math.min(preBlue, preGreen) + costs[i][0];
         blue = Math.min(preRed, preGreen) + costs[i][1];
         green = Math.min(preRed, preBlue) + costs[i][2];
         preRed = red;
         preBlue = blue;
         preGreen = green;
      }
      return Math.min(red, Math.min(blue, green));
   }
}
