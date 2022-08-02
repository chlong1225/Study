package com.demo.algorithm.leetcode.medium;

/**
 * create on 2022/8/2
 * @author chenglong
 * description : 设计循环队列
 *
 * 设计你的循环队列实现。循环队列是一种线性数据结构，其操作表现基于FIFO（先进先出）原则并且队尾被连接在队首之后以形成一个循环。它也被称为“环形缓冲器”。
 * 循环队列的一个好处是我们可以利用这个队列之前用过的空间。在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，即使在队列前面仍有空间。但是使用循环队列，我们能使用这些空间去存储新的值。
 *
 * 你的实现应该支持如下操作：
 * MyCircularQueue(k): 构造器，设置队列长度为 k 。
 * Front: 从队首获取元素。如果队列为空，返回 -1 。
 * Rear: 获取队尾元素。如果队列为空，返回 -1 。
 * enQueue(value): 向循环队列插入一个元素。如果成功插入则返回真。
 * deQueue(): 从循环队列中删除一个元素。如果成功删除则返回真。
 * isEmpty(): 检查循环队列是否为空。
 * isFull(): 检查循环队列是否已满。
 *
 * 示例：
 * MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
 * circularQueue.enQueue(1); // 返回 true
 * circularQueue.enQueue(2); // 返回 true
 * circularQueue.enQueue(3); // 返回 true
 * circularQueue.enQueue(4); // 返回 false，队列已满
 * circularQueue.Rear(); // 返回 3
 * circularQueue.isFull(); // 返回 true
 * circularQueue.deQueue(); // 返回 true
 * circularQueue.enQueue(4); // 返回 true
 * circularQueue.Rear(); // 返回 4
 *
 * 提示：
 * 所有的值都在0至1000的范围内；
 * 操作数将在1至1000的范围内；
 * 请不要使用内置的队列库。
 */
public class MyCircularQueue {

    private final int size;
    private int count;
    private final int[] dates;
    private int endIndex;

    public MyCircularQueue(int k) {
        size = k;
        count = 0;
        dates = new int[size];
        endIndex = -1;
    }

    public boolean enQueue(int value) {
        if (count >= size) {
            return false;
        }
        endIndex++;
        dates[endIndex % size] = value;
        count++;
        return true;
    }

    public boolean deQueue() {
        if (count == 0) {
            return false;
        }
        count--;
        return true;
    }

    public int Front() {
        if (count == 0) {
            return -1;
        }
        //加上size防止越界
        int startIndex = endIndex - count + 1 + size;
        return dates[startIndex % size];
    }

    public int Rear() {
        if (count == 0) {
            return -1;
        }
        return dates[endIndex % size];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == size;
    }
}
