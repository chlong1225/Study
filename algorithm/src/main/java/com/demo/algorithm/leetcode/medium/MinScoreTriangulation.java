package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/4/3
 * @author chenglong
 * description : 多边形三角剖分的最低得分
 *
 * 你有一个凸的n边形，其每个顶点都有一个整数值。给定一个整数数组values，其中values[i]是第i个顶点的值（即顺时针顺序）。
 * 假设将多边形剖分为n-2个三角形。对于每个三角形，该三角形的值是顶点标记的乘积，三角剖分的分数是进行三角剖分后所有n-2个三角形的值之和。
 * 返回多边形进行三角剖分后可以得到的最低分 。
 *
 * 示例 1：
 * 输入：values = [1,2,3]
 * 输出：6
 * 解释：多边形已经三角化，唯一三角形的分数为 6。
 *
 * 示例 2：
 * 输入：values = [3,7,4,5]
 * 输出：144
 * 解释：有两种三角剖分，可能得分分别为：3*7*5 + 4*5*7 = 245，或 3*4*5 + 3*4*7 = 144。最低分数为 144。
 *
 * 示例 3：
 * 输入：values = [1,3,1,4,1,5]
 * 输出：13
 * 解释：最低分数三角剖分的得分情况为 1*1*3 + 1*1*4 + 1*1*5 + 1*1*1 = 13。
 *
 * 提示：
 * n == values.length
 * 3 <= n <= 50
 * 1 <= values[i] <= 100
 */
public class MinScoreTriangulation {

    private int[][] marks;

    public int minScoreTriangulation(int[] values) {
        int n = values.length;
        marks = new int[n][n];
        return dfs(0, n - 1, values);
    }

    private int dfs(int start, int end, int[] values) {
        if (marks[start][end] != 0) {
            return marks[start][end];
        }
        if (end - start <= 1) {
            return 0;
        }
        if (end - start == 2) {
            //此时start～end只有三个点，直接构成三角形
            int num = values[start] * values[start + 1] * values[start + 2];
            marks[start][end] = num;
            return num;
        }
        int min = values[start] * values[start + 1] * values[end] + dfs(start + 1, end, values);
        for (int i = start + 2; i <end; i++) {
            int tem = dfs(start, i, values) + values[start] * values[i] * values[end] + dfs(i, end, values);
            if (tem < min) {
                min = tem;
            }
        }
        marks[start][end] = min;
        return min;
    }
}
