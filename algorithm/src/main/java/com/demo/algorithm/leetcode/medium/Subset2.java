package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2021/11/6.
 * description : 子集II
 *
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 *
 * 示例 1：
 * 输入：nums = [1,2,2]
 * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
 *
 * 示例 2：
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *  
 * 提示：
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 *
 */
public class Subset2 {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int length = nums.length;
        result.add(new ArrayList<>());
        //1，使用桶排序的思想记录相同数字的个数
        int[] numCounts = new int[21];
        for (int i = 0; i < length; i++) {
            int index = nums[i] + 10;
            numCounts[index]++;
        }
        //2,使用回溯算法统计数量
        for (int i = 1; i <= length; i++) {
            List<List<Integer>> datas = new ArrayList<>();
            List<Integer> path = new ArrayList<>();
            //i代表添加子集中的元素数量
            findPath(0, numCounts, i, path, datas);
            result.addAll(datas);
        }
        return result;
    }

    private void findPath(int index, int[] nums, int count, List<Integer> path, List<List<Integer>> datas) {
        if (path.size() == count) {
            datas.add(new ArrayList<>(path));
            return;
        }
        int length = nums.length;
        if (index >= length) {
            return;
        }
        for (int i = index; i < length; i++) {
            if (nums[i] == 0) {
                continue;
            }
            //对应的数字个数与数字大小
            int num = i - 10;
            int n = nums[i];
            for (int j = 1; j <= n; j++) {
                //num可以添加的数量为1~n个,但最多只能添加max个
                int max = count - path.size();
                if (j > max) {
                    continue;
                }
                for (int k = 0; k < j; k++) {
                    path.add(num);
                }
                findPath(i + 1, nums, count, path, datas);
                for (int k = 0; k < j; k++) {
                    path.remove(path.size() - 1);
                }
            }
        }
    }
}
