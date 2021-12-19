package com.demo.algorithm.leetcode.entity;

/**
 * Created by chl on 2021/12/17.
 * description : 二叉树多加了一个右节点
 */
public class RightNode {

    public int val;
    public RightNode left;
    public RightNode right;
    public RightNode next;

    public RightNode() {}

    public RightNode(int _val) {
        val = _val;
    }

    public RightNode(int _val, RightNode _left, RightNode _right, RightNode _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}
