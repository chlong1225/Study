
package com.demo.algorithm.leetcode.hard;

/**
 * Created by chl on 2021/11/17.
 * description : 买卖股票的最佳时机III
 *
 * 给定一个数组，它的第i个元素是一支给定的股票在第i天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔交易。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例 1:
 * 输入：prices = [3,3,5,0,0,3,1,4]
 * 输出：6
 * 解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 *      随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 *
 * 示例 2：
 * 输入：prices = [1,2,3,4,5]
 * 输出：4
 * 解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。  
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。  
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 *
 * 示例 3：
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这个情况下, 没有交易完成, 所以最大利润为 0。
 *
 * 示例 4：
 * 输入：prices = [1]
 * 输出：0
 *  
 * 提示：
 * 1 <= prices.length <= 105
 * 0 <= prices[i] <= 105
 */
public class BuyStock3 {

    public int maxProfit(int[] prices) {
        int length = prices.length;
        if (length == 1) {
            return 0;
        }
        //动态规划分析：总共5中状态：没有买卖，第一次购买；第一次卖出；第二次购买；第二次卖出
        //1,动态规划初始化条件。
        int preBuy1 = -prices[0];
        int preSe111 = 0;
        int preBbuy2 = -prices[0];
        int preSell2 = 0;
        int buy1;
        int sell1 = 0;
        int buy2;
        int sell2 = 0;
        for (int i = 1; i < length; i++) {
            //当天的价格
            int price = prices[i];
            //当天首次购买 = 当天购买,之前购买，当天不进行买卖,并且取最大值
            buy1 = Math.max(-price, preBuy1);
            //当天卖出 = 之前购买今天卖出，之前已经一次买卖，今天忽略,同样取最大值
            sell1 = Math.max(price + preBuy1, preSe111);
            //当天二次购买 = 当前购买,之前一次买卖 , 之前已经二次购买
            buy2 = Math.max(preSe111 - price, preBbuy2);
            //当天二次买卖 = 当天卖出,之前二次购买 , 之前已经二次买卖
            sell2 = Math.max(price + preBbuy2, preSell2);
            if (i < length - 1) {
                preBuy1 = buy1;
                preSe111 = sell1;
                preBbuy2 = buy2;
                preSell2 = sell2;
            }
        }
        return Math.max(sell1, sell2);
    }
}
