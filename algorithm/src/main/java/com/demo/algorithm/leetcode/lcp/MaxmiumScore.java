package com.demo.algorithm.leetcode.lcp;

import java.util.Arrays;

/**
 * create on 2023/12/22
 * @author chenglong
 * description : LCP 40. 心算挑战
 *
 * 「力扣挑战赛」心算项目的挑战比赛中，要求选手从N张卡牌中选出cnt张卡牌，若这cnt张卡牌数字总和为偶数，则选手成绩「有效」且得分为cnt张卡牌数字总和。
 * 给定数组cards和cnt，其中cards[i]表示第i张卡牌上的数字。 请帮参赛选手计算最大的有效得分。若不存在获取有效得分的卡牌方案，则返回 0。
 *
 * 示例 1：
 * 输入：cards = [1,2,8,9], cnt = 3
 * 输出：18
 * 解释：选择数字为 1、8、9 的这三张卡牌，此时可获得最大的有效得分 1+8+9=18。
 *
 * 示例 2：
 * 输入：cards = [3,3,1], cnt = 1
 * 输出：0
 * 解释：不存在获取有效得分的卡牌方案。
 *
 * 提示：
 * 1 <= cnt <= cards.length <= 10^5
 * 1 <= cards[i] <= 1000
 */
public class MaxmiumScore {

    public int maxmiumScore(int[] cards, int cnt) {
        //1，对卡牌进行排序
        Arrays.sort(cards);
        //2，从最大到最小依次取cnt张卡牌
        int minIndex = cards.length - cnt;
        int sum = 0;
        for (int i = cards.length - 1; i >= minIndex; i--) {
            sum += cards[i];
        }
        if (sum % 2 == 0) {
            return sum;
        }
        //3，从sum中取最小的奇数换取剩余中最大的偶数
        int sum1 = 0;
        int findIndex1 = -1;
        int findIndex2 = -1;
        for (int i = minIndex; i < cards.length; i++) {
            if (cards[i] % 2 == 1) {
                findIndex1 = i;
                break;
            }
        }
        if (findIndex1 != -1) {
            for (int i = minIndex - 1; i >= 0; i--) {
                if (cards[i] % 2 == 0) {
                    findIndex2 = i;
                    break;
                }
            }
            if (findIndex2 != -1) {
                sum1 = sum - cards[findIndex1] + cards[findIndex2];
            }
        }
        //3，从sum中取最小的偶数换取剩余中最大的奇数
        int sum2 = 0;
        findIndex1 = -1;
        findIndex2 = -1;
        for (int i = minIndex; i < cards.length; i++) {
            if (cards[i] % 2 == 0) {
                findIndex1 = i;
                break;
            }
        }
        if (findIndex1 != -1) {
            for (int i = minIndex - 1; i >= 0; i--) {
                if (cards[i] % 2 == 1) {
                    findIndex2 = i;
                    break;
                }
            }
            if (findIndex2 != -1) {
                sum2 = sum - cards[findIndex1] + cards[findIndex2];
            }
        }
        return Math.max(sum1, sum2);
    }
}
