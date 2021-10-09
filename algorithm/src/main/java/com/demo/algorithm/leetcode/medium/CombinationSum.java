package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by chl on 2021/10/6.
 * description : 组合总和
 *
 * 给定一个无重复元素的正整数数组 candidates 和一个正整数 target ，找出 candidates 中所有可以使数字和为目标数 target 的唯一组合。
 * candidates 中的数字可以无限制重复被选取。如果至少一个所选数字数量不同，则两种组合是唯一的。 
 * 对于给定的输入，保证和为 target 的唯一组合数少于 150 个。
 *
 * 示例 1：
 * 输入: candidates = [2,3,6,7], target = 7
 * 输出: [[7],[2,2,3]]
 *
 * 示例 2：
 * 输入: candidates = [2,3,5], target = 8
 * 输出: [[2,2,2,2],[2,3,3],[3,5]]
 *
 * 示例 3：
 * 输入: candidates = [2], target = 1
 * 输出: []
 *
 * 示例 4：
 * 输入: candidates = [1], target = 1
 * 输出: [[1]]
 *
 * 示例 5：
 * 输入: candidates = [1], target = 2
 * 输出: [[1,1]]
 *  
 * 提示：
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都是独一无二的。
 * 1 <= target <= 500
 */
public class CombinationSum {

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        //1,先排序
        Arrays.sort(candidates);
        //2,枚举所有可能
        List<List<Integer>> pre = new ArrayList<>();
        List<List<Integer>> datas = new ArrayList<>();
        int length = candidates.length;
        for (int i = 0; i < length; i++) {
            if (candidates[i] < target) {
                List<Integer> data = new ArrayList<>();
                data.add(candidates[i]);
                data.add(candidates[i]);
                pre.add(data);
            } else if (candidates[i] == target) {
                List<Integer> data = new ArrayList<>();
                data.add(candidates[i]);
                result.add(data);
            } else {
                break;
            }
        }
        List<List<Integer>> temResult = new ArrayList<>();
        while (pre.size() > 0) {
            datas.clear();
            temResult.clear();
            int size = pre.size();
            for (int i = 0; i < size; i++) {
                List<Integer> tem = pre.get(i);
                for (int j = 0; j < length; j++) {
                    if (tem.get(0) + candidates[j] < target) {
                        List<Integer> tem2 = new ArrayList<>();
                        tem2.addAll(tem);
                        tem2.set(0, tem.get(0) + candidates[j]);
                        tem2.add(candidates[j]);
                        datas.add(tem2);
                    } else if (tem.get(0) + candidates[j] == target) {
                        tem.add(candidates[j]);
                        tem.remove(0);
                        temResult.add(tem);
                        break;
                    } else {
                        break;
                    }
                }
            }
            pre.clear();
            pre.addAll(datas);

            //temResult去重后添加到result中
            if (temResult.size() > 0) {
                List<List<Integer>> noRepeat = new ArrayList<>();
                noRepeat.add(temResult.get(0));
                temResult.remove(0);
                int temSize = temResult.size();
                for (int i = 0; i < temSize; i++) {
                    boolean isSame = isSame(noRepeat, temResult.get(i));
                    if (!isSame) {
                        noRepeat.add(temResult.get(i));
                    }
                }
                result.addAll(noRepeat);
            }
        }
        return result;
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        //1,先排序
        Arrays.sort(candidates);
        checkSum(candidates, 0, target, path, result);
        return result;
    }

    private static void checkSum(int[] candidates, int index, int target, List<Integer> path, List<List<Integer>> result) {
        int length = candidates.length;
        for (int i = index; i < length; i++) {
            if (candidates[i] == target) {
                List<Integer> tem = new ArrayList<>();
                tem.addAll(path);
                tem.add(candidates[i]);
                result.add(tem);
            } else if (candidates[i] < target) {
                path.add(candidates[i]);
                checkSum(candidates, i, target - candidates[i], path, result);
                path.remove(path.size() - 1);
            } else {
                break;
            }
        }
    }

    private static boolean isSame(List<List<Integer>> allDatas, List<Integer> data) {
        Collections.sort(data);
        int length = data.size();
        int size = allDatas.size();
        for (int i = 0; i < size; i++) {
            List<Integer> check = allDatas.get(i);
            Collections.sort(check);
            boolean isSame = true;
            for (int j = 0; j < length; j++) {
                if (data.get(j) != check.get(j)) {
                    isSame = false;
                    break;
                }
            }
            if (isSame) {
                return true;
            }
        }
        return false;
    }
}
