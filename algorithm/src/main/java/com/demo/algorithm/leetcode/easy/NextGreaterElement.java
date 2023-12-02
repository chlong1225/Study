package com.demo.algorithm.leetcode.easy;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * create on 2023/11/30
 * @author chenglong
 * description : 下一个更大元素I
 *
 * nums1中数字x的下一个更大元素是指x在nums2中对应位置右侧的第一个比x大的元素。
 * 给你两个没有重复元素的数组nums1和nums2，下标从0开始计数，其中nums1是nums2的子集。
 * 对于每个0<=i<nums1.length，找出满足nums1[i]==nums2[j]的下标j，并且在nums2确定nums2[j]的下一个更大元素。
 * 如果不存在下一个更大元素，那么本次查询的答案是-1。
 * 返回一个长度为nums1.length的数组ans作为答案，满足ans[i]是如上所述的下一个更大元素。
 *
 * 示例 1：
 * 输入：nums1 = [4,1,2], nums2 = [1,3,4,2].
 * 输出：[-1,3,-1]
 * 解释：nums1 中每个值的下一个更大元素如下所述：
 * - 4 ，用加粗斜体标识，nums2 = [1,3,4,2]。不存在下一个更大元素，所以答案是 -1 。
 * - 1 ，用加粗斜体标识，nums2 = [1,3,4,2]。下一个更大元素是 3 。
 * - 2 ，用加粗斜体标识，nums2 = [1,3,4,2]。不存在下一个更大元素，所以答案是 -1 。
 *
 * 示例 2：
 * 输入：nums1 = [2,4], nums2 = [1,2,3,4].
 * 输出：[3,-1]
 * 解释：nums1 中每个值的下一个更大元素如下所述：
 * - 2 ，用加粗斜体标识，nums2 = [1,2,3,4]。下一个更大元素是 3 。
 * - 4 ，用加粗斜体标识，nums2 = [1,2,3,4]。不存在下一个更大元素，所以答案是 -1 。
 *
 * 提示：
 * 1 <= nums1.length <= nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 10^4
 * nums1和nums2中所有整数互不相同
 * nums1中的所有整数同样出现在nums2中
 *
 * 进阶：你可以设计一个时间复杂度为O(nums1.length+nums2.length)的解决方案吗？
 */
public class NextGreaterElement {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int m = nums2.length;
        Map<Integer, Integer> marks = new HashMap<>();
        for (int i = 0; i < m; i++) {
            marks.put(nums2[i], i);
        }
        int[] dates = new int[m];
        Deque<Integer> stack = new ArrayDeque<Integer>();
        for (int i = m - 1; i >= 0; i--) {
            while (stack.size() > 0 && stack.peekLast() < nums2[i]) {
                stack.pollLast();
            }
            if (stack.isEmpty()) {
                dates[i] = -1;
            } else {
                dates[i] = stack.peekLast();
            }
            stack.addLast(nums2[i]);
        }
        int n = nums1.length;
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            int index = marks.get(nums1[i]);
            answer[i] = dates[index];
        }
        return answer;
    }
}
