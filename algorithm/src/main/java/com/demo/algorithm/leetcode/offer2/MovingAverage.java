package com.demo.algorithm.leetcode.offer2;

/**
 * create on 2022/7/16
 * @author chenglong
 * description : 剑指 OfferII 041. 滑动窗口的平均值
 *
 * 给定一个整数数据流和一个窗口大小，根据该滑动窗口的大小，计算滑动窗口里所有数字的平均值。
 *
 * 实现 MovingAverage 类：
 * MovingAverage(int size)用窗口大小size初始化对象。
 * double next(int val)成员函数next每次调用的时候都会往滑动窗口增加一个整数，请计算并返回数据流中最后size个值的移动平均值，即滑动窗口里所有数字的平均值。
 *
 * 示例：
 * 输入：
 * inputs = ["MovingAverage", "next", "next", "next", "next"]
 * inputs = [[3], [1], [10], [3], [5]]
 * 输出：
 * [null, 1.0, 5.5, 4.66667, 6.0]
 * 解释：
 * MovingAverage movingAverage = new MovingAverage(3);
 * movingAverage.next(1); // 返回 1.0 = 1 / 1
 * movingAverage.next(10); // 返回 5.5 = (1 + 10) / 2
 * movingAverage.next(3); // 返回 4.66667 = (1 + 10 + 3) / 3
 * movingAverage.next(5); // 返回 6.0 = (10 + 3 + 5) / 3
 *
 * 提示：
 * 1 <= size <= 1000
 * -10^5 <= val <= 10^5
 * 最多调用next方法10^4次
 */
public class MovingAverage {

    private final int[] marks;
    private final int length;
    private int count;
    private int total;
    private int index;

    public MovingAverage(int size) {
        length = size;
        marks = new int[size];
        count = 0;
        total = 0;
        index = 0;
    }

    public double next(int val) {
        //index位置的值替换为val
        int diff = val - marks[index];
        total += diff;
        marks[index] = val;
        index++;
        if (count < length) {
            count++;
        }
        if (index == length) {
            index = 0;
        }
        return total * 1.0 / count;
    }
}
