package com.demo.algorithm.algo;


import android.util.Log;

/**
 * Created by w on 2021/4/24.
 *
 * 链表
 *
 */
public class LinkedList {

    private static final String TAG = "LinkedList";

    private SingleNode head = null;

    /**
     * 添加节点
     * @param data :
     */
    public void add(String data) {
        SingleNode node = new SingleNode(data, null);
        if (head == null) {
            head = node;
        } else {
            SingleNode p = head;
            while (p.next != null) {
                p = p.next;
            }
            p.next = node;
        }
    }

    /**
     * 删除节点 (默认节点data不为null)
     * @param data :
     */
    public void deleteByValue(String data) {
        if (head == null) {
            return;
        }
        if (head.data.equals(data)) {
            head = head.next;
            return;
        }
        SingleNode pre = head;
        SingleNode current = head.next;
        while (current != null && !current.data.equals(data)) {
            pre = current;
            current = current.next;
        }
        if (current == null) {
            return;
        }
        pre.next = current.next;

    }

    /**
     *  使用单链表判断字符串是否是回文字符串
     */

    public boolean isReverseStr(SingleNode head) {
        if (head == null) {
            return false;
        }
        SingleNode p = head;
        SingleNode q = head;


        return false;
    }


    /**
     * 实现LRU缓存算法
     */
    public void addByLru(SingleNode head) {

    }

    /**
     * @param head :
     * @return : 单链表反转
     */
    public SingleNode reverse(SingleNode head) {
        return head;
    }

    /**
     * @param head :
     * @return : 链表是否为环形
     */
    public boolean isRing(SingleNode head) {
        return false;
    }

    /**
     * @param head1 :
     * @param head2 :
     * @return : 两个有序的链表合并
     */
    public SingleNode mergeNode(SingleNode head1, SingleNode head2) {
        return null;
    }

    /**
     * 删除链表倒数第n个结点
     * @param head
     * @param n : 倒数节点的序号
     */
    public void deleteNode(SingleNode head, int n) {

    }

    /**
     * @param head
     * @return : 求链表的中间结点
     */
    public SingleNode quaryCenter(SingleNode head) {
        return null;
    }



    /**
     * 打印单链表的数据
     * @param head :
     */
    public void printSingleAll(SingleNode head) {
        if (head == null) {
            Log.e(TAG, "printSingleAll: 链表为空");
            return;
        }
        int count = 0;
        while (head != null) {
            Log.e(TAG, "printSingleAll: data = " + head.data);
            head = head.next;
            count++;
        }
        Log.e(TAG, "printSingleAll: count = " + count);

    }

    /**
     * 打印双向链表的数据
     * @param head :
     */
    public void printDoubleAll(DoubleNode head) {
        if (head == null) {
            Log.e(TAG, "printDoubleAll: 链表为空");
            return;
        }
        int count = 0;
        while (head != null) {
            Log.e(TAG, "printDoubleAll: data = " + head.data);
            count++;
            head = head.next;
        }
        Log.e(TAG, "printDoubleAll: count = " + count);
    }


    /**
     * 定义单链表的数据结构
     */
    public static class SingleNode{

        String data;
        SingleNode next;

        public SingleNode(String data, SingleNode next) {
            this.data = data;
            this.next = next;
        }
    }

    /**
     * 定义双向链表的数据结构
     */
    public static class DoubleNode{

        DoubleNode pre;
        String data;
        DoubleNode next;
    }

}


