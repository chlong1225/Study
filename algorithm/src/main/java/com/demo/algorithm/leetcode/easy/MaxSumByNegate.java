package com.demo.algorithm.leetcode.easy;

/**
 * create on 12/3/21
 * @author chenglong
 * description : K次取反后最大化的数组和
 *
 * 给你一个整数数组nums和一个整数k ，按以下方法修改该数组：
 * 选择某个下标 i 并将 nums[i] 替换为 -nums[i] 。
 * 重复这个过程恰好 k 次。可以多次选择同一个下标 i 。
 * 以这种方式修改数组后，返回数组 可能的最大和 。
 *
 * 示例 1：
 * 输入：nums = [4,2,3], k = 1
 * 输出：5
 * 解释：选择下标 1 ，nums 变为 [4,-2,3] 。
 *
 * 示例 2：
 * 输入：nums = [3,-1,0,2], k = 3
 * 输出：6
 * 解释：选择下标 (1, 2, 2) ，nums 变为 [3,1,0,2] 。
 *
 * 示例 3：
 * 输入：nums = [2,-3,-1,5,-4], k = 2
 * 输出：13
 * 解释：选择下标 (1, 4) ，nums 变为 [2,3,-1,5,4] 。
 *  
 * 提示：
 * 1 <= nums.length <= 104
 * -100 <= nums[i] <= 100
 * 1 <= k <= 104
 */
public class MaxSumByNegate {

    /**
     * 分析：优先负数取反，越小的负数取反后越大，需要优先
     * 没有负数后，取反绝对值最小的数
     */
    public int largestSumAfterKNegations(int[] nums, int k) {
        int length = nums.length;
        //1，统计负数的个数，最小的绝对值，同时求和，负数求和，最大的负数(用于后序负数排序时确定数组的大小)
        int sum = 0;
        int count = 0;
        int min = Integer.MAX_VALUE;
        int negateSum = 0;
        int negateMin = 0;
        for (int i = 0; i < length; i++) {
            sum += nums[i];
            if (nums[i] < 0) {
                count++;
                negateSum += nums[i];
                if (nums[i] < negateMin) {
                    negateMin = nums[i];
                }
            }
            if (Math.abs(nums[i]) < min) {
                min = Math.abs(nums[i]);
            }
        }
        //2，判断取反的场景
        if (count <= k) {
            //此时所有的负数都取反，剩余的取反次数操作最小绝对值
            // 3.1，将所有的负数取反后新的总和
            sum = sum - 2 * negateSum;
            k -= count;
            //3.2，计算剩余取反的次数，对最小绝对值进行操作。每两次取反会重置，相当与不操作。
            k %= 2;
            if (k == 1) {
                sum -= (2 * min);
            }
        } else {
            //此时负数多于取反的次数，需要对负数进行排序，优先取反最小的负数。
            //4.1，对负数使用桶排序
            int[] negates = new int[1 - negateMin];
            for (int i = 0; i < length; i++) {
                if (nums[i] < 0) {
                    negates[-nums[i]]++;
                }
            }
            //4.2，统计取反的总和，倒着遍历先统计取反结果大的值
            int replaceSum = 0;
            for (int i = -negateMin; i >= 0; i--) {
                if (negates[i] > 0) {
                    if (negates[i] < k) {
                        replaceSum += (i * negates[i]);
                        k -= negates[i];
                    } else {
                        replaceSum += (i * k);
                        break;
                    }
                }
            }
            sum = sum + 2 * replaceSum;
        }
        return sum;
    }

}
