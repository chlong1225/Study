package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/11/22
 * @author chenglong
 * description : 移动零
 *
 * 给定一个数组nums，编写一个函数将所有0移动到数组的末尾，同时保持非零元素的相对顺序。
 * 请注意，必须在不复制数组的情况下原地对数组进行操作。
 *
 * 示例 1:
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 *
 * 示例 2:
 * 输入: nums = [0]
 * 输出: [0]
 *
 * 提示:
 *
 * 1 <= nums.length <= 10^4
 * -2^31 <= nums[i] <= 2^31 - 1
 *
 *
 * 进阶：你能尽量减少完成的操作次数吗？
 */
public class MoveZeroes {

    public void moveZeroes(int[] nums) {
        int n = nums.length;
        int index = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] != 0) {
                nums[index] = nums[i];
                index++;
            }
        }
        for (int i = index; i < n; i++) {
            nums[i] = 0;
        }
    }
}
