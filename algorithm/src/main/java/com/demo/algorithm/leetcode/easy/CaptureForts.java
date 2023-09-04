package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/9/4
 * @author chenglong
 * description : 最多可以摧毁的敌人城堡数目
 *
 * 给你一个长度为n，下标从0开始的整数数组forts，表示一些城堡。forts[i]可以是-1，0或者1，其中：
 * -1表示第i个位置没有城堡。
 * 0表示第i个位置有一个敌人的城堡。
 * 1表示第i个位置有一个你控制的城堡。
 * 现在，你需要决定，将你的军队从某个你控制的城堡位置i移动到一个空的位置j ，满足：
 * 0 <= i, j <= n-1
 * 军队经过的位置只有敌人的城堡。正式的，对于所有 min(i,j) < k < max(i,j) 的 k ，都满足 forts[k] == 0 。
 * 当军队移动时，所有途中经过的敌人城堡都会被摧毁 。
 * 请你返回最多可以摧毁的敌人城堡数目。如果无法移动你的军队，或者没有你控制的城堡，请返回0。
 *
 * 示例 1：
 * 输入：forts = [1,0,0,-1,0,0,0,0,1]
 * 输出：4
 * 解释：
 * - 将军队从位置 0 移动到位置 3 ，摧毁 2 个敌人城堡，位置分别在 1 和 2 。
 * - 将军队从位置 8 移动到位置 3 ，摧毁 4 个敌人城堡。
 * 4 是最多可以摧毁的敌人城堡数目，所以我们返回 4 。
 *
 * 示例 2：
 * 输入：forts = [0,0,1,-1]
 * 输出：0
 * 解释：由于无法摧毁敌人的城堡，所以返回 0 。
 *
 * 提示：
 * 1 <= forts.length <= 1000
 * -1 <= forts[i] <= 1
 */
public class CaptureForts {

    public int captureForts(int[] forts) {
        int max = 0;
        int startIndex = -1;
        for (int i = 0; i < forts.length; i++) {
            if (forts[i] != 0) {
                if (startIndex == -1) {
                    startIndex = i;
                } else {
                    //此时区间为：[startIndex,i]
                    if (forts[startIndex] != forts[i]) {
                        int count = i - startIndex - 1;
                        if (count > max) {
                            max = count;
                        }
                    }
                    startIndex = i;
                }
            }
        }
        return max;
    }
}
