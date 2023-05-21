package com.demo.algorithm.leetcode.hard;

/**
 * create on 2023/5/4
 * @author chenglong
 * description : 摘水果
 *
 * 在一个无限的x坐标轴上，有许多水果分布在其中某些位置。给你一个二维整数数组fruits，其中fruits[i]=[positioni,amounti]表示共有amounti个水果放置在positioni上。
 * fruits已经按positioni升序排列，每个positioni互不相同。
 * 另给你两个整数startPos和k。最初，你位于startPos。从任何位置，你可以选择向左或者向右走。在x轴上每移动一个单位，就记作一步。你总共可以走最多k步。
 * 你每达到一个位置，都会摘掉全部的水果，水果也将从该位置消失（不会再生）。
 * 返回你可以摘到水果的最大总数。
 *
 * 示例 1：
 * 输入：fruits = [[2,8],[6,3],[8,6]], startPos = 5, k = 4
 * 输出：9
 * 解释：
 * 最佳路线为：
 * - 向右移动到位置 6 ，摘到 3 个水果
 * - 向右移动到位置 8 ，摘到 6 个水果
 * 移动 3 步，共摘到 3 + 6 = 9 个水果
 *
 * 示例 2：
 * 输入：fruits = [[0,9],[4,1],[5,7],[6,2],[7,4],[10,9]], startPos = 5, k = 4
 * 输出：14
 * 解释：
 * 可以移动最多 k = 4 步，所以无法到达位置 0 和位置 10 。
 * 最佳路线为：
 * - 在初始位置 5 ，摘到 7 个水果
 * - 向左移动到位置 4 ，摘到 1 个水果
 * - 向右移动到位置 6 ，摘到 2 个水果
 * - 向右移动到位置 7 ，摘到 4 个水果
 * 移动 1 + 3 = 4 步，共摘到 7 + 1 + 2 + 4 = 14 个水果
 *
 * 示例 3：
 * 输入：fruits = [[0,3],[6,4],[8,5]], startPos = 3, k = 2
 * 输出：0
 * 解释：
 * 最多可以移动 k = 2 步，无法到达任一有水果的地方
 *
 * 提示：
 * 1 <= fruits.length <= 10^5
 * fruits[i].length == 2
 * 0 <= startPos, positioni <= 2 * 10^5
 * 对于任意 i > 0 ，positioni-1 < positioni均成立（下标从0开始计数）
 * 1 <= amounti <= 10^4
 * 0 <= k <= 2 * 10^5
 */
public class MaxTotalFruits {

    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int n = fruits.length;
        int maxPosition = fruits[n - 1][0];
        int[] dates = new int[maxPosition + 1];
        for (int i = 0; i < n; i++) {
            dates[fruits[i][0]] = fruits[i][1];
        }
        //统计前缀和
        for (int i = 1; i < dates.length; i++) {
            dates[i] += dates[i - 1];
        }
        int max = 0;
        //向左然后折返
        int minPosition = Math.max(startPos - k, 0);
        for (int left = minPosition; left <= startPos; left++) {
            int right = Math.max(startPos, 2 * left + k - startPos);
            boolean isEnd = false;
            if (right > maxPosition) {
                right = maxPosition;
                isEnd = true;
            }
            //可以移动的区间[left,right]
            if (left > right) {
                continue;
            }
            int tem = dates[right];
            if (left > 0) {
                tem -= dates[left - 1];
            }
            if (tem > max) {
                max = tem;
            }
            if (isEnd) {
                break;
            }
        }
        //向右然后折返
        int maxRight = Math.min(startPos + k, maxPosition);
        for (int right = maxRight; right >= startPos; right--) {
            int left = Math.min(startPos, 2 * right - k - startPos);
            boolean isEnd = false;
            if (left < 0) {
                left = 0;
                isEnd = true;
            }
            //区间[left,right]
            if (left > right) {
                continue;
            }
            int tem = dates[right];
            if (left > 0) {
                tem -= dates[left - 1];
            }
            if (tem > max) {
                max = tem;
            }
            if (isEnd) {
                break;
            }
        }
        return max;
    }
}
