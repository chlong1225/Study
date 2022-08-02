package com.demo.algorithm.leetcode.contest.doubleweek83;

/**
 * create on 2022/8/2
 * @author chenglong
 * description : 全0子数组的数目
 *
 * 给你一个整数数组nums，返回全部为0的子数组数目。
 * 子数组是一个数组中一段连续非空元素组成的序列。
 *
 * 示例 1：
 * 输入：nums = [1,3,0,0,2,0,0,4]
 * 输出：6
 * 解释：
 * 子数组 [0] 出现了 4 次。
 * 子数组 [0,0] 出现了 2 次。
 * 不存在长度大于 2 的全 0 子数组，所以我们返回 6 。
 *
 * 示例 2：
 * 输入：nums = [0,0,0,2,0,0]
 * 输出：9
 * 解释：
 * 子数组 [0] 出现了 5 次。
 * 子数组 [0,0] 出现了 3 次。
 * 子数组 [0,0,0] 出现了 1 次。
 * 不存在长度大于 3 的全 0 子数组，所以我们返回 9 。
 *
 * 示例 3：
 * 输入：nums = [2,10,2019]
 * 输出：0
 * 解释：没有全 0 子数组，所以我们返回 0 。
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * -109 <= nums[i] <= 10^9
 */
public class ZeroFilledSubarray {

    public long zeroFilledSubarray(int[] nums) {
        long sum = 0;
        long base = 1;
        int count = 0;
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (nums[i] == 0) {
                count++;
            } else {
                //计算0的子数组
                sum += (base * count * (count + 1) / 2);
                count = 0;
            }
        }
        sum += (base * count * (count + 1) / 2);
        return sum;
    }
}
