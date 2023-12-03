package com.demo.algorithm.leetcode.easy;

import java.util.Arrays;

/**
 * create by chenglong on 2023/12/3
 * description : 公平的糖果交换
 *
 * 爱丽丝和鲍勃拥有不同总数量的糖果。给你两个数组aliceSizes和bobSizes，aliceSizes[i]是爱丽丝拥有的第i盒糖果中的糖果数量，bobSizes[j]是鲍勃拥有的第j盒糖果中的糖果数量。
 * 两人想要互相交换一盒糖果，这样在交换之后，他们就可以拥有相同总数量的糖果。一个人拥有的糖果总数量是他们每盒糖果数量的总和。
 * 返回一个整数数组answer，其中answer[0]是爱丽丝必须交换的糖果盒中的糖果的数目，answer[1]是鲍勃必须交换的糖果盒中的糖果的数目。
 * 如果存在多个答案，你可以返回其中任何一个。题目测试用例保证存在与输入对应的答案。
 *
 * 示例 1：
 * 输入：aliceSizes = [1,1], bobSizes = [2,2]
 * 输出：[1,2]
 *
 * 示例 2：
 * 输入：aliceSizes = [1,2], bobSizes = [2,3]
 * 输出：[1,2]
 *
 * 示例 3：
 * 输入：aliceSizes = [2], bobSizes = [1,3]
 * 输出：[2,3]
 *
 * 示例 4：
 * 输入：aliceSizes = [1,2,5], bobSizes = [2,4]
 * 输出：[5,4]
 *
 * 提示：
 * 1 <= aliceSizes.length, bobSizes.length <= 10^4
 * 1 <= aliceSizes[i], bobSizes[j] <= 10^5
 * 爱丽丝和鲍勃的糖果总数量不同。
 * 题目数据保证对于给定的输入至少存在一个有效答案。
 */
public class FairCandySwap {

    public int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {
        //1，统计两个人初始糖果的总数
        int sum1 = 0;
        for (int i = 0; i < aliceSizes.length; i++) {
            sum1 += aliceSizes[i];
        }
        int sum2 = 0;
        for (int i = 0; i < bobSizes.length; i++) {
            sum2 += bobSizes[i];
        }
        int diff = (sum1 - sum2) / 2;
        //2，给爱丽丝的糖果进行排序
        Arrays.sort(aliceSizes);
        for (int i = 0; i < bobSizes.length; i++) {
            int find = diff + bobSizes[i];
            int index = findIndex(find, aliceSizes);
            if (index != -1) {
                return new int[]{aliceSizes[index], bobSizes[i]};
            }
        }
        return null;
    }

    private int findIndex(int target, int[] dates) {
        int start = 0;
        int end = dates.length - 1;
        if (target < dates[start] || target > dates[end]) {
            return -1;
        }
        if (dates[start] == target) {
            return start;
        }
        if (dates[end] == target) {
            return end;
        }
        while (start <= end) {
            int middle = (end - start) / 2 + start;
            if (dates[middle] == target) {
                return middle;
            } else if (dates[middle] < target) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }
        return -1;
    }
}
