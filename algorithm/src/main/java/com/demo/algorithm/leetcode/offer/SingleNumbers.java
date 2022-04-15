package com.demo.algorithm.leetcode.offer;

/**
 * Created by chl on 2022/4/15.
 * description : 剑指Offer56-I. 数组中数字出现的次数
 *
 * 一个整型数组nums里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 *
 * 示例 1：
 * 输入：nums = [4,1,4,6]
 * 输出：[1,6] 或 [6,1]
 *
 * 示例 2：
 * 输入：nums = [1,2,10,4,1,4,3,3]
 * 输出：[2,10] 或 [10,2]
 *
 * 限制：
 * 2 <= nums.length <= 10000
 */
public class SingleNumbers {

    public int[] singleNumbers(int[] nums) {
        int length = nums.length;
        if (length == 2) {
            return nums;
        }
        //1，对所有的数进行异或运行，找到不同的两个数a，b的异或值，即：sum = a^b
        int sum = nums[0];
        for (int i = 1; i < length; i++) {
            sum ^= nums[i];
        }
        //2，找到sum二进制首次1
        int count = 0;
        while (sum > 0) {
            if (sum % 2 == 1) {
                break;
            }
            sum /= 2;
            count++;
        }
        //3，对数组进行分类的数字
        int group = 1 << count;
        int a = 0;
        int b = 0;
        for (int i = 0; i < length; i++) {
            if ((nums[i] & group) == 0) {
                a ^= nums[i];
            } else {
                b ^= nums[i];
            }
        }
        int[] result = new int[2];
        result[0] = a;
        result[1] = b;
        return result;
    }
}
