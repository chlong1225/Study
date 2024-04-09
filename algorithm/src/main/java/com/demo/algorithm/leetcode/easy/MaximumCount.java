package com.demo.algorithm.leetcode.easy;

/**
 * create on 2024/4/9
 * @author chenglong
 * description : 正整数和负整数的最大计数
 *
 * 给你一个按非递减顺序排列的数组nums，返回正整数数目和负整数数目中的最大值。
 * 换句话讲，如果nums中正整数的数目是pos，而负整数的数目是neg，返回pos和neg二者中的最大值。
 * 注意：0既不是正整数也不是负整数。
 *
 * 示例 1：
 * 输入：nums = [-2,-1,-1,1,2,3]
 * 输出：3
 * 解释：共有 3 个正整数和 3 个负整数。计数得到的最大值是 3 。
 *
 * 示例 2：
 * 输入：nums = [-3,-2,-1,0,0,1,2]
 * 输出：3
 * 解释：共有 2 个正整数和 3 个负整数。计数得到的最大值是 3 。
 *
 * 示例 3：
 * 输入：nums = [5,20,66,1314]
 * 输出：4
 * 解释：共有 4 个正整数和 0 个负整数。计数得到的最大值是 4 。
 *
 * 提示：
 * 1 <= nums.length <= 2000
 * -2000 <= nums[i] <= 2000
 * nums 按 非递减顺序 排列。
 *
 * 进阶：你可以设计并实现时间复杂度为 O(log(n)) 的算法解决此问题吗？
 */
public class MaximumCount {

    public int maximumCount(int[] nums) {
        int n = nums.length;
        int neg = 0;
        int pos = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] < 0) {
                neg++;
            } else if (nums[i] > 0) {
                pos = n - i;
                break;
            }
        }
        return Math.max(neg, pos);
    }
}
