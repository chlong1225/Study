package com.demo.algorithm.leetcode.interview;

/**
 * Created by chl on 2022/5/21.
 * description : 面试题16.03. 交点
 *
 * 给定两条线段（表示为起点start = {X1, Y1}和终点end = {X2, Y2}），如果它们有交点，请计算其交点，没有交点则返回空值。
 * 要求浮点型误差不超过10^-6。若有多个交点（线段重叠）则返回 X 值最小的点，X 坐标相同则返回 Y 值最小的点。
 *
 * 示例 1：
 * 输入：
 * line1 = {0, 0}, {1, 0}
 * line2 = {1, 1}, {0, -1}
 * 输出： {0.5, 0}
 *
 * 示例 2：
 * 输入：
 * line1 = {0, 0}, {3, 3}
 * line2 = {1, 1}, {2, 2}
 * 输出： {1, 1}
 *
 * 示例 3：
 * 输入：
 * line1 = {0, 0}, {1, 1}
 * line2 = {1, 0}, {2, 1}
 * 输出： {}，两条线段没有交点
 *
 * 提示：
 * 坐标绝对值不会超过 2^7
 * 输入的坐标均是有效的二维坐标
 */
public class Intersection {

    public double[] intersection(int[] start1, int[] end1, int[] start2, int[] end2) {
        //1，检查并交换起点
        checkSwap(start1, end1);
        checkSwap(start2, end2);
        //2，记录线段点之间的偏差，用于判断平行
        int spaceX1 = end1[0] - start1[0];
        int spaceY1 = end1[1] - start1[1];
        int spaceX2 = end2[0] - start2[0];
        int spaceY2 = end2[1] - start2[1];
        //3，检查平行与重合
        //防止数据越界
        long base = 1;
        if (spaceX1 == 0 && spaceX2 == 0) {
            //3.1，line1与line2与y轴平行
            if (start1[0] == start2[0]) {
                //在一条直线上，判断重合点
                if (end1[1] < start2[1]) {
                    //线段没有重合
                    return new double[0];
                }
                if (start1[1] > end2[1]) {
                    return new double[0];
                }
                //此时有重合点
                double[] result = new double[2];
                result[0] = start1[0];
                result[1] = Math.max(start1[1], start2[1]);
                return result;
            }
            return new double[0];
        }
        if (spaceX1 != 0 && spaceX2 != 0) {
            //3.2，line1与line2与y轴都不平行
            long a1 = base * spaceY1 * spaceX2;
            long a2 = base * spaceY2 * spaceX1;
            if (a1 == a2) {
                //line1与lin2平行，判断start1点是否在line2上来确认line1与line2对应的直线是否重合
                int spaceX3 = start2[0] - start1[0];
                int spaceY3 = start2[1] - start1[1];
                long a3 = base * spaceY3 * spaceX2;
                long a4 = base * spaceY2 * spaceX3;
                if (a3 == a4) {
                    //line1与line2对应直线重合
                    if (end1[0] < start2[0]) {
                        //线段没有重合
                        return new double[0];
                    }
                    if (start1[0] > end2[0]) {
                        return new double[0];
                    }
                    double[] result = new double[2];
                    if (start1[0] > start2[0]) {
                        result[0] = start1[0];
                        result[1] = start1[1];
                    } else {
                        result[0] = start2[0];
                        result[1] = start2[1];
                    }
                    return result;
                }
                return new double[0];
            } else {
                //line1与line2相交。
                double[] points = getPoint(start1, end1, start2, end2);
                //判断交点是否在线段上
                if (points[0] >= start1[0] && points[0] <= end1[0] && points[0] >= start2[0] && points[0] <= end2[0]) {
                    return points;
                }
                return new double[0];
            }
        }
        if (spaceX1 == 0) {
            //3.3，line1与y轴平行,相交点的坐标（start1[0],?）
            if (start2[0] == start1[0]) {
                //可能相交点为：start2
                if (start2[1] >= start1[1] && start2[1] <= end1[1]) {
                    double[] result = new double[2];
                    result[0] = start2[0];
                    result[1] = start2[1];
                    return result;
                }
                return new double[0];
            }
            if (end2[0] == start1[0]) {
                //可能相交点end2
                if (end2[1] >= start1[1] && end2[1] <= end1[1]) {
                    double[] result = new double[2];
                    result[0] = end2[0];
                    result[1] = end2[1];
                    return result;
                }
                return new double[0];
            }
            if (start1[0] < start2[0] || start1[0] > end2[0]) {
                return new double[0];
            }
            double sum = 1.0 * spaceY2 * (start1[0] - start2[0]);
            double y = sum / spaceX2 + start2[1];
            if (y >= start1[1] && y <= end1[1]) {
                double[] result = new double[2];
                result[0] = start1[0];
                result[1] = y;
                return result;
            }
            return new double[0];
        }
        //3.4，line2与y轴平行,相交点的坐标（start2[0],?）
        if (start1[0] == start2[0]) {
            //可能相交点为：start1
            if (start1[1] >= start2[1] && start1[1] <= end2[1]) {
                double[] result = new double[2];
                result[0] = start1[0];
                result[1] = start1[1];
                return result;
            }
            return new double[0];
        }
        if (end1[0] == start2[0]) {
            //可能相交点end2
            if (end1[1] >= start2[1] && end1[1] <= end2[1]) {
                double[] result = new double[2];
                result[0] = end1[0];
                result[1] = end1[1];
                return result;
            }
            return new double[0];
        }
        if (start2[0] < start1[0] || start2[0] > end1[0]) {
            return new double[0];
        }
        double sum = 1.0 * spaceY1 * (start2[0] - start1[0]);
        double y = sum / spaceX1 + start1[1];
        if (y >= start2[1] && y <= end2[1]) {
            double[] result = new double[2];
            result[0] = start2[0];
            result[1] = y;
            return result;
        }
        return new double[0];
    }

    //获取交点
    private double[] getPoint(int[] start1, int[] end1, int[] start2, int[] end2) {
        //公式：start[0]*end[1]-start[1]*end[0] = (end[1]-start[1])*x + (start[0]-end[0])*y
        double[] points = new double[2];
        long base = 1;
        long a1 = base * start1[0] * end1[1] - base * start1[1] * end1[0];
        long a2 = base * start2[0] * end2[1] - base * start2[1] * end2[0];
        int spaceX1 = end1[0] - start1[0];
        int spaceY1 = end1[1] - start1[1];
        int spaceX2 = end2[0] - start2[0];
        int spaceY2 = end2[1] - start2[1];
        long sum1 = a2 * spaceX1 - a1 * spaceX2;
        double sum2 = 1.0 * spaceX1 * spaceY2 - 1.0 * spaceX2 * spaceY1;
        points[0] = sum1 / sum2;
        sum1 = a2 * spaceY1 - a1 * spaceY2;
        points[1] = sum1 / sum2;
        return points;
    }

    private void checkSwap(int[] start, int[] end) {
        if (start[0] > end[0]) {
            int tem = start[0];
            start[0] = end[0];
            end[0] = tem;
            tem = start[1];
            start[1] = end[1];
            end[1] = tem;
        } else if (start[0] == end[0]) {
            if (start[1] > end[1]) {
                int tem = start[1];
                start[1] = end[1];
                end[1] = tem;
            }
        }
    }
}
