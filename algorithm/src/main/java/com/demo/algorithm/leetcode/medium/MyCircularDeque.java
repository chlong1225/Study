package com.demo.algorithm.leetcode.medium;

/**
 * create on 2022/8/15
 * @author chenglong
 * description : 设计循环双端队列
 *
 * 设计实现双端队列。
 * 实现 MyCircularDeque 类:
 * MyCircularDeque(int k)：构造函数,双端队列最大为 k 。
 * boolean insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true，否则返回false 。
 * boolean insertLast()：将一个元素添加到双端队列尾部。如果操作成功返回 true，否则返回 false 。
 * boolean deleteFront()：从双端队列头部删除一个元素。 如果操作成功返回 true，否则返回 false 。
 * boolean deleteLast()：从双端队列尾部删除一个元素。如果操作成功返回 true，否则返回 false 。
 * int getFront())：从双端队列头部获得一个元素。如果双端队列为空，返回 -1。
 * int getRear()：获得双端队列的最后一个元素。如果双端队列为空，返回 -1 。
 * boolean isEmpty()：若双端队列为空，则返回true，否则返回 false。
 * boolean isFull()：若双端队列满了，则返回true，否则返回 false。
 *
 * 示例 1：
 * 输入
 * ["MyCircularDeque", "insertLast", "insertLast", "insertFront", "insertFront", "getRear", "isFull", "deleteLast", "insertFront", "getFront"]
 * [[3], [1], [2], [3], [4], [], [], [], [4], []]
 * 输出
 * [null, true, true, true, false, 2, true, true, true, 4]
 * 解释
 * MyCircularDeque circularDeque = new MycircularDeque(3); // 设置容量大小为3
 * circularDeque.insertLast(1);			        // 返回 true
 * circularDeque.insertLast(2);			        // 返回 true
 * circularDeque.insertFront(3);			        // 返回 true
 * circularDeque.insertFront(4);			        // 已经满了，返回 false
 * circularDeque.getRear();  				// 返回 2
 * circularDeque.isFull();				        // 返回 true
 * circularDeque.deleteLast();			        // 返回 true
 * circularDeque.insertFront(4);			        // 返回 true
 * circularDeque.getFront();				// 返回 4
 *
 * 提示：
 * 1 <= k <= 1000
 * 0 <= value <= 1000
 * insertFront,insertLast,deleteFront,deleteLast,getFront,getRear,isEmpty,isFull调用次数不大于2000次
 */
public class MyCircularDeque {

    private int count;
    private int size;
    private final int[] dates;
    //起始值的位置
    private int start = 0;

    public MyCircularDeque(int k) {
        size = k;
        dates = new int[size];
        count = 0;
    }

    public boolean insertFront(int value) {
        if (count >= size) {
            return false;
        }
        if (count > 0) {
            start = (start + size - 1) % size;
        }
        dates[start] = value;
        count++;
        return true;
    }

    public boolean insertLast(int value) {
        if (count >= size) {
            return false;
        }
        int index = (start + count) % size;
        dates[index] = value;
        count++;
        return true;
    }

    public boolean deleteFront() {
        if (count == 0) {
            return false;
        }
        start = (start + 1) % size;
        count--;
        return true;
    }

    public boolean deleteLast() {
        if (count == 0) {
            return false;
        }
        count--;
        return true;
    }

    public int getFront() {
        if (count == 0) {
            return -1;
        }
        return dates[start];
    }

    public int getRear() {
        if (count == 0) {
            return -1;
        }
        int index = (start + count - 1) % size;
        return dates[index];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == size;
    }
}
