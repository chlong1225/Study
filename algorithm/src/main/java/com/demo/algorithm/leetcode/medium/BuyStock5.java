package com.demo.algorithm.leetcode.medium;

/**
 * Created by chl on 2021/11/22.
 * description : 最佳买卖股票时机含冷冻期
 *
 * 给定一个整数数组，其中第 i 个元素代表了第i天的股票价格 。​
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为1天)。
 *
 * 示例:
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 */
public class BuyStock5 {

    public int maxProfit(int[] prices) {
        int length = prices.length;
        if (length <= 1) {
            return 0;
        }
        //股票当前的状态：买入，卖出，冷冻
        //1，初始化状态
        int preBuy = -prices[0];
        int preSell = 0;
        int preFree = 0;
        int buy = preBuy;
        int sell = preSell;
        int free = preFree;
        for (int i = 1; i < length; i++) {
            int price = prices[i];
            //当前买入的状态 = 上一次购买 , 上一次冷冻
            buy = Math.max(preBuy, preFree - price);
            //当前卖出的状态 = 上一次卖出,上一次买入,本次卖出
            sell = Math.max(preSell, preBuy + price);
            //冷冻状态 = 上一次冷冻,上一次卖出
            free = Math.max(preFree, preSell);
            if (i < length - 1) {
                preBuy = buy;
                preSell = sell;
                preFree = free;
            }
        }
        return Math.max(sell, free);
    }
}
