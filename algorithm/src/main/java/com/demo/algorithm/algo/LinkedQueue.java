package com.demo.algorithm.algo;

/**
 * Created by w on 2021/5/2.
 *
 * 使用链表实现队列
 */
public class LinkedQueue implements Queue{

    Node head = null;
    Node tail = null;
    int count = 0;

    @Override
    public void enqueue(String data) {
        Node node = new Node(data, null);
        if (tail == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = tail.next;
        }
        count++;
    }

    @Override
    public String dequeue() {
        if (head == null) {
            count = 0;
            return "";
        } else {
            String data = head.data;
            count--;
            head = head.next;
            if (head == null) {
                tail = null;
            }
            return data;
        }
    }

    @Override
    public int size() {
        return count;
    }
}
