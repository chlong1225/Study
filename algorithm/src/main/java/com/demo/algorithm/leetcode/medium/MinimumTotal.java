package com.demo.algorithm.leetcode.medium;

import java.util.List;

/**
 * create on 2022/8/29
 * @author chenglong
 * description : 三角形最小路径和
 *
 * 给定一个三角形triangle，找出自顶向下的最小路径和。
 * 每一步只能移动到下一行中相邻的结点上。相邻的结点在这里指的是下标与上一层结点下标相同或者等于上一层结点下标+1的两个结点。也就是说，如果正位于当前行的下标i，那么下一步可以移动到下一行的下标i或i+1。
 *
 * 示例 1：
 * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * 输出：11
 * 解释：如下面简图所示：
 *    2
 *   3 4
 *  6 5 7
 * 4 1 8 3
 * 自顶向下的最小路径和为11（即，2+3+5+1= 11）。
 *
 * 示例 2：
 * 输入：triangle = [[-10]]
 * 输出：-10
 *
 * 提示：
 * 1 <= triangle.length <= 200
 * triangle[0].length == 1
 * triangle[i].length == triangle[i - 1].length + 1
 * -10^4 <= triangle[i][j] <= 10^4
 *
 * 进阶：
 * 你可以只使用O(n)的额外空间（n为三角形的总行数）来解决这个问题吗？
 */
public class MinimumTotal {

    public int minimumTotal(List<List<Integer>> triangle) {
        int length = triangle.size();
        if (length == 1) {
            return triangle.get(0).get(0);
        }
        for (int i = 1; i < length; i++) {
            List<Integer> cur = triangle.get(i);
            List<Integer> pre = triangle.get(i - 1);
            for (int j = 0; j < cur.size(); j++) {
                int sum = cur.get(j);
                if (j == 0) {
                    sum += pre.get(j);
                } else if (j == pre.size()) {
                    sum += pre.get(j - 1);
                } else {
                    sum += Math.min(pre.get(j - 1), pre.get(j));
                }
                cur.set(j, sum);
            }
        }
        List<Integer> last = triangle.get(length - 1);
        int min = last.get(0);
        for (int i = 1; i < last.size(); i++) {
            if (last.get(i) < min) {
                min = last.get(i);
            }
        }
        return min;
    }
}
