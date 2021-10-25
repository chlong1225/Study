package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2021/10/25.
 * description : 全排列
 *
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * 示例 2：
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 *
 * 示例 3：
 * 输入：nums = [1]
 * 输出：[[1]]
 *  
 * 提示：
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数 互不相同
 */
public class PermuteAll {

    //广度优先遍历
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int length = nums.length;
        List<Integer> data = new ArrayList<>();
        data.add(nums[0]);
        result.add(data);
        if (length == 1) {
            return result;
        }
        List<List<Integer>> next = new ArrayList<>();
        for (int i = 1; i < length; i++) {
            int tem = nums[i];
            int size = result.size();
            for (int j = 0; j < size; j++) {
                List<Integer> items = result.get(j);
                int itemSize = items.size();
                for (int k = 0; k < itemSize + 1; k++) {
                    List<Integer> item = new ArrayList<>();
                    item.addAll(items);
                    item.add(k, tem);
                    next.add(item);
                }
            }
            result.clear();
            result.addAll(next);
            next.clear();
        }
        return result;
    }

    //深度优先遍历
    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int length = nums.length;
        //深度遍历的轨迹
        List<Integer> path = new ArrayList<>();
        //记录已经遍历的元素
        boolean[] record = new boolean[length];
        dfs(nums, 0, path, record, result);
        return result;
    }

    private void dfs(int[] nums, int index, List<Integer> path, boolean[] record, List<List<Integer>> result) {
        int length = nums.length;
        if (index == length) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < length; i++) {
            if (record[i]) {
                continue;
            }
            int tem = nums[i];
            path.add(tem);
            record[i] = true;
            dfs(nums, index + 1, path, record, result);
            //状态回退
            path.remove(path.size() - 1);
            record[i] = false;
        }
    }
}
