package com.demo.algorithm.leetcode.contest.week305;

/**
 * create on 2022/8/9
 * @author chenglong
 * description : 算术三元组的数目
 *
 * 给你一个下标从0开始、严格递增的整数数组nums和一个正整数diff。如果满足下述全部条件，则三元组(i, j, k) 就是一个算术三元组：
 * i < j < k ，
 * nums[j] - nums[i] == diff 且
 * nums[k] - nums[j] == diff
 * 返回不同算术三元组的数目。
 *
 * 示例 1：
 * 输入：nums = [0,1,4,6,7,10], diff = 3
 * 输出：2
 * 解释：
 * (1, 2, 4) 是算术三元组：7 - 4 == 3 且 4 - 1 == 3 。
 * (2, 4, 5) 是算术三元组：10 - 7 == 3 且 7 - 4 == 3 。
 *
 * 示例 2：
 * 输入：nums = [4,5,6,7,8,9], diff = 2
 * 输出：2
 * 解释：
 * (0, 2, 4) 是算术三元组：8 - 6 == 2 且 6 - 4 == 2 。
 * (1, 3, 5) 是算术三元组：9 - 7 == 2 且 7 - 5 == 2 。
 *
 * 提示：
 * 3 <= nums.length <= 200
 * 0 <= nums[i] <= 200
 * 1 <= diff <= 50
 * nums严格递增
 */
public class ArithmeticTriplets {

    public int arithmeticTriplets(int[] nums, int diff) {
        int max = 201;
        boolean[] marks = new boolean[max];
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            marks[nums[i]] = true;
        }
        int count = 0;
        for (int i = 0; i < length - 2; i++) {
            int cur = nums[i];
            int next = cur + diff;
            if (next >= max) {
                break;
            }
            if (marks[next]) {
                int three = next + diff;
                if (three >= max) {
                    break;
                }
                if (marks[three]) {
                    count++;
                }
            }
        }
        return count;
    }
}
