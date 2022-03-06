package com.demo.algorithm.leetcode.entity;

/**
 * Created by chl on 2022/1/10.
 * description : 双向链表
 */
public class DoubleListNode {

    public DoubleListNode pre;
    public DoubleListNode next;
    public int key;
    public int value;

    public DoubleListNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
