package com.demo.algorithm.leetcode.entity;

/**
 * create by chenglong on 9/13/21
 * description :
 */
public class MinListNode {

    public int value;
    public int min;
    public MinListNode next;

    public MinListNode(int value) {
        this.value = value;
        next = null;
    }
}
