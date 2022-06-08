package com.demo.algorithm.leetcode.easy;

/**
 * create on 2022/6/8
 * @author chenglong
 * description : 有效的回旋镖
 *
 * 给定一个数组points，其中points[i] = [xi, yi]表示X-Y平面上的一个点，如果这些点构成一个回旋镖则返回true。
 * 回旋镖定义为一组三个点，这些点各不相同且不在一条直线上。
 *
 * 示例 1：
 * 输入：points = [[1,1],[2,3],[3,2]]
 * 输出：true
 *
 * 示例 2：
 * 输入：points = [[1,1],[2,2],[3,3]]
 * 输出：false
 *
 * 提示：
 * points.length == 3
 * points[i].length == 2
 * 0 <= xi, yi<= 100
 */
public class Boomerang {

    public boolean isBoomerang(int[][] points) {
        //1，特殊场景：判断三个点有没有重合
        if (isSame(points[0], points[1]) || isSame(points[0], points[2]) || isSame(points[1], points[2])) {
            return false;
        }
        //2，判断三个点是否在一条直线上
        int num1 = (points[1][1] - points[0][1]) * (points[2][0] - points[0][0]);
        int num2 = (points[2][1] - points[0][1]) * (points[1][0] - points[0][0]);
        return num1 != num2;
    }

    private boolean isSame(int[] left, int[] right) {
        return left[0] == right[0] && left[1] == right[1];
    }
}
