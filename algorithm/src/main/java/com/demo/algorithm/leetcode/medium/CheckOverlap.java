package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/6/25
 * @author chenglong
 * description : 圆和矩形是否有重叠
 *
 * 给你一个以(radius, xCenter, yCenter)表示的圆和一个与坐标轴平行的矩形(x1, y1, x2, y2)，其中(x1, y1)是矩形左下角的坐标，而(x2, y2)是右上角的坐标。
 * 如果圆和矩形有重叠的部分，请你返回true，否则返回false。
 * 换句话说，请你检测是否 存在点(xi, yi)，它既在圆上也在矩形上（两者都包括点落在边界上的情况）。
 *
 * 示例 1 ：
 * 输入：radius = 1, xCenter = 0, yCenter = 0, x1 = 1, y1 = -1, x2 = 3, y2 = 1
 * 输出：true
 * 解释：圆和矩形存在公共点 (1,0) 。
 *
 * 示例 2 ：
 * 输入：radius = 1, xCenter = 1, yCenter = 1, x1 = 1, y1 = -3, x2 = 2, y2 = -1
 * 输出：false
 *
 * 示例 3 ：
 * 输入：radius = 1, xCenter = 0, yCenter = 0, x1 = -1, y1 = 0, x2 = 0, y2 = 1
 * 输出：true
 *
 * 提示：
 * 1 <= radius <= 2000
 * -104 <= xCenter, yCenter <= 10^4
 * -104 <= x1 < x2 <= 10^4
 * -104 <= y1 < y2 <= 10^4
 */
public class CheckOverlap {

    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        //1，圆心在矩形内部
        if (xCenter >= x1 && xCenter <= x2 && yCenter >= y1 && yCenter <= y2) {
            return true;
        }
        //2，圆心在矩形上方
        if (xCenter >= x1 && xCenter <= x2 && yCenter - radius <= y2 && yCenter >= y2) {
            return true;
        }
        //3，圆心在矩形下方
        if (xCenter >= x1 && xCenter <= x2 && yCenter + radius >= y1 && yCenter <= y1) {
            return true;
        }
        //4，圆心在矩形左边
        if (yCenter >= y1 && yCenter <= y2 && xCenter <= x1 && xCenter + radius >= x1) {
            return true;
        }
        //5，圆心在矩形右边
        if (yCenter >= y1 && yCenter <= y2 && xCenter >= x2 && xCenter - radius <= x2) {
            return true;
        }
        long comapre = (long) radius * radius;
        //6，圆心在左上方
        if (distance(xCenter, yCenter, x1, y2) <= comapre)  {
            return true;
        }
        //7，圆心在右上方
        if (distance(xCenter, yCenter, x2, y2) <= comapre) {
            return true;
        }
        //8，圆心在左下方
        if (distance(xCenter, yCenter, x1, y1) <= comapre) {
            return true;
        }
        //9，圆心在右下方
        if (distance(xCenter, yCenter, x2, y1) <= comapre) {
            return true;
        }
        return false;
    }

    public long distance(int x1, int y1, int x2, int y2) {
        long base = 1;
        return base * (x2 - x1) * (x2 - x1) + base * (y2 - y1) * (y2 - y1);
    }
}
