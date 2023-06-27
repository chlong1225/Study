package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/6/26
 * @author chenglong
 * description : 找出中枢整数
 *
 * 给你一个正整数n，找出满足下述条件的中枢整数x：
 * 1和x之间的所有元素之和等于x和n之间所有元素之和。
 * 返回中枢整数x。如果不存在中枢整数，则返回-1。题目保证对于给定的输入，至多存在一个中枢整数。
 *
 * 示例 1：
 * 输入：n = 8
 * 输出：6
 * 解释：6 是中枢整数，因为 1 + 2 + 3 + 4 + 5 + 6 = 6 + 7 + 8 = 21 。
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：1
 * 解释：1 是中枢整数，因为 1 = 1 。
 * 示例 3：
 *
 * 输入：n = 4
 * 输出：-1
 * 解释：可以证明不存在满足题目要求的整数。
 *
 * 提示：
 * 1 <= n <= 1000
 */
public class PivotInteger {

    private final static int[] marks = new int[1001];

    static {
        marks[1] = 1;
        for (int i = 2; i < 1001; i++) {
            marks[i] = marks[i - 1] + i;
        }
    }

    public int pivotInteger(int n) {
        if (n == 1) {
            return 1;
        }
        //1~n的总和
        int total = marks[n];
        int start = 1;
        int end = n;
        while (start < end) {
            int middle = (end + start) / 2;
            int leftSum = marks[middle - 1];
            int rightSum = total - leftSum - middle;
            if (leftSum == rightSum) {
                return middle;
            } else if (leftSum < rightSum) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }
        return -1;
    }
}
