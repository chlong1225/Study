package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/12/15
 * @author chenglong
 * description : 寻找重复数
 *
 * 给定一个包含n+1个整数的数组nums，其数字都在[1,n]范围内（包括1和n），可知至少存在一个重复的整数。
 * 假设nums只有一个重复的整数，返回这个重复的数 。
 * 你设计的解决方案必须不修改数组nums且只用常量级O(1)的额外空间。
 *
 * 示例 1：
 * 输入：nums = [1,3,4,2,2]
 * 输出：2
 *
 * 示例 2：
 * 输入：nums = [3,1,3,4,2]
 * 输出：3
 *
 * 提示：
 * 1 <= n <= 10^5
 * nums.length == n+1
 * 1 <= nums[i] <= n
 * nums中只有一个整数出现两次或多次，其余整数均只出现一次
 *
 * 进阶：
 * 如何证明nums中至少存在一个重复的数字?
 * 你可以设计一个线性级时间复杂度O(n)的解决方案吗？
 */
public class FindDuplicate {

    public int findDuplicate(int[] nums) {
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            while (nums[i] != i) {
                //此时需要将nums[i]交换给index = nums[i]的位置
                int nextIndex = nums[i];
                if (nums[nextIndex] == nums[i]) {
                    return nums[i];
                }
                int tem = nums[nextIndex];
                nums[nextIndex] = nums[i];
                nums[i] = tem;
            }
        }
        return nums[0];
    }
}
