package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2023/7/6
 * @author chenglong
 * description : 拆分成最多数目的正偶数之和
 *
 * 给你一个整数finalSum。请你将它拆分成若干个互不相同的正偶数之和，且拆分出来的正偶数数目最多。
 * 比方说，给你finalSum=12，那么这些拆分是符合要求的（互不相同的正偶数且和为finalSum）：(2 + 10)，(2 + 4 + 6)和(4 + 8)。它们中，(2 + 4 + 6)包含最多数目的整数。
 * 注意finalSum不能拆分成(2 + 2 + 4 + 4)，因为拆分出来的整数必须互不相同。
 * 请你返回一个整数数组，表示将整数拆分成最多数目的正偶数数组。如果没有办法将finalSum进行拆分，请你返回一个空数组。你可以按任意顺序返回这些整数。
 *
 * 示例 1：
 * 输入：finalSum = 12
 * 输出：[2,4,6]
 * 解释：以下是一些符合要求的拆分：(2 + 10)，(2 + 4 + 6) 和 (4 + 8) 。
 * (2 + 4 + 6) 为最多数目的整数，数目为 3 ，所以我们返回 [2,4,6] 。
 * [2,6,4] ，[6,2,4] 等等也都是可行的解。
 *
 * 示例 2：
 * 输入：finalSum = 7
 * 输出：[]
 * 解释：没有办法将 finalSum 进行拆分。
 * 所以返回空数组。
 *
 * 示例 3：
 * 输入：finalSum = 28
 * 输出：[6,8,2,12]
 * 解释：以下是一些符合要求的拆分：(2 + 26)，(6 + 8 + 2 + 12) 和 (4 + 24) 。
 * (6 + 8 + 2 + 12) 有最多数目的整数，数目为 4 ，所以我们返回 [6,8,2,12] 。
 * [10,2,4,12] ，[6,2,4,16] 等等也都是可行的解。
 *
 * 提示：
 * 1 <= finalSum <= 10^10
 */
public class MaximumEvenSplit {

    public List<Long> maximumEvenSplit(long finalSum) {
        if (finalSum % 2 != 0) {
            return new ArrayList<>();
        }
        List<Long> result = new ArrayList<>();
        long start = 2;
        while (finalSum > 0) {
            long last = finalSum - start;
            if (last > start) {
                result.add(start);
                finalSum -= start;
                start += 2;
            } else {
                result.add(finalSum);
                finalSum = 0;
            }
        }
        return result;
    }
}
