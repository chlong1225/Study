package com.demo.algorithm.leetcode.easy;

import java.util.Arrays;

/**
 * create on 2023/6/13
 * @author chenglong
 * description : 数组中不等三元组的数目
 *
 * 给你一个下标从0开始的正整数数组nums。请你找出并统计满足下述条件的三元组(i,j,k)的数目：
 * 0 <= i < j < k < nums.length
 * nums[i]、nums[j] 和 nums[k] 两两不同 。
 * 换句话说：nums[i] != nums[j]、nums[i] != nums[k] 且 nums[j] != nums[k] 。
 * 返回满足上述条件三元组的数目。
 *
 * 示例 1：
 * 输入：nums = [4,4,2,4,3]
 * 输出：3
 * 解释：下面列出的三元组均满足题目条件：
 * - (0, 2, 4) 因为 4 != 2 != 3
 * - (1, 2, 4) 因为 4 != 2 != 3
 * - (2, 3, 4) 因为 2 != 4 != 3
 * 共计 3 个三元组，返回 3 。
 * 注意 (2, 0, 4) 不是有效的三元组，因为 2 > 0 。
 *
 * 示例 2：
 * 输入：nums = [1,1,1,1,1]
 * 输出：0
 * 解释：不存在满足条件的三元组，所以返回 0 。
 *
 * 提示：
 * 3 <= nums.length <= 100
 * 1 <= nums[i] <= 1000
 */
public class UnequalTriplets {

    public int unequalTriplets(int[] nums) {
        int count = 0;
        int n = nums.length;
        for (int i = 0; i < n-2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] != nums[j] && nums[i] != nums[k] && nums[j] != nums[k]) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public int unequalTriplets2(int[] nums) {
        int count = 0;
        //1，排序
        Arrays.sort(nums);
        int n = nums.length;
        //查找第一个不同数字最后的index
        int pre = 0;
        for (int i = 1; i < n; i++) {
            if (nums[pre] != nums[i]) {
                break;
            } else {
                pre = i;
            }
        }
        int cur = pre + 1;
        while (cur < n - 1) {
            int tem = 1;
            for (int i = cur + 1; i < n; i++) {
                if (nums[i] == nums[cur]) {
                    tem++;
                    cur = i;
                } else {
                    count += (pre + 1) * tem * (n - tem - pre - 1);
                    pre = cur;
                    cur = i;
                    break;
                }
            }
        }
        return count;
    }
}
