package com.demo.algorithm.leetcode.contest.doubleweek90;

import java.util.HashMap;
import java.util.Map;

/**
 * create on 2022/10/31
 * @author chenglong
 * description : 摧毁一系列目标
 *
 * 给你一个下标从0开始的数组nums，它包含若干正整数，表示数轴上你需要摧毁的目标所在的位置。同时给你一个整数space。
 * 你有一台机器可以摧毁目标。给机器输入nums[i]，这台机器会摧毁所有位置在nums[i] + c * space的目标，其中c是任意非负整数。你想摧毁nums中尽可能多的目标。
 * 请你返回在摧毁数目最多的前提下，nums[i]的最小值。
 *
 * 示例 1：
 * 输入：nums = [3,7,8,1,1,5], space = 2
 * 输出：1
 * 解释：如果我们输入 nums[3] ，我们可以摧毁位于 1,3,5,7,9,... 这些位置的目标。
 * 这种情况下， 我们总共可以摧毁 5 个目标（除了 nums[2]）。
 * 没有办法摧毁多于 5 个目标，所以我们返回 nums[3] 。
 *
 * 示例 2：
 * 输入：nums = [1,3,5,2,4,6], space = 2
 * 输出：1
 * 解释：输入 nums[0] 或者 nums[3] 都会摧毁 3 个目标。
 * 没有办法摧毁多于 3 个目标。
 * 由于 nums[0] 是最小的可以摧毁 3 个目标的整数，所以我们返回 1 。
 *
 * 示例 3：
 * 输入：nums = [6,2,5], space = 100
 * 输出：2
 * 解释：无论我们输入哪个数字，都只能摧毁 1 个目标。输入的最小整数是 nums[1] 。
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= space <=10^9
 */
public class DestroyTargets {

    public int destroyTargets(int[] nums, int space) {
        //统计nums[i]%space对应摧毁的数量
        Map<Integer, Integer> dates = new HashMap<>();
        //统计nums[i]%space对应的最小值
        HashMap<Integer, Integer> marks = new HashMap<>();
        int length = nums.length;
        int maxCount = -1;
        for (int i = 0; i < length; i++) {
            int cur = nums[i];
            int key = cur % space;
            if (dates.containsKey(key)) {
                int count = dates.get(key) + 1;
                if (maxCount < count) {
                    maxCount = count;
                }
                dates.put(key, count);
                int min = marks.get(key);
                if (cur < min) {
                    marks.put(key, cur);
                }
            } else {
                if (maxCount < 1) {
                    maxCount = 1;
                }
                dates.put(key, 1);
                marks.put(key, cur);
            }
        }
        int min = Integer.MAX_VALUE;
        for (int key : dates.keySet()) {
            if (dates.get(key) == maxCount) {
                int curMin = marks.get(key);
                if (curMin < min) {
                    min = curMin;
                }
            }
        }
        return min;
    }
}
