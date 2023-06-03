package com.demo.algorithm.leetcode.medium;

import java.util.Arrays;

/**
 * create on 2023/6/1
 * @author chenglong
 * description : 礼盒的最大甜蜜度
 *
 * 给你一个正整数数组price，其中price[i]表示第i类糖果的价格，另给你一个正整数k。
 * 商店组合k类不同糖果打包成礼盒出售。礼盒的甜蜜度是礼盒中任意两种糖果价格绝对差的最小值。
 * 返回礼盒的最大甜蜜度。
 *
 * 示例 1：
 * 输入：price = [13,5,1,8,21,2], k = 3
 * 输出：8
 * 解释：选出价格分别为 [13,5,21] 的三类糖果。
 * 礼盒的甜蜜度为 min(|13 - 5|, |13 - 21|, |5 - 21|) = min(8, 8, 16) = 8 。
 * 可以证明能够取得的最大甜蜜度就是 8 。
 *
 * 示例 2：
 * 输入：price = [1,3,1], k = 2
 * 输出：2
 * 解释：选出价格分别为 [1,3] 的两类糖果。
 * 礼盒的甜蜜度为 min(|1 - 3|) = min(2) = 2 。
 * 可以证明能够取得的最大甜蜜度就是 2 。
 *
 * 示例 3：
 * 输入：price = [7,7,7,7], k = 2
 * 输出：0
 * 解释：从现有的糖果中任选两类糖果，甜蜜度都会是 0 。
 *
 * 提示：
 * 1 <= price.length <= 10^5
 * 1 <= price[i] <= 10^9
 * 2 <= k <= price.length
 */
public class MaximumTastiness {

    public int maximumTastiness(int[] price, int k) {
        Arrays.sort(price);
        int n = price.length;
        int left = 0;
        int right = price[n - 1] - price[0];
        while (left < right) {
            int middle = (left + right + 1) / 2;
            if (check(middle, k, price)) {
                left = middle;
            } else {
                right = middle - 1;
            }
        }
        return left;
    }

    private boolean check(int target, int k, int[] price) {
        int count = 0;
        int pre = Integer.MIN_VALUE / 2;
        for (int i = 0; i < price.length; i++) {
            if ((price[i] - pre) >= target) {
                count++;
                pre = price[i];
            }
        }
        return count >= k;
    }
}
