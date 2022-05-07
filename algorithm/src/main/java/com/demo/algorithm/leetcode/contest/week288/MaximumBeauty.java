package com.demo.algorithm.leetcode.contest.week288;

import java.util.Arrays;

/**
 * Created by chl on 2022/4/13.
 * description : 花园的最大总美丽值
 *
 * Alice是n个花园的园丁，她想通过种花，最大化她所有花园的总美丽值。
 * 给你一个下标从0开始大小为n的整数数组flowers，其中flowers[i]是第i个花园里已经种的花的数目。已经种了的花不能移走。
 * 同时给你newFlowers，表示Alice额外可以种花的最大数目。同时给你的还有整数target，full和partial。
 * 如果一个花园有至少target朵花，那么这个花园称为完善的，花园的总美丽值为以下分数之和 ：
 * 完善花园数目乘以full.
 * 剩余不完善花园里花的最少数目乘以partial。如果没有不完善花园，那么这一部分的值为0。
 * 请你返回Alice种最多newFlowers朵花以后，能得到的最大总美丽值。
 *
 * 示例 1：
 * 输入：flowers = [1,3,1,1], newFlowers = 7, target = 6, full = 12, partial = 1
 * 输出：14
 * 解释：Alice 可以按以下方案种花
 * - 在第 0 个花园种 2 朵花
 * - 在第 1 个花园种 3 朵花
 * - 在第 2 个花园种 1 朵花
 * - 在第 3 个花园种 1 朵花
 * 花园里花的数目为 [3,6,2,2] 。总共种了 2 + 3 + 1 + 1 = 7 朵花。
 * 只有 1 个花园是完善的。
 * 不完善花园里花的最少数目是 2 。
 * 所以总美丽值为 1 * 12 + 2 * 1 = 12 + 2 = 14 。
 * 没有其他方案可以让花园总美丽值超过 14 。
 *
 * 示例 2：
 * 输入：flowers = [2,4,5,3], newFlowers = 10, target = 5, full = 2, partial = 6
 * 输出：30
 * 解释：Alice 可以按以下方案种花
 * - 在第 0 个花园种 3 朵花
 * - 在第 1 个花园种 0 朵花
 * - 在第 2 个花园种 0 朵花
 * - 在第 3 个花园种 2 朵花
 * 花园里花的数目为 [5,4,5,5] 。总共种了 3 + 0 + 0 + 2 = 5 朵花。
 * 有 3 个花园是完善的。
 * 不完善花园里花的最少数目为 4 。
 * 所以总美丽值为 3 * 2 + 4 * 6 = 6 + 24 = 30 。
 * 没有其他方案可以让花园总美丽值超过 30 。
 * 注意，Alice可以让所有花园都变成完善的，但这样她的总美丽值反而更小。
 *
 * 提示：
 * 1 <= flowers.length <= 10^5
 * 1 <= flowers[i], target <= 10^5
 * 1 <= newFlowers <= 10^10
 * 1 <= full, partial <= 10^5
 */
public class MaximumBeauty {

    public long maximumBeauty(int[] flowers, long newFlowers, int target, int full, int partial) {
        //1，对花园中花的数量进行排序
        Arrays.sort(flowers);
        int length = flowers.length;
        if (flowers[0] >= target) {
            //2，特殊场景，所有花园都已经完善了
            return (long) full * length;
        }
        //3，预处理计算前缀和
        int[] sums = new int[length];
        sums[0] = flowers[0];
        for (int i = 1; i < length; i++) {
            sums[i] = sums[i - 1] + flowers[i];
        }
        long max = 0;
        for (int i = length - 1; i >= 0; i--) {
            if (flowers[i] >= target) {
                continue;
            }
            long minCount = flowers[0];
            long maxCount = target - 1;
            while (minCount < maxCount) {
                long middle = (maxCount - minCount + 1) / 2 + minCount;
                if (check(middle, i, newFlowers, sums,flowers)) {
                    minCount = middle;
                } else {
                    maxCount = middle - 1;
                }
            }
            //计算当前花园保持不完善的最大值
            long total = (long) full * (length - i - 1) + minCount * partial;
            if (total > max) {
                max = total;
            }
            //当前花园填充完美
            if (newFlowers >= (target - flowers[i])) {
                total = (long) full * (length - i);
                if (i > 0) {
                    total += (long) partial * flowers[0];
                }
                if (total > max) {
                    max = total;
                }
            }
            newFlowers -= (target - flowers[i]);
            if (newFlowers <= 0) {
                return max;
            }
        }
        return max;
    }

    //判断是否可以把最小值填充到value
    private boolean check(long value, int end, long newFlowers, int[] sums, int[] flowers) {
        //1，获取小于等于value的index
        int start = 0;
        while (start < end) {
            int middle = (end - start + 1) / 2 + start;
            if (flowers[middle] < value) {
                start = middle;
            } else {
                end = middle - 1;
            }
        }
        long add = (1L + start) * value - sums[start];
        return newFlowers >= add;
    }
}
