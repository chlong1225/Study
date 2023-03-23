package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * create on 2023/3/23
 * @author chenglong
 * description : 等差子数组
 *
 * 如果一个数列由至少两个元素组成，且每两个连续元素之间的差值都相同，那么这个序列就是等差数列。更正式地，数列s是等差数列，只需要满足：对于每个有效的i，s[i+1] - s[i] == s[1] - s[0] 都成立。
 * 例如，下面这些都是等差数列 ：
 * 1, 3, 5, 7, 9
 * 7, 7, 7, 7
 * 3, -1, -5, -9
 * 下面的数列不是等差数列 ：
 * 1, 1, 2, 5, 7
 * 给你一个由n个整数组成的数组nums，和两个由m个整数组成的数组l和r，后两个数组表示m组范围查询，其中第i个查询对应范围[l[i],r[i]]。所有数组的下标都是从0开始的。
 * 返回boolean元素构成的答案列表answer。如果子数组nums[l[i]], nums[l[i]+1], ... , nums[r[i]]可以重新排列形成等差数列 ，answer[i]的值就是true；否则answer[i]的值就是false。
 *
 * 示例 1：
 * 输入：nums = [4,6,5,9,3,7], l = [0,0,2], r = [2,3,5]
 * 输出：[true,false,true]
 * 解释：
 * 第 0 个查询，对应子数组 [4,6,5] 。可以重新排列为等差数列 [6,5,4] 。
 * 第 1 个查询，对应子数组 [4,6,5,9] 。无法重新排列形成等差数列。
 * 第 2 个查询，对应子数组 [5,9,3,7] 。可以重新排列为等差数列 [3,5,7,9] 。
 *
 * 示例 2：
 * 输入：nums = [-12,-9,-3,-12,-6,15,20,-25,-20,-15,-10], l = [0,1,6,4,8,7], r = [4,4,9,7,9,10]
 * 输出：[false,true,false,false,true,true]
 *
 * 提示：
 * n == nums.length
 * m == l.length
 * m == r.length
 * 2 <= n <= 500
 * 1 <= m <= 500
 * 0 <= l[i] < r[i] < n
 * -10^5 <= nums[i] <= 10^5
 */
public class CheckArithmeticSubarrays {

    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        int m = l.length;
        List<Boolean> result = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            int startIndex = l[i];
            int endIndex = r[i];
            result.add(checkNum2(startIndex, endIndex, nums));
        }
        return result;
    }

    private boolean checkNum(int start, int end, int[] nums) {
        if (end - start == 1) {
            return true;
        }
        int length = end - start + 1;
        int[] dates = new int[length];
        for (int i = 0; i < length; i++) {
            dates[i] = nums[i + start];
        }
        Arrays.sort(dates);
        int compare = dates[1] - dates[0];
        for (int i = 1; i < length - 1; i++) {
            int tem = dates[i + 1] - dates[i];
            if (tem != compare) {
                return false;
            }
        }
        return true;
    }

    private boolean checkNum2(int start, int end, int[] nums) {
        if (end - start == 1) {
            return true;
        }
        int min = nums[start];
        int max = nums[start];
        for (int i = start + 1; i <= end; i++) {
            if (nums[i] > max) {
                max = nums[i];
            } else if (nums[i] < min) {
                min = nums[i];
            }
        }
        if (min == max) {
            return true;
        }
        int space = end - start;
        if ((max - min) % space != 0) {
            return false;
        }
        //等差数列的差
        int compare = (max - min) / space;
        boolean[] marks = new boolean[space + 1];
        for (int i = start; i <= end; i++) {
            int tem = nums[i] - min;
            if (tem % compare != 0) {
                return false;
            }
            int index = tem / compare;
            if (marks[index]) {
                return false;
            }
            marks[index] = true;
        }
        return true;
    }
}
