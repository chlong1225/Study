package com.demo.algorithm.leetcode.hard;

/**
 * create on 2024/1/21
 * @author chenglong
 * description : 分割数组的最大值
 *
 * 给定一个非负整数数组nums和一个整数k，你需要将这个数组分成k个非空的连续子数组。
 * 设计一个算法使得这k个子数组各自和的最大值最小。
 *
 * 示例 1：
 * 输入：nums = [7,2,5,10,8], k = 2
 * 输出：18
 * 解释：
 * 一共有四种方法将 nums 分割为 2 个子数组。
 * 其中最好的方式是将其分为 [7,2,5] 和 [10,8] 。
 * 因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
 *
 * 示例 2：
 * 输入：nums = [1,2,3,4,5], k = 2
 * 输出：9
 *
 * 示例 3：
 * 输入：nums = [1,4,4], k = 3
 * 输出：4
 *
 * 提示：
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 10^6
 * 1 <= k <= min(50, nums.length)
 */
public class SplitArray {

    public int splitArray(int[] nums, int k) {
        int n = nums.length;
        //1，获取数组的最大值
        int maxNum = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] > maxNum) {
                maxNum = nums[i];
            }
        }
        //2，处理特殊场景
        if (n == k) {
            //此时拆分的子数组都只有一个元素
            return maxNum;
        }
        //3，对数组求和
        int sum = nums[0];
        for (int i = 1; i < n; i++) {
            sum += nums[i];
        }
        int min = Math.max(maxNum, sum / k);
        int max = sum;
        while (min < max) {
            int middle = (max + min) / 2;
            if (splitSums(middle, nums, k)) {
                max = middle;
            } else {
                min = middle + 1;
            }
        }
        return min;
    }

    private boolean splitSums(int target, int[] nums, int k) {
        int count = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            int tem = sum + nums[i];
            if (tem > target) {
                count++;
                sum = nums[i];
            } else {
                sum = tem;
            }
        }
        count++;
        return count <= k;
    }
}
