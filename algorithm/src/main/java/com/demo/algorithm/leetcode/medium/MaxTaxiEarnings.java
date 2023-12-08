package com.demo.algorithm.leetcode.medium;

import java.util.Arrays;

/**
 * create on 2023/12/8
 * @author chenglong
 * description : 出租车的最大盈利
 *
 * 你驾驶出租车行驶在一条有n个地点的路上。这n个地点从近到远编号为1到n，你想要从1开到n，通过接乘客订单盈利。你只能沿着编号递增的方向前进，不能改变方向。
 * 乘客信息用一个下标从0开始的二维数组rides表示，其中rides[i]=[starti,endi,tipi]表示第i位乘客需要从地点starti前往endi，愿意支付tipi元的小费。
 * 每一位你选择接单的乘客i，你可以盈利endi-starti+tipi元。你同时最多只能接一个订单。
 * 给你n和rides，请你返回在最优接单方案下，你能盈利最多多少元。
 * 注意：你可以在一个地点放下一位乘客，并在同一个地点接上另一位乘客。
 *
 * 示例 1：
 * 输入：n = 5, rides = [[2,5,4],[1,5,1]]
 * 输出：7
 * 解释：我们可以接乘客 0 的订单，获得 5 - 2 + 4 = 7 元。
 *
 * 示例 2：
 * 输入：n = 20, rides = [[1,6,1],[3,10,2],[10,12,3],[11,12,2],[12,15,2],[13,18,1]]
 * 输出：20
 * 解释：我们可以接以下乘客的订单：
 * - 将乘客 1 从地点 3 送往地点 10 ，获得 10 - 3 + 2 = 9 元。
 * - 将乘客 2 从地点 10 送往地点 12 ，获得 12 - 10 + 3 = 5 元。
 * - 将乘客 5 从地点 13 送往地点 18 ，获得 18 - 13 + 1 = 6 元。
 * 我们总共获得 9 + 5 + 6 = 20 元。
 *
 * 提示：
 * 1 <= n <= 10^5
 * 1 <= rides.length <= 3 * 10^4
 * rides[i].length == 3
 * 1 <= starti < endi <= n
 * 1 <= tipi <= 10^5
 */
public class MaxTaxiEarnings {

    public long maxTaxiEarnings(int n, int[][] rides) {
        //1，对下车的终点进行排序
        Arrays.sort(rides, (o1, o2) -> o1[1] - o2[1]);
        //2，使用动态规划
        int m = rides.length;
        long[] marks = new long[m];
        marks[0] = rides[0][1] - rides[0][0] + rides[0][2];
        for (int i = 1; i < m; i++) {
            int j = findIndex(rides, i - 1, rides[i][0]);
            if (j >= 0) {
                marks[i] = Math.max(marks[i - 1], marks[j] + rides[i][1] - rides[i][0] + rides[i][2]);
            } else {
                marks[i] = Math.max(marks[i - 1], rides[i][1] - rides[i][0] + rides[i][2]);
            }
        }
        return marks[m - 1];
    }

    private int findIndex(int[][] rides, int end, int compare) {
        int start = 0;
        if (rides[start][1] > compare) {
            return -1;
        }
        if (rides[end][1] <= compare) {
            return end;
        }
        while (start < end) {
            int middle = (end - start) / 2 + start;
            if (rides[middle][1] > compare) {
                end = middle - 1;
            } else {
                start = middle + 1;
                if (rides[start][1] > compare) {
                    return middle;
                }
            }
        }
        return start;
    }
}
