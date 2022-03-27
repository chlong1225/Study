package com.demo.algorithm.leetcode.offer;

/**
 * Created by chl on 2022/3/27.
 * description : 剑指Offer21. 调整数组顺序使奇数位于偶数前面
 *
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数在数组的前半部分，所有偶数在数组的后半部分。
 *
 * 示例：
 * 输入：nums = [1,2,3,4]
 * 输出：[1,3,2,4]
 * 注：[3,1,2,4] 也是正确的答案之一。
 *
 * 提示：
 * 0 <= nums.length <= 50000
 * 0 <= nums[i] <= 10000
 */
public class Exchange {

    public int[] exchange(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        int length = nums.length;
        int start = 0;
        int end = length - 1;
        while (start < end) {
            if (nums[start] % 2 == 0) {
                while (end > start) {
                    if (nums[end] % 2 == 1) {
                        //直接交换
                        int tem = nums[end];
                        nums[end] = nums[start];
                        nums[start] = tem;
                        start++;
                        end--;
                        break;
                    } else {
                        end--;
                    }
                }
            } else {
                start++;
            }
        }
        return nums;
    }
}
