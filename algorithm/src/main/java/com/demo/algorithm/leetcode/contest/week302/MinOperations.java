package com.demo.algorithm.leetcode.contest.week302;

import java.util.Arrays;

/**
 * create on 2022/7/23
 * @author chenglong
 * description : 使数组可以被整除的最少删除次数
 *
 * 给你两个正整数数组nums和numsDivide。你可以从nums中删除任意数目的元素。
 * 请你返回使nums中最小元素可以整除numsDivide中所有元素的最少删除次数。如果无法得到这样的元素，返回-1。
 * 如果y % x == 0，那么我们说整数x整除y。
 *
 * 示例 1：
 * 输入：nums = [2,3,2,4,3], numsDivide = [9,6,9,3,15]
 * 输出：2
 * 解释：
 * [2,3,2,4,3] 中最小元素是 2 ，它无法整除 numsDivide 中所有元素。
 * 我们从 nums 中删除 2 个大小为 2 的元素，得到 nums = [3,4,3] 。
 * [3,4,3] 中最小元素为 3 ，它可以整除 numsDivide 中所有元素。
 * 可以证明2是最少删除次数。
 *
 * 示例 2：
 * 输入：nums = [4,3,6], numsDivide = [8,2,6,10]
 * 输出：-1
 * 解释：
 * 我们想 nums 中的最小元素可以整除 numsDivide 中的所有元素。
 * 没有任何办法可以达到这一目的。
 *
 * 提示：
 * 1 <= nums.length, numsDivide.length <= 10^5
 * 1 <= nums[i], numsDivide[i] <= 10^9
 */
public class MinOperations {

    public int minOperations(int[] nums, int[] numsDivide) {
        //1，对数据源进行排序
        Arrays.sort(nums);
        Arrays.sort(numsDivide);
        //2，依次遍历判断
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            if (divideNum(nums[i], numsDivide)) {
                return i;
            }
        }
        return -1;
    }

    private boolean divideNum(int num, int[] numsDivide) {
        int length = numsDivide.length;
        for (int i = 0; i < length; i++) {
            if (numsDivide[i] % num != 0) {
                return false;
            }
        }
        return true;
    }

    public int minOperations2(int[] nums, int[] numsDivide) {
        //1，获取numsDivide的最大公约数
        int target = getMaxCommonDivisor(numsDivide);
        //2，对nums进行排序
        Arrays.sort(nums);
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            if (target % nums[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    private int getMaxCommonDivisor(int[] numsDivide) {
        int length = numsDivide.length;
        int result = numsDivide[0];
        for (int i = 1; i < length; i++) {
            result = gcd(result, numsDivide[i]);
        }
        return result;
    }

    private int gcd(int a, int b) {
        return b != 0 ? gcd(b, a % b) : a;
    }
}
