package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/12/4
 * @author chenglong
 * description : 缀点成线
 *
 * 给定一个数组coordinates，其中coordinates[i]=[x,y]，[x,y]表示横坐标为x、纵坐标为y的点。请你来判断，这些点是否在该坐标系中属于同一条直线上。
 *
 * 示例 1：
 * 输入：coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
 * 输出：true
 *
 * 示例 2：
 * 输入：coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
 * 输出：false
 *
 * 提示：
 * 2 <= coordinates.length <= 1000
 * coordinates[i].length == 2
 * -10^4 <= coordinates[i][0], coordinates[i][1] <= 10^4
 * coordinates 中不含重复的点
 */
public class StraightLine {

    public boolean checkStraightLine(int[][] coordinates) {
        int n = coordinates.length;
        if (n <= 2) {
            return true;
        }
        int[] compare = new int[2];
        compare[0] = coordinates[1][0] - coordinates[0][0];
        compare[1] = coordinates[1][1] - coordinates[0][1];
        for (int i = 2; i < n; i++) {
            int dx = coordinates[i][0] - coordinates[0][0];
            int dy = coordinates[i][1] - coordinates[0][1];
            if (dx * compare[1] != dy * compare[0]) {
                return false;
            }
        }
        return true;
    }
}
