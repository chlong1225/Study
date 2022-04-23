package com.demo.algorithm.leetcode.offer;

/**
 * Created by chl on 2022/4/23.
 * description : 剑指Offer60. n个骰子的点数
 *
 * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
 * 你需要用一个浮点数数组返回答案，其中第i个元素代表这n个骰子所能掷出的点数集合中第i小的那个的概率。
 *
 * 示例 1:
 * 输入: 1
 * 输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
 *
 * 示例2:
 * 输入: 2
 * 输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]
 *
 * 限制：
 * 1 <= n <= 11
 */
public class DicesProbability {

    public double[] dicesProbability(int n) {
        //1，统计所有出现的总次数
        int total = 1;
        for (int i = 0; i < n; i++) {
            total *= 6;
        }
        int min = 1;
        int max = 6;
        int[] counts = new int[max + 1];
        for (int i = 1; i <= 6; i++) {
            counts[i] = 1;
        }
        n--;
        for (int i = 0; i < n; i++) {
            int size = max * 6;
            int[] next = new int[size + 1];
            for (int j = 1; j <= 6; j++) {
                for (int l = min; l <= max; l++) {
                    next[l + j] += counts[l];
                }
            }
            min++;
            max += 6;
            counts = next;
        }
        double[] result = new double[max - min + 1];
        for (int i = 0; i < result.length; i++) {
            result[i] = counts[i + min] * 1.0 / total;
        }
        return result;
    }
}
