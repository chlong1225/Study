package com.demo.algorithm.leetcode.offer;

/**
 * Created by chl on 2022/4/15.
 * description : 剑指Offer56-II. 数组中数字出现的次数II
 *
 * 在一个数组nums中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
 *
 * 示例 1：
 * 输入：nums = [3,4,3,3]
 * 输出：4
 *
 * 示例 2：
 * 输入：nums = [9,1,7,9,7,9,7]
 * 输出：1
 *
 * 限制：
 * 1 <= nums.length <= 10000
 * 1 <= nums[i] < 2^31
 */
public class SingleNumber2 {

    public int singleNumber(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        //统计所有数字二进制1的数量
        int[] counts = new int[33];
        for (int i = 0; i < length; i++) {
            int n = nums[i];
            int index = 0;
            while (n > 0) {
                if (n % 2 == 1) {
                    counts[index]++;
                }
                index++;
                n /= 2;
            }
        }
        long sum = 0;
        long add = 1;
        for (int i = 0; i < 33; i++) {
            if (counts[i] % 3 == 1) {
                sum += add;
            }
            add *= 2;
        }
        return (int) sum;
    }
}
