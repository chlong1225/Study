package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/12/15
 * @author chenglong
 * description : 矩形面积
 *
 * 给你二维平面上两个由直线构成且边与坐标轴平行/垂直的矩形，请你计算并返回两个矩形覆盖的总面积。
 * 每个矩形由其左下顶点和右上顶点坐标表示：
 * 第一个矩形由其左下顶点 (ax1, ay1) 和右上顶点 (ax2, ay2) 定义。
 * 第二个矩形由其左下顶点 (bx1, by1) 和右上顶点 (bx2, by2) 定义。
 *
 * 示例 1：
 * Rectangle Area
 * 输入：ax1 = -3, ay1 = 0, ax2 = 3, ay2 = 4, bx1 = 0, by1 = -1, bx2 = 9, by2 = 2
 * 输出：45
 *
 * 示例 2：
 * 输入：ax1 = -2, ay1 = -2, ax2 = 2, ay2 = 2, bx1 = -2, by1 = -2, bx2 = 2, by2 = 2
 * 输出：16
 *
 * 提示：
 * -10^4 <= ax1, ay1, ax2, ay2, bx1, by1, bx2, by2 <= 10^4
 */
public class ComputeArea {

    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        //1，计算不考虑重叠部分的总面积
        int sum = (ax2 - ax1) * (ay2 - ay1) + (bx2 - bx1) * (by2 - by1);
        //2，计算重叠部分的面积并减去
        if (Math.min(ax2, bx2) > Math.max(ax1, bx1)) {
            //此时x方向投影才会有重叠
            int spaceX = Math.min(ax2, bx2) - Math.max(ax1, bx1);
            if (Math.min(ay2, by2) > Math.max(ay1, by1)) {
                //此时y方向投影也有重叠
                int spaceY = Math.min(ay2, by2) - Math.max(ay1, by1);
                sum -= (spaceX * spaceY);
            }
        }
        return sum;
    }
}
