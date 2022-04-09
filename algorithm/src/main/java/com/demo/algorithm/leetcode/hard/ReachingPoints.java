package com.demo.algorithm.leetcode.hard;

/**
 * Created by chl on 2022/4/9.
 * description : 到达终点
 *
 * 给定四个整数sx,sy，tx和ty，如果通过一系列的转换可以从起点(sx, sy)到达终点(tx, ty)，则返回true，否则返回false。
 * 从点(x, y)可以转换到(x, x+y)或者(x+y, y)。
 *
 * 示例 1:
 * 输入: sx = 1, sy = 1, tx = 3, ty = 5
 * 输出: true
 * 解释:
 * 可以通过以下一系列转换从起点转换到终点：
 * (1, 1) -> (1, 2)
 * (1, 2) -> (3, 2)
 * (3, 2) -> (3, 5)
 *
 * 示例 2:
 * 输入: sx = 1, sy = 1, tx = 2, ty = 2
 * 输出: false
 *
 * 示例 3:
 * 输入: sx = 1, sy = 1, tx = 1, ty = 1
 * 输出: true
 *
 * 提示:
 * 1 <= sx, sy, tx, ty <= 10^9
 */
public class ReachingPoints {


    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        //1，默认起始位置相同，不用转换，直接返回true
        if (sx == tx && sy == ty) {
            return true;
        }
        //2，起始位置坐标更大时无法转换
        if (sx > tx || sy > ty) {
            return false;
        }
        //3，此时(tx,ty)肯定是通过（sx，sy）转换给来的。不管哪种转换，tx！=ty
        if (tx == ty) {
            return false;
        }
        //4，最后一次转换肯定是获取tx，ty中较大的
        while (tx >= sx && ty >= sy) {
            if (tx > ty) {
                //通过（x+y，y）转换过来的。使用取模防止tx远大于ty时，重复-ty的操作
                if (tx <= sx + ty) {
                    tx -= ty;
                } else {
                    tx = (tx - sx - ty) % ty + sx;
                }
            } else if (tx == ty) {
                return false;
            } else {
                //通过（x，x+y）转换过来的
                if (ty <= sy + tx) {
                    ty -= tx;
                } else {
                    ty = (ty - sy - tx) % tx + sy;
                }
            }
            if (tx == sx && ty == sy) {
                return true;
            }
        }
        return false;
    }
}
