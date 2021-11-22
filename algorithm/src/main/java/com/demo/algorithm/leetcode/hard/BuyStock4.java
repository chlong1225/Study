package com.demo.algorithm.leetcode.hard;

/**
 * Created by chl on 2021/11/22.
 * description : 买卖股票最佳时机IV
 *
 * 给定一个整数数组prices ，它的第i个元素prices[i]是一支给定的股票在第i天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例 1：
 * 输入：k = 2, prices = [2,4,1]
 * 输出：2
 * 解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 *
 * 示例 2：
 * 输入：k = 2, prices = [3,2,6,5,0,3]
 * 输出：7
 * 解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 *      随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 *  
 * 提示：
 * 0 <= k <= 100
 * 0 <= prices.length <= 1000
 * 0 <= prices[i] <= 1000
 */
public class BuyStock4 {

    public int maxProfit(int k, int[] prices) {
        if (k == 0) {
            return 0;
        }
        int length = prices.length;
        if (length <= 1) {
            return 0;
        }
        //k次买卖总共2*k+1中状态
        int type = 2 * k + 1;
        int[] preTrades = new int[type];
        int[] trades = new int[type];
        //1，初始化状态
        for (int i = 0; i < k; i++) {
            preTrades[2 * i] = 0;
            preTrades[2 * i + 1] = -prices[0];
        }
        preTrades[2 * k] = 0;
        for (int i = 1; i < length; i++) {
            int price = prices[i];
            //2,状态转移
            for (int j = 1; j < type; j++) {
                if (j % 2 == 0) {
                    //卖出的状态
                    trades[j] = Math.max(preTrades[j], preTrades[j - 1] + price);
                } else {
                    //购买的状态
                    trades[j] = Math.max(preTrades[j], preTrades[j - 1] - price);
                }
            }
            //3,初始状态重置,避免使用二维数组,使用时间换空间
            if (i < length - 1) {
                for (int j = 0; j < type; j++) {
                    preTrades[j] = trades[j];
                }
            }
        }
        return trades[2 * k];
    }
}
