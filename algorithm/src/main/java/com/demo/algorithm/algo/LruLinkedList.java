package com.demo.algorithm.algo;

import android.util.Log;

/**
 * Created by w on 2021/4/26.
 * <p>
 * 基于单链表的Lru算法
 */
public class LruLinkedList<T> {

    private static final String TAG = "LruLinkedList";

    //最大容量为20
    private static final int DEFAULT_CAPACITY = 20;

    //链表容量
    private int capacity = DEFAULT_CAPACITY;
    //链表数据长度
    private int length;

    //链表头
    private SNode<T> head;

    public LruLinkedList() {
        this(DEFAULT_CAPACITY);
    }

    public LruLinkedList(int capacity) {
        //至少20条数据
        if (capacity < DEFAULT_CAPACITY) {
            capacity = DEFAULT_CAPACITY;
        }
        this.capacity = capacity;
        head = new SNode();
        length = 0;
    }

    public void add(T data) {
        SNode<T> sNode = new SNode<>(data, null);
        SNode p = head;
        if (p == null) {
            head = sNode;
            length = 1;
            return;
        }
        if (p.next == null) {
            if (p.data == data) {
                length = 1;
                return;
            }
            head = sNode;
            head.next = p;
            length = 2;
        }
        SNode pre = head;
        while (p != null) {
            if (p.data == data) {
                //删除当前数据
                pre.next = p.next;
                SNode tem = head;
                head = sNode;
                head.next = tem;
                return;
            } else {
                pre = p;
                p = p.next;
            }
        }
        if (length == capacity) {
            pre.next = null;
        }
        SNode tem = head;
        head = sNode;
        head.next = tem;
    }

    public void printAll() {
        if (head == null) {
            Log.e(TAG, "printAll: 链表为空");
            return;
        }
        SNode p = head;
        while (p != null) {
            Log.e(TAG, "printAll: data = " + p.data.toString());
            p = p.next;
        }
    }


    public static class SNode<T>{

        private T data;
        private SNode next;

        public SNode() {
        }

        public SNode(T data, SNode next) {
            this.data = data;
            this.next = next;
        }
    }



}
