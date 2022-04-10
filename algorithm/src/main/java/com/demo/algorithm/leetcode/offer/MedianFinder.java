package com.demo.algorithm.leetcode.offer;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.PriorityQueue;

/**
 * Created by chl on 2022/4/10.
 * description : 剑指Offer41. 数据流中的中位数
 *
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
 * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 *
 * 例如，
 * [2,3,4]的中位数是 3
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 *
 * 设计一个支持以下两种操作的数据结构：
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 *
 * 示例 1：
 * 输入：
 * ["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
 * [[],[1],[2],[],[3],[]]
 * 输出：[null,null,null,1.50000,null,2.00000]
 *
 * 示例 2：
 * 输入：
 * ["MedianFinder","addNum","findMedian","addNum","findMedian"]
 * [[],[2],[],[3],[]]
 * 输出：[null,null,2.00000,null,2.50000]
 *
 * 限制：
 *
 * 最多会对addNum、findMedian 进行50000次调用。
 */
//使用大小堆
@RequiresApi(api = Build.VERSION_CODES.N)
public class MedianFinder {

    private PriorityQueue<Integer> left = new PriorityQueue<>((o1, o2) -> o2 - o1);
    private PriorityQueue<Integer> right = new PriorityQueue<>((o1, o2) -> o1 - o2);

    public MedianFinder() {
        left.clear();
        right.clear();
    }

    public void addNum(int num) {
        int s1 = left.size();
        int s2 = right.size();
        if (s1 == s2) {
            if (right.isEmpty() || num <= right.peek()) {
                left.add(num);
            } else {
                left.add(right.poll());
                right.add(num);
            }
        } else {
            if (num >= left.peek()) {
                right.add(num);
            } else {
                right.add(left.poll());
                left.add(num);
            }
        }
    }

    public double findMedian() {
        int s1 = left.size();
        int s2 = right.size();
        if (s1 == 0) {
            return 0;
        }
        if (s1 == s2) {
            return (left.peek() + right.peek()) / 2.0d;
        } else {
            return left.peek();
        }
    }
}
