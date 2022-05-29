package com.demo.algorithm.leetcode.interview;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.PriorityQueue;

/**
 * Created by chl on 2022/5/28.
 * description : 面试题17.20. 连续中值
 *
 * 随机产生数字并传递给一个方法。你能否完成这个方法，在每次产生新值时，寻找当前所有值的中间值（中位数）并保存。
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 *
 * 例如，
 * [2,3,4]的中位数是3
 *
 * [2,3]的中位数是(2 + 3)/2 =2.5
 *
 * 设计一个支持以下两种操作的数据结构：
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 *
 * 示例：
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 */
@RequiresApi(api = Build.VERSION_CODES.N)
public class MedianFinder {

    //维护左边较小值
    private PriorityQueue<Integer> left = new PriorityQueue<>((o1, o2) -> o2 - o1);
    //维护右边较大值
    private PriorityQueue<Integer> right = new PriorityQueue<>((o1, o2) -> o1 - o2);

    public MedianFinder() {
        left.clear();
        right.clear();
    }

    public void addNum(int num) {
        if (left.size() == right.size()) {
            if (left.isEmpty() || num <= right.peek()) {
                left.add(num);
            } else {
                left.add(right.poll());
                right.add(num);
            }
        } else {
            //添加到右边
            if (num >= left.peek()) {
                right.add(num);
            } else {
                right.add(left.poll());
                left.add(num);
            }
        }
    }

    public double findMedian() {
        if (left.size() == right.size()) {
            return (left.peek() + right.peek()) / 2.0;
        }
        return left.peek();
    }
}
