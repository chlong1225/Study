package com.demo.algorithm.leetcode.easy;

/**
 * create on 2022/1/13
 * @author chenglong
 * description : 至少是其他数字两倍的最大数
 *
 * 给你一个整数数组nums，其中总是存在唯一的一个最大整数 。
 * 请你找出数组中的最大元素并检查它是否至少是数组中每个其他数字的两倍。如果是，则返回最大元素的下标，否则返回-1 。
 *
 * 示例 1：
 * 输入：nums = [3,6,1,0]
 * 输出：1
 * 解释：6 是最大的整数，对于数组中的其他整数，6 大于数组中其他元素的两倍。6 的下标是 1 ，所以返回 1 。
 *
 * 示例 2：
 * 输入：nums = [1,2,3,4]
 * 输出：-1
 * 解释：4 没有超过 3 的两倍大，所以返回 -1 。
 *
 * 示例 3：
 * 输入：nums = [1]
 * 输出：0
 * 解释：因为不存在其他数字，所以认为现有数字 1 至少是其他数字的两倍。
 *
 * 提示：
 * 1 <= nums.length <= 50
 * 0 <= nums[i] <= 100
 * nums 中的最大元素是唯一的
 */
public class DoubleMaxNum {

    public int dominantIndex(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return 0;
        }
        int maxIndex = 0;
        //1，找出最大值
        for (int i = 1; i < length ; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }
        //2，与其它元素进行对比
        int compare = nums[maxIndex] >> 1;
        for (int i = 0; i < length; i++) {
            if (i == maxIndex) {
                continue;
            }
            if (nums[i] > compare) {
                return -1;
            }
        }
        return maxIndex;
    }
}
