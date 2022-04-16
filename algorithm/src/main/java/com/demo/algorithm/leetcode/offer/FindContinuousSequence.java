package com.demo.algorithm.leetcode.offer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2022/4/16.
 * description : 剑指Offer57-II. 和为s的连续正数序列
 *
 * 输入一个正整数target，输出所有和为target的连续正整数序列（至少含有两个数）。
 * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
 *
 * 示例 1：
 * 输入：target = 9
 * 输出：[[2,3,4],[4,5]]
 *
 * 示例 2：
 * 输入：target = 15
 * 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
 *
 * 限制：
 * 1 <= target <= 10^5
 */
public class FindContinuousSequence {

    public int[][] findContinuousSequence(int target) {
        int max = target / 2;
        int maxEnd = max + 1;
        List<int[]> result = new ArrayList<>();
        for (int i = 1; i <= max; i++) {
            int sum = i;
            for (int j = i + 1; j <= maxEnd; j++) {
                sum += j;
                if (sum > target) {
                    break;
                }
                if (sum == target) {
                    //i~j之间
                    int count = j - i + 1;
                    int[] items = new int[count];
                    for (int l = i; l <= j; l++) {
                        items[l - i] = l;
                    }
                    result.add(items);
                }
            }
        }
        int m = result.size();
        if (m == 0) {
            return new int[0][0];
        }
        int[][] ans = new int[m][];
        for (int i = 0; i < m; i++) {
            ans[i] = result.get(i);
        }
        return ans;
    }
}
