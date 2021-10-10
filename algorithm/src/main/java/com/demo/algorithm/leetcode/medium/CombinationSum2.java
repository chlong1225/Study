package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chl on 2021/10/8.
 * description : 组合总和II
 *
 * 给定一个正整数数组candidates和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用一次。
 * 注意：解集不能包含重复的组合。 
 *
 * 示例 1:
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 输出:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 *
 * 示例 2:
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 输出:
 * [
 * [1,2,2],
 * [5]
 * ]
 *  
 * 提示:
 * 1 <= candidates.length <= 100
 * 1 <= candidates[i] <= 50
 * 1 <= target <= 30
 */
public class CombinationSum2 {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        checkSum(candidates,0,target,path,result);
        return result;
    }

    private void checkSum(int[] candidates, int index, int target, List<Integer> path, List<List<Integer>> result) {
        int length = candidates.length;
        if (index >= length) {
            return;
        }
        int count = 0;
        int tem = candidates[index];
        for (int i = index; i < length; i++) {
            if (candidates[i] == tem) {
                count++;
            } else {
                for (int j = 1; j <= count; j++) {
                    if (tem * j == target) {
                        List<Integer> data = new ArrayList<>(path);
                        for (int k = 0; k < j; k++) {
                            data.add(tem);
                        }
                        result.add(data);
                        break;
                    } else if (tem * j < target) {
                        for (int k = 0; k < j; k++) {
                            path.add(tem);
                        }
                        checkSum(candidates, i, target - tem * j, path, result);
                        for (int k = 0; k < j; k++) {
                            path.remove(path.size() - 1);
                        }
                    } else {
                        break;
                    }
                }
                tem = candidates[i];
                count = 1;
            }
        }
        if (count > 0) {
            for (int i = 1; i <= count; i++) {
                if (tem * i == target) {
                    List<Integer> data = new ArrayList<>(path);
                    for (int j = 0; j < i; j++) {
                        data.add(tem);
                    }
                    result.add(data);
                    break;
                }
            }
        }
    }
}
