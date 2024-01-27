package com.demo.algorithm.leetcode.medium;

import java.util.List;

/**
 * create on 2024/1/27
 * @author chenglong
 * description : 最大合金数
 *
 * 假设你是一家合金制造公司的老板，你的公司使用多种金属来制造合金。现在共有n种不同类型的金属可以使用，并且你可以使用k台机器来制造合金。每台机器都需要特定数量的每种金属来创建合金。
 * 对于第i台机器而言，创建合金需要composition[i][j]份j类型金属。最初，你拥有 stock[i]份i类型金属，而每购入一份i类型金属需要花费cost[i]的金钱。
 * 给你整数n、k、budget，下标从1开始的二维数组composition，两个下标从1开始的数组stock和cost，请你在预算不超过budget金钱的前提下，最大化公司制造合金的数量。
 * 所有合金都需要由同一台机器制造。
 * 返回公司可以制造的最大合金数。
 *
 * 示例 1：
 * 输入：n = 3, k = 2, budget = 15, composition = [[1,1,1],[1,1,10]], stock = [0,0,0], cost = [1,2,3]
 * 输出：2
 * 解释：最优的方法是使用第 1 台机器来制造合金。
 * 要想制造 2 份合金，我们需要购买：
 * - 2 份第 1 类金属。
 * - 2 份第 2 类金属。
 * - 2 份第 3 类金属。
 * 总共需要 2 * 1 + 2 * 2 + 2 * 3 = 12 的金钱，小于等于预算 15 。
 * 注意，我们最开始时候没有任何一类金属，所以必须买齐所有需要的金属。
 * 可以证明在示例条件下最多可以制造 2 份合金。
 *
 * 示例 2：
 * 输入：n = 3, k = 2, budget = 15, composition = [[1,1,1],[1,1,10]], stock = [0,0,100], cost = [1,2,3]
 * 输出：5
 * 解释：最优的方法是使用第 2 台机器来制造合金。
 * 要想制造 5 份合金，我们需要购买：
 * - 5 份第 1 类金属。
 * - 5 份第 2 类金属。
 * - 0 份第 3 类金属。
 * 总共需要 5 * 1 + 5 * 2 + 0 * 3 = 15 的金钱，小于等于预算 15 。
 * 可以证明在示例条件下最多可以制造5份合金。
 *
 * 示例 3：
 * 输入：n = 2, k = 3, budget = 10, composition = [[2,1],[1,2],[1,1]], stock = [1,1], cost = [5,5]
 * 输出：2
 * 解释：最优的方法是使用第 3 台机器来制造合金。
 * 要想制造 2 份合金，我们需要购买：
 * - 1 份第 1 类金属。
 * - 1 份第 2 类金属。
 * 总共需要 1 * 5 + 1 * 5 = 10 的金钱，小于等于预算 10 。
 * 可以证明在示例条件下最多可以制造 2 份合金。
 *
 * 提示：
 * 1 <= n, k <= 100
 * 0 <= budget <= 10^8
 * composition.length == k
 * composition[i].length == n
 * 1 <= composition[i][j] <= 100
 * stock.length == cost.length == n
 * 0 <= stock[i] <= 10^8
 * 1 <= cost[i] <= 100
 */
public class NumberOfAlloys {

    public int maxNumberOfAlloys(int n, int k, int budget, List<List<Integer>> composition, List<Integer> stock, List<Integer> cost) {
        long min = 0;
        long max = 0;
        //1，计算总共金额
        long base = 1;
        long total = budget;
        for (int i = 0; i < n; i++) {
            total += (base * stock.get(i) * cost.get(i));
        }
        //2，计算每份合金的总价格，获取最大值
        for (int i = 0; i < k; i++) {
            long sum = 0;
            for (int j = 0; j < n; j++) {
                sum += (base * composition.get(i).get(j) * cost.get(j));
            }
            long count = total / sum;
            if (total % sum != 0) {
                count++;
            }
            if (count > max) {
                max = count;
            }
        }
        //3，二分查找满足条件的数量
        while (min < max) {
            long middle = (min + max) / 2;
            if (check(middle, budget, composition, stock, cost)) {
                min = middle + 1;
                if (!check(min, budget, composition, stock, cost)) {
                    return (int) middle;
                }
            } else {
                max = middle - 1;
            }
        }
        return (int) min;
    }

    private boolean check(long count, int budget, List<List<Integer>> composition, List<Integer> stock, List<Integer> cost) {
        long base = 1;
        for (int i = 0; i < composition.size(); i++) {
            //计算生产count个合金的花费
            long sum = 0;
            for (int j = 0; j < stock.size(); j++) {
                long needCount = count * composition.get(i).get(j);
                if (needCount > stock.get(j)) {
                    long buyCount = needCount - stock.get(j);
                    sum += (base * buyCount * cost.get(j));
                    if (sum > budget) {
                        break;
                    }
                }
            }
            if (sum <= budget) {
                return true;
            }
        }
        return false;
    }
}


