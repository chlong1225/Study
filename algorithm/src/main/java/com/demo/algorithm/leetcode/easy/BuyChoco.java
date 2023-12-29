package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/12/29
 * @author chenglong
 * description : 购买两块巧克力
 *
 * 给你一个整数数组prices，它表示一个商店里若干巧克力的价格。同时给你一个整数money，表示你一开始拥有的钱数。
 * 你必须购买恰好两块巧克力，而且剩余的钱数必须是非负数。同时你想最小化购买两块巧克力的总花费。
 * 请你返回在购买两块巧克力后，最多能剩下多少钱。如果购买任意两块巧克力都超过了你拥有的钱，请你返回money。注意剩余钱数必须是非负数。
 *
 * 示例 1：
 * 输入：prices = [1,2,2], money = 3
 * 输出：0
 * 解释：分别购买价格为 1 和 2 的巧克力。你剩下 3 - 3 = 0 块钱。所以我们返回 0 。
 *
 * 示例 2：
 * 输入：prices = [3,2,3], money = 3
 * 输出：3
 * 解释：购买任意 2 块巧克力都会超过你拥有的钱数，所以我们返回 3 。
 *
 * 提示：
 * 2 <= prices.length <= 50
 * 1 <= prices[i] <= 100
 * 1 <= money <= 100
 */
public class BuyChoco {

    public int buyChoco(int[] prices, int money) {
        //1，查找最小价格的位置
        int n = prices.length;
        int minIndex = 0;
        for (int i = 1; i < n; i++) {
            if (prices[i] < prices[minIndex]) {
                minIndex = i;
            }
        }
        //2，处理临界值。两块巧克力至少花费：2*prices[minIndex]
        if (money < 2 * prices[minIndex]) {
            return money;
        }
        //3，查找下一个价格位置
        int nextIndex = 0;
        if (minIndex == 0) {
            nextIndex = 1;
        }
        for (int i = 0; i < n; i++) {
            if (i != minIndex) {
                if (prices[i] < prices[nextIndex]) {
                    nextIndex = i;
                }
            }
        }
        int sum = prices[minIndex] + prices[nextIndex];
        if (sum > money) {
            return money;
        }
        return money - sum;
    }
}
