package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/12/4
 * @author chenglong
 * description : 有序数组的平方
 *
 * 给你一个按非递减顺序排序的整数数组nums，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
 *
 * 示例 1：
 * 输入：nums = [-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 解释：平方后，数组变为 [16,1,0,9,100]
 * 排序后，数组变为 [0,1,9,16,100]
 *
 * 示例 2：
 * 输入：nums = [-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 *
 * 提示：
 * 1 <= nums.length <= 10^4
 * -10^4 <= nums[i] <= 10^4
 * nums已按非递减顺序排序
 *
 * 进阶：
 * 请你设计时间复杂度为O(n)的算法解决本问题
 */
public class SortedSquares {

    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        if (nums[0] >= 0) {
            //此时数组中全部为非负数
            for (int i = 0; i < n; i++) {
                nums[i] = nums[i] * nums[i];
            }
            return nums;
        }
        if (nums[n - 1] <= 0) {
            //此时数组中全部为非正数，平方后需要反转
            for (int i = 0; i < n; i++) {
                nums[i] = nums[i] * nums[i];
            }
            int middle = n / 2;
            for (int i = 0; i < middle; i++) {
                int tem = nums[i];
                nums[i] = nums[n - 1 - i];
                nums[n - 1 - i] = tem;
            }
            return nums;
        }
        //此时nums中有正数有负数
        int[] dates = new int[n];
        //1，查找绝对值最小的位置，然后两边进行发散
        int findIndex = 0;
        for (int i = 1; i < n; i++) {
            if (Math.abs(nums[findIndex]) >= Math.abs(nums[i])) {
                findIndex = i;
            } else {
                break;
            }
        }
        int index = 0;
        int left = findIndex;
        int right = findIndex;
        while (index < n) {
            if (left == right) {
                dates[index] = nums[left] * nums[left];
                left--;
                right++;
            } else if (left < 0) {
                dates[index] = nums[right] * nums[right];
                right++;
            } else if (right >= n) {
                dates[index] = nums[left] * nums[left];
                left--;
            } else {
                if (Math.abs(nums[left]) > Math.abs(nums[right])) {
                    dates[index] = nums[right] * nums[right];
                    right++;
                } else {
                    dates[index] = nums[left] * nums[left];
                    left--;
                }
            }
            index++;
        }
        return dates;
    }
}
