package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/12/25
 * @author chenglong
 * description : 长度最小的子数组
 *
 * 给定一个含有n个正整数的数组和一个正整数target。
 * 找出该数组中满足其总和大于等于target的长度最小的连续子数组[numsl, numsl+1, ..., numsr-1, numsr]，并返回其长度。如果不存在符合条件的子数组，返回0。
 *
 * 示例 1：
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 *
 * 示例 2：
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 *
 * 示例 3：
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 *
 * 提示：
 * 1 <= target <= 10^9
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 */
public class MinSubArrayLen {

    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        //1，获取数组的前缀和。i代表数字的个数
        int[] sums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        //2，sums为递增数组，先处理特殊场景
        if (sums[n] < target) {
            return 0;
        }
        if (sums[n] == target) {
            return n;
        }
        if (sums[1] >= target) {
            return 1;
        }
        //3，使用二分查找刚好满足条件的位置。sums[j]>=target 时的j
        int left = 2;
        int right = n;
        while (left < right) {
            int middle = (left + right) / 2;
            if (sums[middle] == target) {
                left = middle;
                break;
            } else if (sums[middle] < target) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        //此时满足添加的子数组从0～left-1。总计left个
        int min = left;
        //4，使用滑动窗口查找最小的数量
        int startIndex = 0;
        for (int i = left; i <= n; i++) {
            //当前和为：sums[i]，需要进行减少
            int delete = sums[i] - target;
            for (int j = startIndex + 1; j <= n; j++) {
                if (sums[j] > delete) {
                    break;
                } else {
                    startIndex = j;
                }
            }
            int tem = i - startIndex;
            if (tem < min) {
                min = tem;
            }
        }
        return min;
    }
}
