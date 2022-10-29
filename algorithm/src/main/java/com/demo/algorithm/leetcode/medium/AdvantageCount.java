package com.demo.algorithm.leetcode.medium;

import java.util.Arrays;

/**
 * create on 2022/10/8
 * @author chenglong
 * description : 优势洗牌
 *
 * 给定两个大小相等的数组nums1和nums2，nums1相对于nums2的优势可以用满足nums1[i]>nums2[i]的索引i的数目来描述。
 * 返回nums1的任意排列，使其相对于nums2的优势最大化。
 *
 * 示例 1：
 * 输入：nums1 = [2,7,11,15], nums2 = [1,10,4,11]
 * 输出：[2,11,7,15]
 *
 * 示例 2：
 * 输入：nums1 = [12,24,8,32], nums2 = [13,25,32,11]
 * 输出：[24,32,8,12]
 *
 * 提示：
 * 1 <= nums1.length <= 10^5
 * nums2.length == nums1.length
 * 0 <= nums1[i], nums2[i] <= 10^9
 */
public class AdvantageCount {

    public int[] advantageCount(int[] nums1, int[] nums2) {
        int length = nums1.length;
        int[][] dates = new int[length][2];
        for (int i = 0; i < length; i++) {
            dates[i][0] = nums2[i];
            dates[i][1] = i;
        }
        Arrays.sort(nums1);
        Arrays.sort(dates, (o1, o2) -> o1[0] - o2[0]);
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = -1;
        }
        int index = 0;
        for (int i = 0; i < length; i++) {
            int cur = dates[i][0];
            while (index < length) {
                if (nums1[index] > cur) {
                    result[dates[i][1]] = nums1[index];
                    nums1[index] = -1;
                    break;
                } else {
                    index++;
                }
            }
        }
        index = 0;
        for (int i = 0; i < length; i++) {
            if (result[i] == -1) {
                while (index < length) {
                    if (nums1[index] != -1) {
                        result[i] = nums1[index];
                        index++;
                        break;
                    } else {
                        index++;
                    }
                }
            }
        }
        return result;
    }
}
