package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/12/1
 * @author chenglong
 * description : 错误的集合
 *
 * 集合s包含从1到n的整数。不幸的是，因为数据错误，导致集合里面某一个数字复制成了集合里面的另外一个数字的值，导致集合丢失了一个数字并且有一个数字重复。
 * 给定一个数组nums代表了集合S发生错误后的结果。
 * 请你找出重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
 *
 * 示例 1：
 * 输入：nums = [1,2,2,4]
 * 输出：[2,3]
 *
 * 示例 2：
 * 输入：nums = [1,1]
 * 输出：[1,2]
 *
 * 提示：
 * 2 <= nums.length <= 10^4
 * 1 <= nums[i] <= 10^4
 */
public class FindErrorNums {

    public int[] findErrorNums(int[] nums) {
        int repeat = 0;
        boolean[] marks = new boolean[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            if (marks[nums[i]]) {
                repeat = nums[i];
            } else {
                marks[nums[i]] = true;
            }
        }
        int miss = 0;
        for (int i = 1; i < marks.length; i++) {
            if (!marks[i]) {
                miss = i;
                break;
            }
        }
        return new int[]{repeat, miss};
    }
}
