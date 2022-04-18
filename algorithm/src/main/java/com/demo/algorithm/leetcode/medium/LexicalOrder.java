package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2022/4/18.
 * description : 字典序排数
 *
 * 给你一个整数n ，按字典序返回范围[1, n]内所有整数。
 * 你必须设计一个时间复杂度为O(n)且使用O(1)额外空间的算法。
 *
 * 示例 1：
 * 输入：n = 13
 * 输出：[1,10,11,12,13,2,3,4,5,6,7,8,9]
 *
 * 示例 2：
 * 输入：n = 2
 * 输出：[1,2]
 *
 * 提示：
 * 1 <= n <= 5 * 10^4
 */
public class LexicalOrder {

    private int max;

    public List<Integer> lexicalOrder(int n) {
        List<Integer> result = new ArrayList<>(n);
        if (n <= 9) {
            for (int i = 1; i <= n; i++) {
                result.add(i);
            }
            return result;
        }
        max = n;
        for (int i = 1; i <= 9; i++) {
            dfs(i, result);
        }
        return result;
    }

    private void dfs(int pre, List<Integer> result) {
        result.add(pre);
        for (int i = pre * 10; i <= pre * 10 + 9; i++) {
            if (i > max) {
                break;
            }
            dfs(i, result);
        }
    }

    public List<Integer> lexicalOrder2(int n) {
        List<Integer> result = new ArrayList<>(n);
        int start = 1;
        for (int i = 0; i < n; i++) {
            result.add(start);
            if (start * 10 <= n) {
                start *= 10;
            } else {
                //遍历添加从：start~start+9。或start+1>n时截止
                while (start % 10 == 9 || start + 1 > n) {
                    //回溯到上一位：比如：100~109。然后11.
                    start /= 10;
                }
                start++;
            }
        }
        return result;
    }
}
