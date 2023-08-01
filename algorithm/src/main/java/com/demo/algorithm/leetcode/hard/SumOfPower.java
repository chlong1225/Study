package com.demo.algorithm.leetcode.hard;

import java.util.Arrays;

/**
 * create on 2023/8/1
 * @author chenglong
 * description : 英雄的力量
 *
 * 给你一个下标从0开始的整数数组nums，它表示英雄的能力值。如果我们选出一部分英雄，这组英雄的力量定义为：
 * i0，i1，... ik表示这组英雄在数组中的下标。那么这组英雄的力量为max(nums[i0],nums[i1] ... nums[ik])2 * min(nums[i0],nums[i1] ... nums[ik]) 。
 * 请你返回所有可能的非空英雄组的力量之和。由于答案可能非常大，请你将结果对10^9+7取余。
 *
 * 示例 1：
 * 输入：nums = [2,1,4]
 * 输出：141
 * 解释：
 * 第1组：[2] 的力量为 2^2 * 2 = 8 。
 * 第2组：[1] 的力量为 1^2 * 1 = 1 。
 * 第3组：[4] 的力量为 4^2 * 4 = 64 。
 * 第4组：[2,1] 的力量为 2^2 * 1 = 4 。
 * 第5组：[2,4] 的力量为 4^2 * 2 = 32 。
 * 第6组：[1,4] 的力量为 4^2 * 1 = 16 。
 * 第7组：[2,1,4] 的力量为4^2 * 1 = 16 。
 * 所有英雄组的力量之和为 8 + 1 + 64 + 4 + 32 + 16 + 16 = 141 。
 *
 * 示例 2：
 * 输入：nums = [1,1,1]
 * 输出：7
 * 解释：总共有 7 个英雄组，每一组的力量都是 1 。所以所有英雄组的力量之和为 7 。
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 */
public class SumOfPower {

    private static final int MOD = 1000000007;

    public int sumOfPower(int[] nums) {
        //1，排序
        Arrays.sort(nums);
        /**
         * 分析：1，2，4，5，6
         * 1结尾：1^2 * 1
         * 2结尾：2^2 *（2 + 1）
         * 4结尾：4^2 * （4 + 2 + 1*2）
         * 5结尾：5^2 * （5 + 4 + （2 + 1*2）*2 ）= cur + s
         * 6结尾：6^2 * （6 + 5 + （4 + 2*（2 + 1*2））*2）= cur + preCur + 2*preS ;
         */
        long sum = 0;
        long preSum = 0;
        long base = 1;
        for (int i = 0; i < nums.length; i++) {
            int max = nums[i];
            long cur = (base * max * max % MOD) * ((max + preSum) % MOD) % MOD;
            sum = (sum + cur) % MOD;
            preSum = (2 * preSum + max) % MOD;
        }
        return (int) sum;
    }
}
