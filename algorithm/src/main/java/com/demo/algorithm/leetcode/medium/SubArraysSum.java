package com.demo.algorithm.leetcode.medium;

/**
 * create on 2022/3/4
 * @author chenglong
 * description : 子数组范围和
 *
 * 给你一个整数数组nums 。nums中，子数组的范围是子数组中最大元素和最小元素的差值。
 * 返回nums中所有子数组范围的和 。
 * 子数组是数组中一个连续非空的元素序列。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：4
 * 解释：nums 的 6 个子数组如下所示：
 * [1]，范围 = 最大 - 最小 = 1 - 1 = 0
 * [2]，范围 = 2 - 2 = 0
 * [3]，范围 = 3 - 3 = 0
 * [1,2]，范围 = 2 - 1 = 1
 * [2,3]，范围 = 3 - 2 = 1
 * [1,2,3]，范围 = 3 - 1 = 2
 * 所有范围的和是 0 + 0 + 0 + 1 + 1 + 2 = 4
 *
 * 示例 2：
 * 输入：nums = [1,3,3]
 * 输出：4
 * 解释：nums 的 6 个子数组如下所示：
 * [1]，范围 = 最大 - 最小 = 1 - 1 = 0
 * [3]，范围 = 3 - 3 = 0
 * [3]，范围 = 3 - 3 = 0
 * [1,3]，范围 = 3 - 1 = 2
 * [3,3]，范围 = 3 - 3 = 0
 * [1,3,3]，范围 = 3 - 1 = 2
 * 所有范围的和是 0 + 0 + 0 + 2 + 0 + 2 = 4
 *
 * 示例 3：
 * 输入：nums = [4,-2,-3,4,1]
 * 输出：59
 * 解释：nums 中所有子数组范围的和是 59
 *
 * 提示：
 * 1 <= nums.length <= 1000
 * -10^9 <= nums[i] <= 10^9
 */
public class SubArraysSum {

    public long subArrayRanges(int[] nums) {
        /**
         * 数据量10^3比较少，可以直接使用双层循环的暴力解法
         */
        int length = nums.length;
        if (length == 1) {
            return 0;
        }
        long sum = 0;
        for (int i = 0; i < length - 1; i++) {
            //统计从i～j子集范围的最大最小值
            int min = nums[i];
            int max = nums[i];
            for (int j = i + 1; j < length; j++) {
                if (nums[j] > max) {
                    max = nums[j];
                } else if (nums[j] < min) {
                    min = nums[j];
                }
                sum += (max - min);
            }
        }
        return sum;
    }

    public long subArrayRanges2(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return 0;
        }
        /**
         * 分析：区间最大差值 = 区间最大值 - 区间最小值。所有的区间最大差值和可以转换为所有区间最大值和 - 所有区间最小值和
         * 区间对应最大值，最大值也会一一对应区间。转成当前值最大区间数*当前值 = 当前值对应的所有区间最大值。最小值同理
         * 考虑存在相等的数据，这里约定右边的大于左边
         */
        long sum = 0;
        for (int i = 0; i < length; i++) {
            int cur = nums[i];

            //向两边查找当前值为最大值的边界
            int left = i;
            int right = i;
            for (int j = left - 1; j >= 0; j--) {
                if (nums[j] < cur) {
                    left--;
                } else {
                    break;
                }
            }
            for (int j = right + 1; j < length; j++) {
                if (nums[j] <= cur) {
                    right++;
                } else {
                    break;
                }
            }
            //最大值和：子集数量：(right-i+1)*(i-left+1)-1。但最小的子集中同样存在-1,，故都可以省略
            sum += ((long) cur * (i - left + 1) * (right - i + 1));

            //向两边查找当前值为最小值的边界
            left = i;
            right = i;
            for (int j = left - 1; j >= 0; j--) {
                if (nums[j] > cur) {
                    left--;
                } else {
                    break;
                }
            }
            for (int j = right + 1; j < length; j++) {
                if (nums[j] >= cur) {
                    right++;
                } else {
                    break;
                }
            }
            //最小值和
            sum -= ((long) cur * (i - left + 1) * (right - i + 1));
        }
        return sum;
    }
}
