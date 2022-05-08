package com.demo.algorithm.leetcode.contest.week289;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2022/5/6.
 * description : 相邻字符不同的最长路径
 *
 * 给你一棵树（即一个连通、无向、无环图），根节点是节点0，这棵树由编号从0到n-1的n个节点组成。
 * 用下标从0开始长度为n的数组parent来表示这棵树，其中parent[i]是节点i的父节点，由于节点0是根节点，所以parent[0]==-1。
 * 另给你一个字符串s，长度也是n，其中s[i]表示分配给节点i的字符。
 * 请你找出路径上任意一对相邻节点都没有分配到相同字符的最长路径，并返回该路径的长度。
 *
 * 示例 1：
 * 输入：parent = [-1,0,0,1,1,2], s = "abacbe"
 * 输出：3
 * 解释：任意一对相邻节点字符都不同的最长路径是：0 -> 1 -> 3 。该路径的长度是 3 ，所以返回 3 。
 * 可以证明不存在满足上述条件且比 3 更长的路径。
 *
 * 示例 2：
 * 输入：parent = [-1,0,0,0], s = "aabc"
 * 输出：3
 * 解释：任意一对相邻节点字符都不同的最长路径是：2 -> 0 -> 3 。该路径的长度为 3 ，所以返回 3 。
 *
 * 提示：
 * n == parent.length == s.length
 * 1 <= n <= 10^5
 * 对所有 i >= 1 ，0 <= parent[i] <= n - 1 均成立
 * parent[0] == -1
 * parent 表示一棵有效的树
 * s 仅由小写英文字母组成
 */
public class LongestPath {

    private int max = 0;

    public int longestPath(int[] parent, String s) {
        max = 0;
        int length = parent.length;
        List<List<Integer>> dates = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            dates.add(new ArrayList<>());
        }
        for (int i = 1; i < length; i++) {
            dates.get(parent[i]).add(i);
        }
        dfs(0, s, dates);
        return max;
    }

    private int dfs(int cur, String s, List<List<Integer>> dates) {
        int max1 = 0;
        int max2 = 0;
        List<Integer> points = dates.get(cur);
        for (int i = 0; i < points.size(); i++) {
            int num = dfs(points.get(i), s, dates);
            if (s.charAt(cur) != s.charAt(points.get(i))) {
                if (num > max1) {
                    max2 = max1;
                    max1 = num;
                } else if (num > max2) {
                    max2 = num;
                }
            }
        }
        if (max < max1 + max2 + 1) {
            max = max1 + max2 + 1;
        }
        return max1+1;
    }
}
