package com.demo.algorithm.leetcode.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create on 11/16/21
 * @author chenglong
 * description : 完美矩形
 *
 * 给你一个数组 rectangles ，其中 rectangles[i] = [xi, yi, ai, bi] 表示一个坐标轴平行的矩形。这个矩形的左下顶点是 (xi, yi) ，右上顶点是 (ai, bi) 。
 * 如果所有矩形一起精确覆盖了某个矩形区域，则返回 true ；否则，返回 false 。
 * 
 * 示例 1：
 * 输入：rectangles = [[1,1,3,3],[3,1,4,2],[3,2,4,4],[1,3,2,4],[2,3,3,4]]
 * 输出：true
 * 解释：5 个矩形一起可以精确地覆盖一个矩形区域。
 *
 * 示例 2：
 * 输入：rectangles = [[1,1,2,3],[1,3,2,4],[3,1,4,2],[3,2,4,4]]
 * 输出：false
 * 解释：两个矩形之间有间隔，无法覆盖成一个矩形。
 *
 * 示例 3：
 * 输入：rectangles = [[1,1,3,3],[3,1,4,2],[1,3,2,4],[3,2,4,4]]
 * 输出：false
 * 解释：图形顶端留有空缺，无法覆盖成一个矩形。
 *
 * 示例 4：
 * 输入：rectangles = [[1,1,3,3],[3,1,4,2],[1,3,2,4],[2,2,4,4]]
 * 输出：false
 * 解释：因为中间有相交区域，虽然形成了矩形，但不是精确覆盖。
 *  
 * 提示：
 * 1 <= rectangles.length <= 2 * 104
 * rectangles[i].length == 4
 * -10^5 <= xi, yi, ai, bi <= 10^5
 */
public class RectangleCover {

    //统计顶点出现的次数+面积
    public static boolean isRectangleCover(int[][] rectangles) {
        int sum = 0;
        //统计每个顶点出现的次数,同时计算总面积
        Map<Long, Integer> points = new HashMap<>();
        long mod = 200001;
        long offset = 100000;
        int length = rectangles.length;
        for (int i = 0; i < length; i++) {
            int[] point = rectangles[i];
            //计算总的面积
            sum += (point[2] - point[0]) * (point[3] - point[1]);
            //统计顶点。使用x,y的坐标生成不会重复的hash值
            long leftBottom = (point[0] + offset) * mod + (point[1] + offset);
            long leftTop = (point[0] + offset) * mod + (point[3] + offset);
            long rightBottom = (point[2] + offset) * mod + (point[1] + offset);
            long rightTop = (point[2] + offset) * mod + (point[3] + offset);
            if (points.get(leftBottom) == null) {
                points.put(leftBottom, 1);
            } else {
                points.put(leftBottom, points.get(leftBottom) + 1);
            }
            if (points.get(leftTop) == null) {
                points.put(leftTop, 1);
            } else {
                points.put(leftTop, points.get(leftTop) + 1);
            }
            if (points.get(rightBottom) == null) {
                points.put(rightBottom, 1);
            } else {
                points.put(rightBottom, points.get(rightBottom) + 1);
            }
            if (points.get(rightTop) == null) {
                points.put(rightTop, 1);
            } else {
                points.put(rightTop, points.get(rightTop) + 1);
            }
        }
        //统计使用1次，2次，4次的顶点的次数
        int[] pointCounts = new int[3];
        //使用一次的顶点
        List<Long> singlePoint = new ArrayList<>();
        for (Long key : points.keySet()) {
            if (points.get(key) == 1) {
                pointCounts[0]++;
                singlePoint.add(key);
            } else if (points.get(key) == 2) {
                pointCounts[1]++;
            } else if (points.get(key) == 4) {
                pointCounts[2]++;
            } else {
                return false;
            }
        }
        if (pointCounts[0] != 4) {
            return false;
        }
        //判断4个顶点是否可以构成矩形，并获取面积
        int[][] borderPoints = new int[2][2];
        int[] start = new int[2];
        int[] end = null;
        int index = 0;
        for (int i = 0; i < 4; i++) {
            long hash = singlePoint.get(i);
            int y = (int) (hash % mod - offset);
            int x = (int) (hash / mod - offset);
            if (i == 0) {
                start[0] = x;
                start[1] = y;
            } else {
                if (start[0] != x && start[1] != y) {
                    if (end == null) {
                        end = new int[2];
                        end[0] = x;
                        end[1] = y;
                    } else {
                        return false;
                    }
                } else {
                    if (index == 2) {
                        return false;
                    }
                    borderPoints[index][0] = x;
                    borderPoints[index][1] = y;
                    index++;
                }
            }
        }
        //此时start与end对角，判断另外两个顶点borderPoints能够组成矩形
        int compare = Math.abs(start[0] - end[0]) * Math.abs(start[1] - end[1]);
        //判断面积是否相等
        if (sum != compare) {
            return false;
        }
        //判断是否构成矩形
        if (borderPoints[0][0] == start[0]) {
            //组成垂直方向的边
            if (start[1] == borderPoints[1][1] && borderPoints[0][1] == end[1] && end[0] == borderPoints[1][0]) {
                return true;
            } else {
                return false;
            }
        }
        if (borderPoints[0][1] == start[1]) {
            //组成水平方向的边
            if (start[0] == borderPoints[1][0] && borderPoints[0][0] == end[0] && end[1] == borderPoints[1][1]) {
                return true;
            }
        }
        return false;
    }
}
