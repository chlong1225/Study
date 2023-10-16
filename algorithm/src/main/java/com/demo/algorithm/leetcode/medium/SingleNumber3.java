package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/10/16
 * @author chenglong
 * description : 只出现一次的数字III
 *
 * 给你一个整数数组nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。你可以按任意顺序返回答案。
 * 你必须设计并实现线性时间复杂度的算法且仅使用常量额外空间来解决此问题。
 *
 * 示例 1：
 * 输入：nums = [1,2,1,3,2,5]
 * 输出：[3,5]
 * 解释：[5, 3] 也是有效的答案。
 *
 * 示例 2：
 * 输入：nums = [-1,0]
 * 输出：[-1,0]
 *
 * 示例 3：
 * 输入：nums = [0,1]
 * 输出：[1,0]
 *
 * 提示：
 * 2 <= nums.length <= 3 * 10^4
 * -2^31 <= nums[i] <= 2^31 - 1
 * 除两个只出现一次的整数外，nums中的其他数字都出现两次
 */
public class SingleNumber3 {

    public int[] singleNumber(int[] nums) {
        if (nums.length == 2) {
            return nums;
        }
        //1，异或所有值，同时找出只出现一次的两个值
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum ^= nums[i];
        }
        //2，任意抽取一位不相同的进行分组：比如：3^5 = 110。此时可以根据从右到左的第一位或第二位进行分组
        int compare;
        if (sum == Integer.MAX_VALUE) {
            compare = sum;
        } else {
            compare = (sum & (-sum));
        }
        //3，此时可以使用compare将nums进行分组
        int a = 0;
        int b = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & compare) == 0) {
                a ^= nums[i];
            } else {
                b ^= nums[i];
            }
        }
        return new int[]{a, b};
    }
}
