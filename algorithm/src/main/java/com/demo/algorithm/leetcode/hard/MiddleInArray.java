package com.demo.algorithm.leetcode.hard;

/**
 * create by chenglong on 9/22/21
 * description : 寻找两个正序数组的中位数
 *
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 *
 * 示例 1：
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 *
 * 示例 2：
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 *
 * 示例 3：
 * 输入：nums1 = [0,0], nums2 = [0,0]
 * 输出：0.00000
 *
 * 示例 4：
 * 输入：nums1 = [], nums2 = [1]
 * 输出：1.00000
 *
 * 示例 5：
 * 输入：nums1 = [2], nums2 = []
 * 输出：2.00000
 *  
 * 提示：
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -106 <= nums1[i], nums2[i] <= 106
 */
public class MiddleInArray {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) {
            if (nums2 == null || nums2.length == 0) {
                return 0;
            } else {
                int length = nums2.length;
                if (length % 2 == 0) {
                    //偶数个元素
                    return (nums2[length / 2 - 1] + nums2[length / 2]) / 2.0;
                } else {
                    return nums2[length / 2];
                }
            }
        }
        int m = nums1.length;
        int n = nums2.length;
        boolean isDouble;
        int middleIndex1;
        int middleIndex2 = -1;
        if ((m + n) % 2 == 0) {
            isDouble = true;
            middleIndex2 = (m + n) / 2;
            middleIndex1 = middleIndex2 - 1;
        } else {
            isDouble = false;
            middleIndex1 = (m + n) / 2;
        }
        int sum = 0;
        int min;
        int count = 0;
        int indexM = 0;
        int indexN = 0;
        while (count < n + m) {
            if (indexM >= nums1.length) {
                min = nums2[indexN];
                indexN++;

            } else if (indexN >= nums2.length) {
                min = nums1[indexM];
                indexM++;
            } else {
                if (nums1[indexM] <= nums2[indexN]) {
                    min = nums1[indexM];
                    indexM++;
                } else {
                    min = nums2[indexN];
                    indexN++;
                }
            }
            if (count == middleIndex1) {
                sum += min;
                if (!isDouble) {
                    count = m + n + 1;
                }
            }
            if (count == middleIndex2) {
                sum += min;
                count = m + n;
            }
            count++;
        }
        return isDouble ? (sum / 2.0) : sum;
    }
}
