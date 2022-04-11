package com.demo.algorithm.leetcode.contest.week284;

/**
 * Created by chl on 2022/4/1.
 * description :  K次操作后最大化顶端元素
 *
 * 给你一个下标从0开始的整数数组nums，它表示一个栈，其中nums[0]是栈顶的元素。
 * 每一次操作中，你可以执行以下操作 之一：
 * 如果栈非空，那么删除栈顶端的元素。
 * 如果存在1个或者多个被删除的元素，你可以从它们中选择任何一个，添加回栈顶，这个元素成为新的栈顶元素。
 * 同时给你一个整数k，它表示你总共需要执行操作的次数。
 * 请你返回 恰好执行k次操作以后，栈顶元素的最大值。如果执行完 k次操作以后，栈一定为空，请你返回 -1。
 *
 * 示例 1：
 * 输入：nums = [5,2,2,4,0,6], k = 4
 * 输出：5
 * 解释：
 * 4 次操作后，栈顶元素为 5 的方法之一为：
 * - 第 1 次操作：删除栈顶元素 5 ，栈变为 [2,2,4,0,6] 。
 * - 第 2 次操作：删除栈顶元素 2 ，栈变为 [2,4,0,6] 。
 * - 第 3 次操作：删除栈顶元素 2 ，栈变为 [4,0,6] 。
 * - 第 4 次操作：将 5 添加回栈顶，栈变为 [5,4,0,6] 。
 * 注意，这不是最后栈顶元素为 5 的唯一方式。但可以证明，4 次操作以后 5 是能得到的最大栈顶元素。
 *
 * 示例 2：
 * 输入：nums = [2], k = 1
 * 输出：-1
 * 解释：
 * 第 1 次操作中，我们唯一的选择是将栈顶元素弹出栈。
 * 由于 1 次操作后无法得到一个非空的栈，所以我们返回 -1 。
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i], k <= 10^9
 */
public class MaximumTop {

    public int maximumTop(int[] nums, int k) {
        int length = nums.length;
        //1，先处理k=0,1。length=1的特殊场景
        if (k == 0) {
            return nums[0];
        }
        if (k == 1) {
            if (length == 1) {
                return -1;
            } else {
                return nums[1];
            }
        }
        if (length == 1) {
            if (k % 2 == 0) {
                return nums[0];
            } else {
                return -1;
            }
        }
        //2，遍历奇数位置最大值与偶数位置最大值
        int n = Math.min(length - 1, k);
        int maxIndex = 0;
        int maxIndex1 = 1;
        for (int i = 0; i <= n; i++) {
            if (i % 2 == 0) {
                if (nums[i] > nums[maxIndex]) {
                    maxIndex = i;
                }
            } else {
                if (nums[i] > nums[maxIndex1]) {
                    maxIndex1 = i;
                }
            }
        }
        //3，分类讨论
        if (nums[maxIndex] > nums[maxIndex1]) {
            //偶数位置的数字最大
            if (k % 2 == 0) {
                return nums[maxIndex];
            }
            if (k - maxIndex >= 3) {
                return nums[maxIndex];
            }
            if (k >= (length + 1)) {
                return nums[maxIndex];
            }
            return nums[maxIndex1];
        } else {
            //此时奇数位置最大
            if (k % 2 == 1) {
                return nums[maxIndex1];
            }
            if (k - maxIndex1 >= 3) {
                return nums[maxIndex1];
            }
            if (k >= (length + 1)) {
                return nums[maxIndex1];
            }
            return nums[maxIndex];
        }
    }
}
