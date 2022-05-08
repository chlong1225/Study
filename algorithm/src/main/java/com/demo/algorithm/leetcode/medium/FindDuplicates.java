package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2022/5/8.
 * description : 数组中重复的数据
 *
 * 给你一个长度为n的整数数组nums，其中nums的所有整数都在范围[1, n]内，且每个整数出现一次或两次。请你找出所有出现两次的整数，并以数组形式返回。
 * 你必须设计并实现一个时间复杂度为 O(n) 且仅使用常量额外空间的算法解决此问题。
 *
 * 示例 1：
 * 输入：nums = [4,3,2,7,8,2,3,1]
 * 输出：[2,3]
 *
 * 示例 2：
 * 输入：nums = [1,1,2]
 * 输出：[1]
 *
 * 示例 3：
 * 输入：nums = [1]
 * 输出：[]
 *
 * 提示：
 *
 * n == nums.length
 * 1 <= n <= 10^5
 * 1 <= nums[i] <= n
 * nums中的每个元素出现一次或两次
 */
public class FindDuplicates {

    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();
        int length = nums.length;
        if (length == 1) {
            return result;
        }
        for (int i = 0; i < length; i++) {
            if (nums[i] == i + 1 || nums[i] == -1) {
                continue;
            }
            //期待值为：i+1。nums[i]需要放置在index = nums[i]-1
            while (nums[i] != (i + 1) && nums[i] != 0) {
                int index = nums[i] - 1;
                if (nums[index] == nums[i]) {
                    nums[index] = -1;
                    nums[i] = 0;
                } else {
                    int tem = nums[index];
                    nums[index] = nums[i];
                    nums[i] = tem;
                }
            }
        }
        for (int i = 0; i < length; i++) {
            if (nums[i] == -1) {
                result.add(i + 1);
            }
        }
        return result;
    }
}
