package com.demo.algorithm.leetcode.contest.week288;

import java.util.Arrays;

/**
 * Created by chl on 2022/4/13.
 * description :  K次增加后的最大乘积
 *
 * 给你一个非负整数数组nums和一个整数k。每次操作，你可以选择nums中任一元素并将它增加1。
 * 请你返回至多k次操作后，能得到的nums的最大乘积。由于答案可能很大，请你将答案对10^9+7取余后返回。
 *
 * 示例 1：
 * 输入：nums = [0,4], k = 5
 * 输出：20
 * 解释：将第一个数增加 5 次。
 * 得到 nums = [5, 4] ，乘积为 5 * 4 = 20 。
 * 可以证明 20 是能得到的最大乘积，所以我们返回 20 。
 * 存在其他增加 nums 的方法，也能得到最大乘积。
 *
 * 示例 2：
 * 输入：nums = [6,3,3,2], k = 2
 * 输出：216
 * 解释：将第二个数增加 1 次，将第四个数增加 1 次。
 * 得到 nums = [6, 4, 3, 3] ，乘积为 6 * 4 * 3 * 3 = 216 。
 * 可以证明 216 是能得到的最大乘积，所以我们返回 216 。
 * 存在其他增加 nums 的方法，也能得到最大乘积。
 *
 * 提示：
 * 1 <= nums.length, k <= 10^5
 * 0 <= nums[i] <= 10^6
 */
public class MaximumProduct {

    private static final int MOD = 1000000007;

    public int maximumProduct(int[] nums, int k) {
        /**
         * 分析：对应非负数。和相同时，两个数字越接近，乘积越大。比如：2+8=5+5，但5*5>2*8
         */
        int length = nums.length;
        if (length == 1) {
            return nums[0] + k;
        }
        //1，对数组进行排序
        Arrays.sort(nums);
        //2，依次增加数字，使更多的数字趋近相同
        for (int i = 1; i < length; i++) {
            if (nums[i] > nums[0]) {
                //将i之前的值添加到nums[i],需要的总次数
                int total = (nums[i] - nums[0]) * i;
                if (total < k) {
                    k -= total;
                    for (int j = 0; j < i; j++) {
                        nums[j] = nums[i];
                    }
                } else {
                    int add = k / i;
                    if (add > 0) {
                        for (int j = 0; j < i; j++) {
                            nums[j] += add;
                        }
                        k %= i;
                    }
                    for (int j = 0; j < k; j++) {
                        nums[j]++;
                    }
                    k = 0;
                    break;
                }
            }
        }
        //3，所有的数字相同后还有剩余时均分
        if (k > 0) {
            int add = k / length;
            if (add > 0) {
                for (int i = 0; i < length; i++) {
                    nums[i] += add;
                }
                k %= length;
            }
            for (int i = 0; i < k; i++) {
                nums[i]++;
            }
        }
        //4，数组遍历相乘并取模
        long sum = nums[0];
        for (int i = 1; i < length; i++) {
            sum *= nums[i];
            sum %= MOD;
        }
        return (int) sum;
    }
}
