package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/12/13
 * @author chenglong
 * description : 轮转数组
 *
 * 给定一个整数数组nums，将数组中的元素向右轮转k个位置，其中k是非负数。
 *
 * 示例 1:
 * 输入: nums = [1,2,3,4,5,6,7], k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右轮转 1 步: [7,1,2,3,4,5,6]
 * 向右轮转 2 步: [6,7,1,2,3,4,5]
 * 向右轮转 3 步: [5,6,7,1,2,3,4]
 *
 * 示例 2:
 * 输入：nums = [-1,-100,3,99], k = 2
 * 输出：[3,99,-1,-100]
 * 解释:
 * 向右轮转 1 步: [99,-1,-100,3]
 * 向右轮转 2 步: [3,99,-1,-100]
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * -2^31 <= nums[i] <= 2^31 - 1
 * 0 <= k <= 10^5
 *
 * 进阶：
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 你可以使用空间复杂度为O(1)的原地算法解决这个问题吗？
 */
public class Rotate {

    public void rotate(int[] nums, int k) {
        int n = nums.length;
        int[] tem = new int[n];
        for (int i = 0; i < n; i++) {
            int index = (i + k) % n;
            tem[index] = nums[i];
        }
        for (int i = 0; i < n; i++) {
            nums[i] = tem[i];
        }
    }
}
