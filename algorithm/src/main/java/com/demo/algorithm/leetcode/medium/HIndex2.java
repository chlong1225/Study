package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/10/30
 * @author chenglong
 * description : H指数II
 *
 * 给你一个整数数组citations，其中citations[i]表示研究者的第i篇论文被引用的次数，citations已经按照升序排列。计算并返回该研究者的h指数。
 * h指数的定义：h代表“高引用次数”（high citations），一名科研人员的h指数是指他（她）的（n篇论文中）总共有h篇论文分别被引用了至少h次。
 * 请你设计并实现对数时间复杂度的算法解决此问题。
 *
 * 示例 1：
 * 输入：citations = [0,1,3,5,6]
 * 输出：3
 * 解释：给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 0, 1, 3, 5, 6 次。
 *      由于研究者有 3 篇论文每篇 至少 被引用了 3 次，其余两篇论文每篇被引用 不多于 3 次，所以她的 h 指数是 3 。
 *
 * 示例 2：
 * 输入：citations = [1,2,100]
 * 输出：2
 *
 * 提示：
 * n == citations.length
 * 1 <= n <= 10^5
 * 0 <= citations[i] <= 1000
 * citations 按升序排列
 */
public class HIndex2 {

    public int hIndex(int[] citations) {
        int n = citations.length;
        if (n == 1) {
            return Math.min(1, citations[0]);
        }
        int start = 0;
        int end = n - 1;
        while (start <= end) {
            int middle = (end - start) / 2 + start;
            int count = n - middle;
            int value = citations[middle];
            //此时h值为：Math.min(value,count)
            if (value >= count) {
                end = middle - 1;
            } else {
                start = middle + 1;
            }
        }
        return n - start;
    }
}
