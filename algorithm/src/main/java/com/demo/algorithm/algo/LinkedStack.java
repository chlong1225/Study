package com.demo.algorithm.algo;

/**
 * Created by w on 2021/5/1.
 *
 * 使用链表实现栈
 *
 */
public class LinkedStack implements Stack{

    Node head = null;
    int count = 0;

    @Override
    public void push(String data) {
        Node tem = new Node(data, null);
        if (head == null) {
            head = tem;
            count = 1;
        } else {
            //数据放在链表头部
            tem.next = head;
            head = tem;
            count++;
        }
    }

    @Override
    public String pop() {
        if (head == null) {
            count = 0;
            return "";
        } else {
            String result = head.data;
            head = head.next;
            count--;
            return result;
        }
    }

    @Override
    public int size() {
        return count;
    }
}
