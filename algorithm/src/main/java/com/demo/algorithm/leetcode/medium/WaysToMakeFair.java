package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/1/28
 * @author chenglong
 * description :  生成平衡数组的方案数
 *
 * 给你一个整数数组nums。你需要选择恰好一个下标（下标从0开始）并删除对应的元素。请注意剩下元素的下标可能会因为删除操作而发生改变。
 * 比方说，如果nums = [6,1,7,4,1]，那么：
 * 选择删除下标1，剩下的数组为nums = [6,7,4,1]。
 * 选择删除下标2，剩下的数组为nums = [6,1,4,1]。
 * 选择删除下标4，剩下的数组为nums = [6,1,7,4]。
 * 如果一个数组满足奇数下标元素的和与偶数下标元素的和相等，该数组就是一个平衡数组。
 * 请你返回删除操作后，剩下的数组nums是平衡数组的方案数。
 *
 * 示例 1：
 * 输入：nums = [2,1,6,4]
 * 输出：1
 * 解释：
 * 删除下标 0 ：[1,6,4] -> 偶数元素下标为：1 + 4 = 5 。奇数元素下标为：6 。不平衡。
 * 删除下标 1 ：[2,6,4] -> 偶数元素下标为：2 + 4 = 6 。奇数元素下标为：6 。平衡。
 * 删除下标 2 ：[2,1,4] -> 偶数元素下标为：2 + 4 = 6 。奇数元素下标为：1 。不平衡。
 * 删除下标 3 ：[2,1,6] -> 偶数元素下标为：2 + 6 = 8 。奇数元素下标为：1 。不平衡。
 * 只有一种让剩余数组成为平衡数组的方案。
 *
 * 示例 2：
 * 输入：nums = [1,1,1]
 * 输出：3
 * 解释：你可以删除任意元素，剩余数组都是平衡数组。
 *
 * 示例 3：
 * 输入：nums = [1,2,3]
 * 输出：0
 * 解释：不管删除哪个元素，剩下数组都不是平衡数组。
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^4
 */
public class WaysToMakeFair {

    public int waysToMakeFair(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return 1;
        }
        if (length == 2) {
            return 0;
        }
        int count = 0;
        //左边的奇数和
        long sumLeft1 = 0;
        //左边的偶数和
        long sumLeft2 = 0;
        //右边的奇数和，index相对未删除前的位置
        long sumRight1 = 0;
        //右边的偶数和，index相对未删除前的位置
        long sumRight2 = 0;
        //1，初始删除index=0位置时，左边和都为0
        for (int i = 1; i < length; i++) {
            if (i % 2 == 1) {
                sumRight1 += nums[i];
            } else {
                sumRight2 += nums[i];
            }
        }
        if (sumRight1 == sumRight2) {
            count++;
        }
        //2，依次删除数字并求和
        for (int i = 1; i < length; i++) {
            //之前被删除的数
            int pre = nums[i - 1];
            //当前需要被删除的数
            int cur = nums[i];
            //左边需要加上pre，右边需要减少cur
            if (i % 2 == 0) {
                sumLeft1 += pre;
                sumRight2 -= cur;
            } else {
                sumLeft2 += pre;
                sumRight1 -= cur;
            }
            if (sumLeft1 + sumRight2 == sumLeft2 + sumRight1) {
                count++;
            }
        }
        return count;
    }
}
