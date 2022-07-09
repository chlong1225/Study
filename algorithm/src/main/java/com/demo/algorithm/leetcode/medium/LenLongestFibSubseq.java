package com.demo.algorithm.leetcode.medium;

/**
 * create on 2022/7/9
 * @author chenglong
 * description : 最长的斐波那契子序列的长度
 *
 * 如果序列X_1, X_2, ..., X_n满足下列条件，就说它是斐波那契式的：
 * n >= 3
 * 对于所有i + 2 <= n，都有X_i + X_{i+1} = X_{i+2}
 * 给定一个严格递增的正整数数组形成序列arr，找到arr中最长的斐波那契式的子序列的长度。如果一个不存在，返回0 。
 *（回想一下，子序列是从原序列arr中派生出来的，它从arr中删掉任意数量的元素（也可以不删），而不改变其余元素的顺序。例如，[3, 5, 8]是[3, 4, 5, 6, 7, 8]的一个子序列）
 *
 * 示例 1：
 * 输入: arr = [1,2,3,4,5,6,7,8]
 * 输出: 5
 * 解释: 最长的斐波那契式子序列为 [1,2,3,5,8] 。
 *
 * 示例2：
 * 输入: arr = [1,3,7,11,12,14,18]
 * 输出: 3
 * 解释: 最长的斐波那契式子序列有 [1,11,12]、[3,11,14] 以及 [7,11,18] 。
 *
 * 提示：
 * 3 <= arr.length <= 1000
 * 1 <= arr[i] < arr[i + 1] <= 10^9
 */
public class LenLongestFibSubseq {

    public int lenLongestFibSubseq(int[] arr) {
        int max = 0;
        int length = arr.length;
        for (int i = 0; i < length - 2; i++) {
            if (length - i <= max) {
                return max;
            }
            for (int j = i + 1; j < length - 1; j++) {
                if (length - j + 1 <= max) {
                    break;
                }
                int a = arr[i];
                int b = arr[j];
                int count = 2;
                int index = j + 1;
                while (index < length) {
                    int find = a + b;
                    int tem = findIndex(index, arr, find);
                    if (tem == -1) {
                        break;
                    }
                    count++;
                    index = tem + 1;
                    a = b;
                    b = find;
                }
                if (count > 2) {
                    if (count > max) {
                        max = count;
                    }
                }
            }
        }
        return max;
    }

    private int findIndex(int start, int[] arr, int target) {
        int end = arr.length - 1;
        if (arr[start] > target || arr[end] < target) {
            return -1;
        }
        if (arr[start] == target) {
            return start;
        }
        if (arr[end] == target) {
            return end;
        }
        while (start <= end) {
            int middle = (end - start) / 2 + start;
            if (arr[middle] == target) {
                return middle;
            }
            if (arr[middle] > target) {
                end = middle - 1;
            } else {
                start = middle + 1;
            }
        }
        return -1;
    }
}
