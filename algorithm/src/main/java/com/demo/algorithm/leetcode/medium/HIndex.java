package com.demo.algorithm.leetcode.medium;

/**
 * create by chenglong on 2023/10/29
 * description : H指数
 *
 * 给你一个整数数组citations，其中citations[i]表示研究者的第i篇论文被引用的次数。计算并返回该研究者的h指数。
 * 根据维基百科上h指数的定义：h 代表“高引用次数” ，一名科研人员的h指数是指他（她）至少发表了h篇论文，并且每篇论文至少被引用h次。
 * 如果h有多种可能的值，h指数是其中最大的那个。
 *
 * 示例 1：
 * 输入：citations = [3,0,6,1,5]
 * 输出：3
 * 解释：给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 3, 0, 6, 1, 5 次。
 *      由于研究者有 3 篇论文每篇 至少 被引用了 3 次，其余两篇论文每篇被引用 不多于 3 次，所以她的 h 指数是 3。
 *
 * 示例 2：
 * 输入：citations = [1,3,1]
 * 输出：1
 *
 * 提示：
 * n == citations.length
 * 1 <= n <= 5000
 * 0 <= citations[i] <= 1000
 */
public class HIndex {

    public int hIndex(int[] citations) {
        int n = citations.length;
        //1，获取最大引用次数，便于定义桶的大小
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (max < citations[i]) {
                max = citations[i];
            }
        }
        int[] counts = new int[max + 1];
        for (int i = 0; i < n; i++) {
            counts[citations[i]]++;
        }
        int h = 0;
        int total = n;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] == 0) {
                continue;
            }
            //当前引用次数i，当前>=i次引用的文章数为：total
            int curH = Math.min(i, total);
            if (curH > h) {
                h = curH;
            }
            total -= counts[i];
        }
        return h;
    }
}
