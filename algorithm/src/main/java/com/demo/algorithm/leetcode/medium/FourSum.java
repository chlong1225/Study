package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chl on 2021/10/4.
 * description : 四数之和
 *
 * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] ：
 * 0 <= a, b, c, d < n
 * a、b、c 和 d 互不相同
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * 你可以按 任意顺序 返回答案 。
 *
 * 示例 1：
 * 输入：nums = [1,0,-1,0,-2,2], target = 0
 * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 *
 * 示例 2：
 * 输入：nums = [2,2,2,2,2], target = 8
 * 输出：[[2,2,2,2]]
 *  
 * 提示：
 * 1 <= nums.length <= 200
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 */
public class FourSum {

    private static final int DEFAULT_NUM = -200;

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return result;
        }
        //1,对数据进行排序
        Arrays.sort(nums);
        int length = nums.length;
        //用于去重
        int preA = DEFAULT_NUM;
        int preB = DEFAULT_NUM;
        int preC = DEFAULT_NUM;
        int preD = DEFAULT_NUM;
        for (int i = 0; i < length - 3; i++) {
            if (preA == nums[i]) {
                continue;
            }
            preA = nums[i];
            for (int j = i + 1; j < length - 2; j++) {
                if (preB == nums[j]) {
                    continue;
                }
                preB = nums[j];
                for (int k = j + 1; k < length - 1; k++) {
                    if (preC == nums[k]) {
                        continue;
                    }
                    preC = nums[k];
                    int find = target - nums[i] - nums[j] - nums[k];
                    if (find < nums[k + 1] || find > nums[length - 1]) {
                        continue;
                    } else {
                        for (int l = k + 1; l < length; l++) {
                            if (preD != nums[l]) {
                                if (nums[l] == find) {
                                    List<Integer> data = new ArrayList<>();
                                    data.add(nums[i]);
                                    data.add(nums[j]);
                                    data.add(nums[k]);
                                    data.add(nums[l]);
                                    result.add(data);
                                }
                                preD = nums[l];
                            }
                        }
                        preD = DEFAULT_NUM;
                    }
                }
                preC = DEFAULT_NUM;
            }
            preB = DEFAULT_NUM;
        }
        return result;
    }
}
