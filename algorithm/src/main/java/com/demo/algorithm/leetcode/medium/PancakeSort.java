package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2022/2/19.
 * description : 煎饼排序
 *
 * 给你一个整数数组arr，请使用煎饼翻转完成对数组的排序。
 * 一次煎饼翻转的执行过程如下：
 * 选择一个整数k ，1<= k <= arr.length
 * 反转子数组 arr[0...k-1]（下标从0开始）
 * 例如，arr = [3,2,1,4] ，选择k=3进行一次煎饼翻转，反转子数组[3,2,1]，得到arr=[1,2,3,4] 。
 * 以数组形式返回能使arr有序的煎饼翻转操作所对应的k值序列。任何将数组排序且翻转次数在10*arr.length范围内的有效答案都将被判断为正确。
 *
 * 示例 1：
 * 输入：[3,2,4,1]
 * 输出：[4,2,4,3]
 * 解释：
 * 我们执行 4 次煎饼翻转，k 值分别为 4，2，4，和 3。
 * 初始状态 arr = [3, 2, 4, 1]
 * 第一次翻转后（k = 4）：arr = [1, 4, 2, 3]
 * 第二次翻转后（k = 2）：arr = [4, 1, 2, 3]
 * 第三次翻转后（k = 4）：arr = [3, 2, 1, 4]
 * 第四次翻转后（k = 3）：arr = [1, 2, 3, 4]，此时已完成排序。
 *
 * 示例 2：
 * 输入：[1,2,3]
 * 输出：[]
 * 解释：
 * 输入已经排序，因此不需要翻转任何内容。
 * 请注意，其他可能的答案，如 [3，3] ，也将被判断为正确
 *
 * 提示：
 * 1 <= arr.length <= 100
 * 1 <= arr[i] <= arr.length
 * arr中的所有整数互不相同（即，arr是从1到arr.length整数的一个排列）
 */
public class PancakeSort {

    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> result = new ArrayList<>();
        int length = arr.length;
        for (int max = length - 1; max >= 1; max--) {
            //在0~max之间查找最大值的index
            int index = 0;
            for (int i = 1; i <= max; i++) {
                if (arr[index] < arr[i]) {
                    index = i;
                }
            }
            if (index == max) {
                //最后一个为最大值,此时不需要反转
                continue;
            }
            //反转分两步.1,将最大值反转到index = 0
            if (index != 0) {
                reversal(index, arr);
                result.add(index + 1);
            }
            //2，将0~max之间进行翻转
            reversal(max, arr);
            result.add(max + 1);
        }
        return result;
    }

    //将数组arr从0~n之间进行翻转
    private void reversal(int n, int[] arr) {
        for (int i = 0; i <= n / 2; i++) {
            int tem = arr[i];
            arr[i] = arr[n - i];
            arr[n - i] = tem;
        }
    }
}
