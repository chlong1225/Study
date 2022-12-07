package com.demo.algorithm.leetcode.medium;

import java.util.Arrays;

/**
 * create on 2022/12/7
 * @author chenglong
 * description : 通过最少操作次数使数组的和相等
 *
 * 给你两个长度可能不等的整数数组nums1和nums2。两个数组中的所有值都在1到6之间（包含1和6）。
 * 每次操作中，你可以选择任意数组中的任意一个整数，将它变成1到6之间任意的值（包含1和6）。
 * 请你返回使nums1中所有数的和与nums2中所有数的和相等的最少操作次数。如果无法使两个数组的和相等，请返回-1。
 *
 * 示例 1：
 * 输入：nums1 = [1,2,3,4,5,6], nums2 = [1,1,2,2,2,2]
 * 输出：3
 * 解释：你可以通过 3 次操作使 nums1 中所有数的和与 nums2 中所有数的和相等。以下数组下标都从 0 开始。
 * - 将 nums2[0] 变为 6 。 nums1 = [1,2,3,4,5,6], nums2 = [6,1,2,2,2,2] 。
 * - 将 nums1[5] 变为 1 。 nums1 = [1,2,3,4,5,1], nums2 = [6,1,2,2,2,2] 。
 * - 将 nums1[2] 变为 2 。 nums1 = [1,2,2,4,5,1], nums2 = [6,1,2,2,2,2] 。
 *
 * 示例 2：
 * 输入：nums1 = [1,1,1,1,1,1,1], nums2 = [6]
 * 输出：-1
 * 解释：没有办法减少 nums1 的和或者增加 nums2 的和使二者相等。
 *
 * 示例 3：
 * 输入：nums1 = [6,6], nums2 = [1]
 * 输出：3
 * 解释：你可以通过 3 次操作使 nums1 中所有数的和与 nums2 中所有数的和相等。以下数组下标都从 0 开始。
 * - 将 nums1[0] 变为 2 。 nums1 = [2,6], nums2 = [1] 。
 * - 将 nums1[1] 变为 2 。 nums1 = [2,2], nums2 = [1] 。
 * - 将 nums2[0] 变为 4 。 nums1 = [2,2], nums2 = [4] 。
 *
 * 提示：
 * 1 <= nums1.length, nums2.length <= 10^5
 * 1 <= nums1[i], nums2[i] <= 6
 */
public class MinOperations2 {

    public int minOperations(int[] nums1, int[] nums2) {
        //1，判断不可能相等的特殊场景
        int n1 = nums1.length;
        int n2 = nums2.length;
        if (n1 < n2 && n1 * 6 < n2) {
            return -1;
        }
        if (n2 < n1 && n2 * 6 < n1) {
            return -1;
        }
        //2，分别统计数组的和
        int sum1 = 0;
        for (int i = 0; i < n1; i++) {
            sum1 += nums1[i];
        }
        int sum2 = 0;
        for (int i = 0; i < n2; i++) {
            sum2 += nums2[i];
        }
        if (sum1 == sum2) {
            return 0;
        }
        if (sum1 < sum2) {
            return getMinStep(sum1, nums1, sum2, nums2);
        } else {
            return getMinStep(sum2, nums2, sum1, nums1);
        }
    }

    private int getMinStep(int min, int[] nums1, int max, int[] nums2) {
        int step = 0;
        //1，对数组分别排序
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        //nums1中的值需要加大，nums2中的值需要减小
        int n1 = nums1.length;
        int n2 = nums2.length;
        int index1 = 0;
        int index2 = n2 - 1;
        while (min != max) {
            //两个数组的差值
            int diff = max - min;
            if (index1 == n1) {
                //此时只能nums2减小
                int maxReduce = nums2[index2] - 1;
                index2--;
                step++;
                if (diff <= maxReduce) {
                    return step;
                } else {
                    max -= maxReduce;
                }
            } else if (index2 == -1) {
                //此时只能nums1加大
                int maxAdd = 6 - nums1[index1];
                index1++;
                step++;
                if (diff <= maxAdd) {
                    return step;
                } else {
                    min += maxAdd;
                }
            } else {
                //此时nums1加大或nums2减小
                int maxAdd = 6 - nums1[index1];
                int maxReduce = nums2[index2] - 1;
                step++;
                if (maxAdd >= maxReduce) {
                    index1++;
                    if (diff <= maxAdd) {
                        return step;
                    } else {
                        min += maxAdd;
                    }
                } else {
                    index2--;
                    if (diff <= maxReduce) {
                        return step;
                    } else {
                        max -= maxReduce;
                    }
                }
            }
        }
        return step;
    }
}
