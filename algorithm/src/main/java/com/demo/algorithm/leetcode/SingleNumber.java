package com.demo.algorithm.leetcode;

/**
 * create by chenglong on 9/10/21
 * description : 只出现一次的数字
 *
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 说明：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 示例 1:
 * 输入: [2,2,1]
 * 输出: 1
 *
 * 示例 2:
 * 输入: [4,1,2,1,2]
 * 输出: 4
 */
public class SingleNumber {

    public static int singleNumber(int[] nums) {
        if (nums == null || nums.length % 2 == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int length = nums.length;
        int middle = (nums.length + 1) / 2;
        for (int i = 0; i < middle; i++) {
            boolean hasSame = false;
            for (int j = i + 1; j < length - i; j++) {
                if (nums[i] == nums[j]) {
                    hasSame = true;
                    //j跟length-1-i交换
                    if (length - 1 - i != j) {
                        int tem = nums[length - 1 - i];
                        nums[length - 1 - i] = nums[j];
                        nums[j] = tem;
                    }
                    break;
                }
            }
            if (!hasSame) {
                return nums[i];
            }
        }
        return nums[middle];
    }

    //位运算
    public static int singleNumber2(int[] nums) {
        if (nums == null || nums.length % 2 == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int result = nums[0];
        int length = nums.length;
        for (int i = 1; i < length; i++) {
            result ^= nums[i];
        }
        return result;
    }
}
