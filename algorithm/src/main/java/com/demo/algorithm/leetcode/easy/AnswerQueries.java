package com.demo.algorithm.leetcode.easy;

import java.util.Arrays;

/**
 * create on 2023/3/17
 * @author chenglong
 * description : 和有限的最长子序列
 *
 * 给你一个长度为n的整数数组nums，和一个长度为m的整数数组queries。
 * 返回一个长度为m的数组answer，其中answer[i]是nums中元素之和小于等于queries[i]的子序列的最大长度。
 * 子序列是由一个数组删除某些元素（也可以不删除）但不改变剩余元素顺序得到的一个数组。
 *
 * 示例 1：
 * 输入：nums = [4,5,2,1], queries = [3,10,21]
 * 输出：[2,3,4]
 * 解释：queries 对应的 answer 如下：
 * - 子序列 [2,1] 的和小于或等于 3 。可以证明满足题目要求的子序列的最大长度是 2 ，所以 answer[0] = 2 。
 * - 子序列 [4,5,1] 的和小于或等于 10 。可以证明满足题目要求的子序列的最大长度是 3 ，所以 answer[1] = 3 。
 * - 子序列 [4,5,2,1] 的和小于或等于 21 。可以证明满足题目要求的子序列的最大长度是 4 ，所以 answer[2] = 4 。
 *
 * 示例 2：
 * 输入：nums = [2,3,4,5], queries = [1]
 * 输出：[0]
 * 解释：空子序列是唯一一个满足元素和小于或等于 1 的子序列，所以 answer[0] = 0 。
 *
 * 提示：
 * n == nums.length
 * m == queries.length
 * 1 <= n, m <= 1000
 * 1 <= nums[i], queries[i] <= 10^6
 */
public class AnswerQueries {

    public int[] answerQueries(int[] nums, int[] queries) {
        int n = nums.length;
        int m = queries.length;
        int[] answers = new int[m];
        //1，对数组进行排序
        Arrays.sort(nums);
        //2，前缀和
        for (int i = 1; i < n; i++) {
            nums[i] += nums[i - 1];
        }
        //3，查找满足添加值的index
        for (int i = 0; i < m; i++) {
            int index = findIndex(nums, queries[i]);
            answers[i] = index + 1;
        }
        return answers;
    }

    //查找小于或等于指定值的index
    private int findIndex(int[] nums, int query) {
        int start = 0;
        int end = nums.length - 1;
        if (nums[start] > query) {
            return -1;
        }
        if (nums[start] == query) {
            return start;
        }
        if (nums[end] <= query) {
            return end;
        }
        while (start < end) {
            int middle = (end - start) / 2 + start;
            if (nums[middle] > query) {
                end = middle - 1;
            } else {
                start = middle + 1;
                if (nums[start] > query) {
                    return middle;
                }
            }
        }
        return start;
    }
}
