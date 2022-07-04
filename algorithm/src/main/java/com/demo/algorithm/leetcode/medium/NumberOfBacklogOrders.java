package com.demo.algorithm.leetcode.medium;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.PriorityQueue;

/**
 * create on 2022/7/4
 *
 * @author chenglong
 * description : 积压订单中的订单总数
 *
 * 给你一个二维整数数组orders ，其中每个orders[i]=[pricei, amounti, orderTypei] 表示有amounti笔类型为orderTypei、价格为pricei的订单。
 * 订单类型orderTypei可以分为两种：
 * 0 表示这是一批采购订单 buy
 * 1 表示这是一批销售订单 sell
 * 注意，orders[i]表示一批共计amounti笔的独立订单，这些订单的价格和类型相同。对于所有有效的i，由orders[i]表示的所有订单提交时间均早于orders[i+1]表示的所有订单。
 * 存在由未执行订单组成的积压订单。积压订单最初是空的。提交订单时，会发生以下情况：
 * 如果该订单是一笔采购订单buy ，则可以查看积压订单中价格最低的销售订单sell 。如果该销售订单sell的价格低于或等于当前采购订单buy的价格，则匹配并执行这两笔订单，并将销售订单sell从积压订单中删除。否则，采购订单buy将会添加到积压订单中。
 * 反之亦然，如果该订单是一笔销售订单sell，则可以查看积压订单中价格最高的采购订单buy。如果该采购订单buy的价格高于或等于当前销售订单sell的价格，则匹配并执行这两笔订单，
 * 并将采购订单buy从积压订单中删除。否则，销售订单sell将会添加到积压订单中。
 * 输入所有订单后，返回积压订单中的订单总数 。由于数字可能很大，所以需要返回对10^9 + 7 取余的结果。
 *
 * 示例 1：
 * 输入：orders = [[10,5,0],[15,2,1],[25,1,1],[30,4,0]]
 * 输出：6
 * 解释：输入订单后会发生下述情况：
 * - 提交 5 笔采购订单，价格为 10 。没有销售订单，所以这 5 笔订单添加到积压订单中。
 * - 提交 2 笔销售订单，价格为 15 。没有采购订单的价格大于或等于 15 ，所以这 2 笔订单添加到积压订单中。
 * - 提交 1 笔销售订单，价格为 25 。没有采购订单的价格大于或等于 25 ，所以这 1 笔订单添加到积压订单中。
 * - 提交 4 笔采购订单，价格为 30 。前 2 笔采购订单与价格最低（价格为 15）的 2 笔销售订单匹配，从积压订单中删除这 2 笔销售订单。第 3 笔采购订单与价格最低的 1 笔销售订单匹配，销售订单价格为 25 ，从积压订单中删除这 1 笔销售订单。积压订单中不存在更多销售订单，所以第 4 笔采购订单需要添加到积压订单中。
 * 最终，积压订单中有 5 笔价格为 10 的采购订单，和 1 笔价格为 30 的采购订单。所以积压订单中的订单总数为 6 。
 *
 * 示例 2：
 * 输入：orders = [[7,1000000000,1],[15,3,0],[5,999999995,0],[5,1,1]]
 * 输出：999999984
 * 解释：输入订单后会发生下述情况：
 * - 提交 109 笔销售订单，价格为 7 。没有采购订单，所以这 109 笔订单添加到积压订单中。
 * - 提交 3 笔采购订单，价格为 15 。这些采购订单与价格最低（价格为 7 ）的 3 笔销售订单匹配，从积压订单中删除这 3 笔销售订单。
 * - 提交 999999995 笔采购订单，价格为 5 。销售订单的最低价为 7 ，所以这 999999995 笔订单添加到积压订单中。
 * - 提交 1 笔销售订单，价格为 5 。这笔销售订单与价格最高（价格为 5 ）的 1 笔采购订单匹配，从积压订单中删除这 1 笔采购订单。
 * 最终，积压订单中有 (1000000000-3) 笔价格为 7 的销售订单，和 (999999995-1) 笔价格为 5 的采购订单。所以积压订单中的订单总数为 1999999991 ，等于 999999984 % (109 + 7) 。
 *
 * 提示：
 * 1 <= orders.length <= 10^5
 * orders[i].length == 3
 * 1 <= pricei, amounti <= 10^9
 * orderTypei 为 0 或 1
 */
public class NumberOfBacklogOrders {

    private static final int MOD = 1000000007;

    private static final int TYPE_BUY = 0;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int getNumberOfBacklogOrders(int[][] orders) {
        int length = orders.length;
        //使用优先队列，buyDates从大到小，sellDates从小到大
        PriorityQueue<int[]> buyDates = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
        PriorityQueue<int[]> sellDates = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        for (int i = 0; i < length; i++) {
            int[] order = orders[i];
            if (order[2] == TYPE_BUY) {
                //当前是购买订单
                while (sellDates.size() > 0) {
                    int[] peek = sellDates.peek();
                    if (peek[0] > order[0]) {
                        break;
                    }
                    if (peek[1] > order[1]) {
                        peek[1] -= order[1];
                        order[1] = 0;
                        break;
                    } else if (peek[1] == order[1]) {
                        order[1] = 0;
                        sellDates.poll();
                        break;
                    } else {
                        order[1] -= peek[1];
                        sellDates.poll();
                    }
                }
                if (order[1] > 0) {
                    buyDates.offer(order);
                }
            } else {
                //当前为销售订单
                while (buyDates.size() > 0) {
                    int[] peek = buyDates.peek();
                    if (peek[0] < order[0]) {
                        break;
                    }
                    if (peek[1] > order[1]) {
                        peek[1] -= order[1];
                        order[1] = 0;
                        break;
                    } else if (peek[1] == order[1]) {
                        order[1] = 0;
                        buyDates.poll();
                        break;
                    } else {
                        order[1] -= peek[1];
                        buyDates.poll();
                    }
                }
                if (order[1] > 0) {
                    sellDates.offer(order);
                }
            }
        }
        int total = 0;
        while (sellDates.size() > 0) {
            int[] poll = sellDates.poll();
            total += poll[1];
            total %= MOD;
        }
        while (buyDates.size() > 0) {
            int[] poll = buyDates.poll();
            total += poll[1];
            total %= MOD;
        }
        return total;
    }
}
