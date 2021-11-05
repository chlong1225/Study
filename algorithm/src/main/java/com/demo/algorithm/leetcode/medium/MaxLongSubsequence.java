package com.demo.algorithm.leetcode.medium;

/**
 * create on 11/5/21
 * @author chenglong
 * description : 最长定差子序列
 *
 * 给你一个整数数组arr和一个整数difference，请你找出并返回arr中最长等差子序列的长度，该子序列中相邻元素之间的差等于difference 。
 * 子序列 是指在不改变其余元素顺序的情况下，通过删除一些元素或不删除任何元素而从 arr 派生出来的序列。
 *
 * 示例 1：
 * 输入：arr = [1,2,3,4], difference = 1
 * 输出：4
 * 解释：最长的等差子序列是 [1,2,3,4]。
 *
 * 示例 2：
 * 输入：arr = [1,3,5,7], difference = 1
 * 输出：1
 * 解释：最长的等差子序列是任意单个元素。
 *
 * 示例 3：
 * 输入：arr = [1,5,7,8,5,3,4,2,1], difference = -2
 *
 * 输出：4
 * 解释：最长的等差子序列是 [7,5,3,1]。
 *  
 * 提示：
 * 1 <= arr.length <= 105
 * -10^4 <= arr[i], difference <= 10^4
 */
public class MaxLongSubsequence {

    public static int longestSubsequence(int[] arr, int difference) {
        int max = 1;
        int length = arr.length;
        if (length == 1) {
            return 1;
        }
        for (int i = 0; i < length - 1; i++) {
            int count = 1;
            int start = arr[i];
            //可能的最大长度
            int compare = length - i;
            if (max >= compare) {
                return max;
            }
            for (int j = i + 1; j < length; j++) {
                //遍历中检查当前位置可能的最大长度的子序列
                int compare2 = count + length - j;
                if (max >= compare2) {
                    break;
                }
                if (arr[j] == start + difference) {
                    count++;
                    start = arr[j];
                }
            }
            if (count > max) {
                max = count;
            }
        }
        return max;
    }

    //使用空间换时间
    public static int longestSubsequence2(int[] arr, int difference) {
        int max = 1;
        int length = arr.length;
        if (length == 1) {
            return 1;
        }
        //根据范围-10^4 ～ 10^4
        int[] marks = new int[20001];
        for (int i = 0; i < length; i++) {
            int index = arr[i] + 10000;
            int pre = index - difference;
            if (pre >= 0 && pre <= 20000) {
                marks[index] = marks[pre] + 1;
                if (max < marks[index]) {
                    max = marks[index];
                }
            } else {
                marks[index] = 1;
            }
        }
        return max;
    }
}
