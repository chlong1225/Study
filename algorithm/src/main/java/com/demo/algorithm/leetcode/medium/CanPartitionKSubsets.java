package com.demo.algorithm.leetcode.medium;

import java.util.Arrays;

/**
 * create on 2023/11/22
 * @author chenglong
 * description : 划分为k个相等的子集
 *
 * 给定一个整数数组nums和一个正整数k，找出是否有可能把这个数组分成k个非空子集，其总和都相等。
 *
 * 示例 1：
 * 输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * 输出： True
 * 说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。
 *
 * 示例 2:
 * 输入: nums = [1,2,3,4], k = 3
 * 输出: false
 *
 * 提示：
 * 1 <= k <= len(nums) <= 16
 * 0 < nums[i] < 10000
 * 每个元素的频率在 [1,4] 范围内
 */
public class CanPartitionKSubsets {

    private boolean hasFind = false;
    private int target;

    public boolean canPartitionKSubsets(int[] nums, int k) {
        //1，求和提前判断可行性
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % k != 0) {
            return false;
        }
        //每个非空集合的总和
        target = sum / k;
        //2，对数据进行排序
        Arrays.sort(nums);
        int n = nums.length;
        if (nums[n - 1] > target) {
            //最大值超出了总和时无法构建集合
            return false;
        }
        //3，裁剪刚好满足target的值
        while (k > 0) {
            if (nums[n - 1] == target) {
                k--;
                n--;
            } else {
                break;
            }
        }
        if (k == 0) {
            return true;
        }
        //4，通过回溯搜索可行方案
        hasFind = false;
        //记录被访问的数据
        boolean[] visitors = new boolean[n];
        visitors[n - 1] = true;
        //每个集合搜索的起始值都是当前容器中的最大值
        dfs(nums[n-1], n - 2, k, nums, visitors);
        return hasFind;
    }

    private void dfs(int sum, int end, int k, int[] nums, boolean[] visitors) {
        for (int i = end; i >= 0; i--) {
            if (visitors[i] || (sum + nums[i] > target)) {
                continue;
            }
            if (hasFind) {
                return;
            }
            if (sum + nums[i] == target) {
                k--;
                if (k == 0) {
                    hasFind = true;
                    return;
                }
                visitors[i] = true;
                //查找下一个搜索集合的起始值
                int nextMax = visitors.length - 1;
                while (nextMax >= 0) {
                    if (visitors[nextMax]) {
                        nextMax--;
                    } else {
                        break;
                    }
                }
                visitors[nextMax] = true;
                dfs(nums[nextMax], nextMax - 1, k, nums, visitors);
                k++;
                visitors[i] = false;
                visitors[nextMax] = false;
            } else {
                visitors[i] = true;
                dfs(sum + nums[i], i - 1, k, nums, visitors);
                visitors[i] = false;
            }
        }
    }
}
