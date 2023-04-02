package com.demo.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2022/12/29
 * @author chenglong
 * description : 至少在两个数组中出现的值
 *
 * 给你三个整数数组nums1、nums2和nums3，请你构造并返回一个元素各不相同的数组，且由至少在两个数组中出现的所有值组成。数组中的元素可以按任意顺序排列。
 *
 * 示例 1：
 * 输入：nums1 = [1,1,3,2], nums2 = [2,3], nums3 = [3]
 * 输出：[3,2]
 * 解释：至少在两个数组中出现的所有值为：
 * - 3 ，在全部三个数组中都出现过。
 * - 2 ，在数组 nums1 和 nums2 中出现过。
 *
 * 示例 2：
 * 输入：nums1 = [3,1], nums2 = [2,3], nums3 = [1,2]
 * 输出：[2,3,1]
 * 解释：至少在两个数组中出现的所有值为：
 * - 2 ，在数组 nums2 和 nums3 中出现过。
 * - 3 ，在数组 nums1 和 nums2 中出现过。
 * - 1 ，在数组 nums1 和 nums3 中出现过。
 *
 * 示例 3：
 * 输入：nums1 = [1,2,2], nums2 = [4,3,3], nums3 = [5]
 * 输出：[]
 * 解释：不存在至少在两个数组中出现的值。
 *
 * 提示：
 * 1 <= nums1.length, nums2.length, nums3.length <= 100
 * 1 <= nums1[i], nums2[j], nums3[k] <= 100
 */
public class TwoOutOfThree {

    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        int[] marks = new int[101];
        for (int i = 0; i < nums1.length; i++) {
            marks[nums1[i]] |= 1;
        }
        for (int i = 0; i < nums2.length; i++) {
            marks[nums2[i]] |= 2;
        }
        for (int i = 0; i < nums3.length; i++) {
            marks[nums3[i]] |= 4;
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            if (marks[i] == 3 || marks[i] == 5 || marks[i] == 6 || marks[i] == 7) {
                result.add(i);
            }
        }
        return result;
    }
}
