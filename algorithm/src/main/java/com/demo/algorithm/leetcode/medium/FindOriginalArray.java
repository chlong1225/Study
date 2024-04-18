package com.demo.algorithm.leetcode.medium;


/**
 * create on 2024/4/18
 * @author chenglong
 * description : 从双倍数组中还原原数组
 *
 * 一个整数数组original可以转变成一个双倍数组changed，转变方式为将original中每个元素值乘以2加入数组中，然后将所有元素随机打乱。
 * 给你一个数组changed，如果change是双倍数组，那么请你返回original数组，否则请返回空数组。original的元素可以以任意顺序返回。
 *
 * 示例 1：
 * 输入：changed = [1,3,4,2,6,8]
 * 输出：[1,3,4]
 * 解释：一个可能的 original 数组为 [1,3,4] :
 * - 将 1 乘以 2 ，得到 1 * 2 = 2 。
 * - 将 3 乘以 2 ，得到 3 * 2 = 6 。
 * - 将 4 乘以 2 ，得到 4 * 2 = 8 。
 * 其他可能的原数组方案为 [4,3,1] 或者 [3,1,4] 。
 *
 * 示例 2：
 * 输入：changed = [6,3,0,1]
 * 输出：[]
 * 解释：changed 不是一个双倍数组。
 *
 * 示例 3：
 * 输入：changed = [1]
 * 输出：[]
 * 解释：changed 不是一个双倍数组。
 *
 * 提示：
 * 1 <= changed.length <= 10^5
 * 0 <= changed[i] <= 10^5
 */
public class FindOriginalArray {

    public int[] findOriginalArray(int[] changed) {
        int n = changed.length;
        //1，双倍数组长度必须是2的倍数
        if (n % 2 == 1) {
            return new int[0];
        }
        //2，查找最大值便于使用创建桶
        int max = changed[0];
        for (int i = 1; i < n; i++) {
            if (changed[i] > max) {
                max = changed[i];
            }
        }
        int[] counts = new int[max + 1];
        for (int i = 0; i < n; i++) {
            counts[changed[i]]++;
        }
        int[] dates = new int[n / 2];
        int index = n / 2 - 1;
        for (int i = max; i >= 0; i--) {
            if (counts[i] > 0) {
                if (i == 0) {
                    if (counts[i] % 2 == 1) {
                        return new int[0];
                    }
                    for (int j = 0; j < counts[i] / 2; j++) {
                        dates[index] = i;
                        index--;
                    }
                } else {
                    if (i % 2 == 1) {
                        return new int[0];
                    }
                    int find = i / 2;
                    if (counts[find] < counts[i]) {
                        return new int[0];
                    }
                    for (int j = 0; j < counts[i]; j++) {
                        dates[index] = find;
                        index--;
                    }
                    counts[find] -= counts[i];
                    counts[i] = 0;
                }
            }
        }
        if (index == -1) {
            return dates;
        }
        return new int[0];
    }
}
