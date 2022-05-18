package com.demo.algorithm.leetcode.hard;

/**
 * Created by chl on 2022/5/18.
 * description : 乘法表中第k小的数
 *
 * 几乎每一个人都用乘法表。但是你能在乘法表中快速找到第k小的数字吗？
 * 给定高度m、宽度n的一张m * n的乘法表，以及正整数k，你需要返回表中第k小的数字。
 *
 * 例1：
 * 输入: m = 3, n = 3, k = 5
 * 输出: 3
 * 解释:
 * 乘法表:
 * 1	2	3
 * 2	4	6
 * 3	6	9
 * 第5小的数字是 3 (1, 2, 2, 3, 3).
 *
 * 例 2：
 * 输入: m = 2, n = 3, k = 6
 * 输出: 6
 * 解释:
 * 乘法表:
 * 1	2	3
 * 2	4	6
 * 第6小的数字是 6 (1, 2, 2, 3, 4, 6).
 * 注意：
 *
 * m和n的范围在[1, 30000]之间。
 * k的范围在[1, m * n]之间。
 */
public class FindKthNumber {

    public int findKthNumber(int m, int n, int k) {
        int start = 1;
        int end = m * n;
        while (start < end) {
            int middle = (end - start) / 2 + start;
            //对应<=middle的行数
            int index = middle / n;
            int count = (index * n);
            for (int i = index + 1; i <= m; i++) {
                //对应的行<=middle的数量
                if (i > middle) {
                    break;
                }
                count += (middle / i);
            }
            if (count < k) {
                start = middle + 1;
            } else {
                end = middle;
            }
        }
        return start;
    }
}
