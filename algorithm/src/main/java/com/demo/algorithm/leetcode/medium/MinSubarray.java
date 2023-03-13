package com.demo.algorithm.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * create on 2023/3/10
 * @author chenglong
 * description : 使数组和能被P整除
 *
 * 给你一个正整数数组nums，请你移除最短子数组（可以为空），使得剩余元素的和能被p整除。不允许将整个数组都移除。
 * 请你返回你需要移除的最短子数组的长度，如果无法满足题目要求，返回 -1。
 * 子数组定义为原数组中连续的一组元素。
 *
 * 示例 1：
 * 输入：nums = [3,1,4,2], p = 6
 * 输出：1
 * 解释：nums 中元素和为 10，不能被 p 整除。我们可以移除子数组 [4] ，剩余元素的和为 6 。
 *
 * 示例 2：
 * 输入：nums = [6,3,5,2], p = 9
 * 输出：2
 * 解释：我们无法移除任何一个元素使得和被9整除，最优方案是移除子数组 [5,2] ，剩余元素为 [6,3]，和为 9 。
 *
 * 示例3：
 * 输入：nums = [1,2,3], p = 3
 * 输出：0
 * 解释：和恰好为6，已经能被3整除了。所以我们不需要移除任何元素。
 *
 * 示例 4：
 * 输入：nums = [1,2,3], p = 7
 * 输出：-1
 * 解释：没有任何方案使得移除子数组后剩余元素的和被7整除。
 *
 * 示例 5：
 * 输入：nums = [1000000000,1000000000,1000000000], p = 3
 * 输出：0
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= p <= 10^9
 */
public class MinSubarray {

    public int minSubarray(int[] nums, int p) {
        int length = nums.length;
        if (length == 1) {
            if (nums[0] % p == 0) {
                return 0;
            }
            return -1;
        }
        //1，统计前缀和
        nums[0] %= p;
        for (int i = 1; i < length; i++) {
            nums[i] = (nums[i] + nums[i - 1]) % p;
        }
        //2，移除数字之和为:
        int target = nums[length - 1];
        if (target == 0) {
            return 0;
        }
        int minCount = length;
        Map<Integer, Integer> marks = new HashMap<>();
        marks.put(0, -1);
        for (int i = 0; i < length; i++) {
            //当前[0,i]的前缀和
            int cur = nums[i];
            int find = cur >= target ? cur - target : (cur + p - target);
            if (marks.containsKey(find)) {
                int count = i - marks.get(find);
                if (count < minCount) {
                    minCount = count;
                }
            }
            marks.put(cur, i);
        }
        return minCount == length ? -1 : minCount;
    }
}
