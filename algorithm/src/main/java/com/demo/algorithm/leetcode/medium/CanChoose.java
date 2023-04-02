package com.demo.algorithm.leetcode.medium;

/**
 * create on 2022/12/19
 * @author chenglong
 * description : 通过连接另一个数组的子数组得到一个数组
 *
 * 给你一个长度为n的二维整数数组groups，同时给你一个整数数组nums。
 * 你是否可以从nums中选出n个不相交的子数组，使得第i个子数组与groups[i]（下标从0开始）完全相同，且如果i>0，那么第(i-1)个子数组在nums中出现的位置在第i个子数组前面。
 * （也就是说，这些子数组在nums中出现的顺序需要与groups顺序相同）
 * 如果你可以找出这样的n个子数组，请你返回true，否则返回false。
 * 如果不存在下标为k的元素nums[k]属于不止一个子数组，就称这些子数组是不相交的。子数组指的是原数组中连续元素组成的一个序列。
 *
 * 示例 1：
 * 输入：groups = [[1,-1,-1],[3,-2,0]], nums = [1,-1,0,1,-1,-1,3,-2,0]
 * 输出：true
 * 解释：你可以分别在 nums 中选出第 0 个子数组 [1,-1,0,1,-1,-1,3,-2,0] 和第 1 个子数组 [1,-1,0,1,-1,-1,3,-2,0] 。
 * 这两个子数组是不相交的，因为它们没有任何共同的元素。
 *
 * 示例 2：
 * 输入：groups = [[10,-2],[1,2,3,4]], nums = [1,2,3,4,10,-2]
 * 输出：false
 * 解释：选择子数组 [1,2,3,4,10,-2] 和 [1,2,3,4,10,-2] 是不正确的，因为它们出现的顺序与 groups 中顺序不同。
 * [10,-2] 必须出现在 [1,2,3,4] 之前。
 *
 * 示例 3：
 * 输入：groups = [[1,2,3],[3,4]], nums = [7,7,1,2,3,4,7,7]
 * 输出：false
 * 解释：选择子数组 [7,7,1,2,3,4,7,7] 和 [7,7,1,2,3,4,7,7] 是不正确的，因为它们不是不相交子数组。
 * 它们有一个共同的元素 nums[4] （下标从 0 开始）。
 *
 * 提示：
 * groups.length == n
 * 1 <= n <= 10^3
 * 1 <= groups[i].length, sum(groups[i].length) <= 10^3
 * 1 <= nums.length <= 10^3
 * -10^7 <= groups[i][j], nums[k] <= 10^7
 */
public class CanChoose {

    public boolean canChoose(int[][] groups, int[] nums) {
        int index = 0;
        int i = 0;
        int length = nums.length;
        while (i < length) {
            if (nums[i] == groups[index][0]) {
                int lastIndex = find(i, nums, groups[index]);
                if (lastIndex == -2) {
                    return false;
                }
                if (lastIndex == -1) {
                    i++;
                } else {
                    i = lastIndex;
                    index++;
                    if (index == groups.length) {
                        return true;
                    }
                }
            } else {
                i++;
            }
        }
        return false;
    }

    private int find(int start, int[] nums, int[] group) {
        if (start + group.length > nums.length) {
            return -2;
        }
        for (int i = 0; i < group.length; i++) {
            if (group[i] != nums[i + start]) {
                return -1;
            }
        }
        return start + group.length;
    }
}
