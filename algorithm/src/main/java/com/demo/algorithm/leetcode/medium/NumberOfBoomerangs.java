package com.demo.algorithm.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * create on 2024/1/8
 * @author chenglong
 * description : 回旋镖的数量
 *
 * 给定平面上n对互不相同的点points，其中points[i]=[xi,yi]。回旋镖是由点(i,j,k)表示的元组，其中i和j之间的距离和i和k之间的欧式距离相等（需要考虑元组的顺序）。
 * 返回平面上所有回旋镖的数量。
 *
 * 示例 1：
 * 输入：points = [[0,0],[1,0],[2,0]]
 * 输出：2
 * 解释：两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
 *
 * 示例 2：
 * 输入：points = [[1,1],[2,2],[3,3]]
 * 输出：2
 *
 * 示例 3：
 * 输入：points = [[1,1]]
 * 输出：0
 *
 * 提示：
 * n == points.length
 * 1 <= n <= 500
 * points[i].length == 2
 * -10^4 <= xi, yi <= 10^4
 * 所有点都 互不相同
 */
public class NumberOfBoomerangs {

    public int numberOfBoomerangs(int[][] points) {
        int n = points.length;
        if (n < 3) {
            return 0;
        }
        Map<Long, Integer> marks = new HashMap<>();
        long base = 1;
        int count = 0;
        for (int i = 0; i < n; i++) {
            marks.clear();
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    long size = base * (points[j][1] - points[i][1]) * (points[j][1] - points[i][1]) + base * (points[j][0] - points[i][0]) * (points[j][0] - points[i][0]);
                    if (marks.containsKey(size)) {
                        marks.put(size, marks.get(size) + 1);
                    } else {
                        marks.put(size, 1);
                    }
                }
            }
            //计算数量
            for (long key : marks.keySet()) {
                if (marks.get(key) >= 2) {
                    int tem = marks.get(key);
                    count += (tem - 1) * tem;
                }
            }
        }
        return count;
    }
}
