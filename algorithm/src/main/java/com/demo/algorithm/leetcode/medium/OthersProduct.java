package com.demo.algorithm.leetcode.medium;

import java.util.Arrays;

/**
 * Created by chl on 2022/1/3.
 * description :  除自身以外数组的乘积
 *
 * 给你一个长度为n的整数数组nums，其中 n>1，返回输出数组output ，
 * 其中output[i]等于 nums 中除 nums[i]之外其余各元素的乘积。
 *
 * 示例:
 * 输入: [1,2,3,4]
 * 输出: [24,12,8,6]
 *  
 * 提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。
 * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 *
 * 进阶：
 * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 */
public class OthersProduct {

    public int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        //记录i之前数据的乘积
        int[] pre = new int[length];
        Arrays.fill(pre, 1);
        //记录i之后数据的乘积
        int[] result = new int[length];
        Arrays.fill(result, 1);
        for (int i = 1; i < length; i++) {
            pre[i] = nums[i - 1] * pre[i - 1];
        }
        for (int i = length - 2; i >= 0; i--) {
            result[i] = result[i + 1] * nums[i + 1];
        }
        for (int i = 0; i < length; i++) {
            result[i] = pre[i] * result[i];
        }
        return result;
    }

    public int[] productExceptSelf2(int[] nums) {
        int length = nums.length;
        //记录i之后数据的乘积
        int[] result = new int[length];
        result[0] = 1;
        for (int i = 1; i < length; i++) {
            result[i] = nums[i - 1] * result[i - 1];
        }
        int right = 1;
        for (int i = length - 1; i >= 0; i--) {
            result[i] = result[i] * right;
            right *= nums[i];
        }
        return result;
    }
}
