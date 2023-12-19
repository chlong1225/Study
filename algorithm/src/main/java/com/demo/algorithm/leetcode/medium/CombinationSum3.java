package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * create on 2023/12/18
 * @author chenglong
 * description : 组合总和III
 *
 * 找出所有相加之和为n的k个数的组合，且满足下列条件：
 * 只使用数字1到9
 * 每个数字最多使用一次
 * 返回所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。
 *
 * 示例 1:
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 解释:
 * 1 + 2 + 4 = 7
 * 没有其他符合的组合了。
 *
 * 示例 2:
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 * 解释:
 * 1 + 2 + 6 = 9
 * 1 + 3 + 5 = 9
 * 2 + 3 + 4 = 9
 * 没有其他符合的组合了。
 *
 * 示例 3:
 * 输入: k = 4, n = 1
 * 输出: []
 * 解释: 不存在有效的组合。
 * 在[1,9]范围内使用4个不同的数字，我们可以得到的最小和是1+2+3+4 = 10，因为10 > 1，没有有效的组合。
 *
 * 提示:
 * 2 <= k <= 9
 * 1 <= n <= 60
 */
public class CombinationSum3 {

    private List<List<Integer>> answer;
    private int maxCount;

    public List<List<Integer>> combinationSum3(int k, int n) {
        answer = new ArrayList<>();
        maxCount = k;
        //1，计算k个数组合后最小与最大的和，并处理特殊场景
        int min = (1 + k) * k / 2;
        int max = (19 - k) * k / 2;
        if (n < min || n > max) {
            return answer;
        }
        if (n == min) {
            List<Integer> item = new ArrayList<>();
            for (int i = 1; i <= k; i++) {
                item.add(i);
            }
            answer.add(item);
            return answer;
        }
        if (n == max) {
            List<Integer> item = new ArrayList<>();
            for (int i = 9; i >= 10 - k; i--) {
                item.add(i);
            }
            Collections.reverse(item);
            answer.add(item);
            return answer;
        }
        //2，使用回溯算法
        List<Integer> path = new ArrayList<>();
        dfs(0, 9, n, path);
        return answer;
    }

    private void dfs(int sum, int end, int target, List<Integer> path) {
        if (sum > target) {
            return;
        }
        if (path.size() == maxCount) {
            if (sum == target) {
                List<Integer> item = new ArrayList<>(path);
                Collections.reverse(item);
                answer.add(item);
            }
            return;
        }
        for (int i = end; i >= 1; i--) {
            path.add(i);
            dfs(sum + i, i - 1, target, path);
            path.remove(path.size() - 1);
        }
    }
}
