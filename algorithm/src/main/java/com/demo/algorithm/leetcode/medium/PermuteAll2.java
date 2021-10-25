package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chl on 2021/10/25.
 * description : 全排列II
 *
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 *
 * 示例 1：
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],[1,2,1],[2,1,1]]
 *
 * 示例 2：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *  
 * 提示：
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 */
public class PermuteAll2 {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int length = nums.length;
        List<Integer> path = new ArrayList<>();
        //1，单个元素时直接返回
        if (length == 1) {
            path.add(nums[0]);
            result.add(path);
            return result;
        }
        //2，先排序
        Arrays.sort(nums);
        boolean[] record = new boolean[length];
        dfs(nums, 0, path, record, result);
        return result;
    }

    private void dfs(int[] nums, int count, List<Integer> path, boolean[] record, List<List<Integer>> result) {
        int length = nums.length;
        if (count == length) {
            result.add(new ArrayList<>(path));
            return;
        }
        int pre = nums[0] - 1;
        for (int i = 0; i < length; i++) {
            if (record[i]) {
                continue;
            }
            int tem = nums[i];
            if (pre == tem) {
                continue;
            }
            path.add(tem);
            record[i] = true;
            dfs(nums, count + 1, path, record, result);
            //状态回退
            path.remove(path.size() - 1);
            record[i] = false;
            pre = tem;
        }
    }
}
