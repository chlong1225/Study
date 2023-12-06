package com.demo.algorithm.leetcode.medium;

/**
 * create by chenglong on 2023/12/2
 * description : 拼车
 *
 * 车上最初有capacity个空座位。车只能向一个方向行驶（也就是说，不允许掉头或改变方向）
 * 给定整数capacity和一个数组trips,trip[i] = [numPassengersi, fromi, toi]表示第i次旅行有numPassengersi乘客，接他们和放他们的位置分别是fromi和toi。
 * 这些位置是从汽车的初始位置向东的公里数。
 * 当且仅当你可以在所有给定的行程中接送所有乘客时，返回true，否则请返回false。
 *
 * 示例 1：
 * 输入：trips = [[2,1,5],[3,3,7]], capacity = 4
 * 输出：false
 *
 * 示例 2：
 * 输入：trips = [[2,1,5],[3,3,7]], capacity = 5
 * 输出：true
 *
 * 提示：
 * 1 <= trips.length <= 1000
 * trips[i].length == 3
 * 1 <= numPassengersi <= 100
 * 0 <= fromi < toi <= 1000
 * 1 <= capacity <= 105
 */
public class CarPooling {

    public boolean carPooling(int[][] trips, int capacity) {
        int[] dates = new int[1001];
        for (int i = 0; i < trips.length; i++) {
            int num = trips[i][0];
            int from = trips[i][1];
            int to = trips[i][2];
            if (num > capacity) {
                return false;
            }
            dates[from] += num;
            dates[to] -= num;
        }
        if (dates[0] > capacity) {
            return false;
        }
        for (int i = 1; i < dates.length; i++) {
            dates[i] += dates[i - 1];
            if (dates[i] > capacity) {
                return false;
            }
        }
        return true;
    }
}
