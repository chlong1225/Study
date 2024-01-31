package com.demo.algorithm.leetcode.easy;

/**
 * create on 2024/1/31
 * @author chenglong
 * description : 找出不同元素数目差数组
 *
 * 给你一个下标从0开始的数组nums，数组长度为n。
 * nums的不同元素数目差数组可以用一个长度为n的数组diff表示，其中diff[i]等于前缀nums[0, ..., i]中不同元素的数目减去后缀nums[i + 1, ..., n - 1]中不同元素的数目。
 * 返回nums的不同元素数目差数组。
 * 注意nums[i, ..., j]表示nums的一个从下标i开始到下标j结束的子数组（包含下标i和j对应元素）。特别需要说明的是，如果i > j，则 nums[i, ..., j] 表示一个空子数组。
 *
 * 示例 1：
 * 输入：nums = [1,2,3,4,5]
 * 输出：[-3,-1,1,3,5]
 * 解释：
 * 对于 i = 0，前缀中有 1 个不同的元素，而在后缀中有 4 个不同的元素。因此，diff[0] = 1 - 4 = -3 。
 * 对于 i = 1，前缀中有 2 个不同的元素，而在后缀中有 3 个不同的元素。因此，diff[1] = 2 - 3 = -1 。
 * 对于 i = 2，前缀中有 3 个不同的元素，而在后缀中有 2 个不同的元素。因此，diff[2] = 3 - 2 = 1 。
 * 对于 i = 3，前缀中有 4 个不同的元素，而在后缀中有 1 个不同的元素。因此，diff[3] = 4 - 1 = 3 。
 * 对于 i = 4，前缀中有 5 个不同的元素，而在后缀中有 0 个不同的元素。因此，diff[4] = 5 - 0 = 5 。
 *
 * 示例 2：
 * 输入：nums = [3,2,3,4,2]
 * 输出：[-2,-1,0,2,3]
 * 解释：
 * 对于 i = 0，前缀中有 1 个不同的元素，而在后缀中有 3 个不同的元素。因此，diff[0] = 1 - 3 = -2 。
 * 对于 i = 1，前缀中有 2 个不同的元素，而在后缀中有 3 个不同的元素。因此，diff[1] = 2 - 3 = -1 。
 * 对于 i = 2，前缀中有 2 个不同的元素，而在后缀中有 2 个不同的元素。因此，diff[2] = 2 - 2 = 0 。
 * 对于 i = 3，前缀中有 3 个不同的元素，而在后缀中有 1 个不同的元素。因此，diff[3] = 3 - 1 = 2 。
 * 对于 i = 4，前缀中有 3 个不同的元素，而在后缀中有 0 个不同的元素。因此，diff[4] = 3 - 0 = 3 。
 *
 * 提示：
 * 1 <= n == nums.length <= 50
 * 1 <= nums[i] <= 50
 */
public class DistinctDifferenceArray {

    public int[] distinctDifferenceArray(int[] nums) {
        boolean[] preMarks = new boolean[51];
        int count = 0;
        int n = nums.length;
        int[] left = new int[n];
        for (int i = 0; i < n; i++) {
            if (!preMarks[nums[i]]) {
                count++;
                preMarks[nums[i]] = true;
            }
            left[i] = count;
        }
        boolean[] lastMarks = new boolean[n];
        count = 0;
        int[] right = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            if (!lastMarks[nums[i]]) {
                count++;
                lastMarks[nums[i]] = true;
            }
            right[i] = count;
        }
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == n - 1) {
                answer[i] = left[i];
            } else {
                answer[i] = left[i] - right[i + 1];
            }
        }
        return answer;
    }
}
