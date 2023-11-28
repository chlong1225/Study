package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/11/28
 * @author chenglong
 * description : 设计前中后队列
 *
 * 请你设计一个队列，支持在前，中，后三个位置的push和pop操作。
 * 请你完成 FrontMiddleBack 类：
 * FrontMiddleBack() 初始化队列。
 * void pushFront(int val) 将 val 添加到队列的最前面 。
 * void pushMiddle(int val) 将 val 添加到队列的正中间 。
 * void pushBack(int val) 将 val 添加到队里的最后面 。
 * int popFront() 将 最前面 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
 * int popMiddle() 将 正中间 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
 * int popBack() 将 最后面 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
 * 请注意当有两个中间位置的时候，选择靠前面的位置进行操作。比方说：
 * 将 6 添加到 [1, 2, 3, 4, 5] 的中间位置，结果数组为 [1, 2, 6, 3, 4, 5] 。
 * 从 [1, 2, 3, 4, 5, 6] 的中间位置弹出元素，返回 3 ，数组变为 [1, 2, 4, 5, 6] 。
 *
 * 示例 1：
 * 输入：
 * ["FrontMiddleBackQueue", "pushFront", "pushBack", "pushMiddle", "pushMiddle", "popFront", "popMiddle", "popMiddle", "popBack", "popFront"]
 * [[], [1], [2], [3], [4], [], [], [], [], []]
 * 输出：
 * [null, null, null, null, null, 1, 3, 4, 2, -1]
 * 解释：
 * FrontMiddleBackQueue q = new FrontMiddleBackQueue();
 * q.pushFront(1);   // [1]
 * q.pushBack(2);    // [1, 2]
 * q.pushMiddle(3);  // [1, 3, 2]
 * q.pushMiddle(4);  // [1, 4, 3, 2]
 * q.popFront();     // 返回 1 -> [4, 3, 2]
 * q.popMiddle();    // 返回 3 -> [4, 2]
 * q.popMiddle();    // 返回 4 -> [2]
 * q.popBack();      // 返回 2 -> []
 * q.popFront();     // 返回 -1 -> [] （队列为空）
 *
 * 提示：
 * 1 <= val <= 10^9
 * 最多调用 1000 次 pushFront， pushMiddle， pushBack， popFront， popMiddle 和 popBack 。
 */
public class FrontMiddleBackQueue {

    private int size;
    private LinkedNode head;
    private LinkedNode middle;
    private LinkedNode last;

    public FrontMiddleBackQueue() {
        size = 0;
        head = null;
        middle = null;
        last = null;
    }

    public void pushFront(int val) {
        LinkedNode node = new LinkedNode(val);
        if (size == 0) {
            head = node;
        } else if (size == 1) {
            last = head;
            head = node;
        } else if (size == 2) {
            middle = head;
            head = node;
            head.next = middle;
            middle.pre = head;
            middle.next = last;
            last.pre = middle;
        } else {
            head.pre = node;
            node.next = head;
            head = node;
            if (size % 2 == 1) {
                middle = middle.pre;
            }
        }
        size++;
    }

    public void pushMiddle(int val) {
        LinkedNode node = new LinkedNode(val);
        if (size == 0) {
            head = node;
        } else if (size == 1) {
            last = head;
            head = node;
        } else if (size == 2) {
            middle = node;
            head.next = middle;
            middle.pre = head;
            middle.next = last;
            last.pre = middle;
        } else {
            if (size % 2 == 0) {
                //此时节点数为偶数。middle为两个中间的左边
                node.pre = middle;
                node.next = middle.next;
                middle.next.pre = node;
                middle.next = node;
                middle = node;
            } else {
                //此时节点数为奇数，middle为正中间。节点添加在middle左边，并且middle重新赋值为node
                node.pre = middle.pre;
                middle.pre.next = node;
                middle.pre = node;
                node.next = middle;
                middle = node;
            }
        }
        size++;
    }

    public void pushBack(int val) {
        LinkedNode node = new LinkedNode(val);
        if (size == 0) {
            head = node;
        } else if (size == 1) {
            last = node;
        } else if (size == 2) {
            middle = last;
            last = node;
            head.next = middle;
            middle.pre = head;
            middle.next = last;
            last.next = middle;
        } else {
            last.next = node;
            node.pre = last;
            last = node;
            if (size % 2 == 0) {
                middle = middle.next;
            }
        }
        size++;
    }

    public int popFront() {
        if (size == 0) {
            return -1;
        }
        int value = head.value;
        if (size == 1) {
            head = null;
        } else if (size == 2) {
            //此时head与las赋值
            head = last;
            last = null;
        } else if (size == 3) {
            //此时head，last，middle赋值
            head = middle;
            middle = null;
        } else {
            head = head.next;
            if (size % 2 == 0) {
                //此时节点数为偶数。比如：4，6，8。中间节点偏左。删除前节点后中间节点居中。
                middle = middle.next;
            }
        }
        size--;
        return value;
    }

    public int popMiddle() {
        if (size == 0) {
            return -1;
        }
        int value = -1;
        if (size == 1) {
            value = head.value;
            head = null;
        } else if (size == 2) {
            value = head.value;
            head = last;
            last = null;
        } else if (size == 3) {
            value = middle.value;
            middle = null;
        } else {
            value = middle.value;
            if (size % 2 == 0) {
                middle.pre.next = middle.next;
                middle.next.pre = middle.pre;
                middle = middle.next;
            } else {
                middle.pre.next = middle.next;
                middle.next.pre = middle.pre;
                middle = middle.pre;
            }
        }
        size--;
        return value;
    }

    public int popBack() {
        if (size == 0) {
            return -1;
        }
        int value = -1;
        if (size == 1) {
            value = head.value;
            head = null;
        } else if (size == 2) {
            value = last.value;
            last = null;
        } else if (size == 3) {
            value = last.value;
            last = middle;
            middle = null;
        } else {
            value = last.value;
            last = last.pre;
            if (size % 2 == 1) {
                middle = middle.pre;
            }
        }
        size--;
        return value;
    }

    static class LinkedNode{

        public int value;
        public LinkedNode pre;
        public LinkedNode next;

        public LinkedNode(int value) {
            this.value = value;
        }
    }
}
