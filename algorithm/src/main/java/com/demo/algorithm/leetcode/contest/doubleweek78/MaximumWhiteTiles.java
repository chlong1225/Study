package com.demo.algorithm.leetcode.contest.doubleweek78;

import java.util.Arrays;

/**
 * Created by chl on 2022/5/15.
 * description : 毯子覆盖的最多白色砖块数
 *
 * 给你一个二维整数数组tiles，其中tiles[i] = [li, ri]，表示所有在li <= j <= ri之间的每个瓷砖位置j都被涂成了白色。
 * 同时给你一个整数carpetLen，表示可以放在任何位置的一块毯子。
 * 请你返回使用这块毯子，最多可以盖住多少块瓷砖。
 *
 * 示例 1：
 * 输入：tiles = [[1,5],[10,11],[12,18],[20,25],[30,32]], carpetLen = 10
 * 输出：9
 * 解释：将毯子从瓷砖 10 开始放置。
 * 总共覆盖 9 块瓷砖，所以返回 9 。
 * 注意可能有其他方案也可以覆盖 9 块瓷砖。
 * 可以看出，瓷砖无法覆盖超过 9 块瓷砖。
 *
 * 示例 2：
 * 输入：tiles = [[10,11],[1,1]], carpetLen = 2
 * 输出：2
 * 解释：将毯子从瓷砖 10 开始放置。
 * 总共覆盖 2 块瓷砖，所以我们返回 2 。
 *
 * 提示：
 *
 * 1 <= tiles.length <= 5 * 10^4
 * tiles[i].length == 2
 * 1 <= li <= ri <= 10^9
 * 1 <= carpetLen <= 10^9
 * tiles互相不会重叠。
 */
public class MaximumWhiteTiles {

    public int maximumWhiteTiles(int[][] tiles, int carpetLen) {
        //1，对区间进行排序
        Arrays.sort(tiles, (o1, o2) -> o1[0] - o2[0]);
        int length = tiles.length;
        int max = 0;
        //记录完整的区间和
        int total = 0;
        //记录不完整的区间和
        int total2 = 0;
        //2，查找首次满足条件的区间
        int end = -1;
        int find = tiles[0][0] + carpetLen - 1;
        for (int i = 0; i < length; i++) {
            int[] cur = tiles[i];
            if (cur[0] > find) {
                break;
            }
            if (cur[1] > find) {
                total2 = find - cur[0] + 1;
                break;
            } else {
                total += (cur[1] - cur[0] + 1);
                end = i;
            }
        }
        max = total + total2;
        if (end == -1) {
            return max;
        }
        //3,使用滑动窗口的方式
        for (int i = 1; i < length; i++) {
            //去掉上一个区间
            total -= (tiles[i - 1][1] - tiles[i - 1][0] + 1);
            total2 = 0;
            find = tiles[i][0] + carpetLen - 1;
            while (end < length - 1) {
                end++;
                if (tiles[end][0] > find) {
                    end--;
                    break;
                }
                if (tiles[end][1] > find) {
                    total2 = find - tiles[end][0] + 1;
                    if (total + total2 > max) {
                        max = total + total2;
                    }
                    end--;
                    break;
                } else {
                    total += (tiles[end][1] - tiles[end][0] + 1);
                }
            }
            if (total + total2 > max) {
                max = total + total2;
            }
            if (end == length - 1) {
                return max;
            }
        }
        return max;
    }
}
