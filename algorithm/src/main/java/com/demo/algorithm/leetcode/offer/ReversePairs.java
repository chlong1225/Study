package com.demo.algorithm.leetcode.offer;

/**
 * Created by chl on 2022/4/24.
 * description : 剑指Offer51. 数组中的逆序对
 *
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 *
 * 示例 1:
 * 输入: [7,5,6,4]
 * 输出: 5
 *
 * 限制：
 * 0 <= 数组长度 <= 50000
 */
public class ReversePairs {

    private int count = 0;
    private int[] dates;
    private int[] extra;

    public int reversePairs(int[] nums) {
        /**
         * 标准的归并排序
         */
        int length = nums.length;
        if (length < 2) {
            return 0;
        }
        count = 0;
        dates = nums;
        extra = new int[length];
        mergeSort(0, nums.length - 1);
        return count;
    }

    private void mergeSort(int start, int end) {
        if (start >= end) {
            return;
        }
        int middle = (end - start) / 2 + start;
        mergeSort(start, middle);
        mergeSort(middle + 1, end);
        //此时start~middle有序。middle+1~end有序，合并两个有序数组。并借助额外数组
        int i = start;
        int j = middle + 1;
        int index = start;
        while (i <= middle && j <= end) {
            if (dates[i] <= dates[j]) {
                extra[index] = dates[i];
                i++;
            } else {
                extra[index] = dates[j];
                j++;
                count += (middle - i + 1);
            }
            index++;
        }
        if (i > middle) {
            //左边的数据全部使用，右边的数据还有多余，此时不存在逆序对
            for (int l = start; l < index; l++) {
                //保证从start~end有序
                dates[l] = extra[l];
                //数据重置
                extra[l] = 0;
            }
        } else {
            //右边的数据全部使用，左边还剩余数据，此时存在逆序对
            int space = end - middle;
            for (int l = middle; l >= i; l--) {
                dates[l + space] = dates[l];
            }
            for (int l = start; l < index; l++) {
                dates[l] = extra[l];
                extra[l] = 0;
            }
        }
    }
}
