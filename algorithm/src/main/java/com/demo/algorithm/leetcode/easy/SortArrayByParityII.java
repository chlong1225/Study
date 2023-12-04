package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/12/4
 * @author chenglong
 * description : 按奇偶排序数组II
 *
 * 给定一个非负整数数组nums，nums中一半整数是奇数，一半整数是偶数。
 * 对数组进行排序，以便当nums[i]为奇数时，i也是奇数；当nums[i]为偶数时， i也是偶数。
 * 你可以返回任何满足上述条件的数组作为答案 。
 *
 * 示例 1：
 * 输入：nums = [4,2,5,7]
 * 输出：[4,5,2,7]
 * 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
 *
 * 示例 2：
 * 输入：nums = [2,3]
 * 输出：[2,3]
 *
 * 提示：
 * 2 <= nums.length <= 2 * 10^4
 * nums.length 是偶数
 * nums 中一半是偶数
 * 0 <= nums[i] <= 1000
 *
 * 进阶：可以不使用额外空间解决问题吗？
 */
public class SortArrayByParityII {

    public int[] sortArrayByParityII(int[] nums) {
        int n = nums.length;
        int findOneIndex = -1;
        int findDoubleIndex = -1;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                //当前nums[i]必须为偶数，为奇数时需要进行交换。并且从奇数位查找到偶数
                if (nums[i] % 2 == 1) {
                    if (findOneIndex == -1) {
                        findOneIndex = i + 1;
                    }
                    while (findOneIndex < n) {
                        if (nums[findOneIndex] % 2 == 0) {
                            break;
                        }
                        findOneIndex += 2;
                    }
                    int tem = nums[i];
                    nums[i] = nums[findOneIndex];
                    nums[findOneIndex] = tem;
                }
            } else {
                //当前nums[i]必须为奇数，为偶数时需要进行交换，并且从偶数位查找奇数
                if (nums[i] % 2 == 0) {
                    if (findDoubleIndex == -1) {
                        findDoubleIndex = i + 1;
                    }
                    while (findDoubleIndex < n) {
                        if (nums[findDoubleIndex] % 2 == 1) {
                            break;
                        }
                        findDoubleIndex += 2;
                    }
                    int tem = nums[i];
                    nums[i] = nums[findDoubleIndex];
                    nums[findDoubleIndex] = tem;
                }
            }
        }
        return nums;
    }
}
