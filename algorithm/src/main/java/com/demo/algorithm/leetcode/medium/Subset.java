package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2021/11/6.
 * description : 子集
 *
 * 给你一个整数数组 nums ，数组中的元素互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集不能包含重复的子集。你可以按 任意顺序 返回解集。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 *
 * 示例 2：
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *  
 * 提示：
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * nums 中的所有元素 互不相同
 */
public class Subset {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int length = nums.length;
        result.add(new ArrayList<>());
        for (int i = 1; i <= length; i++) {
            List<List<Integer>> datas = new ArrayList<>();
            List<Integer> path = new ArrayList<>();
            //i代表添加子集中的元素数量
            findPath(0, nums, i, path, datas);
            result.addAll(datas);
        }
        return result;
    }

    //在nums中选择count个数据的方式
    private void findPath(int index, int[] nums, int count, List<Integer> path, List<List<Integer>> datas) {
        if (path.size() == count) {
            datas.add(new ArrayList<>(path));
            return;
        }
        int length = nums.length;
        if (index >= length) {
            return;
        }
        //把index之后的所有数据加上后最大的长度
        int maxLength = length - index + path.size();
        if (maxLength < count) {
            return;
        }
        for (int i = index; i < length; i++) {
            path.add(nums[i]);
            findPath(i + 1, nums, count, path, datas);
            path.remove(path.size() - 1);
        }
    }
}
