package com.demo.algorithm.leetcode.contest.week303;

import java.util.HashSet;
import java.util.Set;

/**
 * create on 2022/8/2
 * @author chenglong
 * description : 优质数对的数目
 *
 * 给你一个下标从0开始的正整数数组nums和一个正整数k。
 * 如果满足下述条件，则数对 (num1, num2) 是优质数对 ：
 * num1和num2都在数组nums中存在。
 * num1 OR num2 和 num1 AND num2 的二进制表示中值为1的位数之和大于等于k ，其中OR是按位或操作，而AND是按位与操作。
 * 返回不同优质数对的数目。
 * 如果a != c 或者 b != d ，则认为 (a, b) 和 (c, d) 是不同的两个数对。例如，(1, 2) 和 (2, 1) 不同。
 * 注意：如果num1在数组中至少出现一次，则满足num1 == num2的数对 (num1, num2) 也可以是优质数对。
 *
 * 示例 1：
 * 输入：nums = [1,2,3,1], k = 3
 * 输出：5
 * 解释：有如下几个优质数对：
 * - (3, 3)：(3 AND 3) 和 (3 OR 3) 的二进制表示都等于 (11) 。值为 1 的位数和等于 2 + 2 = 4 ，大于等于 k = 3 。
 * - (2, 3) 和 (3, 2)： (2 AND 3) 的二进制表示等于 (10) ，(2 OR 3) 的二进制表示等于 (11) 。值为 1 的位数和等于 1 + 2 = 3 。
 * - (1, 3) 和 (3, 1)： (1 AND 3) 的二进制表示等于 (01) ，(1 OR 3) 的二进制表示等于 (11) 。值为 1 的位数和等于 1 + 2 = 3 。
 * 所以优质数对的数目是 5 。
 *
 * 示例 2：
 * 输入：nums = [5,1,1], k = 10
 * 输出：0
 * 解释：该数组中不存在优质数对。
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= k <= 60
 */
public class CountExcellentPairs {

    private static final int MAX_COUNT = 32;

    public long countExcellentPairs(int[] nums, int k) {
        //统计出现的数字，便于去重
        Set<Integer> marks = new HashSet<>();
        //记录二进制1个数对应的数字数量
        int[] counts = new int[MAX_COUNT];
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (marks.add(nums[i])) {
                counts[getNum(nums[i])]++;
            }
        }
        int[] sums = new int[MAX_COUNT + 1];
        for (int i = MAX_COUNT - 1; i >= 0; i--) {
            sums[i] = counts[i] + sums[i + 1];
        }
        long sum = 0;
        long base = 1;
        for (int i = MAX_COUNT - 1; i >= 0; i--) {
            if (counts[i] == 0) {
                continue;
            }
            int min = k - i;
            if (min > i) {
                break;
            }
            if (min < 0) {
                min = 0;
            }
            sum += (base * counts[i] * (sums[min] - sums[i]) * 2);
            sum += (base * counts[i] * counts[i]);
        }
        return sum;
    }

    private int getNum(int num) {
        int count = 0;
        while (num > 0) {
            if (num % 2 == 1) {
                count++;
            }
            num /= 2;
        }
        return count;
    }
}
