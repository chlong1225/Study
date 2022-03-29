package com.demo.algorithm.leetcode.contest.week286;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2022/3/29.
 * description : 找出两数组的不同
 *
 * 给你两个下标从0开始的整数数组nums1和nums2，请你返回一个长度为2的列表answer，其中：
 * answer[0]是nums1中所有不存在于nums2中的不同整数组成的列表。
 * answer[1]是nums2中所有不存在于nums1中的不同整数组成的列表。
 * 注意：列表中的整数可以按任意顺序返回。
 *
 * 示例 1：
 * 输入：nums1 = [1,2,3], nums2 = [2,4,6]
 * 输出：[[1,3],[4,6]]
 * 解释：
 * 对于 nums1 ，nums1[1] = 2 出现在 nums2 中下标 0 处，然而 nums1[0] = 1 和 nums1[2] = 3 没有出现在 nums2 中。因此，answer[0] = [1,3]。
 * 对于 nums2 ，nums2[0] = 2 出现在 nums1 中下标 1 处，然而 nums2[1] = 4 和 nums2[2] = 6 没有出现在 nums2 中。因此，answer[1] = [4,6]。
 *
 * 示例 2：
 * 输入：nums1 = [1,2,3,3], nums2 = [1,1,2,2]
 * 输出：[[3],[]]
 * 解释：
 * 对于 nums1 ，nums1[2] 和 nums1[3] 没有出现在 nums2 中。由于 nums1[2] == nums1[3] ，二者的值只需要在 answer[0] 中出现一次，故 answer[0] = [3]。
 * nums2 中的每个整数都在 nums1 中出现，因此，answer[1] = [] 。
 *
 * 提示：
 * 1 <= nums1.length, nums2.length <= 1000
 * -1000 <= nums1[i], nums2[i] <= 1000
 */
public class FindDifference {

    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        List<List<Integer>> result = new ArrayList<>(2);
        int length = nums1.length;
        //1，统计nums1中数字出现的情况
        int[] counts1 = new int[2001];
        for (int i = 0; i < length; i++) {
            counts1[nums1[i] + 1000]++;
        }
        //2，统计nums2中数字出现的情况
        int[] counts2 = new int[2001];
        length = nums2.length;
        for (int i = 0; i < length; i++) {
            counts2[nums2[i] + 1000]++;
        }
        List<Integer> items1 = new ArrayList<>();
        List<Integer> items2 = new ArrayList<>();
        //3，比较nums1与nums2中数字出现的情况
        for (int i = 0; i < 2001; i++) {
            if (counts1[i] == 0 && counts2[i] > 0) {
                //nums2中有，nums1中没有的数字
                items2.add(i - 1000);
            } else if (counts1[i] > 0 && counts2[i] == 0) {
                //nums1中有，nums2中没有的数字
                items1.add(i - 1000);
            }
        }
        result.add(items1);
        result.add(items2);
        return result;
    }
}
