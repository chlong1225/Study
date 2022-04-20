package com.demo.algorithm.leetcode.offer;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by chl on 2022/4/20.
 * description : 剑指Offer59-II. 队列的最大值
 *
 * 请定义一个队列并实现函数max_value得到队列里的最大值，要求函数max_value、push_back和pop_front的均摊时间复杂度都是O(1)。
 * 若队列为空，pop_front和max_value需要返回 -1
 *
 * 示例 1：
 * 输入:
 * ["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
 * [[],[1],[2],[],[],[]]
 * 输出:[null,null,null,2,1,2]
 *
 * 示例 2：
 * 输入:
 * ["MaxQueue","pop_front","max_value"]
 * [[],[],[]]
 * 输出:[null,-1,-1]
 *
 * 限制：
 * 1 <= push_back,pop_front,max_value的总操作数<= 10000
 * 1 <= value <= 10^5
 */
public class MaxQueue {

    //双向队列缓存数据源
    private Deque<Integer> dates = new LinkedList<>();
    //缓存单调递减最大值
    private Deque<Integer> marks = new LinkedList<>();

    public MaxQueue() {
        dates.clear();
        marks.clear();
    }

    public int max_value() {
        if (marks.isEmpty()) {
            return -1;
        }
        return marks.peek();
    }

    public void push_back(int value) {
        dates.offer(value);
        while (!marks.isEmpty() && value > marks.peekLast()) {
            marks.pollLast();
        }
        marks.offerLast(value);
    }

    public int pop_front() {
        if (dates.isEmpty()) {
            return -1;
        }
        if (dates.peekFirst().equals(marks.peekFirst())) {
            marks.pollFirst();
        }
        return dates.pollFirst();
    }
}
