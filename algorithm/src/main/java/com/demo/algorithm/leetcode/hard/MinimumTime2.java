package com.demo.algorithm.leetcode.hard;

import java.util.Arrays;
import java.util.List;

/**
 * create on 2024/1/21
 * @author chenglong
 * description : 使数组和小于等于x的最少时间
 *
 * 给你两个长度相等下标从0开始的整数数组nums1和nums2。每一秒，对于所有下标0 <= i < nums1.length ，nums1[i]的值都增加nums2[i]。操作完成后，你可以进行如下操作：
 * 选择任一满足 0 <= i < nums1.length的下标i，并使nums1[i] = 0 。
 * 同时给你一个整数x。
 * 请你返回使nums1中所有元素之和小于等于x所需要的最少时间，如果无法实现，那么返回-1。
 *
 * 示例 1：
 * 输入：nums1 = [1,2,3], nums2 = [1,2,3], x = 4
 * 输出：3
 * 解释：
 * 第 1 秒，我们对 i = 0 进行操作，得到 nums1 = [0,2+2,3+3] = [0,4,6] 。
 * 第 2 秒，我们对 i = 1 进行操作，得到 nums1 = [0+1,0,6+3] = [1,0,9] 。
 * 第 3 秒，我们对 i = 2 进行操作，得到 nums1 = [1+1,0+2,0] = [2,2,0] 。
 * 现在 nums1 的和为 4 。不存在更少次数的操作，所以我们返回 3 。
 *
 * 示例 2：
 * 输入：nums1 = [1,2,3], nums2 = [3,3,3], x = 4
 * 输出：-1
 * 解释：不管如何操作，nums1 的和总是会超过 x 。
 *
 * 提示：
 * 1 <= nums1.length <= 10^3
 * 1 <= nums1[i] <= 10^3
 * 0 <= nums2[i] <= 10^3
 * nums1.length == nums2.length
 * 0 <= x <= 10^6
 */
public class MinimumTime2 {

    public int minimumTime(List<Integer> nums1, List<Integer> nums2, int x) {
        int n = nums1.size();
        int[][] dates = new int[n][2];
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < n; i++) {
            sum1 += nums1.get(i);
            sum2 += nums2.get(i);
            dates[i][0] = nums1.get(i);
            dates[i][1] = nums2.get(i);
        }
        Arrays.sort(dates, (o1, o2) -> o1[1] - o2[1]);
        int[] marks = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = i; j > 0; j--) {
                //操作时间为：j
                int reduce = dates[i - 1][0] + j * dates[i - 1][1];
                marks[j] = Math.max(marks[j], marks[j - 1] + reduce);
            }
        }
        for (int i = 0; i <= n; i++) {
            int sum = sum1 + i * sum2 - marks[i];
            if (sum <= x) {
                return i;
            }
        }
        return -1;
    }
}
