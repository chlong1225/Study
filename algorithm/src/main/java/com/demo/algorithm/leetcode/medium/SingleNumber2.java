package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/10/16
 * @author chenglong
 * description : 只出现一次的数字II
 *
 * 给你一个整数数组nums，除某个元素仅出现一次外，其余每个元素都恰出现三次。请你找出并返回那个只出现了一次的元素。
 * 你必须设计并实现线性时间复杂度的算法且使用常数级空间来解决此问题。
 *
 * 示例 1：
 * 输入：nums = [2,2,3,2]
 * 输出：3
 *
 * 示例 2：
 * 输入：nums = [0,1,0,1,0,1,99]
 * 输出：99
 *
 * 提示：
 * 1 <= nums.length <= 3 * 10^4
 * -2^31 <= nums[i] <= 2^31 - 1
 * nums中，除某个元素仅出现一次外，其余每个元素都恰出现三次
 */
public class SingleNumber2 {

    public int singleNumber(int[] nums) {
        //1，统计负数的个数
        int count = 0;
        int zeroCount = 0;
        int minCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == Integer.MIN_VALUE) {
                minCount++;
            } else if (nums[i] == 0) {
                zeroCount++;
            } else if (nums[i] < 0) {
                count++;
            }
        }
        if (minCount % 3 == 1) {
            return Integer.MIN_VALUE;
        }
        if (zeroCount % 3 == 1) {
            return 0;
        }
        //2，统计位上出现1的次数
        int[] counts = new int[32];
        if (count % 3 == 1) {
            //此时只出现一次的为负数
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] < 0 && nums[i] != Integer.MIN_VALUE) {
                    int tem = -nums[i];
                    int index = 0;
                    while (tem > 0) {
                        if (tem % 2 == 1) {
                            counts[index]++;
                        }
                        index++;
                        tem /= 2;
                    }
                }
            }
            int sum = 0;
            int base = 1;
            for (int i = 0; i < counts.length; i++) {
                if (counts[i] % 3 == 1) {
                    sum += base;
                }
                base *= 2;
            }
            sum = -sum;
            return sum;
        } else {
            //此时只出现一次的为正数
            for (int i = 0; i < nums.length; i++) {
                int tem = nums[i];
                if (tem > 0) {
                    int index = 0;
                    while (tem > 0) {
                        if (tem % 2 == 1) {
                            counts[index]++;
                        }
                        tem /= 2;
                        index++;
                    }
                }
            }
            int sum = 0;
            int base = 1;
            for (int i = 0; i < counts.length; i++) {
                if (counts[i] % 3 == 1) {
                    sum += base;
                }
                base *= 2;
            }
            return sum;
        }
    }
}
