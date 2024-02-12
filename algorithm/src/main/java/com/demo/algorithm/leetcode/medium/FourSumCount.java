package com.demo.algorithm.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * create on 2023/12/25
 * @author chenglong
 * description : 四数相加II
 *
 * 给你四个整数数组nums1、nums2、nums3 和 nums4，数组长度都是n，请你计算有多少个元组(i,j,k,l)能满足：
 * 0 <= i, j, k, l < n
 * nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
 *
 * 示例 1：
 * 输入：nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
 * 输出：2
 * 解释：
 * 两个元组如下：
 * 1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
 *
 * 示例 2：
 * 输入：nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
 * 输出：1
 *
 *   提示：
 * n == nums1.length
 * n == nums2.length
 * n == nums3.length
 * n == nums4.length
 * 1 <= n <= 200
 * -2^28 <= nums1[i], nums2[i], nums3[i], nums4[i] <= 2^28
 */
public class FourSumCount {

    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int n = nums1.length;
        //1，统计所有的nums1+nums2出现的次数
        Map<Integer, Integer> dates1 = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int sum = nums1[i] + nums2[j];
                if (dates1.containsKey(sum)) {
                    dates1.put(sum, dates1.get(sum) + 1);
                } else {
                    dates1.put(sum, 1);
                }
            }
        }
        int total = 0;
        //2，统计所有nums3+nums4出现的次数，并在dates1中查找相反数
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int find = -(nums3[i] + nums4[j]);
                if (dates1.containsKey(find)) {
                    total += dates1.get(find);
                }
            }
        }
        return total;
    }
}
