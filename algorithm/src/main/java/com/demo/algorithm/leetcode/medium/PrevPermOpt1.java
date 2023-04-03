package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/4/3
 *
 * @author chenglong
 * description : 交换一次的先前排列
 *
 * 给你一个正整数数组arr（可能存在重复的元素），请你返回可在一次交换（交换两数字arr[i]和arr[j]的位置）后得到的、按字典序排列小于arr的最大排列。
 * 如果无法这么操作，就请返回原数组。
 *
 * 示例 1：
 * 输入：arr = [3,2,1]
 * 输出：[3,1,2]
 * 解释：交换 2 和 1
 *
 * 示例 2：
 * 输入：arr = [1,1,5]
 * 输出：[1,1,5]
 * 解释：已经是最小排列
 *
 * 示例 3：
 * 输入：arr = [1,9,4,6,7]
 * 输出：[1,7,4,6,9]
 * 解释：交换 9 和 7
 *
 * 提示：
 * 1 <= arr.length <= 10^4
 * 1 <= arr[i] <= 10^4
 */
public class PrevPermOpt1 {

    public int[] prevPermOpt1(int[] arr) {
        int n = arr.length;
        if (n == 1) {
            return arr;
        }
        //1，查找左边比右边大的数字
        int findIndex = -1;
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] > arr[i + 1]) {
                findIndex = i;
                break;
            }
        }
        if (findIndex == -1) {
            //当前arr已经是最小排列
            return arr;
        }
        //2，findIndex交换右边小的最大值
        int changeIndex = findIndex + 1;
        for (int i = findIndex + 2; i < n; i++) {
            if (arr[i] < arr[findIndex] && arr[i] > arr[changeIndex]) {
                changeIndex = i;
            }
        }
        int tem = arr[findIndex];
        arr[findIndex] = arr[changeIndex];
        arr[changeIndex] = tem;
        return arr;
    }
}
