package com.demo.algorithm.leetcode.contest.doubleweek83;

import java.util.Arrays;

/**
 * create on 2022/8/2
 * @author chenglong
 * description : 最好的扑克手牌
 *
 * 给你一个整数数组ranks和一个字符数组suit。你有5张扑克牌，第i张牌大小为ranks[i]，花色为suits[i]。
 * 下述是从好到坏你可能持有的手牌类型：
 * "Flush"：同花，五张相同花色的扑克牌。
 * "Three of a Kind"：三条，有3张大小相同的扑克牌。
 * "Pair"：对子，两张大小一样的扑克牌。
 * "High Card"：高牌，五张大小互不相同的扑克牌。
 * 请你返回一个字符串，表示给定的5张牌中，你能组成的最好手牌类型。
 * 注意：返回的字符串大小写需与题目描述相同。
 *
 * 示例 1：
 * 输入：ranks = [13,2,3,1,9], suits = ["a","a","a","a","a"]
 * 输出："Flush"
 * 解释：5 张扑克牌的花色相同，所以返回 "Flush" 。
 *
 * 示例 2：
 * 输入：ranks = [4,4,2,4,4], suits = ["d","a","a","b","c"]
 * 输出："Three of a Kind"
 * 解释：第一、二和四张牌组成三张相同大小的扑克牌，所以得到 "Three of a Kind" 。
 * 注意我们也可以得到 "Pair" ，但是 "Three of a Kind" 是更好的手牌类型。
 * 有其他的 3 张牌也可以组成 "Three of a Kind" 手牌类型。
 *
 * 示例 3：
 * 输入：ranks = [10,10,2,12,9], suits = ["a","b","c","a","d"]
 * 输出："Pair"
 * 解释：第一和第二张牌大小相同，所以得到 "Pair" 。
 * 我们无法得到 "Flush" 或者 "Three of a Kind" 。
 *
 * 提示：
 * ranks.length == suits.length == 5
 * 1 <= ranks[i] <= 13
 * 'a' <= suits[i] <= 'd'
 * 任意两张扑克牌不会同时有相同的大小和花色。
 */
public class BestHand {

    private static final String FLUSH = "Flush";
    private static final String THREE = "Three of a Kind";
    private static final String PAIR = "Pair";
    private static final String HIGH = "High Card";


    public String bestHand(int[] ranks, char[] suits) {
        int length = suits.length;
        //1，判断是否为同花顺
        boolean isSame = true;
        for (int i = 1; i < length; i++) {
            if (suits[i] != suits[i - 1]) {
                isSame = false;
                break;
            }
        }
        if (isSame) {
            return FLUSH;
        }
        //2，判断相同的数量
        Arrays.sort(ranks);
        int count = 1;
        int max = 1;
        int pre = ranks[0];
        for (int i = 1; i < length; i++) {
            if (ranks[i] == pre) {
                count++;
            } else {
                if (count > max) {
                    max = count;
                }
                count = 1;
            }
            pre = ranks[i];
        }
        if (count > max) {
            max = count;
        }
        if (max >= 3) {
            return THREE;
        }
        if (max == 2) {
            return PAIR;
        }
        return HIGH;
    }
}
