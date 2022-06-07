package com.demo.algorithm.leetcode.medium;

/**
 * create on 2022/6/7
 * @author chenglong
 * description : 爱吃香蕉的珂珂
 *
 * 珂珂喜欢吃香蕉。这里有n堆香蕉，第i堆中有piles[i]根香蕉。警卫已经离开了，将在h小时后回来。
 * 珂珂可以决定她吃香蕉的速度k（单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉k根。如果这堆香蕉少于k根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。
 * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
 * 返回她可以在h小时内吃掉所有香蕉的最小速度k（k为整数）。
 *
 * 示例 1：
 * 输入：piles = [3,6,7,11], h = 8
 * 输出：4
 *
 * 示例 2：
 * 输入：piles = [30,11,23,4,20], h = 5
 * 输出：30
 *
 * 示例 3：
 * 输入：piles = [30,11,23,4,20], h = 6
 * 输出：23
 *
 * 提示：
 * 1 <= piles.length <= 10^4
 * piles.length <= h <= 10^9
 * 1 <= piles[i] <= 10^9
 */
public class MinEatingSpeed {

    public int minEatingSpeed(int[] piles, int h) {
        int length = piles.length;
        //1，处理特殊场景
        if (length == 1) {
            int num = piles[0] / h;
            if (piles[0] % h != 0) {
                num++;
            }
            return num;
        }
        //2，遍历获取最大值与求和
        int max = piles[0];
        long sum = piles[0];
        for (int i = 1; i < length; i++) {
            sum += piles[i];
            if (piles[i] > max) {
                max = piles[i];
            }
        }
        if (h == length) {
            return max;
        }
        int min = (int) (sum / h);
        //3，使用二分法遍历min～max之间查找满足条件的值
        while (min < max) {
            int middle = (max - min) / 2 + min;
            int count = getCount(middle, piles);
            if (count <= h) {
                max = middle;
                if (getCount(max - 1, piles) > h) {
                    return max;
                }
            } else {
                min = middle + 1;
            }
        }
        return min;
    }

    private int getCount(int num, int[] piles) {
        int length = piles.length;
        int count = 0;
        for (int i = 0; i < length; i++) {
            count += (piles[i] / num);
            if (piles[i] % num != 0) {
                count++;
            }
        }
        return count;
    }
}
