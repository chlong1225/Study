package com.demo.algorithm.leetcode.contest.week291;

import java.util.HashMap;
import java.util.Map;

/**
 * create on 2022/5/6
 * @author chenglong
 * description : 必须拿起的最小连续卡牌数
 *
 * 给你一个整数数组cards，其中cards[i]表示第i张卡牌的值。如果两张卡牌的值相同，则认为这一对卡牌匹配 。
 * 返回你必须拿起的最小连续卡牌数，以使在拿起的卡牌中有一对匹配的卡牌。如果无法得到一对匹配的卡牌，返回 -1 。
 *
 * 示例 1：
 * 输入：cards = [3,4,2,3,4,7]
 * 输出：4
 * 解释：拿起卡牌 [3,4,2,3] 将会包含一对值为 3 的匹配卡牌。注意，拿起 [4,2,3,4] 也是最优方案。
 *
 * 示例 2：
 * 输入：cards = [1,0,5,3]
 * 输出：-1
 * 解释：无法找出含一对匹配卡牌的一组连续卡牌。
 *
 * 提示：
 * 1 <= cards.length <= 10^5
 * 0 <= cards[i] <= 10^6
 */
public class MinimumCardPickup {

    public int minimumCardPickup(int[] cards) {
        int length = cards.length;
        if (length == 1) {
            return -1;
        }
        int min = Integer.MAX_VALUE;
        //记录当前值对应的index
        Map<Integer, Integer> marks = new HashMap<>();
        marks.put(cards[0], 0);
        for (int i = 1; i < length; i++) {
            if (marks.containsKey(cards[i])) {
                int space = i + 1 - marks.get(cards[i]);
                if (space < min) {
                    min = space;
                }
            }
            marks.put(cards[i], i);
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
