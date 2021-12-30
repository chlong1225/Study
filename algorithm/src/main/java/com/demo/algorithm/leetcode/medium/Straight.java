package com.demo.algorithm.leetcode.medium;

import java.util.Arrays;

/**
 * create on 12/30/21
 * @author chenglong
 * description : 一手顺子
 *
 * Alice 手中有一把牌，她想要重新排列这些牌，分成若干组，使每一组的牌数都是 groupSize ，并且由 groupSize 张连续的牌组成。
 * 给你一个整数数组 hand 其中 hand[i] 是写在第 i 张牌，和一个整数 groupSize 。如果她可能重新排列这些牌，返回 true ；否则，返回 false 。
 *
 * 示例 1：
 * 输入：hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
 * 输出：true
 * 解释：Alice 手中的牌可以被重新排列为 [1,2,3]，[2,3,4]，[6,7,8]。
 *
 * 示例 2：
 * 输入：hand = [1,2,3,4,5], groupSize = 4
 * 输出：false
 * 解释：Alice 手中的牌无法被重新排列成几个大小为 4 的组。
 *  
 * 提示：
 * 1 <= hand.length <= 10^4
 * 0 <= hand[i] <= 10^9
 * 1 <= groupSize <= hand.length
 */
public class Straight {

    public boolean isNStraightHand(int[] hand, int groupSize) {
        int length = hand.length;
        //1，groupSize为1肯定可以组合
        if (groupSize == 1) {
            return true;
        }
        //2，手上牌的数量必须是groupSize的倍数
        if (length % groupSize != 0) {
            return false;
        }
        //3，将手上的牌进行排序
        Arrays.sort(hand);
        //4，查找组合，已经使用的重置为-1
        int findStart = hand[0];
        int findEnd = findStart + groupSize - 1;
        int findCount = 1;
        int findNext = findStart + 1;
        int findNextCount = 0;
        int nextIndex = 0;
        int i = 1;
        while (i < length) {
            int tem = hand[i];
            if (tem == -1) {
                i++;
                continue;
            }
            if (findStart == tem) {
                findCount++;
                hand[i] = -1;
            } else {
                if (tem == findNext) {
                    findNextCount++;
                    if (findNextCount <= findCount) {
                        hand[i] = -1;
                    } else {
                        if (nextIndex == 0) {
                            nextIndex = i;
                        }
                    }
                } else {
                    if (findNextCount < findCount) {
                        return false;
                    }
                    if (findNext == findEnd) {
                        //findStart对应的查找结束了。重置起始位置
                        if (nextIndex == 0) {
                            findStart = tem;
                            findEnd = findStart + groupSize - 1;
                            findCount = 1;
                            findNext = findStart + 1;
                            hand[i] = -1;
                            findNextCount = 0;
                        } else {
                            findStart = hand[nextIndex];
                            hand[nextIndex] = -1;
                            i = nextIndex + 1;
                            nextIndex = 0;
                            findEnd = findStart + groupSize - 1;
                            findCount = 1;
                            findNext = findStart + 1;
                            findNextCount = 0;
                            continue;
                        }
                    } else {
                        if (tem - findNext > 1) {
                            return false;
                        }
                        findNext = tem;
                        findNextCount = 1;
                        hand[i] = -1;
                    }
                }
            }
            i++;
        }
        if (findNext != findEnd || findNextCount != findCount || nextIndex != 0) {
            return false;
        }
        return true;
    }
}
