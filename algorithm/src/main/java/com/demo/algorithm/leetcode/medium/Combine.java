package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2021/11/4.
 * description : 组合
 *
 * 给定两个整数n和k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * 你可以按 任何顺序 返回答案。
 *
 * 示例 1：
 * 输入：n = 4, k = 2
 * 输出：
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 *
 * 示例 2：
 * 输入：n = 1, k = 1
 * 输出：[[1]]
 *  
 * 提示：
 * 1 <= n <= 20
 * 1 <= k <= n
 */
public class Combine {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        findCombine(1, k, n, path, result);
        return result;
    }

    private void findCombine(int index, int k, int n, List<Integer> path, List<List<Integer>> result) {
        if (path.size() == k) {
            List<Integer> data = new ArrayList<>(path);
            result.add(data);
            return;
        }
        for (int i = index; i <= n; i++) {
            path.add(i);
            findCombine(i + 1, k, n, path, result);
            path.remove(path.size() - 1);
        }
    }
}
