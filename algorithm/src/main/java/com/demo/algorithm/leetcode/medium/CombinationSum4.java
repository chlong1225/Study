package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/12/18
 * @author chenglong
 * description : 组合总和Ⅳ
 *
 * 给你一个由不同整数组成的数组nums，和一个目标整数target。请你从nums中找出并返回总和为target的元素组合的个数。
 * 题目数据保证答案符合32位整数范围。
 *
 * 示例 1：
 * 输入：nums = [1,2,3], target = 4
 * 输出：7
 * 解释：
 * 所有可能的组合为：
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * 请注意，顺序不同的序列被视作不同的组合。
 *
 * 示例 2：
 * 输入：nums = [9], target = 3
 * 输出：0
 *
 * 提示：
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 1000
 * nums 中的所有元素互不相同
 * 1 <= target <= 1000
 */
public class CombinationSum4 {

    public int combinationSum4(int[] nums, int target) {
        int[] marks = new int[target + 1];
        marks[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int j = 0; j < nums.length; j++) {
                //当前需要获取marks[i]的数量
                int pre = i - nums[j];
                if (pre >= 0) {
                    marks[i] += marks[pre];
                }
            }
        }
        return marks[target];
    }
}
