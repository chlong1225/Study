package com.demo.algorithm.leetcode.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * create on 2023/5/24
 * @author chenglong
 * description : 受标签影响的最大值
 *
 * 我们有一个n项的集合。给出两个整数数组values和labels，第i个元素的值和标签分别是values[i]和labels[i]。还会给出两个整数numWanted和useLimit。
 * 从n个元素中选择一个子集s:
 * 子集s的大小小于或等于numWanted。
 * s中最多有相同标签的useLimit项。
 * 一个子集的分数是该子集的值之和。
 * 返回子集s的最大分数。
 *
 * 示例 1：
 * 输入：values = [5,4,3,2,1], labels = [1,1,2,2,3], numWanted = 3, useLimit = 1
 * 输出：9
 * 解释：选出的子集是第一项，第三项和第五项。
 *
 * 示例 2：
 * 输入：values = [5,4,3,2,1], labels = [1,3,3,3,2], numWanted = 3, useLimit = 2
 * 输出：12
 * 解释：选出的子集是第一项，第二项和第三项。
 *
 * 示例 3：
 * 输入：values = [9,8,8,7,6], labels = [0,0,0,1,1], numWanted = 3, useLimit = 1
 * 输出：16
 * 解释：选出的子集是第一项和第四项。
 *
 * 提示：
 * n == values.length == labels.length
 * 1 <= n <= 2 * 10^4
 * 0 <= values[i], labels[i] <= 2 * 10^4
 * 1 <= numWanted, useLimit <= n
 */
public class LargestValsFromLabels {

    public int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
        int n = values.length;
        int[][] dates = new int[n][2];
        for (int i = 0; i < n; i++) {
            dates[i][0] = values[i];
            dates[i][1] = labels[i];
        }
        Arrays.sort(dates, (o1, o2) -> o2[0] - o1[0]);
        int sum = 0;
        int count = 0;
        Map<Integer, Integer> marks = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int label = dates[i][1];
            if (marks.containsKey(label)) {
                int preCount = marks.get(label);
                if (preCount < useLimit) {
                    sum += dates[i][0];
                    count++;
                    marks.put(label, preCount + 1);
                    if (count >= numWanted) {
                        break;
                    }
                }
            } else {
                marks.put(label, 1);
                sum += dates[i][0];
                count++;
                if (count >= numWanted) {
                    break;
                }
            }
        }
        return sum;
    }
}
