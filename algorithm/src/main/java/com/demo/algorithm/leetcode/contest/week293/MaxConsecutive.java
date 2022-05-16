package com.demo.algorithm.leetcode.contest.week293;

import java.util.Arrays;

/**
 * create on 2022/5/16
 * @author chenglong
 * description : 不含特殊楼层的最大连续楼层数
 *
 * Alice管理着一家公司，并租用大楼的部分楼层作为办公空间。Alice决定将一些楼层作为特殊楼层仅用于放松。
 * 给你两个整数bottom和top，表示Alice租用了从bottom到top（含bottom和top在内）的所有楼层。另给你一个整数数组special，其中special[i]表示Alice指定用于放松的特殊楼层。
 * 返回不含特殊楼层的最大连续楼层数。
 *
 * 示例 1：
 * 输入：bottom = 2, top = 9, special = [4,6]
 * 输出：3
 * 解释：下面列出的是不含特殊楼层的连续楼层范围：
 * - (2, 3) ，楼层数为 2 。
 * - (5, 5) ，楼层数为 1 。
 * - (7, 9) ，楼层数为 3 。
 * 因此，返回最大连续楼层数 3 。
 *
 * 示例 2：
 * 输入：bottom = 6, top = 8, special = [7,6,8]
 * 输出：0
 * 解释：每层楼都被规划为特殊楼层，所以返回 0 。
 *
 * 提示
 * 1 <= special.length <= 10^5
 * 1 <= bottom <= special[i] <= top <= 10^9
 * special 中的所有值 互不相同
 */
public class MaxConsecutive {

    public int maxConsecutive(int bottom, int top, int[] special) {
        Arrays.sort(special);
        int max = 0;
        int length = special.length;
        int start = bottom;
        for (int i = 0; i < length; i++) {
            int end = special[i];
            int count = end - start;
            if (count > max) {
                max = count;
            }
            start = end + 1;
        }
        int count = top - start + 1;
        if (count > max) {
            max = count;
        }
        return max;
    }
}
