package com.demo.algorithm.leetcode.easy;

/**
 * create on 2022/4/28
 * @author chenglong
 * description : 按奇偶排序数组
 *
 * 给你一个整数数组nums，将nums中的的所有偶数元素移动到数组的前面，后跟所有奇数元素。
 * 返回满足此条件的 任一数组 作为答案。
 *
 * 示例 1：
 * 输入：nums = [3,1,2,4]
 * 输出：[2,4,3,1]
 * 解释：[4,2,3,1]、[2,4,1,3] 和 [4,2,1,3] 也会被视作正确答案。
 *
 * 示例 2：
 * 输入：nums = [0]
 * 输出：[0]
 *
 * 提示：
 * 1 <= nums.length <= 5000
 * 0 <= nums[i] <= 5000
 */
public class SortArrayByParity {

    public int[] sortArrayByParity(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return nums;
        }
        /**
         * 使用双指针进行交换
         */
        int start = 0;
        int end = length - 1;
        while (start < end) {
            if (nums[start] % 2 == 1) {
                if (nums[end] % 2 == 0) {
                    //3，此时start为奇数，end为偶数。刚好可以交换
                    int tem = nums[end];
                    nums[end] = nums[start];
                    nums[start] = tem;
                    start++;
                    end--;
                } else {
                    //2。从右边遍历查找第一个偶数
                    for (int i = end - 1; i >= start; i--) {
                        if (nums[i] % 2 == 0) {
                            end = i;
                            break;
                        } else {
                            end = i - 1;
                        }
                    }
                }
            } else {
                //1，先从左边遍历查找第一个奇数
                for (int i = start + 1; i <= end; i++) {
                    if (nums[i] % 2 == 1) {
                        start = i;
                        break;
                    } else {
                        start = i + 1;
                    }
                }
            }
        }
        return nums;
    }
}
