package com.demo.algorithm.leetcode.hard;

/**
 * create on 12/8/21
 * @author chenglong
 * description : 三个无重叠子数组的最大和
 *
 * 给你一个整数数组 nums 和一个整数 k ，找出三个长度为 k 、互不重叠、且 3 * k 项的和最大的子数组，并返回这三个子数组。
 * 以下标的数组形式返回结果，数组中的每一项分别指示每个子数组的起始位置（下标从 0 开始）。如果有多个结果，返回字典序最小的一个。
 *
 * 示例 1：
 * 输入：nums = [1,2,1,2,6,7,5,1], k = 2
 * 输出：[0,3,5]
 * 解释：子数组 [1, 2], [2, 6], [7, 5] 对应的起始下标为 [0, 3, 5]。
 * 也可以取 [2, 1], 但是结果 [1, 3, 5] 在字典序上更大。
 *
 * 示例 2：
 * 输入：nums = [1,2,1,2,1,2,1,2,1], k = 2
 * 输出：[0,2,4]
 *  
 * 提示：
 * 1 <= nums.length <= 2 * 10^4
 * 1 <= nums[i] < 216
 * 1 <= k <= floor(nums.length / 3)
 */
public class MaxThreeInterval {

    //使用暴力解法
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int length = nums.length;
        if (length < 3 * k) {
            return null;
        }
        //1，对区间进行求和
        length -= (k - 1);
        int[] sums = new int[length];
        int pre = 0;
        for (int i = 0; i < k; i++) {
            pre += nums[i];
        }
        sums[0] = pre;
        for (int i = 1; i < length; i++) {
            pre = pre - nums[i - 1] + nums[k - 1 + i];
            sums[i] = pre;
        }
        //2，从sums中找到三个值的和最大并且保证间距为k
        int[] result = new int[3];
        result[1] = k;
        result[2] = 2 * k;
        int max = sums[0] + sums[k] + sums[2 * k];
        for (int i = 0; i < length - 2 * k; i++) {
            for (int j = i + k; j < length - k; j++) {
                for (int l = j + k; l < length; l++) {
                    int tem = sums[i] + sums[j] + sums[l];
                    if (tem > max) {
                        max = tem;
                        result[0] = i;
                        result[1] = j;
                        result[2] = l;
                    }
                }
            }
        }
        return result;
    }

    //使用动态规划
    public int[] maxSumOfThreeSubarrays2(int[] nums, int k) {
        int length = nums.length;
        if (length < 3 * k) {
            return null;
        }
        //1，对区间进行求和
        length -= (k - 1);
        int[] sums = new int[length];
        int pre = 0;
        for (int i = 0; i < k; i++) {
            pre += nums[i];
        }
        sums[0] = pre;
        for (int i = 1; i < length; i++) {
            pre = pre - nums[i - 1] + nums[k - 1 + i];
            sums[i] = pre;
        }
        /**
         * 2，问题转换为在sums中求三个数的最大和并且三个数的间隔至少为k.
         * 定义状态数组:marks[i][j]
         * i：当前选取的个数
         * j:最后选取数字的位置
         * marks[i][j]: 在j位置选取i个数字的最大值:两种获取方式:当前位置的值+上一次j-k位置的最大值;不取时前一个位置的最大值
         * 状态转移方程: marks[i][j] = Math.max(marks[i][j-1],marks[i-1][j-k]+sums[j])
         */
        //count:求和数字的数量,这里为3
        int count = 3;
        int[][] marks = new int[count + 1][length];
        //初始化marks的值
        marks[1][0] = sums[0];
        for (int i = 1; i < length - (count - 1) * k; i++) {
            marks[1][i] = Math.max(marks[1][i - 1], sums[i]);
        }
        for (int i = 2; i <= count; i++) {
            for (int j = (i - 1) * k; j < length - (count - i) * k; j++) {
                marks[i][j] = Math.max(marks[i][j - 1], marks[i - 1][j - k] + sums[j]);
            }
        }
        //最大值 = marks[count][length-1]
        int[] result = new int[count];
        int end = length - 1;
        int index = end;
        for (int i = count; i >= 1; i--) {
            for (int j = end - 1; j >= 0; j--) {
                if (marks[i][end] == marks[i][j]) {
                    index = j;
                } else {
                    result[i - 1] = index;
                    index -= k;
                    end = index;
                    break;
                }
            }
        }
        return result;
    }

    //使用滑动窗口的方式
    public int[] maxSumOfThreeSubarrays3(int[] nums, int k) {
        int length = nums.length;
        if (length < 3 * k) {
            return null;
        }
        /**
         * sum1：区间1的总和
         * maxSum1：区间1的最大值
         * sum2：区间2的总和
         * maxSum2：区间1,2的最大和
         * sum3：区间3的总和
         * sumTotal：三个区间的最大和
         * maxSum1Index：区间1最大值时的起始位置
         * maxSum2Index1：区间1,2和最大时区间1的起始位置
         * maxSum2Index2：区间1,2和最大时区间2的起始位置
         */
        int sum1 = 0;
        int maxSum1 = 0;
        int sum2 = 0;
        int maxSum2 = 0;
        int sum3 = 0;
        int sumTotal = 0;
        int maxSum1Index = 0;
        int maxSum2Index1 = 0;
        int maxSum2Index2 = k;
        int[] result = new int[3];
        //1，初始化result,sum
        result[1] = k;
        result[2] = 2 * k;
        for (int i = 0; i < k; i++) {
            sum1 += nums[i];
            sum2 += nums[i + k];
            sum3 += nums[i + 2 * k];
        }
        maxSum1 = sum1;
        maxSum2 = sum1 + sum2;
        sumTotal = maxSum2 + sum3;
        //2,窗口开始滑动
        for (int i = 3 * k; i < length; i++) {
            //1，滑动窗口更新区间值
            sum1 += nums[i - 2 * k];
            sum1 -= nums[i - 3 * k];
            sum2 += nums[i - k];
            sum2 -= nums[i - 2 * k];
            sum3 += nums[i];
            sum3 -= nums[i - k];
            //2，进行对比替换
            if (sum1 > maxSum1) {
                maxSum1 = sum1;
                maxSum1Index = i - 3 * k + 1;
            }
            if (maxSum1 + sum2 > maxSum2) {
                maxSum2 = maxSum1 + sum2;
                maxSum2Index1 = maxSum1Index;
                maxSum2Index2 = i - 2 * k + 1;
            }
            if (maxSum2 + sum3 > sumTotal) {
                sumTotal = maxSum2 + sum3;
                result[0] = maxSum2Index1;
                result[1] = maxSum2Index2;
                result[2] = i - k + 1;
            }
        }
        return result;
    }
}
