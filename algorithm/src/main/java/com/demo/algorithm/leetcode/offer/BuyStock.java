package com.demo.algorithm.leetcode.offer;

/**
 * Created by chl on 2021/11/23.
 * description : 剑指Offer63. 股票的最大利润
 * 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
 *
 * 示例 1:
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 *
 * 示例 2:
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 * 限制：
 * 0 <= 数组长度 <= 10^5
 */
public class BuyStock {

    //使用动态规划
    public int maxProfit(int[] prices) {
        int length = prices.length;
        if (length <= 1) {
            return 0;
        }
        //当天三种状态：购买，卖出，不交易(这个忽略)
        int preBuy = -prices[0];
        int preSell = 0;
        int buy = preBuy;
        int sell = preSell;
        for (int i = 1; i < length; i++) {
            int price = prices[i];
            //当前状态为购买 = 上次购买 , 之前没有买卖,这次购买
            buy = Math.max(preBuy, -price);
            //当前购买状态 = 上一次购买,上一次卖出+本次卖出
            sell = Math.max(preSell, preBuy + price);
            if (i < length - 1) {
                preBuy = buy;
                preSell = sell;
            }
        }
        return sell;
    }

    //使用类似单调栈的方式
    public int maxProfit2(int[] prices) {
        int length = prices.length;
        if (length <= 1) {
            return 0;
        }
        //用于存放当前位置向右的最大值
        int[] maxRight = new int[length];
        maxRight[length - 1] = prices[length - 1];
        int max = prices[length - 1];
        for (int i = length - 1; i >= 0; i--) {
            if (max < prices[i]) {
                max = prices[i];
                maxRight[i] = prices[i];
            } else {
                maxRight[i] = max;
            }
        }
        int profit = 0;
        for (int i = 0; i < length; i++) {
            if (maxRight[i] - prices[i] > profit) {
                profit = maxRight[i] - prices[i];
            }
        }
        return profit;
    }

    //使用双指针的方式
    public int maxProfit3(int[] prices) {
        int length = prices.length;
        if (length <= 1) {
            return 0;
        }
        int min = prices[0];
        int max = prices[1];
        int profit = 0;
        for (int i = 1; i < length; i++) {
            int price = prices[i];
            if (price < min) {
                //计算上一个区间的最大差价
                if (max - min > profit) {
                    profit = max - min;
                }
                min = price;
                if (i + 1 < length) {
                    max = prices[i + 1];
                } else {
                    max = min;
                }
            } else if (price > max) {
                max = price;
            }
        }
        if (max - min > profit) {
            profit = max - min;
        }
        return profit;
    }
}
