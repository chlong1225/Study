package com.demo.algorithm.leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2021/10/14.
 * description : 排列序列
 *
 * 给出集合 [1,2,3,...,n]，其所有元素共有 n! 种排列。
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 *
 * 示例 1：
 * 输入：n = 3, k = 3
 * 输出："213"
 *
 * 示例 2：
 * 输入：n = 4, k = 9
 * 输出："2314"
 *
 * 示例 3：
 * 输入：n = 3, k = 1
 * 输出："123"
 *
 * 提示：
 * 1 <= n <= 9
 * 1 <= k <= n!
 */
public class Permutation {

    public String getPermutation(int n, int k) {
        if (n < 1 || k < 1) {
            return "";
        }
        if (n == 1) {
            if (k == 1) {
                return "1";
            }
            return "";
        }
        StringBuilder builder = new StringBuilder();
        int max = 1;
        for (int i = 2; i < n; i++) {
            max *= i;
        }
        k--;
        if (k / n >= max) {
            //k超出范围
            return "";
        }
        int count = n - 1;
        int addNum = 0;
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            data.add(i + 1);
        }
        while (addNum < n) {
            int index = k / max;
            builder.append(data.get(index));
            data.remove(index);
            k %= max;
            if (count > 0) {
                max /= count;
                count--;
            }
            addNum++;
        }
        return builder.toString();
    }

}
