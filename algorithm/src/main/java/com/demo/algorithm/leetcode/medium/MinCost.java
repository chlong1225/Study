package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/12/28
 * @author chenglong
 * description : 收集巧克力
 *
 * 给你一个长度为n、下标从0开始的整数数组nums，表示收集不同巧克力的成本。每个巧克力都对应一个不同的类型，最初，位于下标i的巧克力就对应第i个类型。
 * 在一步操作中，你可以用成本x执行下述行为：
 * 同时修改所有巧克力的类型，将巧克力的类型ith 修改为类型((i+1)modn)th。
 * 假设你可以执行任意次操作，请返回收集所有类型巧克力所需的最小成本。
 *
 * 示例 1：
 * 输入：nums = [20,1,15], x = 5
 * 输出：13
 * 解释：最开始，巧克力的类型分别是 [0,1,2] 。我们可以用成本 1 购买第 1 个类型的巧克力。
 * 接着，我们用成本 5 执行一次操作，巧克力的类型变更为 [1,2,0] 。我们可以用成本 1 购买第 2 个类型的巧克力。
 * 然后，我们用成本 5 执行一次操作，巧克力的类型变更为 [2,0,1] 。我们可以用成本 1 购买第 0 个类型的巧克力。
 * 因此，收集所有类型的巧克力需要的总成本是 (1 + 5 + 1 + 5 + 1) = 13 。可以证明这是一种最优方案。
 *
 * 示例 2：
 * 输入：nums = [1,2,3], x = 4
 * 输出：6
 * 解释：我们将会按最初的成本收集全部三个类型的巧克力，而不需执行任何操作。因此，收集所有类型的巧克力需要的总成本是 1 + 2 + 3 = 6 。
 *
 * 提示：
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 10^9
 * 1 <= x <= 10^9
 */
public class MinCost {

    public long minCost(int[] nums, int x) {
        int n = nums.length;
        long ans = 0;
        //记录移动后最小值
        int[] dates = new int[n];
        for (int i = 0; i < n; i++) {
            dates[i] = nums[i];
            ans += nums[i];
        }
        //枚举操作次数
        for (int i = 1; i < n; i++) {
            //更新当前index对应的最小值
            for (int j = 0; j < n; j++) {
                int moveIndex = (j + i) % n;
                dates[j] = Math.min(dates[j], nums[moveIndex]);
            }
            long sum = 0;
            for (int j = 0; j < n; j++) {
                sum += dates[j];
            }
            sum += ((long) i * x);
            if (sum < ans) {
                ans = sum;
            }
        }
        return ans;
    }
}
