package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/2/27
 * @author chenglong
 * description : 递减元素使数组呈锯齿状
 *
 * 给你一个整数数组nums，每次操作会从中选择一个元素并将该元素的值减少1。
 * 如果符合下列情况之一，则数组A就是锯齿数组：
 * 每个偶数索引对应的元素都大于相邻的元素，即A[0] > A[1] < A[2] > A[3] < A[4] > ...
 * 或者，每个奇数索引对应的元素都大于相邻的元素，即A[0] < A[1] > A[2] < A[3] > A[4] < ...
 * 返回将数组nums转换为锯齿数组所需的最小操作次数。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：2
 * 解释：我们可以把 2 递减到 0，或把 3 递减到 1。
 *
 * 示例 2：
 * 输入：nums = [9,6,1,6,2]
 * 输出：4
 *
 * 提示：
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 1000
 */
public class MovesToMakeZigzag {

    public int movesToMakeZigzag(int[] nums) {
        int length = nums.length;
        if (length < 3) {
            return 0;
        }
        //1，奇数位置大于偶数位置
        int sum1 = 0;
        for (int i = 0; i < length; i++) {
            if (i % 2 == 0) {
                //偶数位置需要减小以便满足条件
                if (i == 0) {
                    //只需要判断右边
                    if (nums[i] >= nums[i + 1]) {
                        sum1 += (nums[i] + 1 - nums[i + 1]);
                    }
                } else if (i == length - 1) {
                    //只需要判断左边
                    if (nums[i - 1] <= nums[i]) {
                        sum1 += (nums[i] + 1 - nums[i - 1]);
                    }
                } else {
                    //判断左右两边
                    int compare = Math.min(nums[i - 1], nums[i + 1]);
                    if (compare <= nums[i]) {
                        sum1 += (nums[i] + 1 - compare);
                    }
                }
            }
        }
        //2，偶数位置大于奇数位置
        int sum2 = 0;
        for (int i = 0; i < length; i++) {
            if (i % 2 == 1) {
                //奇数位置需要减小以便满足条件
                if (i == length - 1) {
                    //只需要判断左边
                    if (nums[i - 1] <= nums[i]) {
                        sum2 += (nums[i] + 1 - nums[i - 1]);
                    }
                } else {
                    //判断左右两边
                    int compare = Math.min(nums[i - 1], nums[i + 1]);
                    if (compare <= nums[i]) {
                        sum2 += (nums[i] + 1 - compare);
                    }
                }
            }
        }
        return Math.min(sum1, sum2);
    }
}
