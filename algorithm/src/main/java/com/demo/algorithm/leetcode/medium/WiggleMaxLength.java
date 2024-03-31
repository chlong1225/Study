package com.demo.algorithm.leetcode.medium;

/**
 * create by chenglong on 2023/12/19
 * description : 摆动序列
 *
 * 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为摆动序列。第一个差（如果存在的话）可能是正数或负数。
 * 仅有一个元素或者含两个不等元素的序列也视作摆动序列。
 * 例如，[1, 7, 4, 9, 2, 5] 是一个摆动序列，因为差值(6,-3,5,-7,3)是正负交替出现的。
 * 相反，[1, 4, 7, 2, 5] 和 [1, 7, 4, 5, 5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。
 * 子序列 可以通过从原始序列中删除一些（也可以不删除）元素来获得，剩下的元素保持其原始顺序。
 * 给你一个整数数组nums，返回nums中作为摆动序列的最长子序列的长度。
 *
 * 示例 1：
 * 输入：nums = [1,7,4,9,2,5]
 * 输出：6
 * 解释：整个序列均为摆动序列，各元素之间的差值为 (6, -3, 5, -7, 3) 。
 *
 * 示例 2：
 * 输入：nums = [1,17,5,10,13,15,10,5,16,8]
 * 输出：7
 * 解释：这个序列包含几个长度为 7 摆动序列。
 * 其中一个是 [1, 17, 10, 13, 10, 16, 8] ，各元素之间的差值为 (16, -7, 3, -3, 6, -8) 。
 *
 * 示例 3：
 * 输入：nums = [1,2,3,4,5,6,7,8,9]
 * 输出：2
 *
 * 提示：
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 1000
 *
 * 进阶：你能否用 O(n) 时间复杂度完成此题?
 */
public class WiggleMaxLength {

    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        //1，处理特殊场景
        if (n == 1) {
            return 1;
        }
        int preDiff = nums[1] - nums[0];
        int total = preDiff == 0 ? 1 : 2;
        for (int i = 1; i < n - 1; i++) {
            int diff = nums[i + 1] - nums[i];
            if (diff > 0 && preDiff <= 0) {
                total++;
                preDiff = diff;
            } else if (diff < 0 && preDiff >= 0) {
                total++;
                preDiff = diff;
            }
        }
        return total;
    }
}
