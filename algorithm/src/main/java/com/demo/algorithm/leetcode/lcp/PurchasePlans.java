package com.demo.algorithm.leetcode.lcp;

import java.util.Arrays;

/**
 * Created by chl on 2022/5/5.
 * description : LCP28. 采购方案
 *
 * 小力将N个零件的报价存于数组nums。小力预算为target，假定小力仅购买两个零件，要求购买零件的花费不超过预算，请问他有多少种采购方案。
 *
 * 注意：答案需要以 1e9 + 7 (1000000007) 为底取模，如：计算初始结果为：1000000008，请返回 1
 *
 * 示例 1：
 * 输入：nums = [2,5,3,5], target = 6
 * 输出：1
 * 解释：预算内仅能购买 nums[0] 与 nums[2]。
 *
 * 示例 2：
 * 输入：nums = [2,2,1,9], target = 10
 * 输出：4
 * 解释：符合预算的采购方案如下：
 * nums[0] + nums[1] = 4
 * nums[0] + nums[2] = 3
 * nums[1] + nums[2] = 3
 * nums[2] + nums[3] = 10
 *
 * 提示：
 * 2 <= nums.length <= 10^5
 * 1 <= nums[i], target <= 10^5
 */
public class PurchasePlans {

    private static final int MOD = 1000000007;

    public int purchasePlans(int[] nums, int target) {
        //1，排序
        Arrays.sort(nums);
        int sum = 0;
        //2，遍历第一个工件，然后搜索满足添加的第二个
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            int last = target - nums[i];
            if (last < nums[i]) {
                continue;
            }
            if (nums[length - 1] <= last) {
                int count = length - 1 - i;
                sum += count;
                sum %= MOD;
                continue;
            }
            int start = i + 1;
            int end = length - 1;
            while (start < end) {
                int middle = (end - start) / 2 + start;
                if (nums[middle] <= last) {
                    start = middle + 1;
                } else {
                    end = middle;
                }
            }
            int count = start - 1 - i;
            sum += count;
            sum %= MOD;
        }
        return sum;
    }
}
