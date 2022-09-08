package com.demo.algorithm.leetcode.medium;

/**
 * create on 2022/9/8
 * @author chenglong
 * description : 优美的排列II
 *
 * 给你两个整数n和k，请你构造一个答案列表answer，该列表应当包含从1到n的n个不同正整数，并同时满足下述条件：
 * 假设该列表是answer=[a1, a2, a3, ... , an]，那么列表[|a1 - a2|, |a2 - a3|, |a3 - a4|, ... , |an-1 - an|]中应该有且仅有k个不同整数。
 * 返回列表answer。如果存在多种答案，只需返回其中任意一种。
 *
 * 示例 1：
 * 输入：n = 3, k = 1
 * 输出：[1, 2, 3]
 * 解释：[1, 2, 3]包含3个范围在1-3的不同整数，并且[1, 1]中有且仅有1个不同整数：1
 *
 * 示例 2：
 * 输入：n = 3, k = 2
 * 输出：[1, 3, 2]
 * 解释：[1, 3, 2]包含3个范围在1-3的不同整数，并且[2,1]中有且仅有2个不同整数：1和2
 *
 * 提示：
 * 1 <= k < n <= 10^4
 */
public class ConstructArray {

    public int[] constructArray(int n, int k) {
        /**
         * 将数字排列成：1，2，3...a,n,a+1,n-1,a+2,n-2,a+3,n-3...的结构。
         * 此时不同整数的数量为：1+(n-a)并且a>1
         * 如果a=1，此时数量为：n-1
         */
        int[] result = new int[n];
        if (k == 1) {
            for (int i = 0; i < n; i++) {
                result[i] = i + 1;
            }
        } else if (k == n - 1) {
            int start = 1;
            for (int i = 0; i < n; i += 2) {
                result[i] = start;
                start++;
            }
            int end = n;
            for (int i = 1; i < n; i += 2) {
                result[i] = end;
                end--;
            }
        } else {
            int start = n - k;
            //1，依次填充1～start之间的数
            int index = 0;
            for (int i = 1; i < start; i++) {
                result[index] = i;
                index++;
            }
            for (int i = index; i < n; i += 2) {
                result[i] = start;
                start++;
            }
            int end = n;
            for (int i = index + 1; i < n; i += 2) {
                result[i] = end;
                end--;
            }
        }
        return result;
    }
}
