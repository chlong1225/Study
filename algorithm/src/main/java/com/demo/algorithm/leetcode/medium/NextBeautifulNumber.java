package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * create by chenglong on 2023/12/10
 * description : 下一个更大的数值平衡数
 *
 * 如果整数 x满足：对于每个数位d，这个数位恰好在x中出现d次。那么整数x就是一个数值平衡数。
 * 给你一个整数n，请你返回严格大于n的最小数值平衡数。
 *
 * 示例 1：
 * 输入：n = 1
 * 输出：22
 * 解释：
 * 22 是一个数值平衡数，因为：
 * - 数字 2 出现 2 次
 * 这也是严格大于 1 的最小数值平衡数。
 *
 * 示例 2：
 * 输入：n = 1000
 * 输出：1333
 * 解释：
 * 1333 是一个数值平衡数，因为：
 * - 数字 1 出现 1 次。
 * - 数字 3 出现 3 次。
 * 这也是严格大于 1000 的最小数值平衡数。
 * 注意，1022 不能作为本输入的答案，因为数字0的出现次数超过了0。
 *
 * 示例 3：
 * 输入：n = 3000
 * 输出：3133
 * 解释：
 * 3133 是一个数值平衡数，因为：
 * - 数字 1 出现 1 次。
 * - 数字 3 出现 3 次。
 * 这也是严格大于 3000 的最小数值平衡数。
 *
 * 提示：
 * 0 <= n <= 10^6
 */
public class NextBeautifulNumber {

    private static final List<Integer> marks = new ArrayList<>();

    static {

        marks.clear();
        marks.add(1);
        int index = 2;
        while (true) {
            if (isBalance(index)) {
                marks.add(index);
                if (index > 1000000) {
                    break;
                }
            }
            index++;
        }
    }

    private static boolean isBalance(int num) {
        int[] counts = new int[10];
        while (num > 0) {
            int tem = num % 10;
            if (tem == 0) {
                return false;
            }
            counts[tem]++;
            num /= 10;
        }
        for (int i = 1; i < 10; i++) {
            if (counts[i] > 0 && counts[i] != i) {
                return false;
            }
        }
        return true;
    }

    public int nextBeautifulNumber(int n) {
        int start = 0;
        int end = marks.size() - 1;
        while (start < end) {
            int middle = (end - start) / 2 + start;
            if (marks.get(middle) == n) {
                return marks.get(middle + 1);
            } else if (marks.get(middle) < n) {
                start = middle + 1;
            } else {
                end = middle;
            }
        }
        return marks.get(start);
    }
}
