package com.demo.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2023/11/30
 * @author chenglong
 * description : 找到所有数组中消失的数字
 *
 * 给你一个含n个整数的数组nums，其中nums[i]在区间[1,n]内。请你找出所有在[1,n]范围内但没有出现在nums中的数字，并以数组的形式返回结果。
 *
 * 示例 1：
 * 输入：nums = [4,3,2,7,8,2,3,1]
 * 输出：[5,6]
 *
 * 示例 2：
 * 输入：nums = [1,1]
 * 输出：[2]
 *
 * 提示：
 * n == nums.length
 * 1 <= n <= 10^5
 * 1 <= nums[i] <= n
 * 进阶：你能在不使用额外空间且时间复杂度为 O(n) 的情况下解决这个问题吗? 你可以假定返回的数组不算在额外空间内。
 */
public class FindDisappearedNumbers {

    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (nums[i] != i + 1) {
                int tem = nums[nums[i] - 1];
                if (tem == nums[i]) {
                    break;
                }
                nums[nums[i] - 1] = nums[i];
                nums[i] = tem;
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                result.add(i + 1);
            }
        }
        return result;
    }
}
