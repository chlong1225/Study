package com.demo.algorithm.algo;

import android.util.Log;

/**
 * Created by w on 2021/4/26.
 * <p>
 * 基于单链表的Lru算法
 */
public class LruLinkedList {

    private static final String TAG = "LruLinkedList";

    //最大容量为20
    private static final int DEFAULT_CAPACITY = 10;

    //链表容量
    private int capacity = DEFAULT_CAPACITY;
    //链表数据长度
    private int length;

    //链表头
    private SNode head;

    public LruLinkedList() {
        this(DEFAULT_CAPACITY);
    }

    public LruLinkedList(int capacity) {
        //至少20条数据
        if (capacity < DEFAULT_CAPACITY) {
            capacity = DEFAULT_CAPACITY;
        }
        this.capacity = capacity;
        head = null;
        length = 0;
    }

    public void add(String data) {
        SNode sNode = new SNode(data, null);
        SNode p = head;
        //1,删除相同的节点数据
        SNode pre = head;
        while (p != null) {
            if (p.data.equals(data)) {
                if (pre == p) {
                    //链表头重复了不执行操作
                    return;
                }
                pre.next = p.next;
                length--;
                break;
            } else {
                pre = p;
                p = p.next;
            }
        }

        //2,判断是否超出长度,删除尾结点
        if (length >= capacity) {
            SNode p1 = null;
            SNode q1 = head;
            while (q1.next != null) {
                p1 = q1;
                q1 = q1.next;
            }
            p1.next = null;
            length--;
        }

        //3,添加节点
        SNode tem = head;
        head = sNode;
        head.next = tem;
        length++;
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


    public static class SNode{

        private String data;
        private SNode next;

        public SNode() {
        }

        public SNode(String data, SNode next) {
            this.data = data;
            this.next = next;
        }
    }



}
