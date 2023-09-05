package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/9/5
 * @author chenglong
 * description : 从两个数字数组里生成最小数字
 *
 * 给你两个只包含1到9之间数字的数组nums1和nums2，每个数组中的元素互不相同，请你返回最小的数字，两个数组都至少包含这个数字的某个数位。
 *
 * 示例 1：
 * 输入：nums1 = [4,1,3], nums2 = [5,7]
 * 输出：15
 * 解释：数字 15 的数位 1 在 nums1 中出现，数位 5 在 nums2 中出现。15 是我们能得到的最小数字。
 *
 * 示例 2：
 * 输入：nums1 = [3,5,2,6], nums2 = [3,1,7]
 * 输出：3
 * 解释：数字 3 的数位 3 在两个数组中都出现了。
 *
 * 提示：
 * 1 <= nums1.length, nums2.length <= 9
 * 1 <= nums1[i], nums2[i] <= 9
 * 每个数组中，元素 互不相同 。
 */
public class MinNumber {

    public int minNumber(int[] nums1, int[] nums2) {
        int[] counts = new int[10];
        //1，统计数字出现的次数并查找两个数组分别对应的最小值
        int min1 = Integer.MAX_VALUE;
        for (int i = 0; i < nums1.length; i++) {
            if (nums1[i] < min1) {
                min1 = nums1[i];
            }
            counts[nums1[i]]++;
        }
        int min2 = Integer.MAX_VALUE;
        for (int i = 0; i < nums2.length; i++) {
            if (nums2[i] < min2) {
                min2 = nums2[i];
            }
            counts[nums2[i]]++;
        }
        //2，查找相同值
        for (int i = 0; i < 10; i++) {
            if (counts[i] == 2) {
                return i;
            }
        }
        //3，两个最小值进行拼接
        if (min1 > min2) {
            return min2 * 10 + min1;
        }
        return min1 * 10 + min2;
    }
}
