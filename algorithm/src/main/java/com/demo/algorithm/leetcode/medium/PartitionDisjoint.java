package com.demo.algorithm.leetcode.medium;

/**
 * create on 2022/10/24
 * @author chenglong
 * description : 分割数组
 *
 * 给定一个数组nums，将其划分为两个连续子数组left和right，使得：
 * left中的每个元素都小于或等于right中的每个元素。
 * left和right都是非空的。
 * left的长度要尽可能小。
 * 在完成这样的分组后返回left的长度。
 * 用例可以保证存在这样的划分方法。
 *
 * 示例 1：
 * 输入：nums = [5,0,3,8,6]
 * 输出：3
 * 解释：left = [5,0,3]，right = [8,6]
 *
 * 示例 2：
 * 输入：nums = [1,1,1,0,6,12]
 * 输出：4
 * 解释：left = [1,1,1,0]，right = [6,12]
 *
 * 提示：
 * 2 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^6
 * 可以保证至少有一种方法能够按题目所描述的那样对 nums 进行划分。
 */
public class PartitionDisjoint {

    public int partitionDisjoint(int[] nums) {
        int length = nums.length;
        //记录当前位置到右边的最小值
        int[] marks = new int[length];
        int min = nums[length - 1];
        marks[length - 1] = min;
        for (int i = length - 2; i >= 0; i--) {
            min = Math.min(min, nums[i]);
            marks[i] = min;
        }
        int max = nums[0];
        if (max <= marks[1]) {
            return 1;
        }
        for (int i = 1; i < length - 1; i++) {
            max = Math.max(max, nums[i]);
            if (max <= marks[i + 1]) {
                return i + 1;
            }
        }
        return 0;
    }
}
