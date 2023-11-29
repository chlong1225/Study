package com.demo.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2023/11/29
 * @author chenglong
 * description : 两个数组的交集
 *
 * 给定两个数组nums1和nums2，返回它们的交集。输出结果中的每个元素一定是唯一的。我们可以不考虑输出结果的顺序 。
 *
 * 示例 1：
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2]
 *
 * 示例 2：
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[9,4]
 * 解释：[4,9] 也是可通过的
 *
 * 提示：
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 1000
 */
public class Intersection {

    public int[] intersection(int[] nums1, int[] nums2) {
        List<Integer> result = new ArrayList<>();
        boolean[] marks = new boolean[1001];
        for (int i = 0; i < nums1.length; i++) {
            marks[nums1[i]] = true;
        }
        for (int i = 0; i < nums2.length; i++) {
            int cur = nums2[i];
            if (marks[cur]) {
                result.add(cur);
                marks[cur] = false;
            }
        }
        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }
}
