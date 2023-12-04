package com.demo.algorithm.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * create on 2023/12/4
 * @author chenglong
 * description : 卡牌分组
 *
 * 给定一副牌，每张牌上都写着一个整数。
 * 此时，你需要选定一个数字X，使我们可以将整副牌按下述规则分成1组或更多组：
 * 每组都有X张牌。
 * 组内所有的牌上都写着相同的整数。
 * 仅当你可选的X>=2时返回true。
 *
 * 示例 1：
 * 输入：deck = [1,2,3,4,4,3,2,1]
 * 输出：true
 * 解释：可行的分组是 [1,1]，[2,2]，[3,3]，[4,4]
 *
 * 示例 2：
 * 输入：deck = [1,1,1,2,2,2,3,3]
 * 输出：false
 * 解释：没有满足要求的分组。
 *
 * 提示：
 * 1 <= deck.length <= 10^4
 * 0 <= deck[i] < 10^4
 */
public class GroupsSizeX {

    public boolean hasGroupsSizeX(int[] deck) {
        int n = deck.length;
        if (n == 1) {
            return false;
        }
        //1，统计整数的个数
        Map<Integer, Integer> marks = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (marks.containsKey(deck[i])) {
                marks.put(deck[i], marks.get(deck[i]) + 1);
            } else {
                marks.put(deck[i], 1);
            }
        }
        int x = 0;
        int preCount = 0;
        for (int key : marks.keySet()) {
            int count = marks.get(key);
            if (count == 1) {
                return false;
            }
            if (preCount == 0) {
                preCount = count;
            } else {
                //获取preCount与count的最大公约数
                if (preCount > count) {
                    preCount = gcd(preCount, count);
                    if (preCount == 1) {
                        return false;
                    }
                } else if (preCount < count) {
                    preCount = gcd(count, preCount);
                    if (preCount == 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private int gcd(int a, int b) {
        return b != 0 ? gcd(b, a % b) : a;
    }
}
