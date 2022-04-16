package com.demo.algorithm.leetcode.offer;

/**
 * Created by chl on 2022/4/16.
 * description : 剑指Offer66. 构建乘积数组
 *
 * 给定一个数组A[0,1,…,n-1]，请构建一个数组B[0,1,…,n-1]，其中B[i]的值是数组A中除了下标i以外的元素的积,
 * 即B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。
 *
 * 示例:
 * 输入: [1,2,3,4,5]
 * 输出: [120,60,40,30,24]
 *
 * 提示：
 * 所有元素乘积之和不会溢出 32 位整数
 * a.length <= 100000
 */
public class ConstructArr {

    public int[] constructArr(int[] a) {
        /**
         * 分析：对应i。左边0~i-1。右边从i+1~n-1
         */
        if (a == null) {
            return a;
        }
        int length = a.length;
        if (length < 2) {
            return a;
        }
        if (length == 2) {
            int tem = a[1];
            a[1] = a[0];
            a[0] = tem;
            return a;
        }
        //1，统计左边的乘积
        int[] left = new int[length];
        left[0] = 1;
        for (int i = 1; i < length; i++) {
            left[i] = left[i - 1] * a[i - 1];
        }
        //2，统计右边的乘积
        int[] right = new int[length];
        right[length - 1] = 1;
        for (int i = length - 2; i >= 0; i--) {
            right[i] = right[i + 1] * a[i + 1];
        }
        //3，计算左右边的乘积
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = left[i] * right[i];
        }
        return result;
    }
}
