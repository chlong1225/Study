package com.demo.algorithm.leetcode.easy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * create on 2023/4/25
 * @author chenglong
 * description : 按身高排序
 *
 * 给你一个字符串数组names，和一个由互不相同的正整数组成的数组heights。两个数组的长度均为n。
 * 对于每个下标i，names[i]和heights[i]表示第i个人的名字和身高。
 * 请按身高 降序 顺序返回对应的名字数组 names 。
 *
 * 示例 1：
 * 输入：names = ["Mary","John","Emma"], heights = [180,165,170]
 * 输出：["Mary","Emma","John"]
 * 解释：Mary 最高，接着是 Emma 和 John 。
 *
 * 示例 2：
 * 输入：names = ["Alice","Bob","Bob"], heights = [155,185,150]
 * 输出：["Bob","Alice","Bob"]
 * 解释：第一个 Bob 最高，然后是 Alice 和第二个 Bob 。
 *
 * 提示：
 * n == names.length == heights.length
 * 1 <= n <= 103
 * 1 <= names[i].length <= 20
 * 1 <= heights[i] <= 10^5
 * names[i] 由大小写英文字母组成
 * heights 中的所有值互不相同
 */
public class SortPeople {

    public String[] sortPeople(String[] names, int[] heights) {
        int n = names.length;
        int[][] dates = new int[n][2];
        for (int i = 0; i < n; i++) {
            dates[i][0] = heights[i];
            dates[i][1] = i;
        }
        Arrays.sort(dates, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] - o1[0];
            }
        });
        String[] result = new String[n];
        for (int i = 0; i < n; i++) {
            result[i] = names[dates[i][1]];
        }
        return result;
    }
}
