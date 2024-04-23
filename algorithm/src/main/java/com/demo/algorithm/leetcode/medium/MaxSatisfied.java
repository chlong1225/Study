package com.demo.algorithm.leetcode.medium;

/**
 * create on 2024/4/23
 * @author chenglong
 * description : 爱生气的书店老板
 *
 * 有一个书店老板，他的书店开了n分钟。每分钟都有一些顾客进入这家商店。给定一个长度为n的整数数组customers，其中customers[i]是在第i分钟开始时进入商店的顾客数量，所有这些顾客在第i分钟结束后离开。
 * 在某些时候，书店老板会生气。如果书店老板在第i分钟生气，那么grumpy[i]=1，否则grumpy[i]=0。
 * 当书店老板生气时，那一分钟的顾客就会不满意，若老板不生气则顾客是满意的。
 * 书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续minutes分钟不生气，但却只能使用一次。
 * 请你返回这一天营业下来，最多有多少客户能够感到满意 。
 *
 * 示例 1：
 * 输入：customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], minutes = 3
 * 输出：16
 * 解释：书店老板在最后3分钟保持冷静。
 * 感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.
 *
 * 示例 2：
 * 输入：customers = [1], grumpy = [0], minutes = 1
 * 输出：1
 *
 * 提示：
 * n == customers.length == grumpy.length
 * 1 <= minutes <= n <= 2 * 10^4
 * 0 <= customers[i] <= 1000
 * grumpy[i] == 0 or 1
 */
public class MaxSatisfied {

    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int n = customers.length;
        //1，统计不保持冷静时满意的总数
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (grumpy[i] == 0) {
                sum += customers[i];
            }
        }
        //2，统计连续minutes时间内保存冷静新增的满意人数
        int max = 0;
        int add = 0;
        for (int i = 0; i < minutes; i++) {
            if (grumpy[i] == 1) {
                //当前应该生气保存冷静
                add += customers[i];
            }
        }
        if (add > max) {
            max = add;
        }
        //3，连续滑动窗口
        for (int i = minutes; i < n; i++) {
            int preIndex = i - minutes;
            if (grumpy[preIndex] == 1) {
                add -= customers[preIndex];
            }
            if (grumpy[i] == 1) {
                add += customers[i];
            }
            if (add > max) {
                max = add;
            }
        }
        return sum + max;
    }
}
