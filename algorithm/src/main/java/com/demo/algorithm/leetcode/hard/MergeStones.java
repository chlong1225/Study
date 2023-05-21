package com.demo.algorithm.leetcode.hard;

/**
 * create on 2023/4/4
 * @author chenglong
 * description : 合并石头的最低成本
 *
 * 有N堆石头排成一排，第i堆中有stones[i]块石头。
 * 每次移动（move）需要将连续的K堆石头合并为一堆，而这个移动的成本为这K堆石头的总数。
 * 找出把所有石头合并成一堆的最低成本。如果不可能，返回 -1 。
 *
 * 示例 1：
 * 输入：stones = [3,2,4,1], K = 2
 * 输出：20
 * 解释：
 * 从 [3, 2, 4, 1] 开始。
 * 合并 [3, 2]，成本为 5，剩下 [5, 4, 1]。
 * 合并 [4, 1]，成本为 5，剩下 [5, 5]。
 * 合并 [5, 5]，成本为 10，剩下 [10]。
 * 总成本 20，这是可能的最小值。
 *
 * 示例 2：
 * 输入：stones = [3,2,4,1], K = 3
 * 输出：-1
 * 解释：任何合并操作后，都会剩下 2 堆，我们无法再进行合并。所以这项任务是不可能完成的。
 * 
 * 示例 3：
 * 输入：stones = [3,5,1,2,6], K = 3
 * 输出：25
 * 解释：
 * 从 [3, 5, 1, 2, 6] 开始。
 * 合并 [5, 1, 2]，成本为 8，剩下 [3, 8, 6]。
 * 合并 [3, 8, 6]，成本为 17，剩下 [17]。
 * 总成本 25，这是可能的最小值。
 *
 * 提示：
 * 1 <= stones.length <= 30
 * 2 <= K <= 30
 * 1 <= stones[i] <= 100
 */
public class MergeStones {

    private int[][][] marks;
    private int[] sums;
    private int k;

    public int mergeStones(int[] stones, int k) {
        int n = stones.length;
        //1，处理特殊场景
        if (n == 1) {
            return 0;
        }
        if (k > n) {
            return -1;
        }
        if (k > 2 && (n % (k - 1) != 1)) {
            return -1;
        }
        this.k = k;
        sums = new int[n + 1];
        //2，前缀和
        for (int i = 0; i < n; i++) {
            sums[i + 1] = sums[i] + stones[i];
        }
        //3，深度搜索
        marks = new int[n][n][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int l = 0; l < k + 1; l++) {
                    marks[i][j][l] = -1;
                }
            }
        }
        return dfs(0, n - 1, 1);
    }

    private int dfs(int left, int right, int p) {
        if (marks[left][right][p] != -1) {
            return marks[left][right][p];
        }
        if (p == 1) {
            if (left == right) {
                marks[left][right][p] = 0;
            } else {
                marks[left][right][p] = dfs(left, right, k) + (sums[right + 1] - sums[left]);
            }
            return marks[left][right][p];
        }
        int result = Integer.MAX_VALUE;
        for (int i = left; i < right; i += (k - 1)) {
            int tem = dfs(left, i, 1) + dfs(i + 1, right, p - 1);
            if (tem < result) {
                result = tem;
            }
        }
        marks[left][right][p] = result;
        return result;
    }
}
